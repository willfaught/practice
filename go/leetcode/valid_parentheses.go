package leetcode

/*
Problem:
Return whether brackets are balanced and nested correctly.
Only "(){}[]" chars.

Assumptions:
Empty string is valid.

Examples:
(): true
()[]{}: true
(]: false
([)]: false
{[]}: true

Options:
Parse recursively, return whether valid parse
Track pending close pairs with stack of seen opening chars

Solution:
Iterate through string
Push open chars onto stack
Verify close chars match top of stack, then pop stack
Return false if no match
Return false if end of string and stack full
Return true if end of string and stack empty

Time: O(n), n is string length
Space: O(n), n is string length
*/
func isValid(s string) bool {
	stack := make([]rune, 0, len(s))
	for _, c := range s {
		switch c {
		case '(':
			stack = append(stack, ')')
		case '{':
			stack = append(stack, '}')
		case '[':
			stack = append(stack, ']')
		default:
			if len(stack) == 0 || c != stack[len(stack)-1] {
				return false
			}
			stack = stack[:len(stack)-1]
		}
	}
	return len(stack) == 0
}
