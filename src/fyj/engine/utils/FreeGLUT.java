/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-4
 *
 * porting functions from freeglut
 */
package fyj.engine.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class FreeGLUT {

	/*
	 * 立方体(线框)
	 */
	public static void glutWireCube(GL10 gl, float dSize) {

		float size = dSize * 0.5f;

		ByteBuffer bb = ByteBuffer.allocateDirect(24 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.position(0);

		bb = ByteBuffer.allocateDirect(24 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normalBuffer = bb.asFloatBuffer();
		normalBuffer.position(0);

		vertexBuffer.put(new float[] { +size, -size, +size, +size, -size,
				-size, +size, +size, -size, +size, +size, +size,

				+size, +size, +size, +size, +size, -size, -size, +size, -size,
				-size, +size, +size,

				+size, +size, +size, -size, +size, +size, -size, -size, +size,
				+size, -size, +size,

				-size, -size, +size, -size, +size, +size, -size, +size, -size,
				-size, -size, -size,

				-size, -size, +size, -size, -size, -size, +size, -size, -size,
				+size, -size, +size,

				-size, -size, -size, -size, +size, -size, +size, +size, -size,
				+size, -size, -size });
		normalBuffer.put(new float[] { 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,

				0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 0.0f,

				0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 1.0f,

				-1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f,
				0.0f, 0.0f,

				0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
				-1.0f, 0.0f,

				0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f,
				0.0f, -1.0f,

		});

		vertexBuffer.position(0);
		normalBuffer.position(0);
		gl.glColor4f(1, 0, 0, 1);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		for (int i = 0; i < 6; i++)
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 4 * i, 4);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

	}

	/*
	 * 立方体
	 */
	public static void glutSolidCube(GL10 gl, float dSize) {
		float size = dSize * 0.5f;

		ByteBuffer bb = ByteBuffer.allocateDirect(24 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.position(0);

		bb = ByteBuffer.allocateDirect(24 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normalBuffer = bb.asFloatBuffer();
		normalBuffer.position(0);

		vertexBuffer.put(new float[] { +size, -size, +size, +size, -size,
				-size, +size, +size, -size, +size, +size, +size,

				+size, +size, +size, +size, +size, -size, -size, +size, -size,
				-size, +size, +size,

				+size, +size, +size, -size, +size, +size, -size, -size, +size,
				+size, -size, +size,

				-size, -size, +size, -size, +size, +size, -size, +size, -size,
				-size, -size, -size,

				-size, -size, +size, -size, -size, -size, +size, -size, -size,
				+size, -size, +size,

				-size, -size, -size, -size, +size, -size, +size, +size, -size,
				+size, -size, -size });
		normalBuffer.put(new float[] { 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,

				0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 0.0f,

				0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 1.0f,

				-1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f,
				0.0f, 0.0f,

				0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
				-1.0f, 0.0f,

				0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f,
				0.0f, -1.0f, });

		vertexBuffer.position(0);
		normalBuffer.position(0);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		for (int i = 0; i < 6; i++)
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 4 * i, 4);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

		bb.clear();
		vertexBuffer.clear();
		normalBuffer.clear();
	}

	private static void fghCircleTable(float sint[], float cost[], int n) {
		int i;

		/* Table size, the sign of n flips the circle direction */

		int size = Math.abs(n);

		/* Determine the angle between samples */

		float angle = (float) (2 * Math.PI / (float) ((n == 0) ? 1 : n));

		/* Compute cos and sin around the circle */

		sint[0] = 0.0f;
		cost[0] = 1.0f;
		for (i = 1; i < size; i++) {
			sint[i] = (float) Math.sin(angle * i);
			cost[i] = (float) Math.cos(angle * i);
		}

		/* Last sample is duplicate of the first */

		sint[size] = sint[0];
		cost[size] = cost[0];
	}

	/*
	 * 球面(线框)
	 */
	public static void glutWireSphere(GL10 gl, float radius, int slices,
			int stacks) {
		int i, j;

		/* Adjust z and radius as stacks and slices are drawn. */

		float r;
		float x, y, z;

		/* Pre-computed circle */

		float sint1[] = new float[slices + 1], cost1[] = new float[slices + 1];
		float sint2[] = new float[stacks * 2 + 1], cost2[] = new float[stacks * 2 + 1];

		fghCircleTable(sint1, cost1, -slices);
		fghCircleTable(sint2, cost2, stacks * 2);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		ByteBuffer bb = ByteBuffer.allocateDirect((slices + 1) * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normals = bb.asFloatBuffer();
		normals.position(0);

		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		/* Draw a line loop for each stack */

		for (i = 1; i < stacks; i++) {
			z = cost2[i];
			r = sint2[i];

			for (j = 0; j <= slices; j++) {
				x = cost1[j];
				y = sint1[j];

				normals.put(new float[] { x, y, z });
				vertex.put(new float[] { x * r * radius, y * r * radius,
						z * radius });
			}

			normals.position(0);
			vertex.position(0);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normals);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, slices + 1);

		}

		/* Draw a line loop for each slice */

		bb = ByteBuffer.allocateDirect((stacks + 1) * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);
		normals = bb.asFloatBuffer();
		normals.position(0);

		for (i = 0; i < slices; i++) {

			for (j = 0; j <= stacks; j++) {
				x = cost1[i] * sint2[j];
				y = sint1[i] * sint2[j];
				z = cost2[j];

				normals.put(new float[] { x, y, z });
				vertex.put(new float[] { x * radius, y * radius, z * radius });
			}
			normals.position(0);
			vertex.position(0);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normals);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, stacks + 1);
		}

		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * 球面
	 */
	public static void glutSolidSphere(GL10 gl, float radius, int slices,
			int stacks) {
		int i, j;

		/* Adjust z and radius as stacks are drawn. */

		float z0, z1;
		float r0, r1;

		/* Pre-computed circle */

		float sint1[] = new float[slices + 1], cost1[] = new float[slices + 1];
		float sint2[] = new float[stacks * 2 + 1], cost2[] = new float[stacks * 2 + 1];

		fghCircleTable(sint1, cost1, -slices);
		fghCircleTable(sint2, cost2, stacks * 2);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		/* The top stack is covered with a triangle fan */

		z0 = 1.0f;
		z1 = cost2[(stacks > 0) ? 1 : 0];
		r0 = 0.0f;
		r1 = sint2[(stacks > 0) ? 1 : 0];

		ByteBuffer bb = ByteBuffer.allocateDirect((slices + 2) * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normals = bb.asFloatBuffer();
		normals.position(0);

		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		normals.put(new float[] { 0, 0, 1 });
		vertex.put(new float[] { 0, 0, radius });

		for (j = slices; j >= 0; j--) {
			normals.put(new float[] { cost1[j] * r1, sint1[j] * r1, z1 });
			vertex.put(new float[] { cost1[j] * r1 * radius,
					sint1[j] * r1 * radius, z1 * radius });
		}
		vertex.position(0);
		normals.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normals);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, slices + 2);

		bb = ByteBuffer.allocateDirect((slices + 1) * 3 * 2 * 4);
		bb.order(ByteOrder.nativeOrder());
		normals = bb.asFloatBuffer();
		vertex = bb.asFloatBuffer();
		/* Cover each stack with a quad strip, except the top and bottom stacks */

		for (i = 1; i < stacks - 1; i++) {
			z0 = z1;
			z1 = cost2[i + 1];
			r0 = r1;
			r1 = sint2[i + 1];

			normals.position(0);
			vertex.position(0);

			for (j = 0; j <= slices; j++) {
				normals.put(new float[] { cost1[j] * r1, sint1[j] * r1, z1 });
				vertex.put(new float[] { cost1[j] * r1 * radius,
						sint1[j] * r1 * radius, z1 * radius });
				normals.put(new float[] { cost1[j] * r0, sint1[j] * r0, z0 });
				vertex.put(new float[] { cost1[j] * r0 * radius,
						sint1[j] * r0 * radius, z0 * radius });
			}
			vertex.position(0);
			normals.position(0);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normals);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (slices + 1) * 2);
		}

		/* The bottom stack is covered with a triangle fan */

		z0 = z1;
		r0 = r1;

		bb = ByteBuffer.allocateDirect((slices + 2) * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		normals = bb.asFloatBuffer();
		normals.position(0);
		vertex = bb.asFloatBuffer();
		vertex.position(0);

		normals.put(new float[] { 0, 0, -1 });
		vertex.put(new float[] { 0, 0, -radius });

		for (j = 0; j <= slices; j++) {
			normals.put(new float[] { cost1[j] * r0, sint1[j] * r0, z0 });
			vertex.put(new float[] { cost1[j] * r0 * radius,
					sint1[j] * r0 * radius, z0 * radius });
		}
		vertex.position(0);
		normals.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normals);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, slices + 2);

		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * 圆锥体(线框)
	 */
	public static void glutWireCone(GL10 gl, float base, float height,
			int slices, int stacks) {
		int i, j;

		/* Step in z and radius as stacks are drawn. */

		float z = 0.0f;
		float r = base;

		float zStep = height / ((stacks > 0) ? stacks : 1);
		float rStep = base / ((stacks > 0) ? stacks : 1);

		/* Scaling factors for vertex normals */
		float cosn = (height / (float) Math.sqrt(height * height + base * base));
		float sinn = (base / (float) Math.sqrt(height * height + base * base));

		/* Pre-computed circle */
		float sint[] = new float[slices + 1], cost[] = new float[slices + 1];
		fghCircleTable(sint, cost, -slices);

		/* Draw the stacks... */
		ByteBuffer bb = ByteBuffer.allocateDirect(slices * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		for (i = 0; i < stacks; i++) {

			for (j = 0; j < slices; j++) {
				normal.put(new float[] { cost[j] * sinn, sint[j] * sinn, cosn });
				vertex.put(new float[] { cost[j] * r, sint[j] * r, z });
			}

			normal.position(0);
			vertex.position(0);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, slices);

			z += zStep;
			r -= rStep;
		}

		/* Draw the slices */

		r = base;

		bb = ByteBuffer.allocateDirect(slices * 2 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);

		normal.position(0);

		for (j = 0; j < slices; j++) {
			normal.put(new float[] { cost[j] * sinn, sint[j] * sinn, cosn });
			vertex.put(new float[] { cost[j] * r, sint[j] * r, 0.0f, 0.0f,
					0.0f, height });
		}

		vertex.position(0);
		normal.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_LINES, 0, slices);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}

	/*
	 * 圆锥体
	 */
	public static void glutSolidCone(GL10 gl, float base, float height,
			int slices, int stacks) {
		int i, j;

		/* Step in z and radius as stacks are drawn. */

		float z0, z1;
		float r0, r1;

		float zStep = height / ((stacks > 0) ? stacks : 1);
		float rStep = base / ((stacks > 0) ? stacks : 1);

		/* Scaling factors for vertex normals */

		float cosn = (height / (float) Math.sqrt(height * height + base * base));
		float sinn = (base / (float) Math.sqrt(height * height + base * base));

		/* Pre-computed circle */
		float sint[] = new float[slices + 2], cost[] = new float[slices + 2];
		fghCircleTable(sint, cost, -slices);

		/* Cover the circular base with a triangle fan... */
		z0 = 0.0f;
		z1 = zStep;

		r0 = base;
		r1 = r0 - rStep;

		ByteBuffer bb = ByteBuffer.allocateDirect((slices + 2) * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect(3 * 4);
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		normal.put(new float[] { 0.0f, 0.0f, -1.0f });
		vertex.put(new float[] { 0.0f, 0.0f, z0 });

		for (j = 0; j <= slices; j++)
			vertex.put(new float[] { cost[j] * r0, sint[j] * r0, z0 });
		normal.position(0);
		vertex.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, slices + 2);

		/* Cover each stack with a quad strip, except the top stack */
		bb = ByteBuffer.allocateDirect((slices + 1) * 2 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect((slices + 1) * 3 * 4);
		normal = bb.asFloatBuffer();
		normal.position(0);
		for (i = 0; i < stacks - 1; i++) {

			for (j = 0; j <= slices; j++) {
				normal.put(new float[] { cost[j] * sinn, sint[j] * sinn, cosn });
				vertex.put(new float[] { cost[j] * r0, sint[j] * r0, z0,
						cost[j] * r1, sint[j] * r1, z1 });
			}

			vertex.position(0);
			normal.position(0);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (slices + 1) * 2);

			z0 = z1;
			z1 += zStep;
			r0 = r1;
			r1 -= rStep;

		}

		/* The top stack is covered with individual triangles */
		bb = ByteBuffer.allocateDirect(slices * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect((slices + 1) * 3 * 4);
		normal = bb.asFloatBuffer();
		normal.position(0);

		normal.put(new float[] { cost[0] * sinn, sint[0] * sinn, cosn });

		for (j = 0; j < slices; j++) {
			vertex.put(new float[] { cost[j + 0] * r0, sint[j + 0] * r0, z0, 0,
					0, height, cost[j + 1] * r0, sint[j + 1] * r0, z0 });
			normal.put(new float[] { cost[j + 1] * sinn, sint[j + 1] * sinn,
					cosn });
		}
		vertex.position(0);
		normal.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, slices * 3);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

	}

	/*
	 * 圆柱体(线框)
	 */
	public static void glutWireCylinder(GL10 gl, float radius, float height,
			int slices, int stacks) {
		int i, j;

		/* Step in z and radius as stacks are drawn. */
		float z = 0.0f;
		float zStep = height / ((stacks > 0) ? stacks : 1);

		/* Pre-computed circle */
		float sint[] = new float[slices + 1], cost[] = new float[slices + 1];
		fghCircleTable(sint, cost, -slices);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		/* Draw the stacks... */
		ByteBuffer bb = ByteBuffer.allocateDirect(slices * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		for (i = 0; i <= stacks; i++) {
			if (i == stacks)
				z = height;

			for (j = 0; j < slices; j++) {
				normal.put(new float[] { cost[j], sint[j], 0.0f });
				vertex.put(new float[] { cost[j] * radius, sint[j] * radius, z });
			}
			normal.position(0);
			vertex.position(0);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, slices);
			
			z += zStep;
		}
		
	    /* Draw the slices */
		bb = ByteBuffer.allocateDirect(slices * 2 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect(slices * 3 * 4);
		normal = bb.asFloatBuffer();
		normal.position(0);

        for (j=0; j<slices; j++)
        {
        	normal.put(new float[] {
        			cost[j],        sint[j],        0.0f
        	});
        	vertex.put(new float[] {
        		cost[j]*radius, sint[j]*radius, 0.0f,
        		cost[j]*radius, sint[j]*radius, height
        	});
        }
        
        normal.position(0);
        vertex.position(0);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glDrawArrays(GL10.GL_LINES, 0, slices);
        
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	        
	}

	/*
	 * 圆柱体
	 */
	public static void glutSolidCylinder(GL10 gl, float radius, float height,
			int slices, int stacks) {
		int i, j;

		/* Step in z and radius as stacks are drawn. */
		float z0, z1;
		float zStep = height / ((stacks > 0) ? stacks : 1);

	    /* Pre-computed circle */
		float sint[] = new float[slices + 2], cost[] = new float[slices + 2];
		fghCircleTable(sint, cost, -slices);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

	    /* Cover the base and top */
		ByteBuffer bb = ByteBuffer.allocateDirect((slices+2) * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect(3 * 4);
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		
		normal.put(new float[] {
				0.0f, 0.0f, -1.0f
		});
		vertex.put(new float[] {
				0.0f, 0.0f,  0.0f
		});

        for (j=0; j<=slices; j++)
    		vertex.put(new float[] {
    				cost[j]*radius, sint[j]*radius, 0.0f
    		});
        vertex.position(0);
        normal.position(0);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, slices+2);

        vertex.position(0);
        normal.position(0);
        
		normal.put(new float[] {
				0.0f, 0.0f, 1.0f
		});
		vertex.put(new float[] {
				0.0f, 0.0f,  height
		});

        for (j=slices; j>=0; j--)
    		vertex.put(new float[] {
    				cost[j]*radius, sint[j]*radius, height
    		});
        vertex.position(0);
        normal.position(0);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, slices+2);

	    /* Do the stacks */
	    z0 = 0.0f;
	    z1 = zStep;

		bb = ByteBuffer.allocateDirect((slices+1) * 2 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);
		bb = ByteBuffer.allocateDirect((slices+1) * 3 * 4);
		normal = bb.asFloatBuffer();
		normal.position(0);
	    for (i=1; i<=stacks; i++)
	    {
	        if (i==stacks)
	            z1 = height;

	            for (j=0; j<=slices; j++ )
	            {
	            	normal.put(new float[] {
	            			cost[j],        sint[j],        0.0f
	            	});
	            	vertex.put(new float[] {
	            			cost[j]*radius, sint[j]*radius, z0,
	            			cost[j]*radius, sint[j]*radius, z1
	            	});
	            }

	        normal.position(0);
	        vertex.position(0);
	        gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
	        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
	        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (slices+1) * 2);

	        z0 = z1; z1 += zStep;
	    }
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}

	/*
	 * 圆环面(线框)
	 */
	public static void glutWireTorus(GL10 gl, float dInnerRadius,
			float dOuterRadius, int nSides, int nRings) {
		float iradius = dInnerRadius, oradius = dOuterRadius, phi, psi, dpsi, dphi;
		FloatBuffer vertex, normal;

		int i, j;
		float spsi, cpsi, sphi, cphi;

		if (nSides < 1)
			nSides = 1;
		if (nRings < 1)
			nRings = 1;

		/*
		 * Increment the number of sides and rings to allow for one more point
		 * than surface
		 */
		nSides++;
		nRings++;

		float vertexArray[] = new float[3 * nSides * nRings];
		float normalArray[] = new float[3 * nSides * nRings];

		gl.glPushMatrix();
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		dpsi = 2.0f * (float) Math.PI / (float) (nRings - 1);
		dphi = -2.0f * (float) Math.PI / (float) (nSides - 1);
		psi = 0.0f;

		for (j = 0; j < nRings; j++) {
			cpsi = (float) Math.cos(psi);
			spsi = (float) Math.sin(psi);
			phi = 0.0f;

			for (i = 0; i < nSides; i++) {
				int offset = 3 * (j * nSides + i);
				cphi = (float) Math.cos(phi);
				sphi = (float) Math.sin(phi);
				vertexArray[offset + 0] = cpsi * (oradius + cphi * iradius);
				vertexArray[offset + 1] = spsi * (oradius + cphi * iradius);
				vertexArray[offset + 2] = sphi * iradius;

				normalArray[offset + 0] = cpsi * cphi;
				normalArray[offset + 1] = spsi * cphi;
				normalArray[offset + 2] = sphi;

				phi += dphi;
			}

			psi += dpsi;
		}

		ByteBuffer bb = ByteBuffer.allocateDirect((nSides - 1) * (nRings - 1)
				* 4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);

		normal = bb.asFloatBuffer();
		normal.position(0);

		for (i = 0; i < nSides - 1; i++) {
			for (j = 0; j < nRings - 1; j++) {
				int offset = 3 * (j * nSides + i);
				normal.put(new float[] { normalArray[offset],
						normalArray[offset + 1], normalArray[offset + 2],
						normalArray[offset + 3], normalArray[offset + 4],
						normalArray[offset + 5],
						normalArray[offset + 3 * nSides + 3],
						normalArray[offset + 3 * nSides + 4],
						normalArray[offset + 3 * nSides + 5],
						normalArray[offset + 3 * nSides],
						normalArray[offset + 3 * nSides + 1],
						normalArray[offset + 3 * nSides + 2] });

				vertex.put(new float[] { vertexArray[offset],
						vertexArray[offset + 1], vertexArray[offset + 2],
						vertexArray[offset + 3], vertexArray[offset + 4],
						vertexArray[offset + 5],
						vertexArray[offset + 3 * nSides + 3],
						vertexArray[offset + 3 * nSides + 4],
						vertexArray[offset + 3 * nSides + 5],
						vertexArray[offset + 3 * nSides],
						vertexArray[offset + 3 * nSides + 1],
						vertexArray[offset + 3 * nSides + 2] });
			}
		}

		vertex.position(0);
		normal.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, (nSides - 1) * (nRings - 1) * 4);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glPopMatrix();
	}

	/*
	 * 圆环面
	 */
	public static void glutSolidTorus(GL10 gl, float dInnerRadius,
			float dOuterRadius, int nSides, int nRings) {
		float iradius = dInnerRadius, oradius = dOuterRadius, phi, psi, dpsi, dphi;
		FloatBuffer vertex, normal;

		int i, j;
		float spsi, cpsi, sphi, cphi;

		if (nSides < 1)
			nSides = 1;
		if (nRings < 1)
			nRings = 1;

		/*
		 * Increment the number of sides and rings to allow for one more point
		 * than surface
		 */
		nSides++;
		nRings++;

		float vertexArray[] = new float[3 * nSides * nRings];
		float normalArray[] = new float[3 * nSides * nRings];

		gl.glPushMatrix();
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		dpsi = 2.0f * (float) Math.PI / (float) (nRings - 1);
		dphi = -2.0f * (float) Math.PI / (float) (nSides - 1);
		psi = 0.0f;

		for (j = 0; j < nRings; j++) {
			cpsi = (float) Math.cos(psi);
			spsi = (float) Math.sin(psi);
			phi = 0.0f;

			for (i = 0; i < nSides; i++) {
				int offset = 3 * (j * nSides + i);
				cphi = (float) Math.cos(phi);
				sphi = (float) Math.sin(phi);
				vertexArray[offset + 0] = cpsi * (oradius + cphi * iradius);
				vertexArray[offset + 1] = spsi * (oradius + cphi * iradius);
				vertexArray[offset + 2] = sphi * iradius;

				normalArray[offset + 0] = cpsi * cphi;
				normalArray[offset + 1] = spsi * cphi;
				normalArray[offset + 2] = sphi;

				phi += dphi;
			}

			psi += dpsi;
		}

		ByteBuffer bb = ByteBuffer.allocateDirect((nSides - 1) * (nRings - 1)
				* 4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		vertex = bb.asFloatBuffer();
		vertex.position(0);

		normal = bb.asFloatBuffer();
		normal.position(0);

		for (i = 0; i < nSides - 1; i++) {
			for (j = 0; j < nRings - 1; j++) {
				int offset = 3 * (j * nSides + i);
				normal.put(new float[] { normalArray[offset + 3 * nSides],
						normalArray[offset + 3 * nSides + 1],
						normalArray[offset + 3 * nSides + 2],
						normalArray[offset], vertexArray[offset + 1],
						normalArray[offset + 2],
						normalArray[offset + 3 * nSides + 3],
						normalArray[offset + 3 * nSides + 4],
						normalArray[offset + 3 * nSides + 5],
						normalArray[offset + 3], vertexArray[offset + 4],
						normalArray[offset + 5] });

				vertex.put(new float[] { vertexArray[offset + 3 * nSides],
						vertexArray[offset + 3 * nSides + 1],
						vertexArray[offset + 3 * nSides + 2],
						vertexArray[offset], vertexArray[offset + 1],
						vertexArray[offset + 2],
						vertexArray[offset + 3 * nSides + 3],
						vertexArray[offset + 3 * nSides + 4],
						vertexArray[offset + 3 * nSides + 5],
						vertexArray[offset + 3], vertexArray[offset + 4],
						vertexArray[offset + 5] });
			}
		}

		vertex.position(0);
		normal.position(0);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (nSides - 1) * (nRings - 1)
				* 4);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glPopMatrix();
	}

	/*
	 * 十二面体(线框)
	 */
	public static void glutWireDodecahedron(GL10 gl) {
		/*
		 * Magic Numbers: It is possible to create a dodecahedron by attaching
		 * two pentagons to each face of of a cube. The coordinates of the
		 * points are: (+-x,0, z); (+-1, 1, 1); (0, z, x ) where x = (-1 +
		 * sqrt(5))/2, z = (1 + sqrt(5))/2 or x = 0.61803398875 and z =
		 * 1.61803398875.
		 */
		ByteBuffer bb = ByteBuffer.allocateDirect(12 * 5 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		vertex.put(new float[] { 0.0f, 1.61803398875f, 0.61803398875f, -1.0f,
				1.0f, 1.0f, -0.61803398875f, 0.0f, 1.61803398875f,
				0.61803398875f, 0.0f, 1.61803398875f, 1.0f, 1.0f, 1.0f,

				0.0f, 1.61803398875f, -0.61803398875f, 1.0f, 1.0f, -1.0f,
				0.61803398875f, 0.0f, -1.61803398875f, -0.61803398875f, 0.0f,
				-1.61803398875f, -1.0f, 1.0f, -1.0f,

				0.0f, -1.61803398875f, 0.61803398875f, 1.0f, -1.0f, 1.0f,
				0.61803398875f, 0.0f, 1.61803398875f, -0.61803398875f, 0.0f,
				1.61803398875f, -1.0f, -1.0f, 1.0f,

				0.0f, -1.61803398875f, -0.61803398875f, -1.0f, -1.0f, -1.0f,
				-0.61803398875f, 0.0f, -1.61803398875f, 0.61803398875f, 0.0f,
				-1.61803398875f, 1.0f, -1.0f, -1.0f,

				0.61803398875f, 0.0f, 1.61803398875f, 1.0f, -1.0f, 1.0f,
				1.61803398875f, -0.61803398875f, 0.0f, 1.61803398875f,
				0.61803398875f, 0.0f, 1.0f, 1.0f, 1.0f,

				-0.61803398875f, 0.0f, 1.61803398875f, -1.0f, 1.0f, 1.0f,
				-1.61803398875f, 0.61803398875f, 0.0f, -1.61803398875f,
				-0.61803398875f, 0.0f, -1.0f, -1.0f, 1.0f,

				0.61803398875f, 0.0f, -1.61803398875f, 1.0f, 1.0f, -1.0f,
				1.61803398875f, 0.61803398875f, 0.0f, 1.61803398875f,
				-0.61803398875f, 0.0f, 1.0f, -1.0f, -1.0f,

				-0.61803398875f, 0.0f, -1.61803398875f, -1.0f, -1.0f, -1.0f,
				-1.61803398875f, -0.61803398875f, 0.0f, -1.61803398875f,
				0.61803398875f, 0.0f, -1.0f, 1.0f, -1.0f,

				1.61803398875f, 0.61803398875f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f,
				1.61803398875f, -0.61803398875f, 0.0f, 1.61803398875f,
				0.61803398875f, 1.0f, 1.0f, 1.0f,

				1.61803398875f, -0.61803398875f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f,
				-1.61803398875f, 0.61803398875f, 0.0f, -1.61803398875f,
				-0.61803398875f, 1.0f, -1.0f, -1.0f,

				-1.61803398875f, 0.61803398875f, 0.0f, -1.0f, 1.0f, 1.0f, 0.0f,
				1.61803398875f, 0.61803398875f, 0.0f, 1.61803398875f,
				-0.61803398875f, -1.0f, 1.0f, -1.0f,

				-1.61803398875f, -0.61803398875f, 0.0f, -1.0f, -1.0f, -1.0f,
				0.0f, -1.61803398875f, -0.61803398875f, 0.0f, -1.61803398875f,
				0.61803398875f, -1.0f, -1.0f, 1.0f

		});
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(12 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		normal.put(new float[] { 0.0f, 0.525731112119f, 0.850650808354f, 0.0f,
				0.525731112119f, -0.850650808354f, 0.0f, -0.525731112119f,
				0.850650808354f, 0.0f, -0.525731112119f, -0.850650808354f,
				0.850650808354f, 0.0f, 0.525731112119f, -0.850650808354f, 0.0f,
				0.525731112119f, 0.850650808354f, 0.0f, -0.525731112119f,
				-0.850650808354f, 0.0f, -0.525731112119f, 0.525731112119f,
				0.850650808354f, 0.0f, 0.525731112119f, -0.850650808354f, 0.0f,
				-0.525731112119f, 0.850650808354f, 0.0f, -0.525731112119f,
				-0.850650808354f, 0.0f });
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		for (int i = 0; i < 12; i++)
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 5 * i, 5);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	/*
	 * 十二面体
	 */
	public static void glutSolidDodecahedron(GL10 gl) {
		/*
		 * Magic Numbers: It is possible to create a dodecahedron by attaching
		 * two pentagons to each face of of a cube. The coordinates of the
		 * points are: (+-x,0, z); (+-1, 1, 1); (0, z, x ) where x = (-1 +
		 * sqrt(5))/2, z = (1 + sqrt(5))/2 or x = 0.61803398875 and z =
		 * 1.61803398875.
		 */
		ByteBuffer bb = ByteBuffer.allocateDirect(12 * 5 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		vertex.put(new float[] { 0.0f, 1.61803398875f, 0.61803398875f, -1.0f,
				1.0f, 1.0f, -0.61803398875f, 0.0f, 1.61803398875f,
				0.61803398875f, 0.0f, 1.61803398875f, 1.0f, 1.0f, 1.0f,

				0.0f, 1.61803398875f, -0.61803398875f, 1.0f, 1.0f, -1.0f,
				0.61803398875f, 0.0f, -1.61803398875f, -0.61803398875f, 0.0f,
				-1.61803398875f, -1.0f, 1.0f, -1.0f,

				0.0f, -1.61803398875f, 0.61803398875f, 1.0f, -1.0f, 1.0f,
				0.61803398875f, 0.0f, 1.61803398875f, -0.61803398875f, 0.0f,
				1.61803398875f, -1.0f, -1.0f, 1.0f,

				0.0f, -1.61803398875f, -0.61803398875f, -1.0f, -1.0f, -1.0f,
				-0.61803398875f, 0.0f, -1.61803398875f, 0.61803398875f, 0.0f,
				-1.61803398875f, 1.0f, -1.0f, -1.0f,

				0.61803398875f, 0.0f, 1.61803398875f, 1.0f, -1.0f, 1.0f,
				1.61803398875f, -0.61803398875f, 0.0f, 1.61803398875f,
				0.61803398875f, 0.0f, 1.0f, 1.0f, 1.0f,

				-0.61803398875f, 0.0f, 1.61803398875f, -1.0f, 1.0f, 1.0f,
				-1.61803398875f, 0.61803398875f, 0.0f, -1.61803398875f,
				-0.61803398875f, 0.0f, -1.0f, -1.0f, 1.0f,

				0.61803398875f, 0.0f, -1.61803398875f, 1.0f, 1.0f, -1.0f,
				1.61803398875f, 0.61803398875f, 0.0f, 1.61803398875f,
				-0.61803398875f, 0.0f, 1.0f, -1.0f, -1.0f,

				-0.61803398875f, 0.0f, -1.61803398875f, -1.0f, -1.0f, -1.0f,
				-1.61803398875f, -0.61803398875f, 0.0f, -1.61803398875f,
				0.61803398875f, 0.0f, -1.0f, 1.0f, -1.0f,

				1.61803398875f, 0.61803398875f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f,
				1.61803398875f, -0.61803398875f, 0.0f, 1.61803398875f,
				0.61803398875f, 1.0f, 1.0f, 1.0f,

				1.61803398875f, -0.61803398875f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f,
				-1.61803398875f, 0.61803398875f, 0.0f, -1.61803398875f,
				-0.61803398875f, 1.0f, -1.0f, -1.0f,

				-1.61803398875f, 0.61803398875f, 0.0f, -1.0f, 1.0f, 1.0f, 0.0f,
				1.61803398875f, 0.61803398875f, 0.0f, 1.61803398875f,
				-0.61803398875f, -1.0f, 1.0f, -1.0f,

				-1.61803398875f, -0.61803398875f, 0.0f, -1.0f, -1.0f, -1.0f,
				0.0f, -1.61803398875f, -0.61803398875f, 0.0f, -1.61803398875f,
				0.61803398875f, -1.0f, -1.0f, 1.0f

		});
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(12 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		normal.put(new float[] { 0.0f, 0.525731112119f, 0.850650808354f, 0.0f,
				0.525731112119f, -0.850650808354f, 0.0f, -0.525731112119f,
				0.850650808354f, 0.0f, -0.525731112119f, -0.850650808354f,
				0.850650808354f, 0.0f, 0.525731112119f, -0.850650808354f, 0.0f,
				0.525731112119f, 0.850650808354f, 0.0f, -0.525731112119f,
				-0.850650808354f, 0.0f, -0.525731112119f, 0.525731112119f,
				0.850650808354f, 0.0f, 0.525731112119f, -0.850650808354f, 0.0f,
				-0.525731112119f, 0.850650808354f, 0.0f, -0.525731112119f,
				-0.850650808354f, 0.0f });
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		for (int i = 0; i < 12; i++)
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 5 * i, 5);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * 八面体(线框)
	 */
	public static void glutWireOctahedron(GL10 gl) {
		float RADIUS = 1.0f;
		ByteBuffer bb = ByteBuffer.allocateDirect(8 * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		vertex.put(new float[] { RADIUS, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, 0.0f,
				0.0f, RADIUS,

				RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, RADIUS, 0.0f,

				RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, -RADIUS, 0.0f,

				RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS,

				-RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, RADIUS, 0.0f,

				-RADIUS, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS,

				-RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, 0.0f, 0.0f, RADIUS,

				-RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, -RADIUS, 0.0f

		});
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(8 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		normal.put(new float[] { 0.577350269189f, 0.577350269189f,
				0.577350269189f, 0.577350269189f, 0.577350269189f,
				-0.577350269189f, 0.577350269189f, -0.577350269189f,
				0.577350269189f, 0.577350269189f, -0.577350269189f,
				-0.577350269189f, -0.577350269189f, 0.577350269189f,
				0.577350269189f, -0.577350269189f, 0.577350269189f,
				-0.577350269189f, -0.577350269189f, -0.577350269189f,
				0.577350269189f, -0.577350269189f, -0.577350269189f,
				-0.577350269189f });
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 24);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * 八面体
	 */
	public static void glutSolidOctahedron(GL10 gl) {
		float RADIUS = 1.0f;
		ByteBuffer bb = ByteBuffer.allocateDirect(8 * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		vertex.put(new float[] { RADIUS, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, 0.0f,
				0.0f, RADIUS,

				RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, RADIUS, 0.0f,

				RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, -RADIUS, 0.0f,

				RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS,

				-RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, RADIUS, 0.0f,

				-RADIUS, 0.0f, 0.0f, 0.0f, RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS,

				-RADIUS, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, 0.0f, 0.0f, RADIUS,

				-RADIUS, 0.0f, 0.0f, 0.0f, 0.0f, -RADIUS, 0.0f, -RADIUS, 0.0f

		});
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(8 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		normal.put(new float[] { 0.577350269189f, 0.577350269189f,
				0.577350269189f, 0.577350269189f, 0.577350269189f,
				-0.577350269189f, 0.577350269189f, -0.577350269189f,
				0.577350269189f, 0.577350269189f, -0.577350269189f,
				-0.577350269189f, -0.577350269189f, 0.577350269189f,
				0.577350269189f, -0.577350269189f, 0.577350269189f,
				-0.577350269189f, -0.577350269189f, -0.577350269189f,
				0.577350269189f, -0.577350269189f, -0.577350269189f,
				-0.577350269189f });
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 24);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * Magic Numbers: r0 = ( 1, 0, 0 ) r1 = ( -1/3, 2 sqrt(2) / 3, 0 ) r2 = (
	 * -1/3, -sqrt(2) / 3, sqrt(6) / 3 ) r3 = ( -1/3, -sqrt(2) / 3, -sqrt(6) / 3
	 * ) |r0| = |r1| = |r2| = |r3| = 1 Distance between any two points is 2
	 * sqrt(6) / 3
	 * 
	 * Normals: The unit normals are simply the negative of the coordinates of
	 * the point not on the surface.
	 */

	private static final int NUM_TETR_FACES = 4;

	private static float tet_r[][] = { { 1.0f, 0.0f, 0.0f },
			{ -0.333333333333f, 0.942809041582f, 0.0f },
			{ -0.333333333333f, -0.471404520791f, 0.816496580928f },
			{ -0.333333333333f, -0.471404520791f, -0.816496580928f } };

	private static int tet_i[][] = /* Vertex indices */
	{ { 1, 3, 2 }, { 0, 2, 3 }, { 0, 3, 1 }, { 0, 1, 2 } };

	/*
	 * 四面体(线框)
	 */
	public static void glutWireTetrahedron(GL10 gl) {

		ByteBuffer bb = ByteBuffer.allocateDirect(4 * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		vertex.put(tet_r[1]);
		vertex.put(tet_r[3]);
		vertex.put(tet_r[2]);

		vertex.put(tet_r[0]);
		vertex.put(tet_r[2]);
		vertex.put(tet_r[3]);

		vertex.put(tet_r[0]);
		vertex.put(tet_r[3]);
		vertex.put(tet_r[1]);

		vertex.put(tet_r[0]);
		vertex.put(tet_r[1]);
		vertex.put(tet_r[2]);

		vertex.position(0);

		bb = ByteBuffer.allocateDirect(4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		normal.put(new float[] { -tet_r[0][0], -tet_r[0][1], -tet_r[0][2],
				-tet_r[1][0], -tet_r[1][1], -tet_r[1][2], -tet_r[2][0],
				-tet_r[2][1], -tet_r[2][2], -tet_r[3][0], -tet_r[3][1],
				-tet_r[3][2] });
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 12);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	/*
	 * 四面体
	 */
	public static void glutSolidTetrahedron(GL10 gl) {
		ByteBuffer bb = ByteBuffer.allocateDirect(4 * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		vertex.put(tet_r[1]);
		vertex.put(tet_r[3]);
		vertex.put(tet_r[2]);

		vertex.put(tet_r[0]);
		vertex.put(tet_r[2]);
		vertex.put(tet_r[3]);

		vertex.put(tet_r[0]);
		vertex.put(tet_r[3]);
		vertex.put(tet_r[1]);

		vertex.put(tet_r[0]);
		vertex.put(tet_r[1]);
		vertex.put(tet_r[2]);

		vertex.position(0);

		bb = ByteBuffer.allocateDirect(4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);
		normal.put(new float[] { -tet_r[0][0], -tet_r[0][1], -tet_r[0][2],
				-tet_r[1][0], -tet_r[1][1], -tet_r[1][2], -tet_r[2][0],
				-tet_r[2][1], -tet_r[2][2], -tet_r[3][0], -tet_r[3][1],
				-tet_r[3][2] });
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 12);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	private static float icos_r[][] = { { 1.0f, 0.0f, 0.0f },
			{ 0.447213595500f, 0.894427191000f, 0.0f },
			{ 0.447213595500f, 0.276393202252f, 0.850650808354f },
			{ 0.447213595500f, -0.723606797748f, 0.525731112119f },
			{ 0.447213595500f, -0.723606797748f, -0.525731112119f },
			{ 0.447213595500f, 0.276393202252f, -0.850650808354f },
			{ -0.447213595500f, -0.894427191000f, 0.0f },
			{ -0.447213595500f, -0.276393202252f, 0.850650808354f },
			{ -0.447213595500f, 0.723606797748f, 0.525731112119f },
			{ -0.447213595500f, 0.723606797748f, -0.525731112119f },
			{ -0.447213595500f, -0.276393202252f, -0.850650808354f },
			{ -1.0f, 0.0f, 0.0f } };

	private static int icos_v[][] = { { 0, 1, 2 }, { 0, 2, 3 }, { 0, 3, 4 },
			{ 0, 4, 5 }, { 0, 5, 1 }, { 1, 8, 2 }, { 2, 7, 3 }, { 3, 6, 4 },
			{ 4, 10, 5 }, { 5, 9, 1 }, { 1, 9, 8 }, { 2, 8, 7 }, { 3, 7, 6 },
			{ 4, 6, 10 }, { 5, 10, 9 }, { 11, 9, 10 }, { 11, 8, 9 },
			{ 11, 7, 8 }, { 11, 6, 7 }, { 11, 10, 6 } };

	/*
	 * 二十面体(线框)
	 */
	public static void glutWireIcosahedron(GL10 gl) {
		ByteBuffer bb = ByteBuffer.allocateDirect(20 * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(20 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		for (int i = 0; i < 20; i++) {
			normal.put((icos_r[icos_v[i][1]][1] - icos_r[icos_v[i][0]][1])
					* (icos_r[icos_v[i][2]][2] - icos_r[icos_v[i][0]][2])
					- (icos_r[icos_v[i][1]][2] - icos_r[icos_v[i][0]][2])
					* (icos_r[icos_v[i][2]][1] - icos_r[icos_v[i][0]][1]));
			normal.put((icos_r[icos_v[i][1]][2] - icos_r[icos_v[i][0]][2])
					* (icos_r[icos_v[i][2]][0] - icos_r[icos_v[i][0]][0])
					- (icos_r[icos_v[i][1]][0] - icos_r[icos_v[i][0]][0])
					* (icos_r[icos_v[i][2]][2] - icos_r[icos_v[i][0]][2]));
			normal.put((icos_r[icos_v[i][1]][0] - icos_r[icos_v[i][0]][0])
					* (icos_r[icos_v[i][2]][1] - icos_r[icos_v[i][0]][1])
					- (icos_r[icos_v[i][1]][1] - icos_r[icos_v[i][0]][1])
					* (icos_r[icos_v[i][2]][0] - icos_r[icos_v[i][0]][0]));

			vertex.put(icos_r[icos_v[i][0]]);
			vertex.put(icos_r[icos_v[i][1]]);
			vertex.put(icos_r[icos_v[i][2]]);
		}

		vertex.position(0);
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		for (int i = 0; i < 20; i++)
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 3 * i, 3);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * 二十面体
	 */
	public static void glutSolidIcosahedron(GL10 gl) {
		ByteBuffer bb = ByteBuffer.allocateDirect(20 * 3 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(20 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		for (int i = 0; i < 20; i++) {
			normal.put((icos_r[icos_v[i][1]][1] - icos_r[icos_v[i][0]][1])
					* (icos_r[icos_v[i][2]][2] - icos_r[icos_v[i][0]][2])
					- (icos_r[icos_v[i][1]][2] - icos_r[icos_v[i][0]][2])
					* (icos_r[icos_v[i][2]][1] - icos_r[icos_v[i][0]][1]));
			normal.put((icos_r[icos_v[i][1]][2] - icos_r[icos_v[i][0]][2])
					* (icos_r[icos_v[i][2]][0] - icos_r[icos_v[i][0]][0])
					- (icos_r[icos_v[i][1]][0] - icos_r[icos_v[i][0]][0])
					* (icos_r[icos_v[i][2]][2] - icos_r[icos_v[i][0]][2]));
			normal.put((icos_r[icos_v[i][1]][0] - icos_r[icos_v[i][0]][0])
					* (icos_r[icos_v[i][2]][1] - icos_r[icos_v[i][0]][1])
					- (icos_r[icos_v[i][1]][1] - icos_r[icos_v[i][0]][1])
					* (icos_r[icos_v[i][2]][0] - icos_r[icos_v[i][0]][0]));

			vertex.put(icos_r[icos_v[i][0]]);
			vertex.put(icos_r[icos_v[i][1]]);
			vertex.put(icos_r[icos_v[i][2]]);
		}

		vertex.position(0);
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		for (int i = 0; i < 20; i++)
			gl.glDrawArrays(GL10.GL_TRIANGLES, 3 * i, 3);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	private static float rdod_r[][] = { { 0.0f, 0.0f, 1.0f },
			{ 0.707106781187f, 0.000000000000f, 0.5f },
			{ 0.000000000000f, 0.707106781187f, 0.5f },
			{ -0.707106781187f, 0.000000000000f, 0.5f },
			{ 0.000000000000f, -0.707106781187f, 0.5f },
			{ 0.707106781187f, 0.707106781187f, 0.0f },
			{ -0.707106781187f, 0.707106781187f, 0.0f },
			{ -0.707106781187f, -0.707106781187f, 0.0f },
			{ 0.707106781187f, -0.707106781187f, 0.0f },
			{ 0.707106781187f, 0.000000000000f, -0.5f },
			{ 0.000000000000f, 0.707106781187f, -0.5f },
			{ -0.707106781187f, 0.000000000000f, -0.5f },
			{ 0.000000000000f, -0.707106781187f, -0.5f }, { 0.0f, 0.0f, -1.0f } };

	private static int rdod_v[][] = { { 0, 1, 5, 2 }, { 0, 2, 6, 3 },
			{ 0, 3, 7, 4 }, { 0, 4, 8, 1 }, { 5, 10, 6, 2 }, { 6, 11, 7, 3 },
			{ 7, 12, 8, 4 }, { 8, 9, 5, 1 }, { 5, 9, 13, 10 },
			{ 6, 10, 13, 11 }, { 7, 11, 13, 12 }, { 8, 12, 13, 9 } };

	private static float rdod_n[][] = {
			{ 0.353553390594f, 0.353553390594f, 0.5f },
			{ -0.353553390594f, 0.353553390594f, 0.5f },
			{ -0.353553390594f, -0.353553390594f, 0.5f },
			{ 0.353553390594f, -0.353553390594f, 0.5f },
			{ 0.000000000000f, 1.000000000000f, 0.0f },
			{ -1.000000000000f, 0.000000000000f, 0.0f },
			{ 0.000000000000f, -1.000000000000f, 0.0f },
			{ 1.000000000000f, 0.000000000000f, 0.0f },
			{ 0.353553390594f, 0.353553390594f, -0.5f },
			{ -0.353553390594f, 0.353553390594f, -0.5f },
			{ -0.353553390594f, -0.353553390594f, -0.5f },
			{ 0.353553390594f, -0.353553390594f, -0.5f } };

	/*
	 * 菱形十二面体(线框)
	 */
	public static void glutWireRhombicDodecahedron(GL10 gl) {
		ByteBuffer bb = ByteBuffer.allocateDirect(12 * 4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(12 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		for (int i = 0; i < 12; i++) {
			normal.put(rdod_n[i]);

			vertex.put(rdod_r[rdod_v[i][0]]);
			vertex.put(rdod_r[rdod_v[i][1]]);
			vertex.put(rdod_r[rdod_v[i][2]]);
			vertex.put(rdod_r[rdod_v[i][3]]);
		}

		vertex.position(0);
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		for (int i = 0; i < 12; i++)
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 4 * i, 4);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/*
	 * 菱形十二面体
	 */
	public static void glutSolidRhombicDodecahedron(GL10 gl) {
		ByteBuffer bb = ByteBuffer.allocateDirect(12 * 4 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer vertex = bb.asFloatBuffer();
		vertex.position(0);

		bb = ByteBuffer.allocateDirect(12 * 3 * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer normal = bb.asFloatBuffer();
		normal.position(0);

		for (int i = 0; i < 12; i++) {
			normal.put(rdod_n[i]);

			vertex.put(rdod_r[rdod_v[i][0]]);
			vertex.put(rdod_r[rdod_v[i][1]]);
			vertex.put(rdod_r[rdod_v[i][2]]);
			vertex.put(rdod_r[rdod_v[i][3]]);
		}

		vertex.position(0);
		normal.position(0);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normal);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
		for (int i = 0; i < 12; i++)
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 4 * i, 4);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
