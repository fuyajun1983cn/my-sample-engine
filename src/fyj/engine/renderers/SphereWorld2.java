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
import fyj.engine.utils.GLFrame;
import fyj.engine.utils.Math3d;
import fyj.engine.utils.Math3d.M3DMatrix44f;
import fyj.engine.utils.Math3d.M3DVector3f;
import fyj.engine.utils.Math3d.M3DVector4f;

public class SphereWorld2 implements CommonRenderer {

	private static final int NUM_SPHERES = 50;
	private GLFrame spheres[] = new GLFrame[NUM_SPHERES];
	private GLFrame frameCamera;

	private float yRot = 0.0f;// Rotation angle for animation

	// Light and material Data
	private float fLightPos[] = { -100.0f, 100.0f, 50.0f, 1.0f }; // Point
																	// source
	private float fNoLight[] = { 0.0f, 0.0f, 0.0f, 0.0f };
	private float fLowLight[] = { 0.25f, 0.25f, 0.25f, 1.0f };
	private float fBrightLight[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	M3DMatrix44f mShadowMatrix = new M3DMatrix44f();

	public SphereWorld2(Context ctx) {

	}

	@Override
	public void draw(GL10 gl) {
		// Clear the window with current clearing color
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	        
	    gl.glPushMatrix();
	        frameCamera.ApplyCameraTransform(false);
	        
	        // Position light before any other transformations
	        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, fLightPos, 0);
	        
	        // Draw the ground
	        gl.glColor4f(0.60f, .40f, .10f, 1.0f);
	        DrawGround(gl);
	        
	        // Draw shadows first
	        gl.glDisable(GL10.GL_DEPTH_TEST);
	        gl.glDisable(GL10.GL_LIGHTING);
	        gl.glPushMatrix();
	            gl.glMultMatrixf(mShadowMatrix.matrix44, 0);
	            DrawInhabitants(gl, 1);
	        gl.glPopMatrix();
	        gl.glEnable(GL10.GL_LIGHTING);
	        gl.glEnable(GL10.GL_DEPTH_TEST);
	        
	        // Draw inhabitants normally
	        DrawInhabitants(gl, 0);

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

		// Calculate shadow matrix
		M3DVector3f point0 = new M3DVector3f();
		point0.xyz[0] = 0.0f;
		point0.xyz[1] = -0.4f;
		point0.xyz[2] = 0.0f;
		M3DVector3f point1 = new M3DVector3f();
		point1.xyz[0] = 10.0f;
		point1.xyz[1] = -0.4f;
		point1.xyz[2] = 0.0f;
		M3DVector3f point2 = new M3DVector3f();
		point2.xyz[0] = 5.0f;
		point2.xyz[1] = -0.4f;
		point2.xyz[2] = -5.0f;

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

		// Get the plane equation from three points on the ground
		M3DVector4f vPlaneEquation = new M3DVector4f();
		Math3d.m3dGetPlaneEquation(vPlaneEquation, point0, point1, point2);

		// Calculate projection matrix to draw shadow on the ground
		M3DVector3f lightPosV = new M3DVector3f();
		System.arraycopy(fLightPos, 0, lightPosV.xyz, 0, 3);
		Math3d.m3dMakePlanarShadowMatrix(mShadowMatrix, vPlaneEquation,
				lightPosV);

		// Mostly use material tracking
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
		// glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
		gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 128);

		// Randomly place the sphere inhabitants
		for (iSphere = 0; iSphere < NUM_SPHERES; iSphere++) {
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
		float iStrip, iRun;

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		int count = (int) (4 * (2 * fExtent + 1) / fStep);

		ByteBuffer bb = ByteBuffer.allocateDirect((int) (3 * 4 * count));
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		for (iStrip = -fExtent; iStrip <= fExtent; iStrip += fStep) {
			gl.glNormal3f(0.0f, 1.0f, 0.0f);
			for (iRun = fExtent; iRun >= -fExtent; iRun -= fStep) {
				vertex.put(new float[] { iStrip, y, iRun, iStrip + fStep, y,
						iRun });
			}
			vertex.position(0);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_LINES, 0, count);
		}

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	// /////////////////////////////////////////////////////////////////////
	// Draw random inhabitants and the rotating torus/sphere duo
	private void DrawInhabitants(GL10 gl, int nShadow) {
		int i;

		if (nShadow == 0)
			yRot += 0.5f;
		else
			gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);

		// Draw the randomly located spheres
		if (nShadow == 0)
			gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);

		for (i = 0; i < NUM_SPHERES; i++) {
			gl.glPushMatrix();
			spheres[i].ApplyActorTransform(false);
			FreeGLUT.glutSolidSphere(gl, 0.3f, 17, 9);
			gl.glPopMatrix();
		}

		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.1f, -2.5f);

		if (nShadow == 0)
			gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);

		gl.glPushMatrix();
		gl.glRotatef(-yRot * 2.0f, 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(1.0f, 0.0f, 0.0f);
		FreeGLUT.glutSolidSphere(gl, 0.1f, 17, 9);
		gl.glPopMatrix();

		if (nShadow == 0) {
			// Torus alone will be specular
			gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
			gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, fBrightLight, 0);
		}

		gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);
		FreeGLUT.glutSolidTorus(gl, 0.15f, 0.35f, 61, 37);
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, fNoLight, 0);
		gl.glPopMatrix();
	}

	public boolean keyEvent(KeyEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
