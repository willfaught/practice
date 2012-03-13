#include <math.h>
#include "vector.h"

float vector_length(struct vector *vector)
{
	return (float)sqrt(vector->x * vector->x + vector->y * vector->y + vector->z * vector->z);
}

void vector_normalize(struct vector *vector)
{
	float length = vector_length(vector);
	if (length)
	{
		vector->x /= length;
		vector->y /= length;
		vector->z /= length;
	}
}

float vector_scalarproduct(struct vector *vector1, struct vector *vector2)
{
	return vector1->x * vector2->x + vector1->y * vector2->y + vector1->z * vector2->z;
}

void vector_dotproduct(struct vector *vector1, struct vector *vector2, struct vector *vector3)
{
	vector3->x = vector1->y * vector2->z - vector1->z * vector2->y;
	vector3->y = vector1->z * vector2->x - vector1->x * vector2->z;
	vector3->z = vector1->x * vector2->y - vector1->y * vector2->x;
}