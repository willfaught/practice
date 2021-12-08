package leetcode

/*
Problem:
Return index to insert target into nums, containing ascending, unique values.
If appended, return index outside nums.
Use O(log n) time.
Nums length in [1, 10k].
Nums values and target in [-10k, 10k].

Assumptions:
Target may not be unique.

Examples:
[1,3,5,6], 5: 2
[1,3,5,6], 2: 1
[1,3,5,6], 7: 4
[1,3,5,6], 0: 0
[1], 0: 0
[], 0: 0
[1,2,3], 4: 3
[1,5,10], 9: 2

Options:
Binary search:
    Search for match with binary search.
    If not found, insert near last index compared.
    Average/worst: log n comparisons, 1 space.
    Best: 1 comparison, 1 space.

Solution:
Track division with two indexes, i and j
Find middle index between the two
Compare that value to target
If match, return index
If less than target, set i to mid + 1
If greater, set j to mid - 1
Repeat until i == j, check last index
If last value < target, return next index
If last value > target, return index

Time: O(log n), n is number of comparisons in nums
Space: O(1)
*/
func searchInsertPosition(nums []int, target int) int {
    i, j := 0, len(nums) - 1
    for i <= j {
        mid := (i + j) / 2
        if n := nums[mid]; n == target {
            return mid
        } else if n < target {
            i = mid + 1
        } else {
            j = mid - 1
        }
    }
    return i
}
