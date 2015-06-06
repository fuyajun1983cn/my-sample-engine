package fyj.engine.core;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainView extends GLSurfaceView {

	private static final String LOG_TAG = MainView.class.getSimpleName();

	private MyRenderer myRenderer;

	public MainView(Context context) {
		super(context);
		requestFocus();
		setEGLConfigChooser(new MultisampleConfigChooser());
		myRenderer = new MyRenderer(context);
		setRenderer(myRenderer);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		return myRenderer.keyEvent(event);
		
		/*
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_LEFT:

				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:

				break;
			case KeyEvent.KEYCODE_DPAD_UP:

				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:

				break;
			default:
				return false;
			}
			queueEvent(new Runnable() {				
				@Override
				public void run() {
					// TODO Auto-generated method stub

				}
			});
			return true;
		}
		
		return false;
		*/
	}
	
	public void createMenu(Menu menu) {
		myRenderer.createMenu(menu);
	}
	
	public void onMenuItemSelected(MenuItem item) {
		myRenderer.onMenuItemSelected(item);
	}

}