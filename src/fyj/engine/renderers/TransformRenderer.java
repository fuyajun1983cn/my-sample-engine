package fyj.engine.renderers;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import fyj.engine.objprimitives.Sphere;
import fyj.engine.objprimitives.Torus;
import fyj.engine.utils.FreeGLUT;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class TransformRenderer implements CommonRenderer {
	
	private Torus torus;
	private float yRot = 0;
	
	public TransformRenderer(Context ctx) {

		torus = new Torus(0.35f, 0.15f, 40, 20, true);
	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		 gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		 
		 gl.glMatrixMode(GL10.GL_MODELVIEW);
		 gl.glLoadIdentity();
		 
		 gl.glTranslatef(0, 0, -2.5f);
		 gl.glRotatef( yRot, 0, 1, 0);
		 FreeGLUT.glutWireTorus(gl, 0.15f, 0.35f, 40, 20);
		 
		 yRot += 0.5f;
	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		float fAspect;

	    // Prevent a divide by zero, when window is too short
	    // (you cant make a window of zero width).
	    if(h == 0)
	        h = 1;

	    gl.glViewport(0, 0, w, h);
	        
	    fAspect = (float)w / (float)h;

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
		gl.glClearColor(0.0f, 0.0f, .50f, 1.0f );
		
		gl.glEnable(GL10.GL_DEPTH_TEST); // Hidden surface removal

		gl.glFrontFace(GL10.GL_CCW); // Counter clock-wise polygons face out

		gl.glEnable(GL10.GL_CULL_FACE); // Do not calculate inside of jet
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
