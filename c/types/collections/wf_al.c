#include <stdlib.h>
#include <string.h>
#include "wf_al.h"

struct wf_al
{
	int length;
	int capacity;
	union wf_item *items;
};

struct wf_al *wf_al_create()
{
	struct wf_al *wf_al = malloc(sizeof(struct wf_al));
	if (wf_al == NULL)
	{
		perror("malloc");
	}
	wf_al->items = malloc(sizeof(union wf_item) * 32);
	if (wf_al->items == NULL)
	{
		perror("malloc");
	}
	return wf_al;
}

void wf_al_destroy(struct wf_al *wf_al)
{
	free(wf_al->items);
	free(wf_al);
}

int wf_al_capacity(struct wf_al *wf_al)
{
	return wf_al->capacity;
}

int wf_al_length(struct wf_al *wf_al)
{
	return wf_al->length;
}

static void enlarge(struct wf_al *wf_al)
{
	size_t length = sizeof(union wf_item) * wf_al->capacity;
	union wf_item *items = malloc(length * 2);
	if (items == NULL)
	{
		perror("malloc");
	}
	memcpy(items, wf_al->items, length);
	free(wf_al->items);
	wf_al->items = items;	
}

void wf_al_add(struct wf_al *wf_al, union wf_item item)
{
	if (wf_al->length == wf_al->capacity)
	{
		enlarge(wf_al);
	}
	wf_al->items[wf_al->length] = item;
	++wf_al->length;
}

void wf_al_insert(struct wf_al *wf_al, int index, union wf_item item)
{
	if (wf_al->length == wf_al->capacity)
	{
		enlarge(wf_al);
	}
	if (index < wf_al->length)
	{
		memmove(&(wf_al->items[index]), &(wf_al->items[index + 1]), sizeof(union wf_item) * (wf_al->length - index));
	}
	wf_al->items[index] = item;
}

void wf_al_append(struct wf_al *wf_al_to, struct wf_al *wf_al_from)
{
	int totwf_al_length = wf_al_to->length + wf_al_from->length;
	size_t to_size = sizeof(union wf_item) * wf_al_to->length;
	if (totwf_al_length > wf_al_to->capacity)
	{
		union wf_item *items = malloc(sizeof(union wf_item) * totwf_al_length);
		if (items == NULL)
		{
			perror("malloc");
		}
		memcpy(items, wf_al_to->items, to_size);
		free(wf_al_to->items);
		wf_al_to->items = items;
	}
	memcpy(wf_al_to->items + to_size, wf_al_from->items, sizeof(union wf_item) * wf_al_from->length);
}

union wf_item wf_al_get(struct wf_al *wf_al, int index)
{
	return wf_al->items[index];
}

union wf_item wf_al_first(struct wf_al *wf_al)
{
	return wf_al->items[0];
}

union wf_item wf_al_last(struct wf_al *wf_al)
{
	return wf_al->items[wf_al->length - 1];
}

union wf_item wf_al_remove(struct wf_al *wf_al, int index)
{
	union wf_item item = wf_al->items[index];
	if (wf_al->capacity > 1)
	{
		memmove(&(wf_al->items[index]), &(wf_al->items[index + 1]), sizeof(union wf_item) * (wf_al->capacity - 1));
	}
	return item;
}

void wf_al_resize(struct wf_al *wf_al, int length)
{
	size_t size = sizeof(union wf_item) * length;
	union wf_item *items = malloc(size);
	if (items == NULL)
	{
		perror("malloc");
	}
	memcpy(items, wf_al->items, length < wf_al->length ? size : sizeof(union wf_item) * wf_al->length);
	free(wf_al->items);
	wf_al->items = items;
}

void wf_al_fit(struct wf_al *wf_al)
{
	if (wf_al->length < wf_al->capacity)
	{
		wf_al_resize(wf_al, wf_al->length);
	}
}