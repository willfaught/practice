package leetcode

/*
Problem:
Return the length of the longest substring without repeating chars.
Smallest result is 1 if string is not empty.
Return 0 if string is empty.
String length is in [0, 50k].
String has English letters, digits, symbols, and spaces.

Assumptions:
None

Examples:
"abcabcbb": 3
        l
        r
a,ab,abc,bc,bca,cab,ab,abc,bc,c,cb,b,b
1,2,3

"bbbbb": 1
"pwwkew": 3
"": 0
"a": 1
"aa": 1
"ab": 2
"aba": 2
"abac": 3
"aaa": 1
  l
  r
a

Options:
Copy every substring, sort, iterate sorted chars, look for repeats:
    Uses space for copy, at most n for entire string
    Have to iterate entire copy
    Sort time is nlogn
Iterate every substring, insert chars into map, look for repeats:
    Uses space for map, at most n for entire string
Iterate the string using left and right indexes:
    Init substring is first char
    Advance right to expand substring, inserting into map, updating max seen length
    Advance left when cannot advance right due to repeats
    Best/average/worst: n lookups in map, m unique chars in string stored in map

Solution:
Return 0 if string is empty
Insert first char into map
Set Max to 1
Set L and R substring indexes to 0
While R+1 in string:
    If R+1 char in map:
        Remove L char from map
        Inc L
        Insert L char into map
        Inc R if R < L
    Else:
        Insert R+1 char into map
        Inc R
        Set Max to R-L+1 if > Max

Time: O(n), worst case, map lookups, n is the string length
Space: O(n), worst case, map items, n is the number of unique chars in the string
*/
func longestSubstringWithoutRepeatingCharacters(s string) int {
	if len(s) == 0 {
		return 0
	}
	chars := map[byte]struct{}{s[0]: struct{}{}}
	l, r := 0, 0
	max := 1
	for r+1 < len(s) {
		if _, ok := chars[s[r+1]]; ok {
			delete(chars, s[l])
			l++
			chars[s[l]] = struct{}{}
			if r < l {
				r++
			}
		} else {
			chars[s[r+1]] = struct{}{}
			r++
			if n := r - l + 1; n > max {
				max = n
			}
		}
	}
	return max
}
