#ifndef VECTOR_H
#define VECTOR_H

struct vector
{
	float x;
	float y;
	float z;
};

float vector_length(struct vector *vector);

void vector_normalize(struct vector *vector);

float vector_scalarproduct(struct vector *vector1, struct vector *vector2);

void vector_dotproduct(struct vector *vector1, struct vector *vector2, struct vector *vector3);

#endif /* VECTOR_H */