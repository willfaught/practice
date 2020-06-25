package leetcode

/*
Problem:

Find the minimum element in a rotated sorted (ascending) array. No dups.

Assumptions:

Array is non-empty and any length. Any signed int values.

Examples:

[3,4,5,1,2] -> 1
[4,5,6,7,0,1,2] -> 0
1 2 3 -9 0 -> -9
5 -> 5
4 5 -> 4
5 4 -> 4
4 5 6 ->

Options:

Iterate array, look for smaller inversion, return that or first
    Constant space, but linear time
Modified binary search for smaller inversion, return that or first
    Constant space, but logarithmic time

Solution:

nums = ...
if nums.len == 1 || nums.first < nums.last
    return nums.first
loop:
    if nums.len == 2
        return min(nums.first, nums.last)
    if nums.first < nums.middle
        nums = nums[nums.middleIndex:]
    else
        nums = nums[:nums.middleIndex+1]

Complexities:

Time: O(log N), N=array length
Space: O(1), 1=local vars
*/
func FindMinimumInRotatedSortedArray(nums []int) int {
    l := len(nums)
    first, last := nums[0], nums[l-1]
    if l == 1 || first < last {
        return first
    }
    for {
        l = len(nums)
        first, last = nums[0], nums[l-1]
        if l == 2 {
            if first < last {
                return first
            } else {
                return last
            }
        }
        l2 := l / 2
        middle := nums[l2]
        if first < middle {
            nums = nums[l2:]
        } else {
            nums = nums[:l2+1]
        }
    }
    panic("cannot reach here")
}
