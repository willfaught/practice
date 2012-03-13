#ifndef WF_LLD_H
#define WF_LLD_H

#include <stdio.h>
#include "wf_common.h"

#define wf_lld_first(wf_lld) (wf_lld->first->item)

#define wf_lld_last(wf_lld) (wf_lld->first->previous->item)

#define wf_lld_length(wf_lld) (wf_lld->length)

struct wf_lld_node
{
	union wf_item item;
	struct wf_lld_node *previous;
	struct wf_lld_node *next;
};

struct wf_lld
{
	struct wf_lld_node *first;
	int length;
};

void (wf_lld_add)(struct wf_lld *list, int index, union wf_item item);

void (wf_lld_add_first)(struct wf_lld *list, union wf_item item);

void (wf_lld_add_last)(struct wf_lld *list, union wf_item item);

void (wf_lld_append)(struct wf_lld *to, struct wf_lld *from);

void (wf_lld_clear)(struct wf_lld *list);

void (wf_lld_destroy)(struct wf_lld *list)

void (wf_lld_intersect)(struct wf_lld *left, struct wf_lld *right);

void (wf_lld_print)(struct wf_lld *list, FILE *file);

void (wf_lld_remove_range)(struct wf_lld *list, int index, int length);

void (wf_lld_reverse)(struct wf_lld *list);

void (wf_lld_rotate_forward)(struct wf_lld *list);

void (wf_lld_rotate_backward)(struct wf_lld *list);

void (wf_lld_sort)(struct wf_lld *list, wf_item_comparison compare);

void (wf_lld_subtract)(struct wf_lld *items, struct wf_lld *right);

void (wf_lld_set)(struct wf_lld *list, int index, union wf_item item);

void (wf_lld_set_first)(struct wf_lld *list, union wf_item item);

void (wf_lld_set_last)(struct wf_lld *list, union wf_item item);

void (wf_lld_sort)(struct wf_lld *list, union wf_item item);

void (wf_lld_union)(struct wf_lld *left, struct wf_lld *right);

void (wf_lld_unique)(struct wf_lld *list, enum wf_item_type type);

int (wf_lld_empty)(struct wf_lld *list);

int (wf_lld_contains)(struct wf_lld *list, union wf_item item);

int (wf_lld_contains_all)(struct wf_lld *left, struct wf_lld *right);

int (wf_lld_equal)(struct wf_lld *left, struct wf_lld *right, enum wf_item_type type);

int (wf_lld_index)(struct wf_lld *list, union wf_item item, enum wf_item_type type);

int (wf_lld_length)(struct wf_lld *list);

union wf_item (wf_lld_get)(struct wf_lld *list, int index);

union wf_item (wf_lld_get_first)(struct wf_lld *list);

union wf_item (wf_lld_get_last)(struct wf_lld *list);

union wf_item (wf_lld_remove)(struct wf_lld *list, int index);

union wf_item (wf_lld_remove_first)(struct wf_lld *list);

union wf_item (wf_lld_remove_last)(struct wf_lld *list);

union wf_item *(wf_lld_array)(struct wf_lld *list);

struct wf_lld *(wf_lld_clone)(struct wf_lld *list);

struct wf_lld *(wf_lld_create)(void);

struct wf_lld *(wf_lld_sublist)(struct wf_lld *list, int index, int length);

struct wf_iterator *(wf_lld_iterator)(struct wf_lld *list);

#endif /* WF_LLD_H */