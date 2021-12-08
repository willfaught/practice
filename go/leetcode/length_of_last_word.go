package leetcode

/*
Problem:
Return length of last word in string.
Words can be surrounded by multiple spaces.
The string can end in spaces.
Words are English letters.
String length is in [1, 10k].
There's at least one word.

Assumptions:
None

Examples:
"Hello World": 5
"   fly me   to   the moon  ": 4
"luffy is still joyboy": 6
"a": 1
" a ": 1

Options:
Linear scan:
    Scan from right for last word.
    Worst: n comparisons of chars, 1 space.
    Average: k comparisons of chars, 1 space.
    Best: 1 comparison of chars, 1 space.

Solution:
Iterate from right to left, looking for the first non-space.
Then iterate from right to left starting there.
Count non-spaces.
Look for the first space or the string start, then stop.
Return count.

Time: O(n), n is the number of comparisons in the string
Space: O(1)
*/
func lengthOfLastWord(s string) int {
	i := len(s) - 1
	for s[i] == ' ' {
		i--
	}
	j := i
	for i > 0 && s[i-1] != ' ' {
		i--
	}
	return j - i + 1
}
