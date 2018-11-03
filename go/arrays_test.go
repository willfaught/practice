package practice

import "testing"

func TestStringUniqueChars(t *testing.T) {
	for _, test := range []struct {
		s string
		b bool
	}{
		{"", true},
		{"a", true},
		{"ab", true},
		{"aa", false},
	} {
		if a, e := StringUniqueChars1(test.s), test.b; a != e {
			t.Errorf("StringUniqueChars1: string %s: actual %t, expected %t", test.s, a, e)
		}
		if a, e := StringUniqueChars2(test.s), test.b; a != e {
			t.Errorf("StringUniqueChars2: string %s: actual %t, expected %t", test.s, a, e)
		}
	}
}
