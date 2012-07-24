#ifndef WF_BUBBLE_H
#define WF_BUBBLE_H

#include <stdlib.h>

int wf_sort_bubble(void *first_element, size_t element_count, size_t element_size, int (*compare)(const void *left_element, const void *right_element));

#endif