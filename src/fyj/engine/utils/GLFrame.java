/**
 * fyjengine All Rights Reserved
 *
 * @author Yajun Fu  
 * 2011-11-7
 *
 */
package fyj.engine.utils;

import javax.microedition.khronos.opengles.GL10;

import fyj.engine.utils.Math3d.M3DMatrix44f;
import fyj.engine.utils.Math3d.M3DVector3f;

public class GLFrame {

	private M3DVector3f vOrigin; // Where am I?
	private M3DVector3f vForward; // Where am I going?
	private M3DVector3f vUp; // Which way is up?

	private GL10 mGl = null;

	// Default position and orientation. At the origin, looking
	// down the positive Z axis (right handed coordinate system).
	public GLFrame(GL10 gl) {
		
		vOrigin = new M3DVector3f();
		vForward = new M3DVector3f();
		vUp = new M3DVector3f();		
		
		// At origin
		vOrigin.xyz[0] = 0.0f;
		vOrigin.xyz[1] = 0.0f;
		vOrigin.xyz[2] = 0.0f;

		// Up is up (+Y)
		vUp.xyz[0] = 0.0f;
		vUp.xyz[1] = 1.0f;
		vUp.xyz[2] = 0.0f;

		// Forward is -Z (default OpenGL)
		vForward.xyz[0] = 0.0f;
		vForward.xyz[1] = 0.0f;
		vForward.xyz[2] = -1.0f;

		mGl = gl;
	}

	// ///////////////////////////////////////////////////////////
	// Set Location
	public void SetOrigin(M3DVector3f vPoint) {
		Math3d.m3dCopyVector3(vOrigin, vPoint);
	}

	public void SetOrigin(float x, float y, float z) {
		vOrigin.xyz[0] = x;
		vOrigin.xyz[1] = y;
		vOrigin.xyz[2] = z;
	}

	public void GetOrigin(M3DVector3f vPoint) {
		Math3d.m3dCopyVector3(vPoint, vOrigin);
	}

	public float GetOriginX() {
		return vOrigin.xyz[0];
	}

	public float GetOriginY() {
		return vOrigin.xyz[1];
	}

	public float GetOriginZ() {
		return vOrigin.xyz[2];
	}

	// ///////////////////////////////////////////////////////////
	// Set Forward Direction
	public void SetForwardVector(M3DVector3f vDirection) {
		Math3d.m3dCopyVector3(vForward, vDirection);
	}

	public void SetForwardVector(float x, float y, float z) {
		vForward.xyz[0] = x;
		vForward.xyz[1] = y;
		vForward.xyz[2] = z;
	}

	public void GetForwardVector(M3DVector3f vVector) {
		Math3d.m3dCopyVector3(vVector, vForward);
	}

	// ///////////////////////////////////////////////////////////
	// Set Up Direction
	public void SetUpVector(M3DVector3f vDirection) {
		Math3d.m3dCopyVector3(vUp, vDirection);
	}

	public void SetUpVector(float x, float y, float z) {
		vUp.xyz[0] = x;
		vUp.xyz[1] = y;
		vUp.xyz[2] = z;
	}

	public void GetUpVector(M3DVector3f vVector) {
		Math3d.m3dCopyVector3(vVector, vUp);
	}

	// ///////////////////////////////////////////////////////////
	// Get Axes
	public void GetZAxis(M3DVector3f vVector) {
		GetForwardVector(vVector);
	}

	public void GetYAxis(M3DVector3f vVector) {
		GetUpVector(vVector);
	}

	public void GetXAxis(M3DVector3f vVector) {
		Math3d.m3dCrossProduct(vVector, vUp, vForward);
	}

	// ///////////////////////////////////////////////////////////
	// Translate along orthonormal axis... world or local
	public void TranslateWorld(float x, float y, float z) {
		vOrigin.xyz[0] += x;
		vOrigin.xyz[1] += y;
		vOrigin.xyz[2] += z;
	}

	public void TranslateLocal(float x, float y, float z) {
		MoveForward(z);
		MoveUp(y);
		MoveRight(x);
	}

	// ///////////////////////////////////////////////////////////
	// Move Forward (along Z axis)
	public void MoveForward(float fDelta) {
		// Move along direction of front direction
		vOrigin.xyz[0] += vForward.xyz[0] * fDelta;
		vOrigin.xyz[1] += vForward.xyz[1] * fDelta;
		vOrigin.xyz[2] += vForward.xyz[2] * fDelta;
	}

