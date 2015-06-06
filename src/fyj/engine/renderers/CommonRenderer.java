/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-8
 *
 */
package fyj.engine.renderers;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public interface CommonRenderer {
	public void draw(GL10 gl);
	public void resize(GL10 gl, int w, int h);
	public void init(GL10 gl, EGLConfig config);
	
	public boolean keyEvent(KeyEvent event);
	public void createMenu(Menu menu);	
	public void onMenuItemSelected(MenuItem item);
}
