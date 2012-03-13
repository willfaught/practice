#ifndef WF_LLS_H
#define WF_LLS_H

#include "wf_common.h"

struct wf_lls
{
	union wf_item wf_item;
	struct wf_lls *next;
};

int wf_lls_length(struct wf_lls *wf_lls);

void wf_lls_add(struct wf_lls *wf_lls, union wf_item wf_item);

void wf_lls_insert(struct wf_lls *wf_lls, int index, union wf_item wf_item);

void wf_lls_append(struct wf_lls *wf_lls_to, struct wf_lls *wf_lls_from);

union wf_item wf_lls_remove(struct wf_lls *wf_lls, int index);

union wf_item wf_lls_get(struct wf_lls *wf_lls, int index);

union wf_item wf_lls_first(struct wf_lls *wf_lls);

union wf_item wf_lls_last(struct wf_lls *wf_lls);

#endif /* WF_LLS_H */