	// Move along Y axis
	public void MoveUp(float fDelta) {
		// Move along direction of up direction
		vOrigin.xyz[0] += vUp.xyz[0] * fDelta;
		vOrigin.xyz[1] += vUp.xyz[1] * fDelta;
		vOrigin.xyz[2] += vUp.xyz[2] * fDelta;
	}

	// Move along X axis
	public void MoveRight(float fDelta) {
		// Move along direction of right vector
		M3DVector3f vCross = new M3DVector3f();
		Math3d.m3dCrossProduct(vCross, vUp, vForward);

		vOrigin.xyz[0] += vCross.xyz[0] * fDelta;
		vOrigin.xyz[1] += vCross.xyz[1] * fDelta;
		vOrigin.xyz[2] += vCross.xyz[2] * fDelta;
	}

	// /////////////////////////////////////////////////////////////////////
	// Just assemble the matrix
	public void GetMatrix(M3DMatrix44f matrix, boolean bRotationOnly) {
		// Calculate the right side (x) vector, drop it right into the matrix
		M3DVector3f vXAxis = new M3DVector3f();
		Math3d.m3dCrossProduct(vXAxis, vUp, vForward);

		// Set matrix column does not fill in the fourth value...
		Math3d.m3dSetMatrixColumn44(matrix, vXAxis, 0);
		matrix.matrix44[3] = 0.0f;

		// Y Column
		Math3d.m3dSetMatrixColumn44(matrix, vUp, 1);
		matrix.matrix44[7] = 0.0f;

		// Z Column
		Math3d.m3dSetMatrixColumn44(matrix, vForward, 2);
		matrix.matrix44[11] = 0.0f;

		// Translation (already done)
		if (bRotationOnly == true) {
			matrix.matrix44[12] = 0.0f;
			matrix.matrix44[13] = 0.0f;
			matrix.matrix44[14] = 0.0f;
		} else
			Math3d.m3dSetMatrixColumn44(matrix, vOrigin, 3);

		matrix.matrix44[15] = 1.0f;
	}

	// ///////////////////////////////////////////////////////////
	// Get a 4x4 transformation matrix that describes the ccamera
	// orientation.
	public void GetCameraOrientation(M3DMatrix44f m) {
		M3DVector3f x = new M3DVector3f();
		M3DVector3f z = new M3DVector3f();

		// Make rotation matrix
		// Z vector is reversed
		z.xyz[0] = -vForward.xyz[0];
		z.xyz[1] = -vForward.xyz[1];
		z.xyz[2] = -vForward.xyz[2];

		// X vector = Y cross Z
		Math3d.m3dCrossProduct(x, vUp, z);

		// Matrix has no translation information and is
		// transposed.... (rows instead of columns)
		m.matrix44[0] = x.xyz[0];
		m.matrix44[4] = x.xyz[1];
		m.matrix44[8] = x.xyz[2];
		m.matrix44[12] = 0.0f;
		m.matrix44[1] = vUp.xyz[0];
		m.matrix44[5] = vUp.xyz[1];
		m.matrix44[9] = vUp.xyz[2];
		m.matrix44[13] = 0.0f;
		m.matrix44[2] = z.xyz[0];
		m.matrix44[6] = z.xyz[1];
		m.matrix44[10] = z.xyz[2];
		m.matrix44[14] = 0.0f;
		m.matrix44[3] = 0.0f;
		m.matrix44[7] = 0.0f;
		m.matrix44[11] = 0.0f;
		m.matrix44[15] = 1.0f;

	}

	// ///////////////////////////////////////////////////////////
	// Perform viewing or modeling transformations
	// Position as the camera (for viewing). Apply this transformation
	// first as your viewing transformation
	// The default implementation of gluLookAt can be considerably sped up
	// since it uses doubles for everything... then again profile before you
	// tune... ;-) You might get a boost form page fault reduction too... if
	// no other glu routines are used...
	// This will get called once per frame.... go ahead and inline
	public void ApplyCameraTransform(boolean bRotOnly) {
		M3DMatrix44f m = new M3DMatrix44f();

		GetCameraOrientation(m);

		// Camera Transform
		mGl.glMultMatrixf(m.matrix44, 0);

		// If Rotation only, then do not do the translation
		if (!bRotOnly)
			mGl.glTranslatef(-vOrigin.xyz[0], -vOrigin.xyz[1], -vOrigin.xyz[2]);

		/*
		 * gluLookAt(vOrigin[0], vOrigin[1], vOrigin[2], vOrigin[0] +
		 * vForward[0], vOrigin[1] + vForward[1], vOrigin[2] + vForward[2],
		 * vUp[0], vUp[1], vUp[2]);
		 */
	}

