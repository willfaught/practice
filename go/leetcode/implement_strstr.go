package leetcode

/*
Problem:
Return the first index of need in haystack.
If it doesn't exist, return -1.
If needle is empty, return 0.
Haystack can be empty.
Needle length can be up to 50K.
Only lowercase English chars.

Assumptions:
None

Examples:
"hello", "ll": 2
"aaaaa", "bba": -1
"", "": 0
"x", "": 0
"", "x": -1
"sm", "large": -1

Options:
Brute force:
    Iterate left to right
    Match first char of needle to each haystack char
    For matches, compare rest of needle to rest of haystack
    Worst: n^2 time, 1 space
    Best: n time, 1 space

Solution:
Return 0 if needle is empty
Compare every haystack char to the first needle char
For each match, compare paired chars
Return index for full match
Return -1 at end otherwise
Exit early if needle can't fit in rest of haystack

Time: O(n^2), n is the number of char comparisons
Space: O(1)
*/
func implementStrstr(haystack string, needle string) int {
	if len(needle) == 0 {
		return 0
	}
	if len(haystack) == 0 || len(haystack) < len(needle) {
		return -1
	}
	for i := range haystack[:len(haystack)-len(needle)+1] {
		for j := 0; j < len(needle) && haystack[i+j] == needle[j]; j++ {
			if j == len(needle)-1 {
				return i
			}
		}
	}
	return -1
}
