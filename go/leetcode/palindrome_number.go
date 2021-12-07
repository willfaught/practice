package leetcode

import "strconv"

/*
Problem:
Return whether an int is a palindrome. No if negative.

Assumptions:
Palindrome is in base 10.

Examples:
1: true
11: true
111: true
-1: false
10: false
101: true
12321: true

Options:
Get sequence of digits by dividing by 10 myself.
Get sequence of digits by converting to string.

Solution:
Convert to sequence of digits
Point to both ends, compare pairs
Move pointers inward one at a time

Time: O(n)
Space: O(n)
*/
func palindromeNumber(x int) bool {
	s := strconv.Itoa(x)
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return false
		}
	}
	return true
}
