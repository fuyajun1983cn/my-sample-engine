package fyj.engine.core;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import android.util.Log;


public abstract class Texture {
	
	private static final String TAG = Texture.class.getSimpleName();
	
	private int mRefernces = 0;
	private int mHWTextureID = -1;
	private int mWidth = 0;
	private int mHeight = 0;
	private TextureEngine mTextureEngine;
	
	public Texture(TextureEngine textureEngine)
	{
		mTextureEngine=textureEngine;
	}
	
	public void linkToGL (GL10 gl)
	{
		if (mTextureEngine != null) {
			mHWTextureID=mTextureEngine.generateTexture();
			load(gl);
		}
	}
	
	//配置纹理生成的相关参数信息，子类可覆盖此方法自定义一些配制信息
	protected void configureTexParamEnv(GL10 gl) {		
		
		int error;
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
		
		error = gl.glGetError();
		if (error != GL10.GL_NO_ERROR)
			Log.e(TAG, "load Parameterf GLError: " + error);

		gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
		
		error = gl.glGetError();
		if (error != GL10.GL_NO_ERROR)
			Log.e(TAG, "load Envf GLError: " + error);
	}

	public void load(GL10 gl)
	{
		if ((gl != null) && (mHWTextureID > -1)) {
			
			gl.glBindTexture(GL10.GL_TEXTURE_2D, mHWTextureID);
			int error = gl.glGetError();
			if (error != GL10.GL_NO_ERROR)
				Log.e(TAG, "load Bind GLError: " + error);
	

			configureTexParamEnv(gl);
	
			Bitmap bitmap = create();
	
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			error = gl.glGetError();
			if (error != GL10.GL_NO_ERROR)
				Log.e(TAG, "load Image2D GLError: " + error);
	
			mWidth = bitmap.getWidth();
			mHeight = bitmap.getHeight();
			
			Log.i(TAG, "Width = " + mWidth + " Height = " + mHeight);
	
			bitmap.recycle();
		}
	}
	
	public int getRef() {
		return mRefernces;
	}
	
	public void incRef() {
		mRefernces++;
	}
	
	public void decRef() {
		mRefernces--;
	}
	
	public int getTextureId() {
		return mHWTextureID;
	}
	
	public int getBitmapWidth() {
		return mWidth;
	}
	
	public int getBitmapHeight() {
		return mHeight;
	}

	public abstract Bitmap create();
}
