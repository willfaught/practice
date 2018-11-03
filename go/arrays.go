package practice

// StringUniqueChars1 determines whether a string has all unique characters.
// Space: Best/average/worst: O(n)
// Time: Best/average/worst: O(n)
func StringUniqueChars1(s string) bool {
	if len(s) <= 1 {
		return true
	}
	var seen = map[rune]struct{}{}
	for _, r := range s {
		if _, ok := seen[r]; ok {
			return false
		}
		seen[r] = struct{}{}
	}
	return true
}

// StringUniqueChars2 determines whether a string has all unique characters.
// No other data structures are used.
// Space: Best/average/worst: O(1)
// Time: Best/average/worst: O(n^2)
func StringUniqueChars2(s string) bool {
	for i, x := range s {
		for j, y := range s {
			if j > i && x == y {
				return false
			}
		}
	}
	return true
}

// given two strings, write a method to decide if one is a permutation of the other
func IsPermutation(a, b string) bool {

}
