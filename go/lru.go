type LRU struct {
	head, tail *list
	links      map[int]*list
	capacity   int
	vals       map[int]int
}

type list struct {
	key        int
	prev, next *list
}

func (l *list) rm() {
	if l.prev != nil {
		l.prev.next = l.next
	}

	if l.next != nil {
		l.next.prev = l.prev
	}
}

func (l *list) prepend(head *list) {
	l.next = head
	head.prev = l
}

func (l *list) append(tail *list) {
	tail.next = l
	l.prev = tail
}

func (lru *LRU) get(k int) int {
	return lru.vals[k]
}

func (lru *LRU) set(k, v int) {
	if _, ok := lru.vals[k]; ok {
		if link := lru.links[k]; link != lru.head {
			link.rm()
			link.prepend(lru.head)
			lru.head = link
		}
	} else {
		if len(lru.vals) == lru.capacity {

		}

		var link = &list{prev: lru.tail}

		link.append(lru.tail)
		lru.tail = link
	}

	lru.vals[k] = v
}
