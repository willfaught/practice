package leetcode

/*
Problem:
Add the non-negative numbers represented by digits in two linked lists.
Return the result in a linked list.
Each node contains a digit in [0, 9].
The numbers start with the least-significant digit.
No leading zeroes, except for 0.
Node count per list is in [1, 100].

Assumptions:
None

Examples:
[2,4,3], [5,6,4]: [7,0,8]
[0], [0]: [0]
[9,9,9,9,9,9,9], [9,9,9,9]: [8,9,9,9,0,0,0,1]
[0,1], [1,2]: [1,3]
[9], [9]: [8,1]
[5], [5]: [0,1]

Options:
Iterate lists iteratively:
    Add digits together, carry as necessary
    Worst/average/best: n additions, 1 space
Iterate lists recursively:
    Add digits together, carry as necessary
    Worst/average/best: n additions, n call stack frames

Solution:
Set carry to 0
While one list is not empty or carry > 0:
Add list head digits together with carry
If list is empty, digit is 0
If sum > 9, set result digit to sum - 10, set carry to 1
Else set carry to 0
Iterate to next list nodes
Return result list

Time: O(n), additions, n is the length of the longer list
Space: O(1)
*/
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	result := &ListNode{}
	head := result
	var carry int
	for l1 != nil || l2 != nil || carry > 0 {
		var digit1, digit2 int
		if l1 != nil {
			digit1 = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			digit2 = l2.Val
			l2 = l2.Next
		}
		val := carry + digit1 + digit2
		carry = val / 10
		result.Next = &ListNode{Val: val % 10}
		result = result.Next
	}
	return head.Next
}
