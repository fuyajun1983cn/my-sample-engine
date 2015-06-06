package fyj.engine.core;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;


public class TextureEngine {
	
	private static final String TAG = TextureEngine.class.getSimpleName();
	
	private CopyOnWriteArrayList<Texture> mTexturesX;

	private GL10 mGl;

	public TextureEngine()
	{
		mTexturesX = new CopyOnWriteArrayList<Texture>();
	}

	public void destroy(GL10 gl)
	{
		mGl = gl;
		onContextLost();
	}

	private void onContextLost()
	{
		if (mGl != null)
		{
			int d = 0;
			int[] textures = new int[mTexturesX.size()];
			Iterator<Texture> it = mTexturesX.iterator();
			while (it.hasNext())
				textures[d++] = it.next().getTextureId();

			mGl.glDeleteTextures(d, textures, 0);
			mTexturesX.clear();
		}
	}

	public void loadTextures(GL10 gl)
	{
		mGl = gl;
		if (mGl != null)
		{
			Iterator<Texture> it = mTexturesX.iterator();
			while (it.hasNext())
				it.next().linkToGL(mGl);
		}
	}
	
	public Texture createTextureFromResourceId(int resourceId)
	{
		Texture tex = null;
		Iterator<Texture> it = mTexturesX.iterator();
		while (it.hasNext())
		{
			tex = it.next();
			if (tex instanceof ResourceTexture)
			{
				// Texture already exists
				if (((ResourceTexture) tex).getResourceId() == resourceId)
				{
					tex.incRef();
					return tex;
				}
			}
		}

		tex = new ResourceTexture(this, resourceId);
		mTexturesX.add(tex);
		return tex;
	}
	
	public Texture createTextureFromStaticText(String text)
	{
		Texture tex = null;
		Iterator<Texture> it = mTexturesX.iterator();
		while (it.hasNext())
		{
			tex = it.next();
			if (tex instanceof StaticTextTexture)
			{
				// Texture already exists
				if (((StaticTextTexture) tex).getText().equals(text))
				{
					tex.incRef();
					return tex;
				}
			}
		}

		tex = new StaticTextTexture(this, text);
		mTexturesX.add(tex);
		return tex;
	}

	public int generateTexture()
	{
		if (mGl != null)
		{
			int[] textureIDs = new int[1];

			mGl.glGenTextures(1, textureIDs, 0);

			int error = mGl.glGetError();
			if (error != GL10.GL_NO_ERROR)
				Log.e("AngleTexture", "generate GLError: " + error);
			else
				return textureIDs[0];
		}
		return -1;
	}

	public void deleteTexture(Texture tex)
	{
		if (mTexturesX.indexOf(tex) > -1)
		{
			tex.decRef();
			if (tex.getRef() < 0)
				mTexturesX.remove(tex);
		}
		if (tex.getTextureId() > -1)
		{
			int[] texture = new int[1];
			texture[0] = tex.getTextureId();
			mGl.glDeleteTextures(1, texture, 0);
		}
	}
}
