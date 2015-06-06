package fyj.engine.renderers;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import fyj.engine.utils.TGALoader;
import fyj.engine.utils.TGALoader.Texture;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class TunnelRenderer implements CommonRenderer {
	
	private float zPos = -60.0f;
	
	
	private enum TEXTURE_TYPE{
		TEXTURE_BRICK(0),
		TEXTURE_FLOOR(1),
		TEXTURE_CEILING(2),
		TEXTURE_COUNT(3);
		
		private int value;
		
		private TEXTURE_TYPE(int $value) {
			value = $value;
		}
		
		public int value() {
			return value;
		}
		
	}

	private Texture  textures[] = new Texture[TEXTURE_TYPE.TEXTURE_COUNT.value()];

	String szTextureFiles[] = { "tga/brick.tga", "tga/floor.tga", "tga/ceiling.tga" };

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

	    int iWidth, iHeight, iComponents;
	    int iLoop;	    

		// Black background
		gl.glClearColor(0.0f, 0.0f, 0.0f,1.0f);


	    // Textures applied as decals, no lighting or coloring effects
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_DECAL);

	    // Load textures
	    for(iLoop = 0; iLoop < TEXTURE_TYPE.TEXTURE_COUNT.value(); iLoop++)
	        {
	    	textures[iLoop] = new Texture();
	        // Bind to next texture object
	    	gl.glGenTextures(1, textures[iLoop].texID, 0);
	        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[iLoop].texID[0]);	        

	        // Load texture, set filter and wrap modes
	    	try {
				TGALoader.loadTGA(textures[iLoop], szTextureFiles[iLoop]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	        // Load texture, set filter and wrap modes
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

	        }
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
