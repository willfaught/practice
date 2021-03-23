package main

type subtreeTree struct {
	x           int
	left, right *subtreeTree
}

func subtree(a, b *subtreeTree) bool {
	return subtreeMatch(a, b) || subtree(a, b.left) || subtree(a, b.right)
}

func subtreeMatch(a, b *subtreeTree) bool {
	if a == nil && b == nil {
		return true
	} else if a == nil || b == nil {
		return false
	}

	return a.x == b.x && subtreeMatch(a.left, b.left) && subtreeMatch(a.right, b.right)
}
