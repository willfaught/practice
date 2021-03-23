package main

import "sort"

func twoSum1(a []int, n int) [][2]int {
	var i, j = 0, len(a) - 1
	var s [][2]int

	sort.Ints(a)

	for i < j {
		var x, y = a[i], a[j]

		if sum := x + y; sum == n {
			s = append(s, [2]int{x, y})
			i++
		} else if sum < 0 {
			i++
		} else {
			j--
		}
	}

	return s
}

func twoSum2(a []int, n int) [][2]int {
	var m = map[int]struct{}{}
	var s [][2]int

	for _, x := range a {
		var y = n - x

		m[x] = struct{}{}

		if _, ok := m[y]; ok {
			s = append(s, [2]int{x, y})
		}
	}

	return s
}
