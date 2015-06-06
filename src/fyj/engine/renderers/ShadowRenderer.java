/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-9
 *
 */
package fyj.engine.renderers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import fyj.engine.utils.FreeGLUT;
import fyj.engine.utils.Math3d;
import fyj.engine.utils.Math3d.M3DMatrix44f;
import fyj.engine.utils.Math3d.M3DVector3f;
import fyj.engine.utils.Math3d.M3DVector4f;

public class ShadowRenderer implements CommonRenderer {

	private FloatBuffer vertex = null;
	private FloatBuffer normal = null;
	
	// Rotation amounts
	private float xRot = 0.0f;
	private float yRot = 0.0f;

	// These values need to be available globally
	// Light values and coordinates
	private float  ambientLight[] = { 0.3f, 0.3f, 0.3f, 1.0f };
	private float  diffuseLight[] = { 0.7f, 0.7f, 0.7f, 1.0f };
	private float  specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	private float  lightPos[] = { -75.0f, 150.0f, -50.0f, 0.0f };
	private float  specref[] =  { 1.0f, 1.0f, 1.0f, 1.0f };

	// Transformation matrix to project shadow
	M3DMatrix44f shadowMat = new M3DMatrix44f();
	
	public ShadowRenderer(Context ctx) {

	}
	
	private void drawJet(GL10 gl, boolean shadow) {

		ByteBuffer bb = ByteBuffer.allocateDirect(51 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(51 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		normal = bb.asFloatBuffer();
		normal.position(0);

		M3DVector3f vNormal = new M3DVector3f(); // Storeage for calculated
												 // surface normal

		if(!shadow)
	        gl.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
		else
			gl.glColor4f(0, 0, 0, 1);

//		 gl.glNormal3f(0.0f, -1.0f, 0.0f);
		normal.put(new float[] { 0.0f, -1.0f, 0.0f, 
				0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, 0.0f
				});
		vertex.put(new float[] { 0.0f, 0.0f, 60.0f, -15.0f, 0.0f, 30.0f, 15.0f,
				0.0f, 30.0f });

		// Verticies for this panel

		{

			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 15.0f;
			v0.xyz[1] = 0.0f;
			v0.xyz[2] = 30.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 0.0f;
			v1.xyz[1] = 15.0f;
			v1.xyz[2] = 30.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 0.0f;
			v2.xyz[2] = 60.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 0.0f;
			v0.xyz[2] = 60.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 0.0f;
			v1.xyz[1] = 15.0f;
			v1.xyz[2] = 30.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = -15.0f;
			v2.xyz[1] = 0.0f;
			v2.xyz[2] = 30.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		// Body of the Plane ////////////////////////

		{

			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = -15.0f;
			v0.xyz[1] = 0.0f;
			v0.xyz[2] = 30.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 0.0f;
			v1.xyz[1] = 15.0f;
			v1.xyz[2] = 30.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 0.0f;
			v2.xyz[2] = -56.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 0.0f;
			v0.xyz[2] = -56.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 0.0f;
			v1.xyz[1] = 15.0f;
			v1.xyz[2] = 30.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 15.0f;
			v2.xyz[1] = 0.0f;
			v2.xyz[2] = 30.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

//		 gl.glNormal3f(0.0f, -1.0f, 0.0f);
		normal.put(new float[] { 0.0f, -1.0f, 0.0f, 
				0.0f, -1.0f, 0.0f, 
				0.0f, -1.0f, 0.0f});
		vertex.put(new float[] { 15.0f, 0.0f, 30.0f, -15.0f, 0.0f, 30.0f, 0.0f,
				0.0f, -56.0f });

		// /////////////////////////////////////////////

		// Left wing

		// Large triangle for bottom of wing

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 2.0f;
			v0.xyz[2] = 27.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = -60.0f;
			v1.xyz[1] = 2.0f;
			v1.xyz[2] = -8.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 60.0f;
			v2.xyz[1] = 2.0f;
			v2.xyz[2] = -8.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 60.0f;
			v0.xyz[1] = 2.0f;
			v0.xyz[2] = -8.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 0.0f;
			v1.xyz[1] = 7.0f;
			v1.xyz[2] = -8.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 2.0f;
			v2.xyz[2] = 27.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 60.0f;
			v0.xyz[1] = 2.0f;
			v0.xyz[2] = -8.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = -60.0f;
			v1.xyz[1] = 2.0f;
			v1.xyz[2] = -8.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 7.0f;
			v2.xyz[2] = -8.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 2.0f;
			v0.xyz[2] = 27.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 0.0f;
			v1.xyz[1] = 7.0f;
			v1.xyz[2] = -8.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = -60.0f;
			v2.xyz[1] = 2.0f;
			v2.xyz[2] = -8.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		// Tail section///////////////////////////////
		// Bottom of back fin
//		 gl.glNormal3f(0.0f, -1.0f, 0.0f);
		normal.put(new float[] { 0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, 0.0f});
		vertex.put(new float[] { -30.0f, -0.50f, -57.0f, 30.0f, -0.50f, -57.0f,
				0.0f, -0.50f, -40.0f });

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = -0.5f;
			v0.xyz[2] = -40.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 30.0f;
			v1.xyz[1] = -0.5f;
			v1.xyz[2] = -57.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 4.0f;
			v2.xyz[2] = -57.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 4.0f;
			v0.xyz[2] = -57.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = -30.0f;
			v1.xyz[1] = -0.5f;
			v1.xyz[2] = -57.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = -0.5f;
			v2.xyz[2] = -40.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 30.0f;
			v0.xyz[1] = -0.5f;
			v0.xyz[2] = -57.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = -30.0f;
			v1.xyz[1] = -0.5f;
			v1.xyz[2] = -57.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 4.0f;
			v2.xyz[2] = -57.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 0.5f;
			v0.xyz[2] = -40.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = 3.0f;
			v1.xyz[1] = 0.5f;
			v1.xyz[2] = -57.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 25.0f;
			v2.xyz[2] = -65.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 0.0f;
			v0.xyz[1] = 25.0f;
			v0.xyz[2] = -65.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = -3.0f;
			v1.xyz[1] = 0.5f;
			v1.xyz[2] = -57.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 0.5f;
			v2.xyz[2] = -40.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);

		}

		{
			M3DVector3f v0 = new M3DVector3f();
			v0.xyz[0] = 3.0f;
			v0.xyz[1] = 0.5f;
			v0.xyz[2] = -57.0f;
			M3DVector3f v1 = new M3DVector3f();
			v1.xyz[0] = -3.0f;
			v1.xyz[1] = 0.5f;
			v1.xyz[2] = -57.0f;
			M3DVector3f v2 = new M3DVector3f();
			v2.xyz[0] = 0.0f;
			v2.xyz[1] = 25.0f;
			v2.xyz[2] = -65.0f;

			// Calculate the normal for the plane
			Math3d.m3dFindNormal(vNormal, v0, v1, v2);
			Math3d.m3dNormalizeVector(vNormal);
//			 gl.glNormal3f(vNormal.xyz[0], vNormal.xyz[1], vNormal.xyz[2]);
			normal.put(new float[] { vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2],
					vNormal.xyz[0], vNormal.xyz[1],
					vNormal.xyz[2]});
			vertex.put(v0.xyz);
			vertex.put(v1.xyz);
			vertex.put(v2.xyz);
		}
		normal.position(0);
		vertex.position(0);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 51);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

	}
	
