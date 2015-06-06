package fyj.engine.renderers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import fyj.engine.utils.FreeGLUT;

public class StencilTestRenderer implements CommonRenderer {

	private float yellow_diffuse[] = { 0.7f, 0.7f, 0.0f, 1.0f };
	private float yellow_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float blue_diffuse[] = { 0.1f, 0.1f, 0.7f, 1.0f };
	private float blue_specular[] = { 0.1f, 1.0f, 1.0f, 1.0f };
	private float position_one[] = { 1.0f, 1.0f, 1.0f, 0.0f };

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		/* draw blue sphere where the stencil is 1 */
		gl.glStencilFunc(GL10.GL_EQUAL, 0x1, 0x1);
		gl.glStencilOp(GL10.GL_KEEP, GL10.GL_KEEP, GL10.GL_KEEP);

		makeBlueSphere(gl);
		/* draw the tori where the stencil is not 1 */
		/*
		 * gl.glStencilFunc(GL10.GL_NOTEQUAL, 0x1, 0x1); gl.glPushMatrix();
		 * gl.glRotatef(45.0f, 0.0f, 0.0f, 1.0f); gl.glRotatef(45.0f, 0.0f,
		 * 1.0f, 0.0f); makeYellowTorus(gl); gl.glPushMatrix();
		 * gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f); makeYellowTorus(gl);
		 * gl.glPopMatrix(); gl.glPopMatrix();
		 */

	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, w, h);
		/* create a diamond shaped stencil area */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		if (w <= h)
			GLU.gluOrtho2D(gl, -3.0f, 3.0f, -3.0f * (float) h / (float) w, 3.0f
					* (float) h / (float) w);
		else
			GLU.gluOrtho2D(gl, -3.0f * (float) w / (float) h, 3.0f * (float) w
					/ (float) h, -3.0f, 3.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glClear(GL10.GL_STENCIL_BUFFER_BIT);
		gl.glStencilFunc(GL10.GL_ALWAYS, 0x1, 0x1);
		gl.glStencilOp(GL10.GL_REPLACE, GL10.GL_REPLACE, GL10.GL_REPLACE);
		makeSquare(gl);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) w / (float) h, 3.0f, 7.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
	}

	private void makeBlueSphere(GL10 gl) {

		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, blue_diffuse, 0);
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, blue_specular, 0);
		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 45.0f);
		FreeGLUT.glutSolidSphere(gl, 0.5f, 15, 15);

	}

	private void makeYellowTorus(GL10 gl) {

		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, yellow_diffuse, 0);
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, yellow_specular, 0);
		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 64.0f);
		FreeGLUT.glutSolidTorus(gl, 0.275f, 0.85f, 15, 15);

	}

	private void makeSquare(GL10 gl) {
		float points[] = { -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f };

		ByteBuffer bb = ByteBuffer.allocateDirect(points.length * 2 * 4);
		FloatBuffer vertexBuffer = bb.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexBuffer.position(0);
		vertexBuffer.put(points);
		vertexBuffer.position(0);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, position_one, 0);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClearStencil(0x0);
		gl.glEnable(GL10.GL_STENCIL_TEST);

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
