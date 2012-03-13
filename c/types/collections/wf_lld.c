#include <stdlib.h>
#include <string.h>
#include "wf_lld.h"

struct wf_lld *(wf_lld_create)(void)
{
	struct wf_lld *new = malloc(sizeof(struct wf_lld));
	if (new == NULL)
	{
		perror("malloc");
		exit(EXIT_FAILURE);
	}
	new->first = NULL;
	new->length = 0;
	return new;
}

void (wf_lld_destroy)(struct wf_lld *wf_lld)
{
	struct wf_lld_node *next;
	struct wf_lld_node *current = wf_lld->first;
	while (current)
	{
		next = current->next;
		free(current);
		current = next;
	}
	free(wf_lld);
}

int (wf_lld_length)(struct wf_lld *wf_lld)
{
	return wf_lld->length;
}

void (wf_lld_add)(struct wf_lld *wf_lld, union wf_item wf_item)
{
	struct wf_lld_node *new = malloc(sizeof(struct wf_lld_node));
	if (new == NULL)
	{
		perror("malloc");
		exit(EXIT_FAILURE);
	}
	new->wf_item = wf_item;
	if (wf_lld->length == 0)
	{
		new->previous = new->next = new;
		wf_lld->first = new;
	}
	else
	{
		new->previous = wf_lld->first->previous;
		new->next = wf_lld->first;
		wf_lld->first->previous->next = new;
		wf_lld->first->previous = new;
	}
	++wf_lld->length;
}

void (wf_lld_insert)(struct wf_lld *wf_lld, int index, union wf_item wf_item)
{
	int i;
	struct wf_lld_node *node = wf_lld->first;
	for (i = 0; i < index; ++i)
	{
		node = node->next;
	}
	struct wf_lld_node *new = malloc(sizeof(struct wf_lld_node));
	if (new == NULL)
	{
		perror("malloc");
		exit(EXIT_FAILURE);
	}
	new->wf_item = wf_item;
	new->previous = node->previous;
	new->next = node;
	node->previous->next = new;
	node->previous = new;
	++wf_lld->length;
}

void (wf_lld_append)(struct wf_lld *wf_lld_to, struct wf_lld *wf_lld_from)
{
	if (wf_lld_to->length == 0)
	{
		wf_lld_to->first = wf_lld_from->first;
	}
	else
	{
		wf_lld_to->first->previous->next = wf_lld_from->first;
		wf_lld_to->first->previous = wf_lld_from->first->previous;
	}
	wf_lld_to->length += wf_lld_from->length;
}

union wf_item (wf_lld_remove)(struct wf_lld *wf_lld, int index)
{
	int i;
	union wf_item wf_item;
	struct wf_lld_node *node = wf_lld->first;
	for (i = 0; i < index; ++i)
	{
		node = node->next;
	}
	wf_item = node->wf_item;
	node->previous->next = node->next;
	node->next->previous = node->previous;
	free(node);
	return wf_item;
}

union wf_item (wf_lld_get)(struct wf_lld *wf_lld, int index)
{
	int i;
	struct wf_lld_node *node = wf_lld->first;
	for (i = 0; i < index; ++i)
	{
		node = node->next;
	}
	return node->wf_item;
}

union wf_item (wf_lld_first)(struct wf_lld *wf_lld)
{
	return wf_lld->first->wf_item;
}

union wf_item (wf_lld_last)(struct wf_lld *wf_lld)
{
	return wf_lld->first->previous->wf_item;
}