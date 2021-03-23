package main

import "sort"

type insertIntervalPair [2]int

func (p insertIntervalPair) less(p2 insertIntervalPair) bool {
	if p[0] == p2[0] {
		return p[1] < p2[1]
	}

	return p[0] < p2[0]
}

type insertIntervalPairSlice []insertIntervalPair

var _ sort.Interface = insertIntervalPairSlice{}

func (p insertIntervalPairSlice) Len() int {
	return len(p)
}

func (p insertIntervalPairSlice) Less(i, j int) bool {
	return p[i].less(p[j])
}

func (p insertIntervalPairSlice) Swap(i, j int) {
	p[i], p[j] = p[j], p[i]
}

func insertIntervalBetween(n int, i insertIntervalPair) bool {
	return i[0] <= n && n <= i[1]
}

func insertIntervalWithin(a, b insertIntervalPair) bool {
	return insertIntervalBetween(a[0], b) || insertIntervalBetween(a[1], b)
}

func insertIntervalOverlap(a, b insertIntervalPair) bool {
	return insertIntervalWithin(a, b) || insertIntervalWithin(b, a)
}

func insertIntervalMerge(a, b insertIntervalPair) insertIntervalPair {
	var x, y = a[0], a[1]

	if b[0] < a[0] {
		x = b[0]
	}

	if b[1] > a[1] {
		y = b[1]
	}

	return insertIntervalPair{x, y}
}

func insertInterval1(p insertIntervalPair, ps []insertIntervalPair) []insertIntervalPair {
	sort.Sort(insertIntervalPairSlice(ps))

	var i, j, k int
	var insert bool
	var x insertIntervalPair

	for i, x = range ps {
		if p.less(x) {
			insert = true

			break
		}
	}

	if insert {
		ps = append(append(append([]insertIntervalPair(nil), ps[:i]...), p), ps[i:]...)
	} else {
		ps = append(ps, p)
	}

	for j = i; j-1 >= 0 && insertIntervalOverlap(ps[j-1], ps[j]); j-- {
		ps[j-1] = insertIntervalMerge(ps[j-1], ps[j])
	}

	for k = i; k+1 < len(ps) && insertIntervalOverlap(ps[k], ps[k+1]); k++ {
		ps[k+1] = insertIntervalMerge(ps[k], ps[k+1])
	}

	if j != k {
		ps[k] = insertIntervalMerge(ps[j], ps[k])
		copy(ps[j:], ps[k:])
		ps = ps[:len(ps)-(k-j)]
	}

	return ps
}

func insertInterval2(p insertIntervalPair, ps []insertIntervalPair) []insertIntervalPair {
	var m = map[insertIntervalPair]struct{}{}

	for _, x := range ps {
		if insertIntervalOverlap(p, x) {
			p = insertIntervalMerge(p, x)
		} else {
			m[x] = struct{}{}
		}
	}

	var s []insertIntervalPair

	for x, _ := range m {
		s = append(s, x)
	}

	return append(s, p)
}
