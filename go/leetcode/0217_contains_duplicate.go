package leetcode

/*
Problem:
Return whether a slice contains duplicate integers.

Assumptions:
Return false for empty slices. Slice has unknown length.

Examples:
1 2 1: true
1 2 3: false
1 1 1: true

Options:
Compare every pair of nums: constant space, but quadratic time
Sort, then compare neighbors: constant space, but linearithmic time
Look up items in hash table: linear time, but linear space

Solution:
Iterate all nums. Look up each num in hash table. Return true if in hash table.
Return false otherwise.

Time: O(N), N=hash table lookups
Space: O(N), N=elements in hash table
*/
func containsDuplicate(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}
	seen := map[int]struct{}{}
	for _, num := range nums {
		if _, ok := seen[num]; ok {
			return true
		} else {
			seen[num] = struct{}{}
		}
	}
	return false
}
