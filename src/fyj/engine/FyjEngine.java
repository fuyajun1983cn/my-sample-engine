package fyj.engine;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import fyj.engine.core.TextureEngine;
import fyj.engine.gui.GUIManager;
import fyj.engine.io.IOManager;
import fyj.engine.scene.SceneManager;

public final class FyjEngine {

	private static  Context mContext;
	private static  Renderer mRenderer;
	private static  TextureEngine mTextureEngine;
	
	public static void initContext(Context ctx) {
		mContext = ctx;
	}
	
	public static Context getContext() {
		return mContext;
	}
	
	public static void initRenderer(Renderer renderer) {
		mRenderer = renderer;
	}
	
	public static Renderer getRenderer() {
		return mRenderer;
	}
	
	public static void initTextureEngine(TextureEngine textureEngine) {
		mTextureEngine = textureEngine;
	}
	
	public static TextureEngine getTextureEngine() {
		return mTextureEngine;
	}
	
	public static GUIManager getGUIManager() {
		return new GUIManager();
	}
	
	public static IOManager getIOManager() {
		return new IOManager();
	}
	
	public static SceneManager getSceneManager() {
		return new SceneManager();
	}
}
