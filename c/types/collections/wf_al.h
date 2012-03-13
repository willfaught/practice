#ifndef WF_AL_H
#define WF_AL_H

#include "wf_common.h"

struct wf_al;

struct wf_al *wf_al_create(void);

void wf_al_destroy(struct wf_al *wf_al);

void wf_al_add(struct wf_al *wf_al, union wf_item item);

int wf_al_capacity(struct wf_al *wf_al);

int wf_al_size(struct wf_al *wf_al);

void wf_al_insert(struct wf_al *wf_al, int index, union wf_item item);

void wf_al_append(struct wf_al *wf_al_to, struct wf_al *wf_al_from);

union wf_item wf_al_get(struct wf_al *wf_al, int index);

union wf_item wf_al_first(struct wf_al *wf_al);

union wf_item wf_al_last(struct wf_al *wf_al);

union wf_item wf_al_remove(struct wf_al *wf_al, int index);

void wf_al_resize(struct wf_al *wf_al, int length);

void wf_al_fit(struct wf_al *wf_al);

#endif /* WF_AL_H */