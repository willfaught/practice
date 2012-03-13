#include <stdlib.h>
#include <string.h>
#include "wf_lls.h"

int wf_lls_length(struct wf_lls *wf_lls)
{
	int count;
	for (count = 0; wf_lls != NULL; wf_lls = wf_lls->next, ++count);
	return count;
}

void wf_lls_add(struct wf_lls *wf_lls, union wf_item wf_item)
{
	struct wf_lls *new = malloc(sizeof(struct wf_lls));
	if (new == NULL)
	{
		perror("malloc");
		exit(EXIT_FAILURE);
	}
	new->wf_item = wf_item;
	new->next = NULL;
	while (wf_lls->next != NULL)
	{
		wf_lls = wf_lls->next;
	}
	wf_lls->next = new;
}

void wf_lls_insert(struct wf_lls *wf_lls, int index, union wf_item wf_item)
{
	int i;
	for (i = 0; i < index - 1; wf_lls = wf_lls->next, ++i);
	struct wf_lls *new = malloc(sizeof(struct wf_lls));
	if (new == NULL)
	{
		perror("malloc");
		exit(EXIT_FAILURE);
	}
	new->wf_item = wf_item;
	new->next = wf_lls->next;
	wf_lls->next = new;
}

void wf_lls_append(struct wf_lls *wf_lls_to, struct wf_lls *wf_lls_from)
{
	while (wf_lls_to->next != NULL)
	{
		wf_lls_to = wf_lls_to->next;
	}
	wf_lls_to->next = wf_lls_from;
}

union wf_item wf_lls_remove(struct wf_lls *wf_lls, int index)
{
	int i;
	union wf_item wf_item;
	struct wf_lls *old;
	for (i = 0; i < index - 1; wf_lls = wf_lls->next, ++i);
	old = wf_lls->next;
	wf_item = old->wf_item;
	wf_lls->next = old->next;
	free(old);
	return wf_item;
}

union wf_item wf_lls_get(struct wf_lls *wf_lls, int index)
{
	int i;
	for (i = 0; i < index; wf_lls = wf_lls->next, ++i);
	return wf_lls->wf_item;
}

union wf_item wf_lls_first(struct wf_lls *wf_lls)
{
	return wf_lls->wf_item;
}

union wf_item wf_lls_last(struct wf_lls *wf_lls)
{
	while (wf_lls->next != NULL)
	{
		wf_lls = wf_lls->next;
	}
	return wf_lls->wf_item;
}