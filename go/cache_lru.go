package main

type lruCacheListNode struct {
	key, uses  int
	prev, next *lruCacheListNode
}

func (n *lruCacheListNode) insertAfter(prev *lruCacheListNode) {
	n.link(prev, prev.next)
}

func (n *lruCacheListNode) insertBefore(next *lruCacheListNode) {
	n.link(next.prev, next)
}

func (n *lruCacheListNode) link(prev, next *lruCacheListNode) {
	n.prev, n.next = prev, next

	if prev != n {
		prev.next = n
	}

	if next != n {
		next.prev = n
	}
}

func (n *lruCacheListNode) sink() {
	for x, y := n, n.next; y != n && x.uses < y.uses; x, y = y, y.next {
		x.unlink()
		x.link(y, y.next)
	}
}

func (n *lruCacheListNode) unlink() {
	if n.prev == n {
		n.prev = nil
	} else {
		n.prev.next = n.next
	}

	if n.next == n {
		n.next = nil
	} else {
		n.next.prev = n.prev
	}
}

type lruCacheList struct {
	head *lruCacheListNode
}

func (l *lruCacheList) dequeue() *lruCacheListNode {
	var first = l.head

	l.remove(first)

	return first
}

func (l *lruCacheList) pop() *lruCacheListNode {
	var last = l.head.prev

	l.remove(last)

	return last
}

func (l *lruCacheList) prepend(first *lruCacheListNode) {
	if l.head == nil {
		first.prev = first
		first.next = first
	} else {
		first.insertBefore(l.head)
	}

	l.head = first
}

func (l *lruCacheList) remove(n *lruCacheListNode) {
	n.unlink()

	if n == l.head {
		l.head = l.head.next
	} else {
		n.unlink()
	}
}

type lruCache struct {
	capacity int
	history  *lruCacheList
	items    map[int]int
	nodes    map[int]*lruCacheListNode
}

func (c *lruCache) bump(key int) {
	var n, ok = c.nodes[key]

	if ok {
		c.history.remove(n)
	} else {
		n = &lruCacheListNode{key: key}
		c.nodes[key] = n
	}

	c.history.prepend(n)
}

func (c *lruCache) set(key, value int) {
	if _, ok := c.items[key]; !ok && len(c.items) == c.capacity {
		var n = c.history.pop()

		delete(c.items, n.key)
		delete(c.nodes, n.key)
	}

	c.items[key] = value
	c.bump(key)
}

func (c *lruCache) get(key int) (int, bool) {
	var value, ok = c.items[key]

	if ok {
		c.bump(key)
	}

	return value, ok
}
