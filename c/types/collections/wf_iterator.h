#ifndef WF_ITERATOR_H
#define WF_ITERATOR_H

struct wf_iterator
{
	
};

int wf_iterator_more(struct wf_iterator *wf_iterator)



struct wf_iterator *i = wf_lld_iterator(mylist);
while (wf_iterator_more(i))
{
	union wf_item wf_item = wf_iterator_next(i);
	...
}

#endif /* WF_ITERATOR_H */