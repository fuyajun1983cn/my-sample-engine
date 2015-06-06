package fyj.engine;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import fyj.engine.core.MainView;

public class MySampleRenderActivity extends Activity {
	
	private MainView mView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new MainView(this);
        setContentView(mView);
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		mView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mView.onPause();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if (mView.dispatchKeyEvent(event))
			return true;
		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean b = super.onCreateOptionsMenu(menu);
		mView.createMenu(menu);
		return b;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		boolean b = super.onOptionsItemSelected(item);
		mView.onMenuItemSelected(item);
		return b;
	}
	
	
}