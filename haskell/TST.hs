-- Ternary search tree

module TST (
    TST(..),
    add,
    contains,
    empty,
    get,
    iterate,
    keys,
    longest,
    match,
    maximum,
    minimum,
    prefix,
    remove,
    size,
    values) where

import Prelude hiding (iterate, maximum, minimum)
import Data.List (permutations)
import Data.Maybe

data TST k v = Empty | TST k (Maybe v) (TST k v) (TST k v) (TST k v) deriving Show

type Key = []

zeroLengthKey = error "Keys cannot be zero length"

add :: Ord k => Key k -> v -> TST k v -> TST k v
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

contains :: Ord k => Key k -> TST k v -> Bool
contains k = isJust . get k

empty :: TST k v -> Bool
empty t = size t == 0

get :: Ord k => Key k -> TST k v -> Maybe v
get k t = case getTST k t of
    Nothing -> Nothing
    Just (TST _ v _ _ _) -> v

iterate :: TST k v -> [(Key k, v)]
iterate = iterate' [] where
    iterate' _ Empty = []
    iterate' ks (TST k Nothing l e g) = iterate' ks l ++ iterate' (k : ks) e ++ iterate' ks g
    iterate' ks (TST k (Just v) l e g) = iterate' ks l ++ (reverse (k : ks), v) : iterate' (k : ks) e ++ iterate' ks g

getTST :: Ord k => Key k -> TST k v -> Maybe (TST k v)
getTST [] _ = zeroLengthKey
getTST _ Empty = Nothing
getTST [k] t @ (TST k' _ _ _ _) | k == k' = Just t
getTST kks @ (k : ks) (TST k' _ l e g) = case compare k k' of
    LT -> getTST kks l
    EQ -> getTST ks e
    GT -> getTST kks g

keys :: TST k v -> [Key k]
keys = keys' []

keys' :: Key k -> TST k v -> [Key k]
keys' _ Empty = []
keys' ks (TST k Nothing l e g) = keys' ks l ++ keys' (k : ks) e ++ keys' ks g
keys' ks (TST k (Just _) l e g) = keys' ks l ++ reverse (k : ks) : keys' (k : ks) e ++ keys' ks g

longest :: Ord k => Key k -> TST k v -> Maybe (Key k)
longest [] _ = zeroLengthKey
longest k t = longest' k [] t where
    longest' [] t _ = Just $ reverse t
    longest' _ t Empty = if length t == 0 then Nothing else Just $ reverse t
    longest' kks @ (k : ks) t (TST k' _ l e g) = case compare k k' of
        LT -> longest' kks t l
        EQ -> longest' ks (k : t) e
        GT -> longest' kks t g

match :: Key Char -> TST Char v -> [Key Char]
match [] _ = zeroLengthKey
match k t = let
    likeChar c c' = c == c' || '*' `elem` [c, c']
    likeString s s' = if length s /= length s' then False else and $ map (uncurry likeChar) $ zip s s'
    in filter (likeString k) $ keys t

maximum :: TST k v -> Maybe (Key k)
maximum = fmap reverse . maximum' [] where
    maximum' _ Empty = Nothing
    maximum' ks (TST k Nothing _ e Empty) = maximum' (k : ks) e
    maximum' ks (TST k (Just v) _ e Empty) = Just . fromMaybe (k : ks) $ maximum' (k : ks) e
    maximum' ks (TST k Nothing _ Empty g) = maximum' ks g
    maximum' ks (TST k (Just _) _ Empty g) = Just . fromMaybe (k : ks) $ maximum' ks g

minimum :: TST k v -> Maybe (Key k)
minimum Empty = Nothing
minimum (TST k _ _ _ _) = Just [k]

prefix :: Ord k => Key k -> TST k v -> [Key k]
prefix k t = case getTST k t of
    Nothing -> []
    Just (TST _ Nothing _ e _) -> keys' (reverse k) e
    Just (TST _ (Just _) _ e _) -> k : keys' (reverse k) e

remove :: Ord k => Key k -> TST k v -> TST k v
remove _ Empty = Empty
remove [k] (TST k' _ l e g) | k == k' = collapse $ TST k' Nothing l e g
remove kks @ (k : ks) (TST k' v' l e g) = collapse $ case compare k k' of
    LT -> TST k' v' (remove kks l) e g
    EQ -> TST k' v' l (remove ks e) g
    GT -> TST k' v' l e (remove kks g)

size :: TST k v -> Int
size Empty = 0
size (TST _ Nothing l e g) = size l + size e + size g
size (TST _ (Just _) l e g) = size l + size e + size g + 1

values :: TST k v -> [v]
values Empty = []
values (TST k Nothing l e g) = values l ++ values e ++ values g
values (TST k (Just v) l e g) = values l ++ v : values e ++ values g

-- Test

m = add "m" "m" Empty
l = add "l" "l" m
n = add "n" "n" l
mm = add "mm" "mm" n
addAll = foldl (\x y -> add y y x) Empty
abc = addAll $ permutations "abc"
abcdef = addAll $ permutations "abcdef"
len = length (keys abcdef) == length (permutations "abcdef")