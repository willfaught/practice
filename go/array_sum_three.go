package main

import "sort"

func threeSum1(a []int) (bool, [3]int) {
	var ints = map[int]struct{}{}

	for _, x := range a {
		ints[x] = struct{}{}
	}

	for i := range a {
		for j := range a[i+1:] {
			var diff = -(a[i] + a[j])

			if _, ok := ints[diff]; ok {
				return true, [3]int{a[i], a[j], diff}
			}
		}
	}

	return false, [3]int{}
}

func threeSum2(a []int) (bool, [3]int) {
	sort.Ints(a)

	for i, x := range a {
		var j, k = i + 1, len(a) - 1

		for j < k {
			if sum := x + a[j] + a[k]; sum < 0 {
				j++
			} else if sum > 0 {
				k--
			} else {
				return true, [3]int{x, a[j], a[k]}
			}
		}
	}

	return false, [3]int{}
}
