package leetcode

/*
Problem:
Return the indices of two elements that sum to a target.
The indices must be unique. There is one solution.
*/
func twoSum(nums []int, target int) []int {
	indexes := map[int]int{}
	for i, x := range nums {
		y := target - x
		if j, ok := indexes[y]; ok {
			return []int{j, i}
		}
		indexes[x] = i
	}
	return nil
}
