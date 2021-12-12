package leetcode

type ListNode struct {
	Val  int
	Next *ListNode
}

/*
Problem:
Merge two sorted (ascending) singly-linked lists.
Node count for each list is in [0, 50].
Node values are in [-100, 100].

Assumptions:
No cycles in given lists.

Examples:
[1,2,4], [1,3,4]: [1,1,2,3,4,4]
[], []: []
[], [0]: [0]

Options:
Iterate both and stitch them together in order.
Do it iteratively or recursively.

Solution:
Iterate both iteratively and stitch them together in order.
Find the smallest node and use that as the "first" and "last" ordered node.
Advance the pointer for the list containing that last node to its next node.
Find the next smallest node in the two lists, then point the last node to it.
Make that next smallest node the last node.
Repeat.
Once one list is exhausted, then tack on the rest of the other list.

Time: O(n), n is the number of comparisons between values
Space: O(1)
*/
func mergeTwoSortedLists(list1 *ListNode, list2 *ListNode) *ListNode {
	if list1 == nil {
		return list2
	} else if list2 == nil {
		return list1
	}
	first := &ListNode{}
	last := first
	for list1 != nil && list2 != nil {
		if list1.Val < list2.Val {
			last.Next = list1
			last = list1
			list1 = list1.Next
		} else {
			last.Next = list2
			last = list2
			list2 = list2.Next
		}
	}
	if list1 != nil {
		last.Next = list1
	} else if list2 != nil {
		last.Next = list2
	}
	return first.Next
}
