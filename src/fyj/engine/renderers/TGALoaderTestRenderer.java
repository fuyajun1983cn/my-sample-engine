package fyj.engine.renderers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import fyj.engine.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import fyj.engine.FyjEngine;
import fyj.engine.utils.TGALoader;
import fyj.engine.utils.TGALoader.Texture;

public class TGALoaderTestRenderer implements CommonRenderer {

	private float spin;													// Spin Variable
    private Texture[] texture = new Texture[2];							// Storage For 2 Textures ( NEW )
	
    private void loadGLTextures(GL10 gl) throws IOException			// Load Bitmaps And Convert To Textures
    {
        texture[0] = new Texture();
        texture[1] = new Texture();
        // Load The Bitmap, Check For Errors.
        TGALoader.loadTGA(texture[0], "tga/uncompressed.tga");
        TGALoader.loadTGA(texture[1], "tga/compressed.tga");
        for (int loop = 0; loop < 2; loop++)						// Loop Through Both Textures
        {
            // Typical Texture Generation Using Data From The TGA ( CHANGE )
            gl.glGenTextures(1, texture[loop].texID, 0);				// Create The Texture ( CHANGE )
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[loop].texID[0]);
            gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, texture[loop].type, texture[loop].width, texture[loop].height, 0, texture[loop].type, GL10.GL_UNSIGNED_BYTE, texture[loop].imageData);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            
            Log.i("40", "bpp = " + texture[loop].bpp + " type = " + texture[loop].type);
        }
    }
    
    private void drawSquare(GL10 gl) {
    	float vertices[] = {    			  			
    			-1.0f, -1.0f, 0.0f,
    			-1.0f, 1.0f, 0.0f,    			
    			1.0f, -1.0f, 0.0f,
    			1.0f, 1.0f, 0.0f,
    		};
    	float texel[] = {
    			0, 0,
    			0, 1,
    			1, 0,
    			1, 1
    	};
    	FloatBuffer vertex = ByteBuffer.allocateDirect(vertices.length * 4)
    			.order(ByteOrder.nativeOrder()).asFloatBuffer();
    	vertex.put(vertices);
    	vertex.position(0);
    	FloatBuffer tex = ByteBuffer.allocateDirect(texel.length * 4)
    			.order(ByteOrder.nativeOrder()).asFloatBuffer();
    	tex.put(texel);
    	tex.position(0);
    	
    	gl.glEnable(GL10.GL_TEXTURE_2D);
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    	
    	gl.glTexCoordPointer(2, GL10.GL_SHORT, 0, tex);
    	gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
    	gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0].texID[0]);
    	gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    	
    }
    
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub

	        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);			// Clear The Screen And The Depth Buffer
	        gl.glLoadIdentity();											// Reset The Modelview Matrix
//	        gl.glTranslatef(0.0f, 0.0f, -10.0f);								// Translate 20 Units Into The Screen

	        spin += 0.05f;												// Increase Spin
	        
	        gl.glRotatef(spin, 0, 1, 0);

	        gl.glPushMatrix();
	        gl.glTranslatef(-1.0f, 0, 0);
	        
	        drawSquare(gl);
	        gl.glPopMatrix();
	        
//	        gl.glPushMatrix();
//	        gl.glTranslatef(1.0f, 0, 0);
//	        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[1].texID[0]);
//	        drawSquare(gl);
//	        gl.glPopMatrix();
	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		 if (h == 0) 
			 h = 1;

	        gl.glViewport(0, 0, w, h);                       // Reset The Current Viewport And Perspective Transformation
	        gl.glMatrixMode(GL10.GL_PROJECTION);                           // Select The Projection Matrix
	        gl.glLoadIdentity();                                      // Reset The Projection Matrix
	        GLU.gluPerspective(gl, 45.0f, (float) w / (float) h, 0.1f, 100.0f);  // Calculate The Aspect Ratio Of The Window
	        GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 1, 0);
	        gl.glMatrixMode(GL10.GL_MODELVIEW);                            // Select The Modelview Matrix
	        gl.glLoadIdentity();                                      // Reset The ModalView Matrix
	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
        try {
            loadGLTextures(gl);										// Jump To Texture Loading Routine ( NEW )
        } catch (IOException e) {
        	Log.e("120", "An error occured!");
            e.printStackTrace();
        }

        gl.glEnable(GL10.GL_TEXTURE_2D);									// Enable Texture Mapping ( NEW )
        gl.glShadeModel(GL10.GL_SMOOTH);									// Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);						// Black Background
        gl.glClearDepthf(1.0f);											// Depth Buffer Setup
        gl.glEnable(GL10.GL_DEPTH_TEST);									// Enables Depth Testing
        gl.glDepthFunc(GL10.GL_LEQUAL);										// The Type Of Depth Testing To Do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);			// Really Nice Perspective Calculations
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
	
	private int mTextureID = -1;
	private void loadTexture(GL10 gl, int drawableId) {

		int[] textures = new int[1];

		// ´´½¨ÎÆÀí
		gl.glGenTextures(1, textures, 0);
		mTextureID = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_CLAMP_TO_EDGE);
		gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
				GL10.GL_REPLACE);

		InputStream is = FyjEngine.getContext().getResources()
				.openRawResource(drawableId);
		Bitmap bitmapTmp;

		try {
			bitmapTmp = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// Ignore.
			}
		}

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
		bitmapTmp.recycle();

	}

}
