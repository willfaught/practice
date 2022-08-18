"""Array list"""


import itertools
import unittest


class ArrayList(object):
    """Array list"""

    def __init__(self, *pargs):
        self._items = []
        if pargs:
            self._items.extend(pargs)

    def __contains__(self, key):
        return key in self._items

    def __del__(self):
        for i in xrange(len(self)):            
            self[i] = None
        self._items = None

    def __delitem__(self, key):
        del self._items[key]

    def __eq__(self, other):
        try:
            n = len(other)
        except TypeError:
            return False
        if n != len(self):
            return False
        for i in xrange(n):
            try:
                if self[i] != other[i]:
                    return False
            except TypeError:
                return False
        return True

    def __getitem__(self, key):
        return self._items[key]

    def __hash__(self):
        pass

    def __iter__(self):
        return iter(self._items)

    def __len__(self):
        return len(self._items)

    def __ne__(self, other):
        return not (self == other)

    def __repr__(self):
        return 'ArrayList(%s)' % ', '.join(repr(x) for x in self._items)

    def __reversed__(self):
        return reversed(self._items)

    def __setitem__(self, key, value):
        self._items[key] = value

    def __str__(self):
        return '<%s>' % ', '.join(self._items)




def bubble_sort(a):
    """
    Stable, adaptive, offline, comparisons
    Space: O(n) total, O(1) auxiliary
    Worst time: O(n^2) swaps, comparisons
    Average time: O(n^2) swaps, comparisons
    Best time: O(1) swaps, O(n) comparisons
    """
    n = len(a)
    for i in xrange(n - 1):
        swapped = False
        for j in xrange(n - 1, i, -1):
            if a[j] < a[j - 1]:
                a[j], a[j - 1] = a[j - 1], a[j]
                swapped = True
        if not swapped:
            break


def counting_sort(a, k):
    """
    Stable, not adaptive, offline, no comparisons
    Space: O(n) total, O(k) auxiliary
    Worst time: O(n) assignments
    Average time: O(n) assignments
    Best time: O(n) assignments
    """
    counts = [0 for _ in xrange(k)]
    for x in a:
        counts[x] += 1
    total = 0
    for i in xrange(len(counts)):
        count = counts[i]
        counts[i] = total
        total += count
    b = [None for _ in xrange(len(a))]
    for x in a:
        b[counts[x]] = x
        counts[x] += 1
    return b


def heap_sort(a):
    """
    Unstable, not adaptive, offline, comparisons
    Space: O(n) total, O(1) auxiliary
    Worst time: O(n log n) swaps, comparisons
    Average time: O(n log n) swaps, comparisons
    Best time: O(n log n) swaps, comparisons
    """
    def sink(parent, n):
        while True:
            left = parent * 2 + 1
            if left >= n:
                return
            right = left + 1
            if right >= n:
                child = left
            else:
                child = left if a[left] >= a[right] else right
            if a[parent] >= a[child]:
                return
            a[parent], a[child] = a[child], a[parent]
            parent = child
    n = len(a)
    for i in xrange(n / 2 - 1, -1, -1):
        sink(i, n)
    for i in xrange(n - 1):
        j = n - 1 - i
        a[0], a[j] = a[j], a[0]
        sink(0, n - 1 - i)


def insertion_sort(a):
    """
    Stable, adaptive, online, comparisons
    Space: O(n) total, O(1) auxiliary
    Worst time: O(n^2) swaps, comparisons
    Average time: O(n^2) swaps, comparisons
    Best time: O(1) swaps, O(n) comparisons
    """
    for i in xrange(1, len(a)):
        j = i
        while j > 0 and a[j - 1] > a[j]:
            a[j - 1], a[j] = a[j], a[j - 1]
            j -= 1


def quick_sort(a):
    """
    Not stable, not adaptive, offline, comparisons
    Worst case space complexity: O(n) total, O(log n) auxiliary
    Best time complexity: O(n log n) comparisons
    Average time complexity: O(n log n) comparisons
    Worst time complexity: O(n^2) comparisons
    """


def selection_sort(a):
    """
    Unstable, not adaptive, offline, comparisons
    Space: O(n) total, O(1) auxiliary
    Worst time: O(n) swaps, O(n^2) comparisons
    Average time: O(n) swaps, O(n^2) comparisons
    Best time: O(n) swaps, O(n^2) comparisons
    """
    n = len(a)
    for i in xrange(n - 1):
        k = i
        for j in xrange(i + 1, n):
            if a[j] < a[k]:
                k = j
        if k != i:
            a[i], a[k] = a[k], a[i]


def linear_search(a, x):
    """
    Space: O(n) total, O(1) auxiliary
    Worst time: O(n) comparisons
    Average time: O(n) comparisons
    Best time: O(n) comparisons
    """
    for i in len(a):
        if a[i] == x:
            return i
    return -1


def binary_search(a, x):
    """
    Space: O(n) total, O(1) auxiliary
    Worst time: O(log n) comparisons
    Average time: O(log n) comparisons
    Best time: O(log n) comparisons
    """
    n = len(a)
    low = 0
    high = n - 1
    i = n / 2
    while low <= high:
        if x < a[i]:
            high = i - 1
        elif x > a[i]:
            low = i + 1
        else:
            return i
        i = ((high - low) / 2) + low
    return -1


class ArrayTestCase(unittest.TestCase):
    def _sort(self, f):
        def t(choices, length):
            for c in itertools.product(*([choices] * length)):
                c = list(c)
                self.assertEqual(f(c), sorted(c))
        t([], 0)
        t([0, 1], 1)
        t([0, 1], 2)
        t([0, 1, 2], 3)
    
    def _sort_adapt(self, f):
        def sort(a):
            f(a)
            return a
        self._sort(sort)

    def test_bubble_sort(self):
        self._sort_adapt(bubble_sort)

    def test_counting_sort(self):
        self._sort(lambda a: counting_sort(a, 16))

    def test_heap_sort(self):
        self._sort_adapt(heap_sort)

    def test_insertion_sort(self):
        self._sort_adapt(insertion_sort)

    def test_selection_sort(self):
        self._sort_adapt(selection_sort)


if __name__ == '__main__':
    unittest.main()
