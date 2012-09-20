module AVL (add, remove, removeMaximum, removeMinimum, module BST) where

import BST hiding (add, remove, removeMaximum, removeMinimum)

rotateLeft :: BST k v -> BST k v
rotateLeft Empty = Empty
rotateLeft (Node _ _ _ Empty) = 
rotateLeft (Node k v (Node k' v' l' r') (Node k'' v'' l'' r'')) =
    Node k'' v'' (Node k v l )

rotateR = undefined

height :: BST k v -> Int
height Empty = 0
height (Node _ _ l r) = max (height l) (height r) + 1

balance :: BST k v -> BST k v
balance Empty = Empty
balance n @ (Node k v l r) = let factor = height l - height r in
    if abs factor <= 1 then n else
        if factor < 0 then rotateR n else rotateL n

add :: Ord k => k -> v -> BST k v -> BST k v
add k v Empty = Node k v Empty Empty
add k v (Node k' v' l r) = if k == k' then Node k' v l r else
    balance $ if k < k' then Node k' v' (add k v l) r else Node k' v' l (add k v r)

remove = undefined

removeMaximum = undefined

removeMinimum = undefined