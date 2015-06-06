package fyj.engine.renderers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class ColorTestRenderer implements CommonRenderer {

	private float colors[] = { 1.0f, 0, 0, 1.0f,
	// 0.0f, 1.0f, 0, 1.0f,
	// 1.0f, 1.0f, 0, 1.0f,
	// 0.0f, 0, 1.0f, 1.0f,
	};

	private float vertex[] = { -1, -0.4f, 1, 1, -0.4f, 1, -1, -0.4f, -1, 1,
			-0.4f, -1 };

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;

	private void DrawGround2(GL10 gl) {
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
		bb = ByteBuffer.allocateDirect(2 * 4 * count);
		FloatBuffer color = bb.asFloatBuffer();
		color.position(0);

		for (iStrip = -fExtent; iStrip <= fExtent; iStrip += fStep) {
			// iBounce = 0;
			for (iRun = fExtent; iRun >= -fExtent; iRun -= fStep) {
				if ((iBounce % 2) == 0)
					fColor = 1.0f;
				else
					fColor = 0.0f;

				color.put(new float[] { fColor, fColor, fColor, 0.5f });
				vertex.put(new float[] { iStrip, y, iRun, iStrip + fStep, y,
						iRun });
				// Log.i("73", "iStrip, iRun " + iStrip + " " + iRun);

				iBounce++;

			}
			vertex.position(0);
			color.position(0);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, color);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, count);

		}

		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glShadeModel(GL10.GL_SMOOTH);

	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// gl.glTranslatef(0, 0, -0.2f);

		// gl.glColor4f(1, 0, 0, 1);

		DrawGround2(gl);

		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		// gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//
		// gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		// gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		// gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		//
		// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		// gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
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
		GLU.gluPerspective(gl, 45.0f, fAspect, 1.0f, 50.0f);
		GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 1, 0);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0, 0, 0, 0);

		gl.glEnable(GL10.GL_DEPTH_TEST);
		// gl.glDepthFunc(GL10.GL_LEQUAL);
		// gl.glClearDepthf(10.0f);

		// ByteBuffer bb = ByteBuffer.allocateDirect(4 * 3 * 4);
		// bb.order(ByteOrder.nativeOrder());
		// vertexBuffer = bb.asFloatBuffer();
		// vertexBuffer.position(0);
		//
		// bb = ByteBuffer.allocateDirect(colors.length * 4);
		// bb.order(ByteOrder.nativeOrder());
		// colorBuffer = bb.asFloatBuffer();
		// colorBuffer.position(0);
		//
		// vertexBuffer.put(vertex);
		// colorBuffer.put(colors);
		//
		// vertexBuffer.position(0);
		// colorBuffer.position(0);

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
