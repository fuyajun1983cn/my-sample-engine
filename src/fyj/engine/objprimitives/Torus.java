package fyj.engine.objprimitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import fyj.engine.utils.Math3d;

public class Torus {

	private float majorRadius;
	private float minorRadius;
	private int numMajor;
	private int numMinor;

	private FloatBuffer vertexBuffer = null;
	private FloatBuffer texBuffer = null;
	private FloatBuffer normalBuffer = null;

	private boolean iswire = false;

	public Torus(float majorRadius, float minorRadius, int numMajor,
			int numMinor, boolean isWire) {
		this.majorRadius = majorRadius;
		this.minorRadius = minorRadius;
		this.numMajor = numMajor;
		this.numMinor = numMinor;
		this.iswire = isWire;
		
		makeTorus();
	}

	private void makeTorus() {

		Math3d.M3DVector3f vNormal = new Math3d.M3DVector3f();

		double majorStep = 2.0f * Math3d.M3D_PI / numMajor;
		double minorStep = 2.0f * Math3d.M3D_PI / numMinor;
		int i, j;

		ByteBuffer bb = ByteBuffer.allocateDirect(numMajor * (numMinor + 1) * 3 * 4
				* 2);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.position(0);

		bb = ByteBuffer.allocateDirect(numMajor * (numMinor + 1) * 2 * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		texBuffer = bb.asFloatBuffer();
		texBuffer.position(0);

		bb = ByteBuffer.allocateDirect(numMajor * (numMinor + 1) * 3 * 4 * 2);
		bb.order(ByteOrder.nativeOrder());
		normalBuffer = bb.asFloatBuffer();
		normalBuffer.position(0);

		for (i = 0; i < numMajor; ++i) {
			
			double a0 = i * majorStep;
			double a1 = a0 + majorStep;
			float x0 = (float) Math.cos(a0);
			float y0 = (float) Math.sin(a0);
			float x1 = (float) Math.cos(a1);
			float y1 = (float) Math.sin(a1);

			for (j = 0; j <= numMinor; ++j) {
				
				double b = j * minorStep;
				float c = (float) Math.cos(b);
				float r = minorRadius * c + majorRadius;
				float z = minorRadius * (float) Math.sin(b);

				// First point
				texBuffer.put(new float[] { (float) (i) / (float) (numMajor),
						(float) (j) / (float) (numMinor) });

				vNormal.xyz[0] = x0 * c;
				vNormal.xyz[1] = y0 * c;
				vNormal.xyz[2] = z / minorRadius;
				Math3d.m3dNormalizeVector(vNormal);
				normalBuffer.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
						vNormal.xyz[2] });

				vertexBuffer.put(new float[] { x0 * r, y0 * r, z });

				texBuffer.put(new float[] {
						(float) (i + 1) / (float) (numMajor),
						(float) (j) / (float) (numMinor) });
				vNormal.xyz[0] = x1 * c;
				vNormal.xyz[1] = y1 * c;
				vNormal.xyz[2] = z / minorRadius;
				Math3d.m3dNormalizeVector(vNormal);
				normalBuffer.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
						vNormal.xyz[2] });
				vertexBuffer.put(new float[] { x1 * r, y1 * r, z });

			}

		}
		vertexBuffer.position(0);
		normalBuffer.position(0);
		texBuffer.position(0);
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
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, numMajor * (numMinor + 1) * 3 * 2);
		else
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, numMajor * (numMinor + 1) * 3 * 2);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}
}
