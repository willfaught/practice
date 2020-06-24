package leetcode

// Problem:
// For a slice of ints, find the largest non-empty subslice and return its sum.
//
// Assumptions:
// Slice can be any non-zero length. Elems can be any int.
//
// Examples:
// 1 -> 1
// 1 2 -> 3
// 1 2 3 -> 6
// 0 -> 0
// 0 1 -> 1
// 1 0 0 1 -> 2
// -9 -> -9
// 1 -2 1 -> 1
// -1 -2 -3 -> -1
// 1 2 -1 3 4 -2 -3 3 -1 -1 -18 22 99 -> 9
// 3 -1 -1 -1
// 3  2  1  0
//
// Options:
// Brute force, search every subslice and track largest sum
//     Linear space, but quadratic time
//
// Solution:
// Compute cumulative sum slice.
// Brute force, search every subslice and track largest sum.
//
// Time: O(N^2), N=visiting each slice
// Space: O(N), N=cumulative sum slice
func MaximumSubarray(nums []int) int {
    sums := make([]int, len(nums))
    sums[0] = nums[0]
    for i := 1; i < len(nums); i++ {
        sums[i] = sums[i-1] + nums[i]
    }
    max := nums[0]
    for i := 0; i < len(nums); i++ {
        for j := i; j < len(nums); j++ {
            var sum int
            if i == 0 {
                sum = sums[j]
            } else {
                sum = sums[j] - sums[i-1]
            }
            if sum > max {
                max = sum
            }
        }
    }
    return max
}
