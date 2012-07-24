#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

int myatoi(const char *c)
{
	int i = 0;
	
	while (*c && !isdigit(*c))
	{
		++c;
	}
	
	if (*c == '\0')
	{
		return 0;
	}
	
	while (*c && isdigit(*c))
	{
		i *= 10;
		i += *c - '0';
		++c;
	}
	
	return i;
}

void myitoa(int n, char *str, int length)
{
	
}

int main(void)
{
	int i;
	
	i = myatoi("123456");
	
	printf("%d\n%d\n", i, 123456 % 10);
	
	return EXIT_SUCCESS;
}