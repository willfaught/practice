#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <GLUT/glut.h>
#include "common.h"

struct bitmap_magic
{
  	unsigned char magic[2];
};
 
struct bitmap_header
{
	unsigned int filesz;
	unsigned short creator1;
	unsigned short creator2;
	unsigned int bmp_offset;
};

struct BITMAPINFOHEADER {
	unsigned int header_sz;
	int width;
	int height;
	unsigned short nplanes;
	unsigned short bitspp;
	unsigned int compress_type;
	unsigned int bmp_bytesz;
	int hres;
	int vres;
	unsigned int ncolors;
	unsigned int nimpcolors;
};

struct RGBTRIPLE {
  unsigned char rgbtBlue;
  unsigned char rgbtGreen;
  unsigned char rgbtRed;
};

static int num_texture = 0;

int bitmap_read(char *filename)
{
	unsigned char *l_texture;
	int i, j=0;
	FILE *l_file;
	struct bitmap_magic magic;
	struct bitmap_header fileheader; 
	struct BITMAPINFOHEADER infoheader;
	struct RGBTRIPLE rgb;
	
	if ((l_file = fopen(filename, "rb")) == NULL)
		return (-1);
	
	fread(&magic, sizeof(magic), 1, l_file);
	fread(&fileheader, sizeof(fileheader), 1, l_file);
	fread(&infoheader, sizeof(infoheader), 1, l_file);
	
	l_texture = malloc(infoheader.width * infoheader.height * 4);
	memset(l_texture, 0, infoheader.width * infoheader.height * 4);
	
	for (i=0; i < infoheader.width * infoheader.height; i++)
	{ 
		fread(&rgb, sizeof(rgb), 1, l_file); 

		l_texture[j+0] = rgb.rgbtRed;
		l_texture[j+1] = rgb.rgbtRed;
		l_texture[j+2] = rgb.rgbtBlue;
		l_texture[j+3] = 255;
		j += 4;
	}
	
	fclose(l_file);
	
	glBindTexture(GL_TEXTURE_2D, num_texture);
	
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
	
	glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
	glTexImage2D(GL_TEXTURE_2D, 0, 4, infoheader.width, infoheader.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, l_texture);
	
	gluBuild2DMipmaps(GL_TEXTURE_2D, 4, infoheader.width, infoheader.height, GL_RGBA, GL_UNSIGNED_BYTE, l_texture);
	
	free(l_texture);
	
	return num_texture++;
}

int threeds_read(struct mesh *p_object, char *filename)
{
	int i;
	FILE *l_file;
	unsigned short l_chunk_id;
	unsigned int l_chunk_length;
	unsigned char l_char;
	unsigned short l_qty;
	unsigned short l_face_flags;
	
	if ((l_file=fopen (filename, "rb"))== NULL) return 0;
	while (!feof(l_file))
	{
		fread (&l_chunk_id, 2, 1, l_file);
		fread (&l_chunk_length, 4, 1, l_file);
		switch (l_chunk_id)
		{
			case 0x4d4d:
				/* main */
				break;
			case 0x3d3d:
				/* 3d editor */
				break;
			case 0x4000:
				/* object */
				i=0;
				do
				{
					fread (&l_char, 1, 1, l_file);
					p_object->name[i]=l_char;
					i++;
				}
				while (l_char != '\0' && i<20);
				break;
			case 0x4100:
				/* triangular mesh */
				break;
			case 0x4110:
				/* vertices */
				fread (&l_qty, sizeof (unsigned short), 1, l_file);
				p_object->vertices_qty = l_qty;
				printf("Number of vertices: %d\n",l_qty);
				for (i=0; i<l_qty; i++)
				{
					fread (&p_object->vertex[i].x, sizeof(float), 1, l_file);
					fread (&p_object->vertex[i].y, sizeof(float), 1, l_file);
					fread (&p_object->vertex[i].z, sizeof(float), 1, l_file);
				}
				break;
			case 0x4120:
				/* faces */
				fread (&l_qty, sizeof (unsigned short), 1, l_file);
				p_object->polygons_qty = l_qty;
				printf("Number of polygons: %d\n",l_qty); 
				for (i=0; i<l_qty; i++)
				{
					fread (&p_object->polygon[i].a, sizeof (unsigned short), 1, l_file);
					fread (&p_object->polygon[i].b, sizeof (unsigned short), 1, l_file);
					fread (&p_object->polygon[i].c, sizeof (unsigned short), 1, l_file);
					fread (&l_face_flags, sizeof (unsigned short), 1, l_file);
				}
				break;
			case 0x4140:
				/* mapping */
				fread (&l_qty, sizeof (unsigned short), 1, l_file);
				for (i=0; i<l_qty; i++)
				{
					fread (&p_object->mapcoord[i].u, sizeof (float), 1, l_file);
					fread (&p_object->mapcoord[i].v, sizeof (float), 1, l_file);
				}
				break;
			default:
				fseek(l_file, l_chunk_length-6, SEEK_CUR);
		}
	}
	fclose (l_file);
	return (1);
}