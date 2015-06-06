/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-9
 *
 */
package fyj.engine.renderers;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import fyj.engine.utils.FreeGLUT;

public class SpotLightRendererer implements CommonRenderer {

	// Rotation amounts
	private float xRot = 0.0f;
	private float yRot = 0.0f;

	private float lightPos[] = { 0.0f, 0.0f, 75.0f, 1.0f };
	private float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float specref[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float ambientLight[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	private float spotDir[] = { 0.0f, 0.0f, -1.0f };

	enum Flags {
		// Flags for effects
		MODE_FLAT(1), MODE_SMOOTH(2), MODE_VERYLOW(3), MODE_MEDIUM(4), MODE_VERYHIGH(
				5);

		private int value;

		private Flags(int $value) {
			value = $value;
		}

		public int value() {
			return value;
		}
	}

	private int iShade = Flags.MODE_FLAT.value();
	private int iTess = Flags.MODE_VERYLOW.value();

	public SpotLightRendererer(Context ctx) {

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fyj.engine.renderers.CommonRenderer#draw(javax.microedition.khronos.opengles
	 * .GL10)
	 */
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		if (iShade == Flags.MODE_FLAT.value())
			gl.glShadeModel(GL10.GL_FLAT);
		else
			// iShade = MODE_SMOOTH;
			gl.glShadeModel(GL10.GL_SMOOTH);

		// Clear the window with current clearing color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// First place the light
		// Save the coordinate transformation
		gl.glPushMatrix();
		// Rotate coordinate system
		gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(xRot, 1.0f, 0.0f, 0.0f);

		// Specify new position and direction in rotated coords.
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, spotDir, 0);

		// Draw a red cone to enclose the light source
		gl.glColor4f(1, 0, 0, 1);

		// Translate origin to move the cone out to where the light
		// is positioned.
		gl.glTranslatef(lightPos[0], lightPos[1], lightPos[2]);
		FreeGLUT.glutSolidCone(gl, 4.0f, 6.0f, 15, 15);

		// Turn off lighting and specify a bright yellow sphere
		gl.glDisable(GL10.GL_LIGHTING);
		gl.glColor4f(1, 1, 0, 1);
		FreeGLUT.glutSolidSphere(gl, 3.0f, 15, 15);
		gl.glEnable(GL10.GL_LIGHTING);

		// Restore coordinate transformations
		gl.glPopMatrix();

		// Set material color and draw a sphere in the middle
		gl.glColor4f(0, 0, 1, 1);

		if (iTess == Flags.MODE_VERYLOW.value())
			FreeGLUT.glutSolidSphere(gl, 30.0f, 7, 7);
		else if (iTess == Flags.MODE_MEDIUM.value())
			FreeGLUT.glutSolidSphere(gl, 30.0f, 15, 15);
		else
			// iTess = MODE_MEDIUM;
			FreeGLUT.glutSolidSphere(gl, 30.0f, 50, 50);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fyj.engine.renderers.CommonRenderer#resize(javax.microedition.khronos
	 * .opengles.GL10, int, int)
	 */
	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		float fAspect;

		// Prevent a divide by zero
		if (h == 0)
			h = 1;

		// Set Viewport to window dimensions
		gl.glViewport(0, 0, w, h);

		// Reset coordinate system
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		// Establish viewing volume
		fAspect = (float) w / (float) h;
		GLU.gluPerspective(gl, 35.0f, fAspect, 1.0f, 500.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -250.0f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fyj.engine.renderers.CommonRenderer#init(javax.microedition.khronos.opengles
	 * .GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// Light values and coordinates

		gl.glEnable(GL10.GL_DEPTH_TEST); // Hidden surface removal
		gl.glFrontFace(GL10.GL_CCW); // Counter clock-wise polygons face out
		gl.glEnable(GL10.GL_CULL_FACE); // Do not try to display the back sides

		// Enable lighting
		gl.glEnable(GL10.GL_LIGHTING);

		// Setup and enable light 0
		// Supply a slight ambient light so the objects can be seen
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);

		// The light is composed of just a diffuse and specular components
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, ambientLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specular, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);

		// Specific spot effects
		// Cut off angle is 60 degrees
		gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 50.0f);

		// Enable this light in particular
		gl.glEnable(GL10.GL_LIGHT0);

		// Enable color tracking
		gl.glEnable(GL10.GL_COLOR_MATERIAL);

		// All materials hereafter have full specular reflectivity
		// with a high shine
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, specref, 0);
		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 128);

		// Black background
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fyj.engine.renderers.CommonRenderer#keyEvent(android.view.KeyEvent)
	 */
	@Override
	public boolean keyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				yRot -= 5.0f;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				yRot += 5.0f;
				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				xRot -= 5.0f;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				xRot += 5.0f;
				break;
			default:
				return false;
			}
			if (xRot > 356.0f)
				xRot = 0.0f;
			if (xRot < -1.0f)
				xRot = 355.0f;
			if (yRot > 356.0f)
				yRot = 0.0f;
			if (yRot < -1.0f)
				yRot = 355.0f;
			return true;
		}
		return false;
	}

	@Override
	public void createMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, Menu.NONE, "MODE_FLAT");
		menu.add(0, 2, Menu.NONE, "MODE_SMOOTH");
		menu.add(0, 3, Menu.NONE, "MODE_VERYLOW");
		menu.add(0, 4, Menu.NONE, "MODE_MEDIUM");
		menu.add(0, 5, Menu.NONE, "MODE_VERYHIGH");
	}

	@Override
	public void onMenuItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			iShade = Flags.MODE_FLAT.value();
			break;

		case 2:
			iShade = Flags.MODE_SMOOTH.value();
			break;

		case 3:
			iTess = Flags.MODE_VERYLOW.value();
			break;

		case 4:
			iTess = Flags.MODE_MEDIUM.value();
			break;

		case 5:
		default:
			iTess = Flags.MODE_VERYHIGH.value();
			break;
		}
	}

}
