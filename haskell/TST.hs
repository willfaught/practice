module TST (TST(..), add, get, remove) where

data TST k v = Empty | TST k (Maybe v) (TST k v) (TST k v) (TST k v) deriving Show

zeroLengthKey = error "Keys cannot be zero length"

add :: Ord k => [k] -> v -> TST k v -> TST k v
add [] _ _ = zeroLengthKey
add [k] v (TST k' _ l e g) | k == k' = TST k' (Just v) l e g
add kks @ (k : _) v Empty = add kks v (TST k Nothing Empty Empty Empty)
add kks @ (k : ks) v (TST k' v' l e g) = case compare k k' of
    LT -> TST k' v' (add kks v l) e g
    EQ -> TST k' v' l (add ks v e) g
    GT -> TST k' v' l e (add kks v g)

collapse :: TST k v -> TST k v
collapse Empty = Empty
collapse (TST _ Nothing Empty Empty Empty) = Empty
collapse t = t

get :: Ord k => [k] -> TST k v -> Maybe v
get [] _ = zeroLengthKey
get _ Empty = Nothing
get [k] (TST k' v _ _ _) | k == k' = v
get kks @ (k : ks) (TST k' _ l e g) = case compare k k' of
    LT -> get kks l
    EQ -> get ks e
    GT -> get kks g

remove :: Ord k => [k] -> TST k v -> TST k v
remove _ Empty = Empty
remove [k] (TST k' _ l e g) | k == k' = collapse $ TST k' Nothing l e g
remove kks @ (k : ks) (TST k' v' l e g) = collapse $ case compare k k' of
    LT -> TST k' v' (remove kks l) e g
    EQ -> TST k' v' l (remove ks e) g
    GT -> TST k' v' l e (remove kks g)