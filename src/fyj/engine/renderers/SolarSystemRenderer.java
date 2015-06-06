/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-4
 *
 */
package fyj.engine.renderers;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import fyj.engine.FyjEngine;
import fyj.engine.core.TextureEngine;
import fyj.engine.objprimitives.Sphere;
import fyj.engine.utils.FreeGLUT;

public class SolarSystemRenderer implements CommonRenderer {
	private float whiteLight[] = { 0.2f, 0.2f, 0.2f, 1.0f };
	private float sourceLight[] = { 0.8f, 0.8f, 0.8f, 1.0f };
	private float lightPos[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	
	private Sphere sun = null;
	private Sphere earth = null;
	private Sphere moon = null;

	
	
	// Earth and Moon angle of revolution

	private float fMoonRot = 0.0f;

	private float fEarthRot = 0.0f;

	public SolarSystemRenderer(Context ctx) {

		sun = new Sphere(15.0f, 25, 10, false);
		earth = new Sphere(10.0f, 25, 10, false);
		moon = new Sphere(6.0f, 25, 10, false);
		
	}

	@Override
	public void draw(GL10 gl) {

		// Clear the window with current clearing color

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Save the matrix state and do the rotations

		gl.glMatrixMode(GL10.GL_MODELVIEW);

		gl.glPushMatrix();

		// Translate the whole scene out and into view

		gl.glTranslatef(0.0f, 0.0f, -300.0f);

		// Set material color, Yellow
		// Sun

		gl.glDisable(GL10.GL_LIGHTING);

		gl.glColor4f(1, 1, 0, 1);

//		sun.draw(gl);
		FreeGLUT.glutSolidSphere(gl, 15.0f, 25, 10);

		gl.glEnable(GL10.GL_LIGHTING);

		// Move the light after we draw the sun!

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);

		// Rotate coordinate system

		gl.glRotatef(fEarthRot, 0.0f, 1.0f, 0.0f);

		// Draw the Earth

		gl.glColor4f(0, 0, 1, 1);

		gl.glTranslatef(105.0f, 0.0f, 0.0f);

//		earth.draw(gl);
		FreeGLUT.glutSolidSphere(gl, 10.0f, 25, 10);

		// Rotate from Earth based coordinates and draw Moon

		gl.glColor4f(0.78f, 0.78f, 0.78f, 1);

		gl.glRotatef(fMoonRot, 0.0f, 1.0f, 0.0f);

		gl.glTranslatef(30.0f, 0.0f, 0.0f);

		fMoonRot += 15.0f;

		if (fMoonRot > 360.0f)

			fMoonRot = 0.0f;

//		moon.draw(gl);
		FreeGLUT.glutSolidSphere(gl, 5.0f, 25, 10);

		// Restore the matrix state

		gl.glPopMatrix(); // Modelview matrix

		// Step earth orbit 5 degrees

		fEarthRot += 5.0f;

		if (fEarthRot > 360.0f)

			fEarthRot = 0.0f;

	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		float fAspect;

		// Prevent a divide by zero

		if (h == 0)

			h = 1;

		// Set Viewport to window dimensions

		gl.glViewport(0, 0, w, h);

		// Calculate aspect ratio of the window

		fAspect = (float) w / (float) h;

		// Set the perspective coordinate system

		gl.glMatrixMode(GL10.GL_PROJECTION);

		gl.glLoadIdentity();

		// field of view of 45 degrees, near and far planes 1.0 and 425

		GLU.gluPerspective(gl, 45.0f, fAspect, 1.0f, 425.0f);

		// Modelview matrix reset

		gl.glMatrixMode(GL10.GL_MODELVIEW);

		gl.glLoadIdentity();

	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// Light values and coordinates

		gl.glEnable(GL10.GL_DEPTH_TEST); // Hidden surface removal

		gl.glFrontFace(GL10.GL_CCW); // Counter clock-wise polygons face out

		gl.glEnable(GL10.GL_CULL_FACE); // Do not calculate inside of jet

		// Enable lighting

		gl.glEnable(GL10.GL_LIGHTING);

		// Setup and enable light 0

		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, whiteLight, 0);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, sourceLight, 0);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);

		gl.glEnable(GL10.GL_LIGHT0);

		// Enable color tracking

		gl.glEnable(GL10.GL_COLOR_MATERIAL);

		// Set Material properties to follow glColor values

		// ((GLES10Ext)gl).glColorMaterial(GL10.GL_FRONT,
		// GL10.GL_AMBIENT_AND_DIFFUSE);

		// Black blue background

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

	}

	@Override
	public boolean keyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMenuItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
	}
}
