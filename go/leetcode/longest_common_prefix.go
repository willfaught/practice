package leetcode

/*
Problem:
Return the longest common prefix among a set of strings.
Return an empty string if there isn't a common prefix.
String count is in [1, 200].
String lengths are in [0, 200].
Strings are only lower-case English letters.

Assumptions:
The common prefix is the string if there is only one string.
A common prefix must be shared among all strings.

Examples:
"i": ""
"flower","flow","flight": "fl"
"dog","racecar","car": ""

Solution:
Start with prefix as first string
For each string in rest of strings,
Reduce prefix by shared prefix of the string
If prefix is empty, break

Time: O(n^2)
Space: O(1)
*/
func longestCommonPrefix(strs []string) string {
	if len(strs) == 1 {
		return strs[0]
	}
	prefix := strs[0]
	for _, s := range strs[1:] {
		if len(prefix) < len(s) {
			s = s[:len(prefix)]
		}
		if len(s) < len(prefix) {
			prefix = prefix[:len(s)]
		}
		for i := 0; i < len(s); i++ {
			if prefix[i] != s[i] {
				prefix = prefix[:i]
				break
			}
		}
		if prefix == "" {
			break
		}
	}
	return prefix
}