	// Position as an object in the scene. This places and orients a
	// coordinate frame for other objects (besides the camera)
	// There is ample room for optimization here...
	// This is going to be called alot... don't inline
	// Add flag to perform actor rotation only and not the translation
	public void ApplyActorTransform(boolean bRotationOnly) {
		M3DMatrix44f rotMat = new M3DMatrix44f();

		GetMatrix(rotMat, bRotationOnly);

		// Apply rotation to the current matrix
		mGl.glMultMatrixf(rotMat.matrix44, 0);
	}

	// Rotate around local X Axes - Note all rotations are in radians
	public void RotateLocalX(float fAngle) {
		M3DMatrix44f rotMat = new M3DMatrix44f();
		M3DVector3f vCross = new M3DVector3f();
		Math3d.m3dCrossProduct(vCross, vUp, vForward);
		Math3d.m3dRotationMatrix44(rotMat, fAngle, vCross.xyz[0],
				vCross.xyz[1], vCross.xyz[2]);

		M3DVector3f newVect = new M3DVector3f();
		// Inline 3x3 matrix multiply for rotation only
		newVect.xyz[0] = rotMat.matrix44[0] * vForward.xyz[0]
				+ rotMat.matrix44[4] * vForward.xyz[1] + rotMat.matrix44[8]
				* vForward.xyz[2];
		newVect.xyz[1] = rotMat.matrix44[1] * vForward.xyz[0]
				+ rotMat.matrix44[5] * vForward.xyz[1] + rotMat.matrix44[9]
				* vForward.xyz[2];
		newVect.xyz[2] = rotMat.matrix44[2] * vForward.xyz[0]
				+ rotMat.matrix44[6] * vForward.xyz[1] + rotMat.matrix44[10]
				* vForward.xyz[2];
		Math3d.m3dCopyVector3(vForward, newVect);

		// Update pointing up vector
		newVect.xyz[0] = rotMat.matrix44[0] * vUp.xyz[0] + rotMat.matrix44[4]
				* vUp.xyz[1] + rotMat.matrix44[8] * vUp.xyz[2];
		newVect.xyz[1] = rotMat.matrix44[1] * vUp.xyz[0] + rotMat.matrix44[5]
				* vUp.xyz[1] + rotMat.matrix44[9] * vUp.xyz[2];
		newVect.xyz[2] = rotMat.matrix44[2] * vUp.xyz[0] + rotMat.matrix44[6]
				* vUp.xyz[1] + rotMat.matrix44[10] * vUp.xyz[2];
		Math3d.m3dCopyVector3(vUp, newVect);
	}

	// Rotate around local Y
	public void RotateLocalY(float fAngle) {
		M3DMatrix44f rotMat = new M3DMatrix44f();

		// Just Rotate around the up vector
		// Create a rotation matrix around my Up (Y) vector
		Math3d.m3dRotationMatrix44(rotMat, fAngle, vUp.xyz[0], vUp.xyz[1],
				vUp.xyz[2]);

		M3DVector3f newVect = new M3DVector3f();

		// Rotate forward pointing vector (inlined 3x3 transform)
		newVect.xyz[0] = rotMat.matrix44[0] * vForward.xyz[0]
				+ rotMat.matrix44[4] * vForward.xyz[1] + rotMat.matrix44[8]
				* vForward.xyz[2];
		newVect.xyz[1] = rotMat.matrix44[1] * vForward.xyz[0]
				+ rotMat.matrix44[5] * vForward.xyz[1] + rotMat.matrix44[9]
				* vForward.xyz[2];
		newVect.xyz[2] = rotMat.matrix44[2] * vForward.xyz[0]
				+ rotMat.matrix44[6] * vForward.xyz[1] + rotMat.matrix44[10]
				* vForward.xyz[2];
		Math3d.m3dCopyVector3(vForward, newVect);
	}

