package main

func maxSubarray(a []int) []int {
	var sum, maxsum = a[0], a[0]
	var left, right = 0, 1
	var maxleft, maxright = 0, 1

	for i, x := range a[1:] {
		if sum+x < x {
			sum, left, right = x, i+1, i+2
		} else {
			sum += x
			right++
		}

		if maxsum < sum {
			maxsum, maxleft, maxright = sum, left, right
		}
	}

	return a[maxleft:maxright]
}
