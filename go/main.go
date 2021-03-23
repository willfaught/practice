package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(stringUniqueChars2("abc"))
}

//str has all unique chars?

func stringUniqueChars1(s string) bool {
	var m = map[rune]struct{}{}

	for _, r := range s {
		if _, ok := m[r]; ok {
			return false
		}

		m[r] = struct{}{}
	}

	return true
}

type stringUniqueChars2ByteSlice []byte

func (s stringUniqueChars2ByteSlice) Len() int {
	return len(s)
}

func (s stringUniqueChars2ByteSlice) Less(i, j int) bool {
	return s[i] < s[j]
}

func (s stringUniqueChars2ByteSlice) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}

func stringUniqueChars2(s string) bool {
	if s == "" {
		return false
	}

	var bs = []byte(s)

	sort.Sort(stringUniqueChars2ByteSlice(bs))

	var last = bs[0]

	for _, b := range bs {
		if b == last {
			return false
		}

		last = b
	}

	return true
}
