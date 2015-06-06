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
import fyj.engine.utils.Math3d;
import fyj.engine.utils.Math3d.M3DVector3f;

public class ShinyJetRenderer implements CommonRenderer {

	// Rotation amounts
	private float xRot = 0.0f;
	private float yRot = 0.0f;

	private FloatBuffer vertex = null;
	private FloatBuffer normal = null;

	public ShinyJetRenderer(Context ctx) {

	}

	private void makeJet(GL10 gl) {

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

		// Nose Cone - Points straight down
		// Set material color
		gl.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
		// glBegin(GL_TRIANGLES);

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

		vertex.position(0);
		normal.position(0);

	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub

		// Clear the window with current clearing color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		// Save the matrix state and do the rotations
		gl.glPushMatrix();
		gl.glRotatef(xRot, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);		
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 51);

		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		float fAspect;

		float lightPos[] = { -50.f, 50.0f, 100.0f, 1.0f };

		// Prevent a divide by zero
		if (h == 0)
			h = 1;

		// Set Viewport to window dimensions
		gl.glViewport(0, 0, w, h);

		// Reset coordinate system
		gl.glMatrixMode(GL10.GL_PROJECTION);

		gl.glLoadIdentity();

		fAspect = (float) w / (float) h;
		GLU.gluPerspective(gl, 45.0f, fAspect, 1.0f, 225.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
		gl.glTranslatef(0.0f, 0.0f, -150.0f);
	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// Light values and coordinates

		float ambientLight[] = { 0.3f, 0.3f, 0.3f, 1.0f };
		float diffuseLight[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float specref[] = { 1.0f, 1.0f, 1.0f, 1.0f };

		gl.glEnable(GL10.GL_DEPTH_TEST); // Hidden surface removal
		gl.glFrontFace(GL10.GL_CCW); // Counter clock-wise polygons face out
		gl.glEnable(GL10.GL_CULL_FACE); // Do not calculate inside of jet

		// Enable lighting
		gl.glEnable(GL10.GL_LIGHTING);

		// Setup and enable light 0
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambientLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffuseLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specular, 0);
		gl.glEnable(GL10.GL_LIGHT0);

		// Enable color tracking
		gl.glEnable(GL10.GL_COLOR_MATERIAL);

		// All materials hereafter have full specular reflectivity
		// with a high shine
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, specref, 0);
		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 128.0f);

		// Light blue background
		gl.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);

//		gl.glEnable(GL10.GL_NORMALIZE);

		makeJet(gl);
	}

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
		
	}

	@Override
	public void onMenuItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
	}

}
