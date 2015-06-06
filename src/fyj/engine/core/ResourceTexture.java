package fyj.engine.core;

import java.io.IOException;
import java.io.InputStream;

import fyj.engine.FyjEngine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ResourceTexture extends Texture {

	private static final String TAG = ResourceTexture.class.getSimpleName();
	
	private static final BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
	private int mResourceId = -1;
	
	public ResourceTexture(TextureEngine textureEngine, int resourceId) {
		super(textureEngine);
		// TODO Auto-generated constructor stub
		mResourceId = resourceId;
	}

	public int getResourceId() {
		return mResourceId;
	}
	
	@Override
	public Bitmap create() {
		// TODO Auto-generated method stub
		sBitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
		InputStream is = FyjEngine.getContext().getResources().openRawResource(mResourceId);
		Bitmap bitmap;
		try
		{
			bitmap = BitmapFactory.decodeStream(is, null, sBitmapOptions);
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (IOException e)
			{
				Log.e("AngleTextureEngine", "loadTexture::InputStream.close error: " + e.getMessage());
			}
		}
		return bitmap;
	}

}
