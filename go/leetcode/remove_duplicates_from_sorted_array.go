package leetcode

/*
Problem:
Remove duplicates from a sorted (ascending) array.
Move remaining items to front of array in same order.
Return count of remaining items.
Space complexity must be constant.
Array length is in [0, 30k]. Values are in [-100, 100].

Assumptions:
None

Examples:
1,1,2: 2 - 1,2,_
0,0,1,1,1,2,2,3,3,4: 5 - 0,1,2,3,4,_,_,_,_,_
nil: 0 - nil
1: 1 - 1

Options:
Brute force:
    Iterate left to right.
    Shift entire end of array right 1 place for every duplicate found.
    Better: Collapse all duplicates at once, shift only once.
Build list at array front:
    Iterate left to right.
    Move new, greater items to the end of a growing list at the front of the array.

Solution:
Return array length if array has 0 or 1 items
Start with "unique" array slice having just first item
Iterate through rest of array
If item > last unique item, append to unique
Return unique length

Time: O(n), n is the number of items compared
Space: O(1)
*/
func removeDuplicatesFromSortedArray(nums []int) int {
	if len(nums) <= 1 {
		return len(nums)
	}
	unique := nums[:1]
	for _, n := range nums[1:] {
		if n > unique[len(unique)-1] {
			unique = append(unique, n)
		}
	}
	return len(unique)
}
