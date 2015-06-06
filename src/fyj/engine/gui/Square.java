package fyj.engine.gui;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.microedition.khronos.opengles.GL10;

import fyj.engine.FyjEngine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class Square {
	
	private int mTextureID;
	
	private int mCount = 0;
	
	
	private Context mContext;
	
	private float vertices[] = {
			-1.0f, 1.0f, 0.0f,
			
			-1.0f, -1.0f, 0.0f,
			
			1.0f, -1.0f, 0.0f,

			1.0f, 1.0f, 0.0f,
		};

	// Our vertex buffer.
	private FloatBuffer vertexBuffer;
	
	private FloatBuffer mTexBuffer;
	
	private boolean hasUv = false;
	
	public void addVetex(float x, float y, float z) {
		vertexBuffer.put(new float[] { x, y, z });
	}
	
	public void addUv(float u, float v) {
		if (hasUv) {
			mTexBuffer.put(new float[] { u, v });
		}
	}
	

	public Square(Context ctx, boolean hasUv, int maxVertex) {
		
		mCount = maxVertex;
		mContext = ctx;
		// a float is 4 bytes, therefore we 
		// multiply the number if
		// vertices with 4.
		ByteBuffer vbb 
		  = ByteBuffer.allocateDirect(maxVertex * 3 * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.position(0);

		// short is 2 bytes, therefore we multiply 
		//the number if
		// vertices with 2.
		
		ByteBuffer tbb = ByteBuffer.allocateDirect(maxVertex * 2 * 4);
		tbb.order(ByteOrder.nativeOrder());
		mTexBuffer = tbb.asFloatBuffer();
		mTexBuffer.position(0);
		
	}
	
	public void setTexture(int ResId) {
		if (hasUv) {
			FyjEngine.getTextureEngine().createTextureFromResourceId(ResId);
		}
	}

	/**
	 * This function draws our square on screen.
	 * @param gl
	 */
	public void draw(GL10 gl) {		
		
		vertexBuffer.position(0);
		if (hasUv)
			mTexBuffer.position(0);
		// Counter-clockwise winding.
		//gl.glFrontFace(GL10.GL_CCW); 
		// Enable face culling.
		//gl.glEnable(GL10.GL_CULL_FACE); 
		// What faces to remove with the face culling.
		//gl.glCullFace(GL10.GL_BACK); 

		// Enabled the vertices buffer for writing 
		//and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		// Specifies the location and data format of 
		//an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, 
                                 vertexBuffer);
		
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTexBuffer);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, mCount);

		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); 
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		vertexBuffer.clear();
		if (hasUv)
			mTexBuffer.clear();
		
	}

}
