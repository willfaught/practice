// TwoSum returns the indices of two elements that sum to target.
// The indices must be unique. There is one solution.
func TwoSum(nums []int, target int) []int {
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
