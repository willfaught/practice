package leetcode

/*
Problem:
Convert a string into zigzag order given the number of rows.
The string length is in [1, 1000].
The string has English letters, commas, and periods.
The row number is in [1, 1000].

Assumptions:
Always go down first.
Not all rows must be used.

Examples:
A, 1: A
	A
AB, 1: AB
	AB
AB, 2: AB
	A
	B
PAYPALISHIRING, 3: PAHNAPLSIIGYIR
	P A H N
	APLSIIG
	Y I R
PAYPALISHIRING, 4: PINALSIGYAHRPI
	P  I  N
	A LS IG
	YA HR
	P  I

	6 4 2 6
	6 2 4 6
	max = (R-1) * 2
	offset1 = if max - C*2 == 0 then max else max - C*2
	offset2 = if offset1 == max then max else max - offset1
PAYPALISHIRINGRIGHTNOW, 5:
	P   H   G
	A  SI  IH
	Y I R R T
	PL  IG  NW
	A   N   O

	0  8  16           8, 8           (R-1)*2
	1  7   9 15 17     6, 2, 6, 2      (5-1-1)*2 = (R-C-1)*2
	2  6  10 14 18     4, 4, 4, 4
	3  5  11 13 19 21  2, 6, 2, 6, 2
	4 12  20           8, 8
PAYPALISHIRINGRIGHTNOW, 7:
	P     N
	A    IG
	Y   R R
	P  I  I  W
	A H   G O
	LS    HN
	I     T

	Max offset: (R-1)*2
	12 10 8 6 4  2 12
	12  2 4 6 8 10 12

Options:
Write and read 2D array:
	Allocate 2D array, fill in values, iterate array
	Best/average/worst space: n/r + r array allocs
	Time: n assignments, n reads
R passes through string, skipping by offsets to build each row
	Best/average/worst space: N length array
	Best/average/worst time: N appends

Solution:
Given S, R
Set Chars to []
Set Max to (R-1) * 2
Def Offset1(C) as if Max - C*2 == 0 then Max else Max - C*2
Def Offset2(C) as if Offset1(C) == Max then Max else Max - Offset1(C)
For C in 0..R:
	Append S[C] to Chars
	Set I to C
	Loop:
		If I + Offset1(C) in S:
			Set I to I + Offset1(C)
			Append S[I] to Chars
		Else:
			Break
		If I + Offset2(C) in S:
			Set I to I + Offset2(C)
			Append S[I] to Chars
		Else:
			Break
Return Chars as string

Time: O(N), worst case, slice appends, N is the number of chars
Space: O(N), worst case, N is the number of chars
*/
func zigzagConversion(s string, numRows int) string {
	if numRows > len(s) {
		numRows = len(s)
	}
	if numRows == 1 {
		return s
	}
	chars := make([]byte, 0, len(s))
	max := (numRows - 1) * 2
	for row := 0; row < numRows; row++ {
		chars = append(chars, s[row])
		i := row
		var offset1, offset2 int
		if n := max - row*2; n == 0 {
			offset1 = max
		} else {
			offset1 = n
		}
		if offset1 == max {
			offset2 = max
		} else {
			offset2 = max - offset1
		}
		for {
			if n := i + offset1; n < len(s) {
				i = n
				chars = append(chars, s[i])
			} else {
				break
			}
			if n := i + offset2; n < len(s) {
				i = n
				chars = append(chars, s[i])
			} else {
				break
			}
		}
	}
	return string(chars)
}
