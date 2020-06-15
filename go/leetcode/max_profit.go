package leetcode

// Problem:
// Have an array of stock prices over time.
// Determine the most profit to be had from one buy and then one sell.
//
// Assumptions:
// Prices >= 0. Prices can go up, down, stay the same. At least one price.
// Can buy and sell on same day (0 profit).
//
// Examples:
// 9 1 5 2 6 4
// 1 2 3 4 5
// 3 2 1 4 5
// 6 10 [4] 1 2 3 4 5 6 [5]
//
// Time: O(N)
// Space: O(1)
func MaxProfit(prices []int) int {
    var max int
    for i, j := 0, 1; j < len(prices); j++ {
        if prices[j] < prices[i] {
            i = j
        } else if profit := prices[j] - prices[i]; profit > max {
            max = profit
        }
    }
    return max
}
