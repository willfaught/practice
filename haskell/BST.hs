-- Binary search tree

module BST (
    BST(..),
    add,
    ceiling,
    empty,
    floor,
    get,
    inorder,
    maximum,
    member,
    minimum,
    postorder,
    preorder,
    select,
    size,
    rank,
    remove,
    removeMaximum,
    removeMinimum) where

import Prelude hiding (ceiling, floor, maximum, minimum)

data BST k v = Empty | Node k v (BST k v) (BST k v) deriving (Show)

instance (Ord k, Ord v) => Eq (BST k v) where
    Empty == Empty = True
    Empty == _ = False
    _ == Empty = False
    x == y = inorder x == inorder y where
        inorder Empty = []
        inorder (Node k v l r) = inorder l ++ [(k, v)] ++ inorder r

instance Functor (BST k) where
    fmap _ Empty = Empty
    fmap f (Node k v l r) = Node k (f v) (fmap f l) (fmap f r)

add :: Ord k => k -> v -> BST k v -> BST k v
add k v Empty = Node k v Empty Empty
add k v (Node k' v' l r) = if k == k' then Node k' v l r else
    if k < k' then Node k' v' (add k v l) r else Node k' v' l (add k v r)

ceiling :: Ord k => k -> BST k v -> Maybe k
ceiling _ Empty = Nothing
ceiling k (Node k' v l r) = if k == k' then Just k else
    if k > k' then ceiling k r else Just $ case ceiling k l of
        Nothing -> k'
        Just lk -> lk

empty :: BST k v -> Bool
empty Empty = True
empty _ = False

floor :: Ord k => k -> BST k v -> Maybe k
floor _ Empty = Nothing
floor k (Node k' v l r) = if k == k' then Just k else
    if k < k' then floor k l else Just $ case floor k r of
        Nothing -> k'
        Just rk -> rk

get :: Ord k => k -> BST k v -> Maybe v
get _ Empty = Nothing
get k (Node k' v l r) = if k == k' then Just v else
    if k < k' then get k l else get k r

inorder :: BST k v -> [k]
inorder Empty = []
inorder (Node k _ l r) = inorder l ++ [k] ++ inorder r

maximum :: Ord k => BST k v -> Maybe k
maximum Empty = Nothing
maximum (Node k _ l r) =
    let lm = maximum l
        rm = maximum r
    in Just $ case lm of
        Nothing -> case rm of
            Nothing -> k
            Just rk -> max k rk
        Just lk -> case rm of
            Nothing -> max k lk
            Just rk -> max k (max lk rk)

member :: Ord k => k -> BST k v -> Bool
member _ Empty = False
member k (Node k' v l r) = if k == k' then True else
    if k < k' then member k l else member k r

minimum :: Ord k => BST k v -> Maybe k
minimum Empty = Nothing
minimum (Node k _ l r) =
    let lm = minimum l
        rm = minimum r
    in Just $ case lm of
        Nothing -> case rm of
            Nothing -> k
            Just rk -> min k rk
        Just lk -> case rm of
            Nothing -> min k lk
            Just rk -> min k (min lk rk)

postorder :: BST k v -> [k]
postorder Empty = []
postorder (Node k _ l r) = postorder l ++ postorder r ++ [k]

preorder :: BST k v -> [k]
preorder Empty = []
preorder (Node k _ l r) = [k] ++ preorder l ++ preorder r

select :: Int -> BST k v -> Maybe k
select _ Empty = Nothing
select n (Node k _ l r) = let ls = size l in if n == ls then Just k else
    if n < ls then select n l else select (n - ls - 1) r

size :: BST k v -> Int
size Empty = 0
size (Node _ _ l r) = size l + size r + 1

rank :: Ord k => k -> BST k v -> Maybe Int
rank _ Empty = Nothing
rank k (Node k' v l r) = if k == k' then Just $ size l else
    if k < k' then rank k l else case rank k r of
        Nothing -> Nothing
        Just n -> Just $ size l + 1 + n

remove :: Ord k => k -> BST k v -> BST k v
remove _ Empty = Empty
remove k (Node k' v Empty r) | k == k' = r
remove k (Node k' v l Empty) | k == k' = l
remove k (Node k' v l r) | k == k' =
    let Just k'' = minimum r
        Just v' = get k'' r
    in Node k'' v' l (removeMinimum r)
remove k (Node k' v l r) = if k < k' then Node k' v (remove k l) r else Node k' v l (remove k r)

removeMaximum :: BST k v -> BST k v
removeMaximum Empty = Empty
removeMaximum (Node _ _ l Empty) = l
removeMaximum (Node k v l r) = Node k v l (removeMaximum r)

removeMinimum :: BST k v -> BST k v
removeMinimum Empty = Empty
removeMinimum (Node _ _ Empty r) = r
removeMinimum (Node k v l r) = Node k v (removeMinimum l) r

-- Test

zero = Empty
one = add 1 2 Empty
twol = add 1 2 $ add 2 3 Empty
twor = add 2 3 $ add 1 2 Empty
threeba = add 3 4 $ add 1 2 $ add 2 3 Empty
threell = add 1 2 $ add 2 3 $ add 3 4 Empty
threelr = add 2 3 $ add 1 2 $ add 3 4 Empty
threerl = add 2 3 $ add 3 4 $ add 1 2 Empty
threerr = add 3 4 $ add 2 3 $ add 1 2 Empty

addAll = foldl (\x y -> add y (y + 1) x) Empty
tenasc = addAll [1..10]
tendesc = addAll [10..1]
hundredasc = addAll [1..100]
hundreddesc = addAll [100..1]