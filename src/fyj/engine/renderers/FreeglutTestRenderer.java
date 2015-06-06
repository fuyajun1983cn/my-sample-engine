package fyj.engine.renderers;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import fyj.engine.utils.FreeGLUT;

public class FreeglutTestRenderer implements CommonRenderer {

	private float rot = 0;
	
	public FreeglutTestRenderer(Context ctx) {

	}
	
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		 gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		 
		 gl.glMatrixMode(GL10.GL_MODELVIEW);
		 gl.glLoadIdentity();
		 
		 
		 gl.glTranslatef(0, 0, -0.2f);
		 gl.glRotatef(rot, 0, 1, 0);
		 rot += 0.5f;
//		 Log.i("37", " rot = " + rot);
		 
//		 Freeglut.glutSolidCube(gl, 1);
//		 Freeglut.glutWireSphere(gl, 1, 35, 45);
//		 FreeGLUT.glutWireTorus(gl, 0.5f, 1, 35, 30);
//		 Freeglut.glutSolidTorus(gl, 0.5f, 1, 20, 60);
		 
//		 FreeGLUT.glutWireDodecahedron(gl);
//		 FreeGLUT.glutSolidDodecahedron(gl);
		 
//		 FreeGLUT.glutWireOctahedron(gl);
//		 FreeGLUT.glutSolidOctahedron(gl);
		 
//		 FreeGLUT.glutWireTetrahedron(gl);
		 
//		 FreeGLUT.glutWireIcosahedron(gl);
//		 FreeGLUT.glutSolidIcosahedron(gl);
		 
//		 FreeGLUT.glutWireRhombicDodecahedron(gl);
//		 FreeGLUT.glutSolidRhombicDodecahedron(gl);

//		 FreeGLUT.glutWireCone(gl, 1.0f, 5.0f, 35, 30);
//		 FreeGLUT.glutSolidCone(gl, 1.0f, 5.0f, 35, 30);
		 
//		 FreeGLUT.glutWireCylinder(gl, 1.0f, 5, 35, 30);
		 FreeGLUT.glutSolidCylinder(gl, 1.0f, 5, 36, 30);
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
	    GLU.gluPerspective(gl, 45.0f, fAspect, 1.0f, 50.0f);
	    GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 1, 0);
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();

	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		gl.glClearColor(0.0f, 0.0f, .50f, 1.0f );
		
		gl.glEnable(GL10.GL_DEPTH_TEST); // Hidden surface removal

//		gl.glFrontFace(GL10.GL_CCW); // Counter clock-wise polygons face out
//
//		gl.glEnable(GL10.GL_CULL_FACE); // Do not calculate inside of jet		
		
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
