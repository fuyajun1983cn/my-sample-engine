package fyj.engine.core;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

import android.opengl.GLSurfaceView;
import android.util.Log;

public class MultisampleConfigChooser implements GLSurfaceView.EGLConfigChooser {

	static private final String kTag = "MultisampleConfigChooser";

	public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
		mValue = new int[1];
		// Try to find a normal multisample configuration first.
		int[] configSpec = { EGL10.EGL_RED_SIZE, 8, EGL10.EGL_GREEN_SIZE, 8,
				EGL10.EGL_BLUE_SIZE, 8,
				EGL10.EGL_ALPHA_SIZE,
				8,
				EGL10.EGL_DEPTH_SIZE,
				16,
				// Requires that setEGLContextClientVersion(2) is called on the
				// view.
				EGL10.EGL_RENDERABLE_TYPE, 4 /* EGL_OPENGL_ES2_BIT */,
				EGL10.EGL_SAMPLE_BUFFERS, 1 /* true */, EGL10.EGL_SAMPLES, 2,
				EGL10.EGL_NONE };

		if (!egl.eglChooseConfig(display, configSpec, null, 0, mValue)) {
			throw new IllegalArgumentException("eglChooseConfig failed");
		}
		int numConfigs = mValue[0];

		// Get all matching configurations.
		EGLConfig[] configs = new EGLConfig[numConfigs];
		if (!egl.eglChooseConfig(display, configSpec, configs, numConfigs,
				mValue)) {
			throw new IllegalArgumentException("data eglChooseConfig failed");
		}

		int index = -1;
		for (int i = 0; i < configs.length; ++i) {
			if (findConfigAttrib(egl, display, configs[i],
					EGL10.EGL_ALPHA_SIZE, 0) == 8) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			Log.w(kTag, "Did not find sane config, using first");
		}
		Log.i(kTag, "index: " + index);
		EGLConfig config = configs.length > 0 ? configs[index] : null;
		if (config == null) {
			throw new IllegalArgumentException("No config chosen");
		}
		return config;
	}

	private int findConfigAttrib(EGL10 egl, EGLDisplay display,
			EGLConfig config, int attribute, int defaultValue) {
		if (egl.eglGetConfigAttrib(display, config, attribute, mValue)) {
			return mValue[0];
		}
		return defaultValue;
	}

	private int[] mValue;

}
