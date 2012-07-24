module Core where

type Name = String

type Primitive = Syntax -> Maybe Syntax

data Syntax = Name Name | List [Syntax] | Character Char | Number Double | Application Syntax Syntax deriving (Show, Eq)

preserve :: Primitive
preserve (List ss) = do
    ss' <- sequence $ map preserve ss
    return $ List ss'
preserve (Application x y) = do
    x' <- preserve x
    y' <- preserve y
    return (Application x' y')
preserve x = Just x

-- Names

name :: Primitive
name (Name _) = return $ Name "true"
name x = preserve x >> return (Name "false")

-- Booleans

true :: Primitive
true list @ (List [x, y]) = do
    x' <- preserve x
    y' <- preserve y
    return x'
true _ = Nothing

false :: Primitive
false list @ (List [x, y]) = do
    x' <- preserve x
    y' <- preserve y
    return y'
false _ = Nothing

--and

--or

--not

boolean :: Primitive
boolean (Name name) | name `elem` ["true", "false"] = return $ Name "true"
boolean x = preserve x >> return (Name "false")

-- Lists

head :: Primitive
head list @ (List (x : _)) = preserve list >> return x
head _ = Nothing

tail :: Primitive
tail list @ (List (_ : xs)) = preserve list >> return (List xs)
tail _ = Nothing

--reverse

--element

--index

--length

--null

list :: Primitive
list list @ (List _) = preserve list >> return (Name "true")
list x = preserve x >> return (Name "false")

-- Characters

character :: Primitive
character (Character _) = return $ Name "true"
character x = preserve x >> return (Name "false")

-- Numbers

--add

--subtract

--multiply

--divide

number :: Primitive
number (Number _) = return $ Name "true"
number x = preserve x >> return (Name "false")

-- Functions

id :: Primitive
id = preserve

apply :: Primitive
apply (List [x, y]) = preserve (Application x y)
apply _ = Nothing

--equal

-- Forms

constant :: Primitive
constant list @ (List [List [_, x], _]) = preserve list >> return x
constant _ = Nothing

--composeNumber :: 
--composeNumber :: Primitive
--composeNumber list @ (List [List [Number number, function], operand]) | floor n == ceiling n = 

--compose

--construct

--map

--foldl

--foldr

-- Computation

primitives :: [(Name, Primitive)]
primitives = [("apply", apply),
    ("boolean", boolean),
    ("character", character),
    ("compute", compute),
    ("constant", constant),
    ("false", false),
    ("head", Core.head),
    ("id", Core.id),
    ("list", list),
    ("tail", Core.tail),
    ("true", true)]

compute :: Primitive
compute (List vs) = do
    vs' <- sequence $ map compute vs
    return $ List vs'
compute (Application (Name name) operand) = do
    operator <- lookup name primitives
    operand' <- compute operand
    result <- operator operand'
    compute result
compute (Application operator @ (List (name @ (Name _) : _)) operand) = compute $ Application name $ List [operator, operand]
compute (Application (List _)) = Nothing
compute (Application operator operand) = do
    operator' <- compute operator
    compute $ Application operator' operand
compute x = return x