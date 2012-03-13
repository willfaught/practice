#include <stdio.h>
#include <stdlib.h>

void test(int condition)
{
	if (!condition)
	{
		abort();
	}
}