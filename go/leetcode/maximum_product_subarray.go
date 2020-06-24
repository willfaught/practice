/*
Problem:

Find the non-empty subslice of an int slice with the largest product
and return the product.

Assumptions:

Slice can be any length.

Examples:

[2,3,-2,4] -> 6
[-2,0,-1] -> 0
x -> x
1 2 3 -> 6
1 1 1 -> 1
0 1 -> 1 // if curprod == 0, set curprod to next elem
0 1 0 -> 1
1 0 -> 1 // if next == 0, reset curprod, keep maxprod
1 0 1 -> 1
-2 3 -> 3 // if curprod < next, curprod = next
-2 3 -4 -> 24 // keep track of min/max curprod
-1 0 -> 0 // if curprod < next, curprod = next

Options:

Compute the product for every subslice and remember the max product
    Linear space, but quadratic time
Keep track of best and current min/max products, iterate elements and update products
    Constant space, but linear time

Solution:

minprod, maxprod, bestprod = nums[0]
for elem in nums[1:]
    newminprod = min(elem, minprod * elem, maxprod * elem)
    newmaxprod = max(elem, maxprod * elem, minprod * elem)
    minprod = newminprod
    maxprod = newmaxprod
    bestprod = max(bestprod, maxprod)
return bestprod

Complexities:

Time: O(N), N=element visits
Space: O(1), 1=local scalar vars
*/
func MaximumProductSubarray(nums []int) int {
    bestProd, minProd, maxProd := nums[0], nums[0], nums[0]
    for _, x := range nums[1:] {
        minProdX := minProd * x
        maxProdX := maxProd * x
        nextMinProd := min(min(x, minProdX), maxProdX)
        nextMaxProd := max(max(x, minProdX), maxProdX)
        minProd = nextMinProd
        maxProd = nextMaxProd
        bestProd = max(bestProd, maxProd)
    }
    return bestProd
}

func min(x, y int) int {
    if x < y {
        return x
    }
    return y
}

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}