	// Rotate around local Z
	public void RotateLocalZ(float fAngle) {
		M3DMatrix44f rotMat = new M3DMatrix44f();

		// Only the up vector needs to be rotated
		Math3d.m3dRotationMatrix44(rotMat, fAngle, vForward.xyz[0],
				vForward.xyz[1], vForward.xyz[2]);

		M3DVector3f newVect = new M3DVector3f();
		newVect.xyz[0] = rotMat.matrix44[0] * vUp.xyz[0] + rotMat.matrix44[4]
				* vUp.xyz[1] + rotMat.matrix44[8] * vUp.xyz[2];
		newVect.xyz[1] = rotMat.matrix44[1] * vUp.xyz[0] + rotMat.matrix44[5]
				* vUp.xyz[1] + rotMat.matrix44[9] * vUp.xyz[2];
		newVect.xyz[2] = rotMat.matrix44[2] * vUp.xyz[0] + rotMat.matrix44[6]
				* vUp.xyz[1] + rotMat.matrix44[10] * vUp.xyz[2];
		Math3d.m3dCopyVector3(vUp, newVect);
	}

	// Reset axes to make sure they are orthonormal. This should be called on
	// occasion
	// if the matrix is long-lived and frequently transformed.
	public void Normalize() {
		M3DVector3f vCross = new M3DVector3f();

		// Calculate cross product of up and forward vectors
		Math3d.m3dCrossProduct(vCross, vUp, vForward);

		// Use result to recalculate forward vector
		Math3d.m3dCrossProduct(vForward, vCross, vUp);

		// Also check for unit length...
		Math3d.m3dNormalizeVector(vUp);
		Math3d.m3dNormalizeVector(vForward);
	}

	// Rotate in world coordinates...
	public void RotateWorld(float fAngle, float x, float y, float z) {
		M3DMatrix44f rotMat = new M3DMatrix44f();

		// Create the Rotation matrix
		Math3d.m3dRotationMatrix44(rotMat, fAngle, x, y, z);

		M3DVector3f newVect = new M3DVector3f();

		// Transform the up axis (inlined 3x3 rotation)
		newVect.xyz[0] = rotMat.matrix44[0] * vUp.xyz[0] + rotMat.matrix44[4]
				* vUp.xyz[1] + rotMat.matrix44[8] * vUp.xyz[2];
		newVect.xyz[1] = rotMat.matrix44[1] * vUp.xyz[0] + rotMat.matrix44[5]
				* vUp.xyz[1] + rotMat.matrix44[9] * vUp.xyz[2];
		newVect.xyz[2] = rotMat.matrix44[2] * vUp.xyz[0] + rotMat.matrix44[6]
				* vUp.xyz[1] + rotMat.matrix44[10] * vUp.xyz[2];
		Math3d.m3dCopyVector3(vUp, newVect);

		// Transform the forward axis
		newVect.xyz[0] = rotMat.matrix44[0] * vForward.xyz[0]
				+ rotMat.matrix44[4] * vForward.xyz[1] + rotMat.matrix44[8]
				* vForward.xyz[2];
		newVect.xyz[1] = rotMat.matrix44[1] * vForward.xyz[0]
				+ rotMat.matrix44[5] * vForward.xyz[1] + rotMat.matrix44[9]
				* vForward.xyz[2];
		newVect.xyz[2] = rotMat.matrix44[2] * vForward.xyz[0]
				+ rotMat.matrix44[6] * vForward.xyz[1] + rotMat.matrix44[10]
				* vForward.xyz[2];
		Math3d.m3dCopyVector3(vForward, newVect);
	}

	// Rotate around a local axis
	public void RotateLocal(float fAngle, float x, float y, float z) {
		M3DVector3f vWorldVect = new M3DVector3f();
		M3DVector3f vLocalVect = new M3DVector3f();
		Math3d.m3dLoadVector3(vLocalVect, x, y, z);

		LocalToWorld(vLocalVect, vWorldVect);
		RotateWorld(fAngle, vWorldVect.xyz[0], vWorldVect.xyz[1],
				vWorldVect.xyz[2]);
	}

	// Convert Coordinate Systems
	// This is pretty much, do the transformation represented by the rotation
	// and position on the point
	// Is it better to stick to the convention that the destination always comes
	// first, or use the conventions that "sounds" like the function...
	public void LocalToWorld(M3DVector3f vLocal, M3DVector3f vWorld) {
		// Create the rotation matrix based on the vectors
		M3DMatrix44f rotMat = new M3DMatrix44f();

		GetMatrix(rotMat, true);

		// Do the rotation (inline it, and remove 4th column...)
		vWorld.xyz[0] = rotMat.matrix44[0] * vLocal.xyz[0] + rotMat.matrix44[4]
				* vLocal.xyz[1] + rotMat.matrix44[8] * vLocal.xyz[2];
		vWorld.xyz[1] = rotMat.matrix44[1] * vLocal.xyz[0] + rotMat.matrix44[5]
				* vLocal.xyz[1] + rotMat.matrix44[9] * vLocal.xyz[2];
		vWorld.xyz[2] = rotMat.matrix44[2] * vLocal.xyz[0] + rotMat.matrix44[6]
				* vLocal.xyz[1] + rotMat.matrix44[10] * vLocal.xyz[2];

		// Translate the point
		vWorld.xyz[0] += vOrigin.xyz[0];
		vWorld.xyz[1] += vOrigin.xyz[1];
		vWorld.xyz[2] += vOrigin.xyz[2];
	}

