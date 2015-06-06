package fyj.engine.core;

import java.util.HashMap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import fyj.engine.FyjEngine;
import fyj.engine.renderers.ColorTestRenderer;
import fyj.engine.renderers.CommonRenderer;
import fyj.engine.renderers.FreeglutTestRenderer;
import fyj.engine.renderers.ReflectionRenderer;
import fyj.engine.renderers.ShadowRenderer;
import fyj.engine.renderers.ShinyJetRenderer;
import fyj.engine.renderers.SolarSystemRenderer;
import fyj.engine.renderers.SphereWorld;
import fyj.engine.renderers.SphereWorld2;
import fyj.engine.renderers.SphereWorld3;
import fyj.engine.renderers.SpotLightRendererer;
import fyj.engine.renderers.StencilTestRenderer;
import fyj.engine.renderers.TGALoaderTestRenderer;
import fyj.engine.renderers.TransformRenderer;

public class MyRenderer implements Renderer {	
	

	private HashMap<String, CommonRenderer> renderers = new HashMap();
	
	private String test = "";

	public MyRenderer(Context ctx) {
		FyjEngine.initRenderer(this);
		FyjEngine.initContext(ctx);
		FyjEngine.initTextureEngine(new TextureEngine());

		renderers.put("solar", new SolarSystemRenderer(ctx));
		renderers.put("torus", new TransformRenderer(ctx));
		renderers.put("freeglut", new FreeglutTestRenderer(ctx));
		renderers.put("sphereworld", new SphereWorld(ctx));
		renderers.put("shinyjet", new ShinyJetRenderer(ctx));
		renderers.put("spotlight", new SpotLightRendererer(ctx));
		renderers.put("shadow", new ShadowRenderer(ctx));
		renderers.put("SphereWorld2", new SphereWorld2(ctx));
		renderers.put("SphereWorld3", new SphereWorld3(ctx));
		renderers.put("reflection", new ReflectionRenderer(ctx));
		renderers.put("colortest", new ColorTestRenderer());
		renderers.put("tgaloadertest", new TGALoaderTestRenderer());
		renderers.put("stenciltest", new StencilTestRenderer());
		
		test = "stenciltest";
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		renderers.get(test).draw(gl);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		renderers.get(test).resize(gl, w, h);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		renderers.get(test).init(gl, config);
	}
	
	public boolean keyEvent(KeyEvent event) {
		return renderers.get(test).keyEvent(event);
	}
	
	public void createMenu(Menu menu) {
		renderers.get(test).createMenu(menu);
	}
	
	public void onMenuItemSelected(MenuItem item) {
		renderers.get(test).onMenuItemSelected(item);
	}
}
