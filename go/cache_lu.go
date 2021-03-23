package main

type luCache struct {
	capacity int
	history  *lruCacheList
	items    map[int]int
	nodes    map[int]*lruCacheListNode
}

func (c *luCache) bump(key int) {
	if n, ok := c.nodes[key]; ok {
		n.uses++
		n.sink()
	} else {
		n = &lruCacheListNode{key: key, uses: 1}
		c.nodes[key] = n
		c.history.prepend(n)
	}
}

func (c *luCache) set(key, value int) {
	if _, ok := c.items[key]; !ok && len(c.items) == c.capacity {
		var n = c.history.dequeue()

		delete(c.items, n.key)
		delete(c.nodes, n.key)
	}

	c.items[key] = value
	c.bump(key)
}

func (c *luCache) get(key int) (int, bool) {
	var value, ok = c.items[key]

	if ok {
		c.bump(key)
	}

	return value, ok
}