	private void drawGround(GL10 gl) {
	    
		ByteBuffer bb = ByteBuffer.allocateDirect(4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect(4 * 4 * 4);
		FloatBuffer color = bb.asFloatBuffer();
		color.position(0);
		
//		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		vertex.put(new float[] {
				400.0f, -150.0f, -200.0f,
				-400.0f, -150.0f, -200.0f,
				-400.0f, -150.0f, 200.0f,
				400.0f, -150.0f, 200.0f
		});
		color.put(new float[] {
				0,0.13f,0,1,
				0,0.13f,0,1,
				0,1,0,1,
				0,1,0,1
		});
		vertex.position(0);
		color.position(0);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, color);
//		gl.glColor4f(0,0.13f,0,1);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		
//		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    
	}
	
	/* (non-Javadoc)
	 * @see fyj.engine.renderers.CommonRenderer#draw(javax.microedition.khronos.opengles.GL10)
	 */
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		 // Clear the window with current clearing color
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

	    // Draw the ground, we do manual shading to a darker green
	    // in the background to give the illusion of depth
	    drawGround(gl);

	    // Save the matrix state and do the rotations
	    gl.glPushMatrix();

	    // Draw jet at new orientation, put light in correct position
	    // before rotating the jet
	    gl.glEnable(GL10.GL_LIGHTING);
	    gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
	    gl.glRotatef(xRot, 1.0f, 0.0f, 0.0f);
	    gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);

	    drawJet(gl, false);

	    // Restore original matrix state
	    gl.glPopMatrix();	


	    // Get ready to draw the shadow and the ground
	    // First disable lighting and save the projection state
	    gl.glDisable(GL10.GL_DEPTH_TEST);
	    gl.glDisable(GL10.GL_LIGHTING);
	    gl.glPushMatrix();

	    // Multiply by shadow projection matrix
	    gl.glMultMatrixf(shadowMat.matrix44, 0);

	    // Now rotate the jet around in the new flattend space
	    gl.glRotatef(xRot, 1.0f, 0.0f, 0.0f);
	    gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);

	    // Pass true to indicate drawing shadow
	    drawJet(gl, true);	

	    // Restore the projection to normal
	    gl.glPopMatrix();

	    // Draw the light source
	    gl.glPushMatrix();
	    gl.glTranslatef(lightPos[0],lightPos[1], lightPos[2]);
	    gl.glColor4f(1,1,0,1);
	    FreeGLUT.glutSolidSphere(gl, 5.0f, 10, 10);
	    gl.glPopMatrix();

	    // Restore lighting state variables
	    gl.glEnable(GL10.GL_DEPTH_TEST);
	}

	/* (non-Javadoc)
	 * @see fyj.engine.renderers.CommonRenderer#resize(javax.microedition.khronos.opengles.GL10, int, int)
	 */
	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		float fAspect;

	    // Prevent a divide by zero
	    if(h == 0)
	        h = 1;

		
	    // Set Viewport to window dimensions
	    gl.glViewport(0, 0, w, h);

	    // Reset coordinate system
	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();

	    fAspect = (float)w/(float)h;
	    GLU.gluPerspective(gl, 60.0f, fAspect, 200.0f, 500.0f);

	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();

	    // Move out Z axis so we can see everything
	    gl.glTranslatef(0.0f, 0.0f, -400.0f);
	    gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
	}

	/* (non-Javadoc)
	 * @see fyj.engine.renderers.CommonRenderer#init(javax.microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// Any three points on the ground (counter clockwise order)
		M3DVector3f point0 = new M3DVector3f();
		point0.xyz[0] = -30.0f;
		point0.xyz[1] = -149.0f;
		point0.xyz[2] = -20.0f;
		M3DVector3f point1 = new M3DVector3f();
		point1.xyz[0] = -30.0f;
		point1.xyz[1] = -149.0f;
		point1.xyz[2] = 20.0f;
		M3DVector3f point2 = new M3DVector3f();
		point2.xyz[0] = 40.0f;
		point2.xyz[1] = -149.0f;
		point2.xyz[2] = 20.0f;

	    gl.glEnable(GL10.GL_DEPTH_TEST);	// Hidden surface removal
	    gl.glFrontFace(GL10.GL_CCW);		// Counter clock-wise polygons face out
	    gl.glEnable(GL10.GL_CULL_FACE);		// Do not calculate inside of jet

	    // Setup and enable light 0
	    gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambientLight, 0);
	    gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffuseLight, 0);
	    gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specular, 0);
	    gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
	    gl.glEnable(GL10.GL_LIGHT0);

	    // Enable color tracking
	    gl.glEnable(GL10.GL_COLOR_MATERIAL);
		
	    // Set Material properties to follow glColor values
//	    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

	    // All materials hereafter have full specular reflectivity
	    // with a high shine
	    gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, specref, 0);
	    gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 128);

	    // Light blue background
	    gl.glClearColor(0.0f, 0.0f, 1.0f, 1.0f );

	    // Get the plane equation from three points on the ground
	    M3DVector4f vPlaneEquation = new M3DVector4f();
	    Math3d.m3dGetPlaneEquation(vPlaneEquation, point0, point1, point2);

	    // Calculate projection matrix to draw shadow on the ground
	    M3DVector3f lightPosV = new M3DVector3f();
	    System.arraycopy(lightPos, 0, lightPosV.xyz, 0, 3);
	    Math3d.m3dMakePlanarShadowMatrix(shadowMat, vPlaneEquation, lightPosV);
	    
	    gl.glShadeModel(GL10.GL_SMOOTH);
//	    glEnable(GL_NORMALIZE);
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see fyj.engine.renderers.CommonRenderer#createMenu(android.view.Menu)
	 */
	@Override
	public void createMenu(Menu menu) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see fyj.engine.renderers.CommonRenderer#onMenuItemSelected(android.view.MenuItem)
	 */
	@Override
	public void onMenuItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

	}

}
