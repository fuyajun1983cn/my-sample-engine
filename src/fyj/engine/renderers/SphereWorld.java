package fyj.engine.renderers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import fyj.engine.utils.FreeGLUT;
import fyj.engine.utils.GLFrame;

public class SphereWorld implements CommonRenderer {

	private static final int NUM_SPHERES = 50;
	private GLFrame spheres[] = new GLFrame[NUM_SPHERES];
	private GLFrame frameCamera;

	private float yRot = 0.0f;// Rotation angle for animation

	public SphereWorld(Context ctx) {

	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		int i;

		yRot += 0.5f;

		// Clear the window with current clearing color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glPushMatrix();
		frameCamera.ApplyCameraTransform(false);

		// Draw the ground
		DrawGround(gl);

		// Draw the randomly located spheres
		for (i = 0; i < NUM_SPHERES; i++) {
			gl.glPushMatrix();
			spheres[i].ApplyActorTransform(false);
			FreeGLUT.glutSolidSphere(gl, 0.1f, 13, 26);
			gl.glPopMatrix();
		}

		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, -2.5f);

		gl.glPushMatrix();
		gl.glRotatef(-yRot * 2.0f, 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(1.0f, 0.0f, 0.0f);
		FreeGLUT.glutWireSphere(gl, 0.1f, 13, 26);
		gl.glPopMatrix();

		gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);
		FreeGLUT.glutWireTorus(gl, 0.15f, 0.35f, 40, 20);
		gl.glPopMatrix();
		gl.glPopMatrix();
	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		float fAspect;

		// Prevent a divide by zero, when window is too short
		// (you cant make a window of zero width).
		if (h == 0)
			h = 1;

		gl.glViewport(0, 0, w, h);

		fAspect = (float) w / (float) h;

		// Reset the coordinate system before modifying
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		// Set the clipping volume
		GLU.gluPerspective(gl, 35.0f, fAspect, 1.0f, 50.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		int iSphere;

		// Bluish background
		gl.glClearColor(0.0f, 0.0f, .50f, 1.0f);

		// Randomly place the sphere inhabitants
		for (iSphere = 0; iSphere < NUM_SPHERES; iSphere++) {
			// Pick a random location between -20 and 20 at .1 increments

			float x = ((float) (400 * Math.random() - 200) * 0.1f);
			float z = (float) (400 * Math.random() - 200) * 0.1f;
			spheres[iSphere] = new GLFrame(gl);
			spheres[iSphere].SetOrigin(x, 0.0f, z);
		}

		frameCamera = new GLFrame(gl);
	}

	// /////////////////////////////////////////////////////////
	// Draw a gridded ground
	private void DrawGround(GL10 gl) {
		float fExtent = 20.0f;
		float fStep = 1.0f;
		float y = -0.4f;
		float iLine;

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		int count = (int) (4 * ((2 * fExtent) / fStep + 1));

		ByteBuffer bb = ByteBuffer.allocateDirect((int) (3 * 4 * count));
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		for (iLine = -fExtent; iLine <= fExtent; iLine += fStep) {
			vertex.put(new float[] { iLine, y, fExtent, iLine, y, -fExtent,
					fExtent, y, iLine, -fExtent, y, iLine });
		}
		vertex.position(0);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_LINES, 0, count);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	public boolean keyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				frameCamera.RotateLocalY(0.1f);
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				frameCamera.RotateLocalY(-0.1f);
				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				frameCamera.MoveForward(0.1f);
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				frameCamera.MoveForward(-0.1f);
				break;
			default:
				return false;
			}
			return true;
		}
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
