package leetcode

/*
Problem:
Return the longest palindromic substring in a string s.
String length is in [1, 1000].
String only contains digits and English letters.

Assumptions:
Any substring length of 1 is a palindrome.
When there's a tie, it doesn't matter which one is returned.

Examples:
babad: bab
cbbd: bb
a: a
ab: a, b

Options:
Check if each substring is a palindrome
	Worst: n * n substring generation, n palindrome check
Check if each char is a palindrome center
	Track longest palindrome found
	Best: n time, 1 space
	Average: n * k time, 1 space
	Worst: n * n/2, 1 space

Solution:
Set Longest to substring containing first char
For each index, I:
	Check for even length palindrome if I > 0:
		Set Current to ""
		Set L and R to previous and current indexes
		While L and R chars match:
			Set Current to L...R
			Dec and inc L and R
		If Current length > Longest length:
			Set Longest to Current
	Check for odd length palindrome:
		Set Current to substring containing current index
		Set L and R to previous and next indexes
		While L and R chars match:
			Set Current to L...R
			Dec and inc L and R
		If Current length > Longest length:
			Set Longest to L...R
Return Longest

Time: O(n^2), worst case, char comparisons, n is the number of chars in s
Space: O(1), worst case, 1 is local variables
*/
func longestPalindromicSubstring(s string) string {
	search := func(current string, l, r int) string {
		for l >= 0 && r <= len(s)-1 && s[l] == s[r] {
			current = s[l : r+1]
			l--
			r++
		}
		return current
	}
	longest := s[:1]
	for i := 1; i < len(s); i++ {
		// Check if right of center of even length palindrome
		if found := search("", i-1, i); len(found) > len(longest) {
			longest = found
		}
		// Check if center of odd length palindrome
		if found := search(s[i:i+1], i-1, i+1); len(found) > len(longest) {
			longest = found
		}
	}
	return longest
}
