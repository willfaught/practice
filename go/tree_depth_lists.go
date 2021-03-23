package main

type treeDepthListsNode struct {
	value             int
	left, right, next *treeDepthListsNode
}

func treeDepthLists(n *treeDepthListsNode) {
	for n != nil {
		var last, next *treeDepthListsNode

		for x := n; x != nil; x = x.next {
			var link = func(y *treeDepthListsNode) {
				if y == nil {
					return
				}

				if last == nil {
					next = y
				} else {
					last.next = y
				}

				last = y
			}

			link(x.left)
			link(x.right)
		}

		n = next
	}
}
