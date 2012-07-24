module A where

type Name = String

data Syntax = Name Name | EmptyList | List [Syntax] | Undefined | Application Syntax Syntax deriving (Show, Eq)

--defs :: Val
--defs = List [
--	List [ValName "foo", ]
--]

rep :: Name -> Syntax -> Syntax
rep name =
	case name of
		"id" -> SyntaxVal
		"apply" -> \x -> case x of List [x, y] -> Application (SyntaxVal x) (SyntaxVal y) ; _ -> SyntaxVal Undefined
		"true" -> \x -> SyntaxVal $ case x of List [v1, v2] -> v1 ; _ -> Undefined
		"false" -> \x -> SyntaxVal $ case x of List [v1, v2] -> v2 ; _ -> Undefined
		"head" -> \x -> SyntaxVal $ case x of List (v : _) -> v ; _ -> Undefined
		"tail" -> \x -> SyntaxVal $ case x of List (_ : vs) -> List vs ; _ -> Undefined
		"name" -> \x -> SyntaxVal $ case x of ValName _ -> ValName "true" ; Undefined -> Undefined ; _ -> ValName "false"
		"list" -> \x -> SyntaxVal $ case x of EmptyList -> ValName "true" ; List _ -> ValName "true" ; Undefined -> Undefined ; _ -> ValName "false"

compute :: Syntax -> Val
compute x =
	case x of
		SyntaxVal (List vs) -> List $ map (compute . SyntaxVal) vs
		SyntaxVal v -> v
		Application (SyntaxVal (ValName name)) rand -> compute $ (rep name) (compute rand)
		Application (SyntaxVal vals @ (List (name @ (ValName _) : _))) (SyntaxVal rand) -> compute $ Application (SyntaxVal name) (SyntaxVal $ List [vals, rand])
		Application e1 e2 -> compute $ Application (SyntaxVal . compute $ e1) e2
		Application _ _ -> Undefined