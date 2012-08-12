#include <stdlib.h>
#include <GLUT/glut.h>
#include "common.h"

struct mesh object;

struct mesh cube =
{
    {
        { -10,-10, 10 },
        { 10, -10, 10 },
        { 10, -10, -10 },
        { -10, -10, -10 },
        { -10, 10, 10 },
        { 10, 10, 10 },
        { 10, 10, -10 },
        { -10, 10, -10 }
    },
    {
        {0, 1, 4},
        {1, 5, 4},
        {1, 2, 5},
        {2, 6, 5},
        {2, 3, 6},
        {3, 7, 6},
        {3, 0, 7},
        {0, 4, 7},
        {4, 5, 7},
        {5, 6, 7},
        {3, 2, 0},
        {2, 1, 0},
    },
	{
		{0.0, 0.0},
		{1.0, 0.0},
		{1.0, 0.0},
		{0.0, 0.0},
		{0.0, 1.0},
		{1.0, 1.0},
		{1.0, 1.0},
		{0.0, 1.0}
	},
	0
};

static float screen_width;
static float screen_height;
static float rotation_x_increment = 0.0f;
static float rotation_y_increment = 0.0f;
static float rotation_z_increment = 0.0f;
static int filling = 0;
static float rotation_x = 0.0f;
static float rotation_y = 0.0f;
static float rotation_z = 0.0f;

int bitmap_read(char *filename);

void init(void)
{
	screen_width = 640.0f;
	screen_height = 480.0f;
	glClearColor(0.0, 0.0, 0.2, 0.0);
	glShadeModel(GL_SMOOTH);
	glViewport(0, 0, screen_width, screen_height);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f, (GLfloat)screen_width / (GLfloat)screen_height, 1.0f, 1000.0f);
	glEnable(GL_DEPTH_TEST);
	glPolygonMode (GL_FRONT_AND_BACK, GL_FILL);
	
	glEnable(GL_TEXTURE_2D);
	cube.id_texture = bitmap_read("tile.bmp");
	if (cube.id_texture == -1)
	{
	   exit(EXIT_FAILURE);
	}
}

void resize(int width, int height)
{
	screen_width = width;
	screen_height = height;
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glViewport(0, 0, screen_width, screen_height);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f, (GLfloat)screen_width / (GLfloat)screen_height, 1.0f, 1000.0f);
	glutPostRedisplay();
}

void keyboard(unsigned char key, int x, int y)
{
	switch (key)
	{
		case ' ':
			rotation_x_increment = 0;
			rotation_y_increment = 0;
			rotation_z_increment = 0;
			break;
		case 'r':
		case 'R':
			if (filling == 0)
			{
				glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
				filling = 1;
			} 
			else 
			{
				glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
				filling = 0;
			}
			break;
	}
}

void keyboard_s(int key, int x, int y)
{
	switch (key)
	{
		case GLUT_KEY_UP:
			rotation_x_increment = rotation_x_increment + 0.005;
			break;
		case GLUT_KEY_DOWN:
			rotation_x_increment = rotation_x_increment - 0.005;
			break;
		case GLUT_KEY_LEFT:
			rotation_y_increment = rotation_y_increment + 0.005;
			break;
		case GLUT_KEY_RIGHT:
			rotation_y_increment = rotation_y_increment -0.005;
			break;
	}
}

void display(void)
{
	int l_index;
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0.0,0.0,-50);
	rotation_x = rotation_x + rotation_x_increment;
	rotation_y = rotation_y + rotation_y_increment;
	rotation_z = rotation_z + rotation_z_increment;
	if (rotation_x > 359) rotation_x = 0;
	if (rotation_y > 359) rotation_y = 0;
	if (rotation_z > 359) rotation_z = 0;
	glRotatef(rotation_x, 1.0, 0.0, 0.0);
	glRotatef(rotation_y, 0.0, 1.0, 0.0);
	glRotatef(rotation_z, 0.0, 0.0, 1.0);
	
	/*glBegin(GL_TRIANGLES);
	for (l_index=0;l_index<12;l_index++)
	{
		glColor3f(1.0, 0.0, 0.0);
		glVertex3f(cube.vertex[cube.polygon[l_index].a].x, cube.vertex[cube.polygon[l_index].a].y, cube.vertex[cube.polygon[l_index].a].z);
		glColor3f(0.0, 1.0, 0.0);
		glVertex3f(cube.vertex[cube.polygon[l_index].b].x, cube.vertex[cube.polygon[l_index].b].y, cube.vertex[cube.polygon[l_index].b].z);
		glColor3f(0.0, 0.0, 1.0);
		glVertex3f(cube.vertex[cube.polygon[l_index].c].x, cube.vertex[cube.polygon[l_index].c].y, cube.vertex[cube.polygon[l_index].c].z);
	}
	glEnd();*/
	
	glBindTexture(GL_TEXTURE_2D, cube.id_texture);

	glBegin(GL_TRIANGLES); 
	for (l_index=0;l_index<12;l_index++)
	{
		glTexCoord2f( cube.mapcoord[ cube.polygon[l_index].a ].u, cube.mapcoord[ cube.polygon[l_index].a ].v);
		glVertex3f( cube.vertex[ cube.polygon[l_index].a ].x,cube.vertex[ cube.polygon[l_index].a ].y,cube.vertex[ cube.polygon[l_index].a ].z);

		glTexCoord2f( cube.mapcoord[ cube.polygon[l_index].b ].u,cube.mapcoord[ cube.polygon[l_index].b ].v);
		glVertex3f( cube.vertex[ cube.polygon[l_index].b ].x,cube.vertex[ cube.polygon[l_index].b ].y,cube.vertex[ cube.polygon[l_index].b ].z);

		glTexCoord2f( cube.mapcoord[ cube.polygon[l_index].c ].u,cube.mapcoord[ cube.polygon[l_index].c ].v);
		glVertex3f( cube.vertex[ cube.polygon[l_index].c ].x,cube.vertex[ cube.polygon[l_index].c ].y,cube.vertex[ cube.polygon[l_index].c ].z);
	}
	glEnd();
	
	glFlush();
	glutSwapBuffers();
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(screen_width,screen_height);
	glutInitWindowPosition(0,0);
	glutCreateWindow("www.spacesimulator.net - 3d engine tutorials: Tutorial 2");
	glutDisplayFunc(display);
	glutIdleFunc(display);
	glutReshapeFunc(resize);
	glutKeyboardFunc(keyboard);
	glutSpecialFunc(keyboard_s);
	init();
	glutMainLoop();
	return EXIT_SUCCESS;
}
