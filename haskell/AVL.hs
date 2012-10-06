-- AVL tree

module AVL (add, remove, removeMaximum, removeMinimum, module BST) where

import BST hiding (add, remove, removeMaximum, removeMinimum)

rotateLeft :: BST k v -> BST k v
rotateLeft (Node k v l (Node k' v' l' r')) = Node k' v' (Node k v l l') r'

rotateRight :: BST k v -> BST k v
rotateRight (Node k v (Node k' v' l' r') r) = Node k' v' l' (Node k v r' r)

height :: BST k v -> Int
height Empty = 0
height (Node _ _ l r) = max (height l) (height r) + 1

balanceRight :: BST k v -> BST k v
balanceRight n @ (Node _ _ l r) = let factor = height l - height r in
    if factor == -1 then n else rotateRight n

balanceLeft :: BST k v -> BST k v
balanceLeft n @ (Node _ _ l r) = let factor = height l - height r in
    if factor == 1 then n else rotateLeft n

balance :: BST k v -> BST k v
balance Empty = Empty
balance n @ (Node k v l r) = let factor = height l - height r in
    if abs factor <= 1 then n else
        if factor < 0
        then rotateLeft $ Node k v l (balanceRight r)
        else rotateRight $ Node k v (balanceLeft l) r

add :: Ord k => k -> v -> BST k v -> BST k v
add k v Empty = Node k v Empty Empty
add k v (Node k' v' l r) = if k == k' then Node k' v l r else
    balance $ if k < k' then Node k' v' (add k v l) r else Node k' v' l (add k v r)

remove :: Ord k => k -> BST k v -> BST k v
remove _ Empty = Empty
remove k (Node k' v Empty r) | k == k' = r
remove k (Node k' v l Empty) | k == k' = l
remove k (Node k' v l r) | k == k' =
    let Just k'' = BST.minimum r
        Just v' = get k'' r
    in balance $ Node k'' v' l (removeMinimum r)
remove k (Node k' v l r) = balance $ if k < k' then Node k' v (remove k l) r else Node k' v l (remove k r)

removeMaximum :: BST k v -> BST k v
removeMaximum Empty = Empty
removeMaximum (Node _ _ l Empty) = l
removeMaximum (Node k v l r) = balance $ Node k v l (removeMaximum r)

removeMinimum :: BST k v -> BST k v
removeMinimum Empty = Empty
removeMinimum (Node _ _ Empty r) = r
removeMinimum (Node k v l r) = balance $ Node k v (removeMinimum l) r

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