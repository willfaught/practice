package leetcode

/*
Problem:
Convert a roman numeral to an integer.
String length >= 1, <= 15.
String only contains roman numeral symbols.
String is valid.
Number in range [1, 3999].
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.

Assumptions:
None.

Examples:
I: 1
III: 3
IV: 4
LVIII: 58
MCMXCIV: 1994
CMXC: 990
MMMCMXCIX: 3999
MDCLXVI: 1000+500+100+50+10+5+1
MMDDCCLLXXVVII: 2*1000+2*500+2*100+2*50+2*10+2*5+2*1
CDXLIV: 444

Options:

Solution:
Iterate left to right
Add symbol values as we go to total
If symbol is I, check for following V or X
If so, add 4 or 9 instead, then skip both symbols
Same for X and C

Time: O(n)
Space: O(1)
*/
func romanToInt(s string) int {
    var total int
    for i, n := 0, len(s); i < n; i++ {
        switch s[i] {
        case 'I':
            if i+1 < n {
                switch s[i+1] {
                case 'V':
                    total += 4
                    i++
                case 'X':
                    total += 9
                    i++
                default:
                    total++
                }
            } else {
                total++
            }
        case 'V':
            total += 5
        case 'X':
            if i+1 < n {
                switch s[i+1] {
                case 'L':
                    total += 40
                    i++
                case 'C':
                    total += 90
                    i++
                default:
                    total += 10
                }
            } else {
                total += 10
            }
        case 'L':
            total += 50
        case 'C':
            if i+1 < n {
                switch s[i+1] {
                case 'D':
                    total += 400
                    i++
                case 'M':
                    total += 900
                    i++
                default:
                    total += 100
                }
            } else {
                total += 100
            }
        case 'D':
            total += 500
        case 'M':
            total += 1000
        }
    }
    return total
}
