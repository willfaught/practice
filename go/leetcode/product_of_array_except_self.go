package leetcode

// Problem:
// Given slice of integers where len >= 2, produce new slice where newslice[i]
// is the product of all integers in oldslice except oldslice[i]. All products
// will fit in 32 bits. Must not use division. Must be linear time.
//
// Assumptions:
// All products will fit in 32-bit signed ints. Input can be mutated.
//
// Examples:
// 1 2 -> 2 1
// 1 2 3 -> 6 3 2
// 5 5 5 -> 25 25 25
// 3 0 6 -> 0 18 0
// -5 2 3 -> 6 -15 -10
//
// 1   2   3  4  5
// -----------------
// 1   2   6  24 120 (prefix products)
// 120 120 60 20 5   (suffix products)
// -----------------
// 120 60  40 30 24  (products)
//
// Options:
// Bruce force, for each index, iterate all other indexes and calc product: constant space, but quadratic time (won't work, slower than linear time)
// Calc total product, divide by each int (won't work, uses division)
// Calc prefix/suffix products per index, mult prefix/suffix products from either side
// of index: linear time, constant space (not including input/output)
//
// Solution:
// Calc prefix/suffix products per index, mult prefix/suffix products from either side
// of index
//
// Time: O(N), N=prefix product multiplications
// Space: O(1), 1=scalar local vars
func ProductOfArrayExceptSelf(nums []int) []int {
    n := len(nums)
    products := make([]int, n)
    products[n-1] = nums[n-1]
    for i := n - 2; i >= 0; i-- {
        products[i] = nums[i] * products[i+1]
    }
    for i := 1; i < n; i++ {
        nums[i] *= nums[i-1]
    }
    products[0] = products[1]
    for i := 1; i < n-1; i++ {
        products[i] = nums[i-1] * products[i+1]
    }
    products[n-1] = nums[n-2]
    return products
}
