package leetcode

/*
Problem:
Reverse the digits in a 32-bit signed integer X.
Return 0 if the reverse is outside the signed integer range [-2^31, 2^31-1].
You cannot use 64-bit integers.

Assumptions:
None

Examples:
1: 1
123: 321
-123: -321
120: 21
-2147483648: 0 // -8463847412

Options:
Convert to string, reverse string, convert to int:
	Worst/average/best time: 3N
	Worst/average/best space: N + N + 1
"Pop" digits with mod, "push" with mult/add:
	Worst/average/best time: N
	Worst/average/best space: 1

Solution:
Skipped

Time: O(N) worst case divisions, N is the number of digits
Space: O(1)
*/
func reverseInteger(x int) int {
	x2 := int32(x)
	var neg bool
	if x2 < 0 {
		neg = true
		x2 = -x2
	}
	var y int32
	const max = 1<<31 - 1
	for x2 > 0 {
		if y > max/10 {
			return 0
		}
		y *= 10
		if y > max-x2%10 {
			return 0
		}
		y += x2 % 10
		x2 /= 10
	}
	if neg {
		y = -y
	}
	return int(y)
}