	// Change world coordinates into "local" coordinates
	public void WorldToLocal(M3DVector3f vWorld, M3DVector3f vLocal) {
		// //////////////////////////////////////////////
		// Translate the origin
		M3DVector3f vNewWorld = new M3DVector3f();
		vNewWorld.xyz[0] = vWorld.xyz[0] - vOrigin.xyz[0];
		vNewWorld.xyz[1] = vWorld.xyz[1] - vOrigin.xyz[1];
		vNewWorld.xyz[2] = vWorld.xyz[2] - vOrigin.xyz[2];

		// Create the rotation matrix based on the vectors
		M3DMatrix44f rotMat = new M3DMatrix44f();
		M3DMatrix44f invMat = new M3DMatrix44f();
		GetMatrix(rotMat, true);

		// Do the rotation based on inverted matrix
		Math3d.m3dInvertMatrix44(invMat, rotMat);

		vLocal.xyz[0] = invMat.matrix44[0] * vNewWorld.xyz[0]
				+ invMat.matrix44[4] * vNewWorld.xyz[1] + invMat.matrix44[8]
				* vNewWorld.xyz[2];
		vLocal.xyz[1] = invMat.matrix44[1] * vNewWorld.xyz[0]
				+ invMat.matrix44[5] * vNewWorld.xyz[1] + invMat.matrix44[9]
				* vNewWorld.xyz[2];
		vLocal.xyz[2] = invMat.matrix44[2] * vNewWorld.xyz[0]
				+ invMat.matrix44[6] * vNewWorld.xyz[1] + invMat.matrix44[10]
				* vNewWorld.xyz[2];
	}

	// ///////////////////////////////////////////////////////////////////////////
	// Transform a point by frame matrix
	public void TransformPoint(M3DVector3f vPointSrc, M3DVector3f vPointDst) {
		M3DMatrix44f m = new M3DMatrix44f();
		GetMatrix(m, false); // Rotate and translate
		vPointDst.xyz[0] = m.matrix44[0] * vPointSrc.xyz[0] + m.matrix44[4]
				* vPointSrc.xyz[1] + m.matrix44[8] * vPointSrc.xyz[2]
				+ m.matrix44[12];// * v[3];
		vPointDst.xyz[1] = m.matrix44[1] * vPointSrc.xyz[0] + m.matrix44[5]
				* vPointSrc.xyz[1] + m.matrix44[9] * vPointSrc.xyz[2]
				+ m.matrix44[13];// * v[3];
		vPointDst.xyz[2] = m.matrix44[2] * vPointSrc.xyz[0] + m.matrix44[6]
				* vPointSrc.xyz[1] + m.matrix44[10] * vPointSrc.xyz[2]
				+ m.matrix44[14];// * v[3];
	}

	// //////////////////////////////////////////////////////////////////////////
	// Rotate a vector by frame matrix
	public void RotateVector(M3DVector3f vVectorSrc, M3DVector3f vVectorDst) {
		M3DMatrix44f m = new M3DMatrix44f();
		GetMatrix(m, true); // Rotate only

		vVectorDst.xyz[0] = m.matrix44[0] * vVectorSrc.xyz[0] + m.matrix44[4]
				* vVectorSrc.xyz[1] + m.matrix44[8] * vVectorSrc.xyz[2];
		vVectorDst.xyz[1] = m.matrix44[1] * vVectorSrc.xyz[0] + m.matrix44[5]
				* vVectorSrc.xyz[1] + m.matrix44[9] * vVectorSrc.xyz[2];
		vVectorDst.xyz[2] = m.matrix44[2] * vVectorSrc.xyz[0] + m.matrix44[6]
				* vVectorSrc.xyz[1] + m.matrix44[10] * vVectorSrc.xyz[2];
	}

}
