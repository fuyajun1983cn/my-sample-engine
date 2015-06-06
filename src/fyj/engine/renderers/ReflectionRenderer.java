package fyj.engine.renderers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import fyj.engine.utils.FreeGLUT;

public class ReflectionRenderer implements CommonRenderer {

	private float fLightPos[] = { -100.0f, 100.0f, 50.0f, 1.0f }; // Point
																	// source
	private float fLightPosMirror[] = { -100.0f, -100.0f, 50.0f, 1.0f };
	private float fNoLight[] = { 0.0f, 0.0f, 0.0f, 0.0f };
	private float fLowLight[] = { 0.25f, 0.25f, 0.25f, 1.0f };
	private float fBrightLight[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	private float yRot = 0.0f; // Rotation angle for animation

	public ReflectionRenderer(Context ctx) {

	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		// Clear the window with current clearing color

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glPushMatrix();
		yRot += 1.0f;
		// Move light under floor to light the "reflected" world

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, fLightPosMirror, 0);

		gl.glPushMatrix();

		gl.glFrontFace(GL10.GL_CW); // geometry is mirrored, swap orientation

		gl.glScalef(1.0f, -1.0f, 1.0f);

		DrawWorld(gl);

		gl.glFrontFace(GL10.GL_CCW);

		gl.glPopMatrix();

		// Draw the ground transparently over the reflection

		gl.glDisable(GL10.GL_LIGHTING);

		gl.glEnable(GL10.GL_BLEND);

		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		DrawGround(gl);

		gl.glDisable(GL10.GL_BLEND);

		gl.glEnable(GL10.GL_LIGHTING);

		// Restore correct lighting and draw the world correctly

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, fLightPos, 0);

		DrawWorld(gl);

		gl.glPopMatrix();
	}

	public void DrawGround(GL10 gl) {

		float fExtent = 20.0f;
		float fStep = 0.5f;
		float y = -0.4f;
		float fColor;
		float iStrip, iRun;
		int iBounce = 0;

		gl.glShadeModel(GL10.GL_FLAT);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		int count = (int) (2 * ((2 * fExtent) / fStep + 1));
		Log.i("95", "count = " + count);
		ByteBuffer bb = ByteBuffer.allocateDirect((int) (3 * 4 * count));
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect(2 * 4 * count );
		FloatBuffer color = bb.asFloatBuffer();
		color.position(0);

		for (iStrip = -fExtent; iStrip <= fExtent; iStrip += fStep) {
			iBounce = 0;
			for (iRun = fExtent; iRun >= -fExtent; iRun -= fStep) {
				if ((iBounce % 2) == 0)
					fColor = 1.0f;
				else
					fColor = 0.0f;

				color.put(new float[] { fColor, fColor, fColor, 0.5f });
				vertex.put(new float[] { iStrip, y, iRun, iStrip + fStep, y,
						iRun });

				iBounce++;

			}
			vertex.position(0);
			color.position(0);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, color);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, count);

		}

		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glShadeModel(GL10.GL_SMOOTH);

	}

	public void DrawWorld(GL10 gl) {

		gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

		gl.glPushMatrix();

		gl.glTranslatef(0.0f, 0.5f, -3.5f);

		gl.glPushMatrix();

		gl.glRotatef(-yRot * 2.0f, 0.0f, 1.0f, 0.0f);

		gl.glTranslatef(1.0f, 0.0f, 0.0f);

		FreeGLUT.glutSolidSphere(gl, 0.1f, 17, 9);

		gl.glPopMatrix();

		gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);

		FreeGLUT.glutSolidTorus(gl, 0.15f, 0.35f, 61, 37);

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

		gl.glTranslatef(0.0f, -0.4f, 0.0f);
	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// Grayish background

		gl.glClearColor(fLowLight[0], fLowLight[1], fLowLight[2], fLowLight[3]);

		// Cull backs of polygons
		gl.glCullFace(GL10.GL_BACK);
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glEnable(GL10.GL_DEPTH_TEST);

		// Setup light parameters
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, fNoLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, fLowLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, fBrightLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, fBrightLight, 0);

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);

		// Mostly use material tracking
		gl.glEnable(GL10.GL_COLOR_MATERIAL);

		// glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 128);
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
