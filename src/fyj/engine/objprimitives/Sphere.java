/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-3
 *
 */
package fyj.engine.objprimitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class Sphere {
	
	private float radius = 0;
	private int slices = 0;
	private int stacks = 0;

	private FloatBuffer vertexBuffer = null;
	private FloatBuffer texBuffer = null;
	private FloatBuffer normalBuffer = null;
	
	private boolean iswire = false;

	public Sphere(float fRadius, int iSlices, int iStacks, boolean isWire) {
		
		radius = fRadius;
		slices = iSlices;
		stacks = iStacks;

		iswire = isWire;
		
		makeSphere();

	}

	private void makeSphere() {
		
		float drho = (float) (3.141592653589) / (float) stacks;
		float dtheta = 2.0f * (float) (3.141592653589) / (float) slices;
		float ds = 1.0f / (float) slices;
		float dt = 1.0f / (float) stacks;
		float t = 1.0f;
		float s = 0.0f;
		int i, j; // Looping variables

		ByteBuffer bb = ByteBuffer.allocateDirect(stacks * (slices + 1) * 3 * 4
				* 2);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.position(0);

		bb = ByteBuffer.allocateDirect(stacks * (slices + 1) * 2 * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		texBuffer = bb.asFloatBuffer();
		texBuffer.position(0);

		bb = ByteBuffer.allocateDirect(stacks * (slices + 1) * 3 * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		normalBuffer = bb.asFloatBuffer();
		normalBuffer.position(0);

		for (i = 0; i < stacks; i++) {
			float rho = (float) i * drho;
			float srho = (float) (Math.sin(rho));
			float crho = (float) (Math.cos(rho));
			float srhodrho = (float) (Math.sin(rho + drho));
			float crhodrho = (float) (Math.cos(rho + drho));

			// Many sources of OpenGL sphere drawing code uses a triangle fan
			// for the caps of the sphere. This however introduces texturing
			// artifacts at the poles on some OpenGL implementations

			s = 0.0f;
			for (j = 0; j <= slices; j++) {
				float theta = (j == slices) ? 0.0f : j * dtheta;
				float stheta = (float) (-Math.sin(theta));
				float ctheta = (float) (Math.cos(theta));

				float x = stheta * srho;
				float y = ctheta * srho;
				float z = crho;

				texBuffer.put(s);
				texBuffer.put(t);

				normalBuffer.put(x);
				normalBuffer.put(y);
				normalBuffer.put(z);

				vertexBuffer.put(x * radius);
				vertexBuffer.put(y * radius);
				vertexBuffer.put(z * radius);

				x = stheta * srhodrho;
				y = ctheta * srhodrho;
				z = crhodrho;
				texBuffer.put(s);
				texBuffer.put(t - dt);
				s += ds;
				normalBuffer.put(x);
				normalBuffer.put(y);
				normalBuffer.put(z);

				vertexBuffer.put(x * radius);
				vertexBuffer.put(y * radius);
				vertexBuffer.put(z * radius);
			}

			t -= dt;

		}
		
		vertexBuffer.position(0);
		normalBuffer.position(0);
		texBuffer.position(0);
	}

	private void makeSphere2() {

		float RAD = 3.1415926f / 180;
		float x, y, z, r;
		int i, j;

		ByteBuffer bb = ByteBuffer.allocateDirect(181 * 360 * 3 * 4
				* 2);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.position(0);

//		bb = ByteBuffer.allocateDirect(stacks * (slices + 1) * 2 * 4 * 2);
//		bb.order(ByteOrder.nativeOrder());
//		texBuffer = bb.asFloatBuffer();
//		texBuffer.position(0);

		bb = ByteBuffer.allocateDirect(181 * 360 * 3 * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		normalBuffer = bb.asFloatBuffer();
		normalBuffer.position(0);

		for (i = 0; i <= 180; i ++) {

			r = (float)(2 * Math.sin((float)(i * RAD)));
			z = (float)(2 * Math.cos(i * RAD));
			for (j = 0; j < 360; j ++) {
				x = (float)(r * Math.cos(j * RAD));
				y = (float)(r * Math.sin(j * RAD));
				vertexBuffer.put(new float[]{x, y, z});
				normalBuffer.put(new float[]{x/r, y/r, z/r});
			}

		}

		for (j = 0; j < 360; j ++) {

			for (i = 0; i <= 180; i ++) {
				r = (float)(2 * Math.sin(i * RAD));
				z = (float)(2 * Math.cos(i * RAD));
				x = (float)(r * Math.cos(j * RAD));
				y = (float)(r * Math.sin(j * RAD));
				vertexBuffer.put(new float[]{x, y, z});
				normalBuffer.put(new float[]{x/r, y/r, z/r});
			}

		}
		
		vertexBuffer.position(0);
		normalBuffer.position(0);
	}

	public void draw(GL10 gl) {
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		if (iswire)
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, stacks * (slices + 1) * 3 * 2);
		else
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, stacks * (slices + 1) * 3 * 2);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
}
