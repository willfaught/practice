#ifndef COMMON_H
#define COMMON_H

struct vertex
{
	float x;
	float y;
	float z;
};

struct polygon
{
	int a, b, c;
};

struct mapcoord
{
	float u;
	float v;
};

#define MAX_VERTICES 8000
#define MAX_POLYGONS 8000

struct mesh
{
	struct vertex vertex[MAX_VERTICES];
	struct polygon polygon[MAX_POLYGONS];
	struct mapcoord mapcoord[MAX_VERTICES];
	int id_texture;
	char name[20];
	unsigned short vertices_qty;
	unsigned short polygons_qty;
};

#endif /* COMMON_H */