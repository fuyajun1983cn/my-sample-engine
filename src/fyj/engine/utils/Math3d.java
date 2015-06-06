package fyj.engine.utils;

public class Math3d {

	public static class M3DVector3f {
		public float[] xyz = new float[3];// Vector of three floats (x, y, z)
	}

	public static class M3DVector3d {
		public double[] xyz = new double[3];// Vector of three doubles (x, y, z)
	}

	public static class M3DVector4f {
		public float[] xyzw = new float[4];// Lesser used... Do we really need
											// these?
	}

	public static class M3DVector4d {
		public double[] xyzw = new double[4];// Yes, occasionaly
	}

	public static class M3DVector2f {
		public float[] xy = new float[2];// 3D points = 3D Vectors, but we need
											// a
	}

	public static class M3DVector2d {
		public double[] xy = new double[2];// 2D representations sometimes...
											// (x,y) order
	}

	// 3x3 matrix - column major. X vector is 0, 1, 2, etc.
	// 0 3 6
	// 1 4 7
	// 2 5 8
	public static class M3DMatrix33f {// A 3 x 3 matrix, column major (floats) -
										// OpenGL Style
		public float matrix33[] = new float[9];
	}

	public static class M3DMatrix33d {// A 3 x 3 matrix, column major (doubles)
										// - OpenGL Style
		public double matrix33[] = new double[9];
	}

	// 4x4 matrix - column major. X vector is 0, 1, 2, etc.
	// 0 4 8 12
	// 1 5 9 13
	// 2 6 10 14
	// 3 7 11 15
	public static class M3DMatrix44f {// A 4 x 4 matrix, column major (floats) -
										// OpenGL Style
		public float matrix44[] = new float[16];
	}

	public static class M3DMatrix44d {// A 4 x 4 matrix, column major (doubles)
										// - OpenGL Style
		public double matrix44[] = new double[16];
	}

	// /////////////////////////////////////////////////////////////////////////////
	// Useful constants
	public static final double M3D_PI = 3.14159265358979323846;
	public static final double M3D_2PI = 2.0 * M3D_PI;
	public static final double M3D_PI_DIV_180 = 0.017453292519943296;
	public static final double M3D_INV_PI_DIV_180 = 57.2957795130823229;

	// /////////////////////////////////////////////////////////////////////////////
	// Useful shortcuts
	// Radians are king... but we need a way to swap back and forth
	public static double m3dDegToRad(double x) {
		return ((x) * M3D_PI_DIV_180);
	}

	public static double m3dRadToDeg(double x) {
		return ((x) * M3D_INV_PI_DIV_180);
	}

	// Hour angles
	public static double m3dHrToDeg(double x) {
		return ((x) * (1.0 / 15.0));
	}

	public static double m3dHrToRad(double x) {
		return m3dDegToRad(m3dHrToDeg(x));
	}

	public static double m3dDegToHr(double x) {
		return ((x) * 15.0);
	}

	public static double m3dRadToHr(double x) {
		return m3dDegToHr(m3dRadToDeg(x));
	}

	// Returns the same number if it is a power of
	// two. Returns a larger integer if it is not a
	// power of two. The larger integer is the next
	// highest power of two.
	int m3dIsPOW2(int iValue) {
		int nPow2 = 1;

		while (iValue > nPow2)
			nPow2 = (nPow2 << 1);

		return nPow2;
	}

	// /////////////////////////////////////////////////////////////////////////////
	// Inline accessor functions for people who just can't count to 3 - Vectors
	public static float m3dGetVectorX(M3DVector4f v) {
		return v.xyzw[0];
	}

	public static double m3dGetVectorX(M3DVector4d v) {
		return v.xyzw[0];
	}

	public static float m3dGetVectorY(M3DVector4f v) {
		return v.xyzw[1];
	}

	public static double m3dGetVectorY(M3DVector4d v) {
		return v.xyzw[1];
	}

	public static float m3dGetVectorZ(M3DVector4f v) {
		return v.xyzw[2];
	}

	public static double m3dGetVectorZ(M3DVector4d v) {
		return v.xyzw[2];
	}

	public static float m3dGetVectorW(M3DVector4f v) {
		return v.xyzw[3];
	}

	public static double m3dGetVectorW(M3DVector4d v) {
		return v.xyzw[3];
	}

	public static void m3dSetVectorX(M3DVector4f v, float x) {
		v.xyzw[0] = x;
	}

	public static void m3dSetVectorX(M3DVector4d v, double x) {
		v.xyzw[0] = x;
	}

	public static void m3dSetVectorY(M3DVector4f v, float y) {
		v.xyzw[1] = y;
	}

	public static void m3dSetVectorY(M3DVector4d v, double y) {
		v.xyzw[1] = y;
	}

	public static void m3dSetVectorZ(M3DVector4f v, float z) {
		v.xyzw[2] = z;
	}

	public static void m3dSetVectorZ(M3DVector4d v, double z) {
		v.xyzw[2] = z;
	}

	public static void m3dSetVectorW(M3DVector4f v, float w) {
		v.xyzw[3] = w;
	}

	public static void m3dSetVectorW(M3DVector4d v, double w) {
		v.xyzw[3] = w;
	}

	// for 3
	public static float m3dGetVectorX(M3DVector3f v) {
		return v.xyz[0];
	}

	public static double m3dGetVectorX(M3DVector3d v) {
		return v.xyz[0];
	}

	public static float m3dGetVectorY(M3DVector3f v) {
		return v.xyz[1];
	}

	public static double m3dGetVectorY(M3DVector3d v) {
		return v.xyz[1];
	}

	public static float m3dGetVectorZ(M3DVector3f v) {
		return v.xyz[2];
	}

	public static double m3dGetVectorZ(M3DVector3d v) {
		return v.xyz[2];
	}

	public static void m3dSetVectorX(M3DVector3f v, float x) {
		v.xyz[0] = x;
	}

	public static void m3dSetVectorX(M3DVector3d v, double x) {
		v.xyz[0] = x;
	}

	public static void m3dSetVectorY(M3DVector3f v, float y) {
		v.xyz[1] = y;
	}

	public static void m3dSetVectorY(M3DVector3d v, double y) {
		v.xyz[1] = y;
	}

	public static void m3dSetVectorZ(M3DVector3f v, float z) {
		v.xyz[2] = z;
	}

	public static void m3dSetVectorZ(M3DVector3d v, double z) {
		v.xyz[2] = z;
	}

	// for 2
	public static float m3dGetVectorX(M3DVector2f v) {
		return v.xy[0];
	}

	public static double m3dGetVectorX(M3DVector2d v) {
		return v.xy[0];
	}

	public static float m3dGetVectorY(M3DVector2f v) {
		return v.xy[1];
	}

	public static double m3dGetVectorY(M3DVector2d v) {
		return v.xy[1];
	}

	public static void m3dSetVectorX(M3DVector2f v, float x) {
		v.xy[0] = x;
	}

	public static void m3dSetVectorX(M3DVector2d v, double x) {
		v.xy[0] = x;
	}

	public static void m3dSetVectorY(M3DVector2f v, float y) {
		v.xy[1] = y;
	}

	public static void m3dSetVectorY(M3DVector2d v, double y) {
		v.xy[1] = y;
	}

	// Load Vector with (x, y, z, w).
	public static void m3dLoadVector2(M3DVector2f v, float x, float y) {
		v.xy[0] = x;
		v.xy[1] = y;
	}

	public static void m3dLoadVector2(M3DVector2d v, double x, double y) {
		v.xy[0] = x;
		v.xy[1] = y;
	}

	public static void m3dLoadVector3(M3DVector3f v, float x, float y, float z) {
		v.xyz[0] = x;
		v.xyz[0] = y;
		v.xyz[0] = z;
	}

	public static void m3dLoadVector3(M3DVector3d v, double x, double y,
			double z) {
		v.xyz[0] = x;
		v.xyz[0] = y;
		v.xyz[0] = z;
	}

	public static void m3dLoadVector4(M3DVector4f v, float x, float y, float z,
			float w) {
		v.xyzw[0] = x;
		v.xyzw[1] = y;
		v.xyzw[2] = z;
		v.xyzw[3] = w;
	}

	public static void m3dLoadVector4(M3DVector4d v, double x, double y,
			double z, double w) {
		v.xyzw[0] = x;
		v.xyzw[1] = y;
		v.xyzw[2] = z;
		v.xyzw[3] = w;
	}

	// //////////////////////////////////////////////////////////////////////////////
	// Copy vector src into vector dst
	public static void m3dCopyVector2(M3DVector2f dst, M3DVector2f src) {
		System.arraycopy(src.xy, 0, dst.xy, 0, 2);
	}

	public static void m3dCopyVector2(M3DVector2d dst, M3DVector2d src) {
		System.arraycopy(src.xy, 0, dst.xy, 0, 2);
	}

	public static void m3dCopyVector3(M3DVector3f dst, M3DVector3f src) {
		System.arraycopy(src.xyz, 0, dst.xyz, 0, 3);
	}

	public static void m3dCopyVector3(M3DVector3d dst, M3DVector3d src) {
		System.arraycopy(src.xyz, 0, dst.xyz, 0, 3);
	}

	public static void m3dCopyVector4(M3DVector4f dst, M3DVector4f src) {
		System.arraycopy(src.xyzw, 0, dst.xyzw, 0, 4);
	}

	public static void m3dCopyVector4(M3DVector4d dst, M3DVector4d src) {
		System.arraycopy(src.xyzw, 0, dst.xyzw, 0, 4);
	}

	// //////////////////////////////////////////////////////////////////////////////
	// Add Vectors (r, a, b) r = a + b
	public static void m3dAddVectors2(M3DVector2f r, M3DVector2f a,
			M3DVector2f b) {
		r.xy[0] = a.xy[0] + b.xy[0];
		r.xy[1] = a.xy[1] + b.xy[1];
	}

	public static void m3dAddVectors2(M3DVector2d r, M3DVector2d a,
			M3DVector2d b) {
		r.xy[0] = a.xy[0] + b.xy[0];
		r.xy[1] = a.xy[1] + b.xy[1];
	}

	public static void m3dAddVectors3(M3DVector3f r, M3DVector3f a,
			M3DVector3f b) {
		r.xyz[0] = a.xyz[0] + b.xyz[0];
		r.xyz[1] = a.xyz[1] + b.xyz[1];
		r.xyz[2] = a.xyz[2] + b.xyz[2];
	}

	public static void m3dAddVectors3(M3DVector3d r, M3DVector3d a,
			M3DVector3d b) {
		r.xyz[0] = a.xyz[0] + b.xyz[0];
		r.xyz[1] = a.xyz[1] + b.xyz[1];
		r.xyz[2] = a.xyz[2] + b.xyz[2];
	}

	public static void m3dAddVectors4(M3DVector4f r, M3DVector4f a,
			M3DVector4f b) {
		r.xyzw[0] = a.xyzw[0] + b.xyzw[0];
		r.xyzw[1] = a.xyzw[1] + b.xyzw[1];
		r.xyzw[2] = a.xyzw[2] + b.xyzw[2];
		r.xyzw[3] = a.xyzw[3] + b.xyzw[3];
	}

	public static void m3dAddVectors4(M3DVector4d r, M3DVector4d a,
			M3DVector4d b) {
		r.xyzw[0] = a.xyzw[0] + b.xyzw[0];
		r.xyzw[1] = a.xyzw[1] + b.xyzw[1];
		r.xyzw[2] = a.xyzw[2] + b.xyzw[2];
		r.xyzw[3] = a.xyzw[3] + b.xyzw[3];
	}

	// //////////////////////////////////////////////////////////////////////////////
	// Subtract Vectors (r, a, b) r = a - b
	public static void m3dSubtractVectors2(M3DVector2f r, M3DVector2f a,
			M3DVector2f b) {
		r.xy[0] = a.xy[0] - b.xy[0];
		r.xy[1] = a.xy[1] - b.xy[1];
	}

	public static void m3dSubtractVectors2(M3DVector2d r, M3DVector2d a,
			M3DVector2d b) {
		r.xy[0] = a.xy[0] - b.xy[0];
		r.xy[1] = a.xy[1] - b.xy[1];
	}

	public static void m3dSubtractVectors3(M3DVector3f r, M3DVector3f a,
			M3DVector3f b) {
		r.xyz[0] = a.xyz[0] - b.xyz[0];
		r.xyz[1] = a.xyz[1] - b.xyz[1];
		r.xyz[2] = a.xyz[2] - b.xyz[2];
	}

	public static void m3dSubtractVectors3(M3DVector3d r, M3DVector3d a,
			M3DVector3d b) {
		r.xyz[0] = a.xyz[0] - b.xyz[0];
		r.xyz[1] = a.xyz[1] - b.xyz[1];
		r.xyz[2] = a.xyz[2] - b.xyz[2];
	}

	public static void m3dSubtractVectors4(M3DVector4f r, M3DVector4f a,
			M3DVector4f b) {
		r.xyzw[0] = a.xyzw[0] - b.xyzw[0];
		r.xyzw[1] = a.xyzw[1] - b.xyzw[1];
		r.xyzw[2] = a.xyzw[2] - b.xyzw[2];
		r.xyzw[3] = a.xyzw[3] - b.xyzw[3];
	}

	public static void m3dSubtractVectors4(M3DVector4d r, M3DVector4d a,
			M3DVector4d b) {
		r.xyzw[0] = a.xyzw[0] - b.xyzw[0];
		r.xyzw[1] = a.xyzw[1] - b.xyzw[1];
		r.xyzw[2] = a.xyzw[2] - b.xyzw[2];
		r.xyzw[3] = a.xyzw[3] - b.xyzw[3];
	}

	// /////////////////////////////////////////////////////////////////////////////////////
	// Scale Vectors (in place)
	public static void m3dScaleVector2(M3DVector2f v, float scale) {
		v.xy[0] *= scale;
		v.xy[1] *= scale;
	}

	public static void m3dScaleVector2(M3DVector2d v, double scale) {
		v.xy[0] *= scale;
		v.xy[1] *= scale;
	}

	public static void m3dScaleVector3(M3DVector3f v, float scale) {
		v.xyz[0] *= scale;
		v.xyz[1] *= scale;
		v.xyz[2] *= scale;
	}

	public static void m3dScaleVector3(M3DVector3d v, double scale) {
		v.xyz[0] *= scale;
		v.xyz[1] *= scale;
		v.xyz[2] *= scale;
	}

	public static void m3dScaleVector4(M3DVector4f v, float scale) {
		v.xyzw[0] *= scale;
		v.xyzw[1] *= scale;
		v.xyzw[2] *= scale;
		v.xyzw[3] *= scale;
	}

	public static void m3dScaleVector4(M3DVector4d v, double scale) {
		v.xyzw[0] *= scale;
		v.xyzw[1] *= scale;
		v.xyzw[2] *= scale;
		v.xyzw[3] *= scale;
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Cross Product
	// u x v = result
	// We only need one version for floats, and one version for doubles. A 3
	// component
	// vector fits in a 4 component vector. If M3DVector4d or M3DVector4f are
	// passed
	// we will be OK because 4th component is not used.
	public static void m3dCrossProduct(M3DVector3f result, M3DVector3f u,
			M3DVector3f v) {
		result.xyz[0] = u.xyz[1] * v.xyz[2] - v.xyz[1] * u.xyz[2];
		result.xyz[1] = -u.xyz[0] * v.xyz[2] + v.xyz[0] * u.xyz[2];
		result.xyz[2] = u.xyz[0] * v.xyz[1] - v.xyz[0] * u.xyz[1];
	}

	public static void m3dCrossProduct(M3DVector3d result, M3DVector3d u,
			M3DVector3d v) {
		result.xyz[0] = u.xyz[1] * v.xyz[2] - v.xyz[1] * u.xyz[2];
		result.xyz[1] = -u.xyz[0] * v.xyz[2] + v.xyz[0] * u.xyz[2];
		result.xyz[2] = u.xyz[0] * v.xyz[1] - v.xyz[0] * u.xyz[1];
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Dot Product, only for three component vectors
	// return u dot v
	public static float m3dDotProduct(M3DVector3f u, M3DVector3f v) {
		return u.xyz[0] * v.xyz[0] + u.xyz[1] * v.xyz[1] + u.xyz[2] * v.xyz[2];
	}

	public static double m3dDotProduct(M3DVector3d u, M3DVector3d v) {
		return u.xyz[0] * v.xyz[0] + u.xyz[1] * v.xyz[1] + u.xyz[2] * v.xyz[2];
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Angle between vectors, only for three component vectors. Angle is in
	// radians...
	public static float m3dGetAngleBetweenVectors(M3DVector3f u, M3DVector3f v) {
		float dTemp = m3dDotProduct(u, v);
		return (float) (Math.acos((double) dTemp));
	}

	public static double m3dGetAngleBetweenVectors(M3DVector3d u, M3DVector3d v) {
		double dTemp = m3dDotProduct(u, v);
		return Math.acos(dTemp);
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Get Square of a vectors length
	// Only for three component vectors
	public static float m3dGetVectorLengthSquared(M3DVector3f u) {
		return (u.xyz[0] * u.xyz[0]) + (u.xyz[1] * u.xyz[1])
				+ (u.xyz[2] * u.xyz[2]);
	}

	public static double m3dGetVectorLengthSquared(M3DVector3d u) {
		return (u.xyz[0] * u.xyz[0]) + (u.xyz[1] * u.xyz[1])
				+ (u.xyz[2] * u.xyz[2]);
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Get lenght of vector
	// Only for three component vectors.
	public static float m3dGetVectorLength(M3DVector3f u) {
		return (float) (Math.sqrt((double) (m3dGetVectorLengthSquared(u))));
	}

	public static double m3dGetVectorLength(M3DVector3d u) {
		return Math.sqrt(m3dGetVectorLengthSquared(u));
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Normalize a vector
	// Scale a vector to unit length. Easy, just scale the vector by it's length
	public static void m3dNormalizeVector(M3DVector3f u) {
		m3dScaleVector3(u, 1.0f / m3dGetVectorLength(u));
	}

	public static void m3dNormalizeVector(M3DVector3d u) {
		m3dScaleVector3(u, 1.0 / m3dGetVectorLength(u));
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// Get the distance between two points. The distance between two points is
	// just
	// the magnitude of the difference between two vectors
	//
	public static float m3dGetDistanceSquared(M3DVector3f u, M3DVector3f v) {
		return 0;
	}

	public static double m3dGetDistanceSquared(M3DVector3d u, M3DVector3d v) {
		return 0;
	}

	public static double m3dGetDistance(M3DVector3d u, M3DVector3d v) {
		return Math.sqrt(m3dGetDistanceSquared(u, v));
	}

	public static float m3dGetDistance(M3DVector3f u, M3DVector3f v) {
		return (float) (Math.sqrt(m3dGetDistanceSquared(u, v)));
	}

	public static float m3dGetMagnitudeSquared(M3DVector3f u) {
		return u.xyz[0] * u.xyz[0] + u.xyz[1] * u.xyz[1] + u.xyz[2] * u.xyz[2];
	}

	public static double m3dGetMagnitudeSquared(M3DVector3d u) {
		return u.xyz[0] * u.xyz[0] + u.xyz[1] * u.xyz[1] + u.xyz[2] * u.xyz[2];
	}

	public static float m3dGetMagnitude(M3DVector3f u) {
		return (float) (Math.sqrt(m3dGetMagnitudeSquared(u)));
	}

	public static double m3dGetMagnitude(M3DVector3d u) {
		return Math.sqrt(m3dGetMagnitudeSquared(u));
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Matrix functions
	// Both floating point and double precision 3x3 and 4x4 matricies are
	// supported.
	// No support is included for arbitrarily dimensioned matricies on purpose,
	// since
	// the 3x3 and 4x4 matrix routines are the most common for the purposes of
	// this
	// library. Matrices are column major, like OpenGL matrices.
	// Unlike the vector functions, some of these are going to have to not be
	// inlined,
	// although many will be.

	// Copy Matrix
	// Brain-dead memcpy
	public static void m3dCopyMatrix33(M3DMatrix33f dst, M3DMatrix33f src) {
		System.arraycopy(src.matrix33, 0, dst.matrix33, 0, 9);
	}

	public static void m3dCopyMatrix33(M3DMatrix33d dst, M3DMatrix33d src) {
		System.arraycopy(src.matrix33, 0, dst.matrix33, 0, 9);
	}

	public static void m3dCopyMatrix44(M3DMatrix44f dst, M3DMatrix44f src) {
		System.arraycopy(src.matrix44, 0, dst.matrix44, 0, 16);
	}

	public static void m3dCopyMatrix44(M3DMatrix44d dst, M3DMatrix44d src) {
		System.arraycopy(src.matrix44, 0, dst.matrix44, 0, 16);
	}

	// LoadIdentity
	// Implemented in Math3d.cpp
	public static void m3dLoadIdentity33(M3DMatrix33f m) {

	}

	public static void m3dLoadIdentity33(M3DMatrix33d m) {

	}

	public static void m3dLoadIdentity44(M3DMatrix44f m) {

	}

	public static void m3dLoadIdentity44(M3DMatrix44d m) {

	}

	// ///////////////////////////////////////////////////////////////////////////
	// Get/Set Column.
	public static void m3dGetMatrixColumn33(M3DVector3f dst, M3DMatrix33f src,
			int column) {
		System.arraycopy(src.matrix33, 3 * column, dst.xyz, 0, 3);
	}

	public static void m3dGetMatrixColumn33(M3DVector3d dst, M3DMatrix33d src,
			int column) {
		System.arraycopy(src.matrix33, 3 * column, dst.xyz, 0, 3);
	}

	public static void m3dSetMatrixColumn33(M3DMatrix33f dst, M3DVector3f src,
			int column) {
		System.arraycopy(src, 0, dst.matrix33, 3 * column, 3);
	}

	public static void m3dSetMatrixColumn33(M3DMatrix33d dst, M3DVector3d src,
			int column) {
		System.arraycopy(src, 0, dst.matrix33, 3 * column, 3);
	}

	public static void m3dGetMatrixColumn44(M3DVector4f dst, M3DMatrix44f src,
			int column) {
		System.arraycopy(src.matrix44, 4 * column, dst.xyzw, 0, 4);
	}

	public static void m3dGetMatrixColumn44(M3DVector4d dst, M3DMatrix44d src,
			int column) {
		System.arraycopy(src.matrix44, 4 * column, dst.xyzw, 0, 4);
	}

	public static void m3dSetMatrixColumn44(M3DMatrix44f dst, M3DVector4f src,
			int column) {
		System.arraycopy(src, 0, dst.matrix44, 4 * column, 4);
	}

	public static void m3dSetMatrixColumn44(M3DMatrix44d dst, M3DVector4d src,
			int column) {
		System.arraycopy(src, 0, dst.matrix44, 4 * column, 4);
	}
	
	public static void m3dSetMatrixColumn44(M3DMatrix44f dst, M3DVector3f src, int column) {
		switch (column) {
		case 0:
			dst.matrix44[0] = src.xyz[0];
			dst.matrix44[1] = src.xyz[1];
			dst.matrix44[2] = src.xyz[2];
			break;
		case 1:
			dst.matrix44[4] = src.xyz[0];
			dst.matrix44[5] = src.xyz[1];
			dst.matrix44[6] = src.xyz[2];
			break;
		case 2:
			dst.matrix44[8] = src.xyz[0];
			dst.matrix44[9] = src.xyz[1];
			dst.matrix44[10] = src.xyz[2];
			break;
		case 3:
			dst.matrix44[12] = src.xyz[0];
			dst.matrix44[13] = src.xyz[1];
			dst.matrix44[14] = src.xyz[2];
			break;
		default:
			break;
		}
		return;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// Get/Set RowCol - Remember column major ordering...
	// Provides for element addressing
	public static void m3dSetMatrixRowCol33(M3DMatrix33f m, int row, int col,
			float value) {
		m.matrix33[(col * 3) + row] = value;
	}

	public static float m3dGetMatrixRowCol33(M3DMatrix33f m, int row, int col) {
		return m.matrix33[(col * 3) + row];
	}

	public static void m3dSetMatrixRowCol33(M3DMatrix33d m, int row, int col,
			double value) {
		m.matrix33[(col * 3) + row] = value;
	}

	public static double m3dGetMatrixRowCol33(M3DMatrix33d m, int row, int col) {
		return m.matrix33[(col * 3) + row];
	}

	public static void m3dSetMatrixRowCol44(M3DMatrix44f m, int row, int col,
			float value) {
		m.matrix44[(col * 4) + row] = value;
	}

	public static float m3dGetMatrixRowCol44(M3DMatrix44f m, int row, int col) {
		return m.matrix44[(col * 4) + row];
	}

	public static void m3dSetMatrixRowCol44(M3DMatrix44d m, int row, int col,
			double value) {
		m.matrix44[(col * 4) + row] = value;
	}

	public static double m3dGetMatrixRowCol44(M3DMatrix44d m, int row, int col) {
		return m.matrix44[(col * 4) + row];
	}

	// /////////////////////////////////////////////////////////////////////////////
	// Extract a rotation matrix from a 4x4 matrix
	// Extracts the rotation matrix (3x3) from a 4x4 matrix
	public static void m3dExtractRotation(M3DMatrix33f dst, M3DMatrix44f src) {
		System.arraycopy(src.matrix44, 0, dst.matrix33, 0, 3);// X column
		System.arraycopy(src.matrix44, 4, dst.matrix33, 3, 3);// Y column
		System.arraycopy(src.matrix44, 8, dst.matrix33, 6, 3);// Z column
	}

	// Ditto above, but for doubles
	public static void m3dExtractRotation(M3DMatrix33d dst, M3DMatrix44d src) {
		System.arraycopy(src.matrix44, 0, dst.matrix33, 0, 3);// X column
		System.arraycopy(src.matrix44, 4, dst.matrix33, 3, 3);// Y column
		System.arraycopy(src.matrix44, 8, dst.matrix33, 6, 3);// Z column
	}

	// Inject Rotation (3x3) into a full 4x4 matrix...
	public static void m3dInjectRotation(M3DMatrix44f dst, M3DMatrix33f src) {
		System.arraycopy(src.matrix33, 0, dst.matrix44, 0, 3);
		System.arraycopy(src.matrix33, 3, dst.matrix44, 4, 3);
		System.arraycopy(src.matrix33, 6, dst.matrix44, 8, 3);
	}

	// Ditto above for doubles
	public static void m3dInjectRotation(M3DMatrix44d dst, M3DMatrix33d src) {
		System.arraycopy(src.matrix33, 0, dst.matrix44, 0, 3);
		System.arraycopy(src.matrix33, 3, dst.matrix44, 4, 3);
		System.arraycopy(src.matrix33, 6, dst.matrix44, 8, 3);
	}

	// //////////////////////////////////////////////////////////////////////////////
	// MultMatrix
	public static void m3dMatrixMultiply44(M3DMatrix44f product,
			M3DMatrix44f a, M3DMatrix44f b) {

	}

	public static void m3dMatrixMultiply44(M3DMatrix44d product,
			M3DMatrix44d a, M3DMatrix44d b) {

	}

	public static void m3dMatrixMultiply33(M3DMatrix33f product,
			M3DMatrix33f a, M3DMatrix33f b) {

	}

	public static void m3dMatrixMultiply33(M3DMatrix33d product,
			M3DMatrix33d a, M3DMatrix33d b) {

	}

	// Transform - Does rotation and translation via a 4x4 matrix. Transforms
	// a point or vector.
	// By-the-way __inline means I'm asking the compiler to do a cost/benefit
	// analysis. If
	// these are used frequently, they may not be inlined to save memory. I'm
	// experimenting
	// with this....
	public static void m3dTransformVector3(M3DVector3f vOut, M3DVector3f v,
			M3DMatrix44f m) {
		vOut.xyz[0] = m.matrix44[0] * v.xyz[0] + m.matrix44[4] * v.xyz[1]
				+ m.matrix44[8] * v.xyz[2] + m.matrix44[12];// * v[3];
		vOut.xyz[1] = m.matrix44[1] * v.xyz[0] + m.matrix44[5] * v.xyz[1]
				+ m.matrix44[9] * v.xyz[2] + m.matrix44[13];// * v[3];
		vOut.xyz[2] = m.matrix44[2] * v.xyz[0] + m.matrix44[6] * v.xyz[1]
				+ m.matrix44[10] * v.xyz[2] + m.matrix44[14];// * v[3];
	}

	// Ditto above, but for doubles
	public static void m3dTransformVector3(M3DVector3d vOut, M3DVector3d v,
			M3DMatrix44d m) {
		vOut.xyz[0] = m.matrix44[0] * v.xyz[0] + m.matrix44[4] * v.xyz[1]
				+ m.matrix44[8] * v.xyz[2] + m.matrix44[12];// * v[3];
		vOut.xyz[1] = m.matrix44[1] * v.xyz[0] + m.matrix44[5] * v.xyz[1]
				+ m.matrix44[9] * v.xyz[2] + m.matrix44[13];// * v[3];
		vOut.xyz[2] = m.matrix44[2] * v.xyz[0] + m.matrix44[6] * v.xyz[1]
				+ m.matrix44[10] * v.xyz[2] + m.matrix44[14];// * v[3];

	}

	public static void m3dTransformVector4(M3DVector4f vOut, M3DVector4f v,
			M3DMatrix44f m) {
		vOut.xyzw[0] = m.matrix44[0] * v.xyzw[0] + m.matrix44[4] * v.xyzw[1]
				+ m.matrix44[8] * v.xyzw[2] + m.matrix44[12] * v.xyzw[3];
		vOut.xyzw[1] = m.matrix44[1] * v.xyzw[0] + m.matrix44[5] * v.xyzw[1]
				+ m.matrix44[9] * v.xyzw[2] + m.matrix44[13] * v.xyzw[3];
		vOut.xyzw[2] = m.matrix44[2] * v.xyzw[0] + m.matrix44[6] * v.xyzw[1]
				+ m.matrix44[10] * v.xyzw[2] + m.matrix44[14] * v.xyzw[3];
		vOut.xyzw[3] = m.matrix44[3] * v.xyzw[0] + m.matrix44[7] * v.xyzw[1]
				+ m.matrix44[11] * v.xyzw[2] + m.matrix44[15] * v.xyzw[3];
	}

	// Ditto above, but for doubles
	public static void m3dTransformVector4(M3DVector4d vOut, M3DVector4d v,
			M3DMatrix44d m) {
		vOut.xyzw[0] = m.matrix44[0] * v.xyzw[0] + m.matrix44[4] * v.xyzw[1]
				+ m.matrix44[8] * v.xyzw[2] + m.matrix44[12] * v.xyzw[3];
		vOut.xyzw[1] = m.matrix44[1] * v.xyzw[0] + m.matrix44[5] * v.xyzw[1]
				+ m.matrix44[9] * v.xyzw[2] + m.matrix44[13] * v.xyzw[3];
		vOut.xyzw[2] = m.matrix44[2] * v.xyzw[0] + m.matrix44[6] * v.xyzw[1]
				+ m.matrix44[10] * v.xyzw[2] + m.matrix44[14] * v.xyzw[3];
		vOut.xyzw[3] = m.matrix44[3] * v.xyzw[0] + m.matrix44[7] * v.xyzw[1]
				+ m.matrix44[11] * v.xyzw[2] + m.matrix44[15] * v.xyzw[3];
	}

	// Just do the rotation, not the translation... this is usually done with a
	// 3x3
	// Matrix.
	public static void m3dRotateVector(M3DVector3f vOut, M3DVector3f p,
			M3DMatrix33f m) {
		vOut.xyz[0] = m.matrix33[0] * p.xyz[0] + m.matrix33[3] * p.xyz[1]
				+ m.matrix33[6] * p.xyz[2];
		vOut.xyz[1] = m.matrix33[1] * p.xyz[0] + m.matrix33[4] * p.xyz[1]
				+ m.matrix33[7] * p.xyz[2];
		vOut.xyz[2] = m.matrix33[2] * p.xyz[0] + m.matrix33[5] * p.xyz[1]
				+ m.matrix33[8] * p.xyz[2];
	}

	// Ditto above, but for doubles
	public static void m3dRotateVector(M3DVector3d vOut, M3DVector3d p,
			M3DMatrix33d m) {
		vOut.xyz[0] = m.matrix33[0] * p.xyz[0] + m.matrix33[3] * p.xyz[1]
				+ m.matrix33[6] * p.xyz[2];
		vOut.xyz[1] = m.matrix33[1] * p.xyz[0] + m.matrix33[4] * p.xyz[1]
				+ m.matrix33[7] * p.xyz[2];
		vOut.xyz[2] = m.matrix33[2] * p.xyz[0] + m.matrix33[5] * p.xyz[1]
				+ m.matrix33[8] * p.xyz[2];
	}

	// Scale a matrix (I don't beleive in Scaling matricies ;-)
	// Yes, it's faster to loop backwards... These could be
	// unrolled... but eh... if you find this is a bottleneck,
	// then you should unroll it yourself
	public static void m3dScaleMatrix33(M3DMatrix33f m, float scale) {
		for (int i = 8; i >= 0; i--)
			m.matrix33[i] *= scale;
	}

	public static void m3dScaleMatrix33(M3DMatrix33d m, double scale) {
		for (int i = 8; i >= 0; i--)
			m.matrix33[i] *= scale;
	}

	public static void m3dScaleMatrix44(M3DMatrix44f m, float scale) {
		for (int i = 15; i >= 0; i--)
			m.matrix44[i] *= scale;
	}

	public static void m3dScaleMatrix44(M3DMatrix44d m, double scale) {
		for (int i = 15; i >= 0; i--)
			m.matrix44[i] *= scale;
	}

	// Create a Rotation matrix
	// Implemented in math.cpp
	public static void m3dRotationMatrix33(M3DMatrix33f m, float angle,
			float x, float y, float z) {

	}

	public static void m3dRotationMatrix33(M3DMatrix33d m, double angle,
			double x, double y, double z) {

	}

	public static void m3dRotationMatrix44(M3DMatrix44f m, float angle,
			float x, float y, float z) {

	}

	public static void m3dRotationMatrix44(M3DMatrix44d m, double angle,
			double x, double y, double z) {

	}

	// Create a Translation matrix. Only 4x4 matrices have translation
	// components
	public static void m3dTranslationMatrix44(M3DMatrix44f m, float x, float y,
			float z) {
		m3dLoadIdentity44(m);
		m.matrix44[12] = x;
		m.matrix44[13] = y;
		m.matrix44[14] = z;
	}

	public static void m3dTranslationMatrix44(M3DMatrix44d m, double x,
			double y, double z) {
		m3dLoadIdentity44(m);
		m.matrix44[12] = x;
		m.matrix44[13] = y;
		m.matrix44[14] = z;
	}

	// Translate matrix. Only 4x4 matrices supported
	public static void m3dTranslateMatrix44(M3DMatrix44f m, float x, float y,
			float z) {
		m.matrix44[12] += x;
		m.matrix44[13] += y;
		m.matrix44[14] += z;
	}

	public static void m3dTranslateMatrix44(M3DMatrix44d m, double x, double y,
			double z) {
		m.matrix44[12] += x;
		m.matrix44[13] += y;
		m.matrix44[14] += z;
	}

	// Scale matrix. Only 4x4 matrices supported
	public static void m3dScaleMatrix44(M3DMatrix44f m, float x, float y,
			float z) {
		m.matrix44[0] *= x;
		m.matrix44[5] *= y;
		m.matrix44[10] *= z;
	}

	public static void m3dScaleMatrix44(M3DMatrix44d m, double x, double y,
			double z) {
		m.matrix44[0] *= x;
		m.matrix44[5] *= y;
		m.matrix44[10] *= z;
	}

	// Transpose/Invert - Only 4x4 matricies supported
	public static void m3dTransposeMatrix44(M3DMatrix44f dst, M3DMatrix44f src) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				dst.matrix44[(j * 4) + i] = src.matrix44[(i * 4) + j];
			}
		}
	}

	public static void m3dTransposeMatrix44(M3DMatrix44d dst, M3DMatrix44d src) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				dst.matrix44[(j * 4) + i] = src.matrix44[(i * 4) + j];
			}
		}
	}

	public static boolean m3dInvertMatrix44(M3DMatrix44f dst, M3DMatrix44f src) {
		return false;
	}

	public static boolean m3dInvertMatrix44(M3DMatrix44d dst, M3DMatrix44d src) {
		return false;
	}

	// /////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////
	// Other Miscellaneous functions

	// Find a normal from three points
	// Implemented in math3d.cpp
	public static void m3dFindNormal(M3DVector3f result, M3DVector3f point1,
			M3DVector3f point2, M3DVector3f point3) {
		M3DVector3f v1 = new M3DVector3f();
		M3DVector3f v2 = new M3DVector3f();		// Temporary vectors

		// Calculate two vectors from the three points. Assumes counter clockwise
		// winding!
		v1.xyz[0] = point1.xyz[0] - point2.xyz[0];
		v1.xyz[1] = point1.xyz[1] - point2.xyz[1];
		v1.xyz[2] = point1.xyz[2] - point2.xyz[2];

		v2.xyz[0] = point2.xyz[0] - point3.xyz[0];
		v2.xyz[1] = point2.xyz[1] - point3.xyz[1];
		v2.xyz[2] = point2.xyz[2] - point3.xyz[2];

		// Take the cross product of the two vectors to get
		// the normal vector.
		m3dCrossProduct(result, v1, v2);
	}

	public static void m3dFindNormal(M3DVector3d result, M3DVector3d point1,
			M3DVector3d point2, M3DVector3d point3) {
		M3DVector3d v1 = new M3DVector3d();
		M3DVector3d v2 = new M3DVector3d();		// Temporary vectors

		// Calculate two vectors from the three points. Assumes counter clockwise
		// winding!
		v1.xyz[0] = point1.xyz[0] - point2.xyz[0];
		v1.xyz[1] = point1.xyz[1] - point2.xyz[1];
		v1.xyz[2] = point1.xyz[2] - point2.xyz[2];

		v2.xyz[0] = point2.xyz[0] - point3.xyz[0];
		v2.xyz[1] = point2.xyz[1] - point3.xyz[1];
		v2.xyz[2] = point2.xyz[2] - point3.xyz[2];

		// Take the cross product of the two vectors to get
		// the normal vector.
		m3dCrossProduct(result, v1, v2);
	}
	
	// Calculates the signed distance of a point to a plane
	public static float m3dGetDistanceToPlane( M3DVector3f point,  M3DVector4f plane)
	           { return point.xyz[0]*plane.xyzw[0] + point.xyz[1]*plane.xyzw[1] + point.xyz[2]*plane.xyzw[2] + plane.xyzw[3]; }

	public static double m3dGetDistanceToPlane( M3DVector3d point,  M3DVector4d plane)
	           { return point.xyz[0]*plane.xyzw[0] + point.xyz[1]*plane.xyzw[1] + point.xyz[2]*plane.xyzw[2] + plane.xyzw[3]; }


	// Get plane equation from three points and a normal
	public static void m3dGetPlaneEquation(M3DVector4f planeEq,  M3DVector3f p1,  M3DVector3f p2,  M3DVector3f p3){
		
	}
	public static void m3dGetPlaneEquation(M3DVector4d planeEq,  M3DVector3d p1,  M3DVector3d p2,  M3DVector3d p3){
		
	}

	// Determine if a ray intersects a sphere
	public static double m3dRaySphereTest( M3DVector3d point,  M3DVector3d ray,  M3DVector3d sphereCenter, double sphereRadius){
	return 0;	
	}
	public static float m3dRaySphereTest( M3DVector3f point,  M3DVector3f ray,  M3DVector3f sphereCenter, float sphereRadius){
		return 0;
	}

	// Etc. etc.

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	// Faster (and more robust) replacements for gluProject
	public static void m3dProjectXY( M3DVector2f vPointOut,  M3DMatrix44f mModelView,  M3DMatrix44f mProjection,  int iViewPort[],  M3DVector3f vPointIn) {
		
	}
	public static void m3dProjectXYZ(M3DVector3f vPointOut,  M3DMatrix44f mModelView,  M3DMatrix44f mProjection,  int iViewPort[],  M3DVector3f vPointIn){
		
	}



	//////////////////////////////////////////////////////////////////////////////////////////////////
	// This function does a three dimensional Catmull-Rom "spline" interpolation between p1 and p2
	public static void m3dCatmullRom(M3DVector3f vOut, M3DVector3f vP0, M3DVector3f vP1, M3DVector3f vP2, M3DVector3f vP3, float t){
		
	}
	public static void m3dCatmullRom(M3DVector3d vOut, M3DVector3d vP0, M3DVector3d vP1, M3DVector3d vP2, M3DVector3d vP3, double t){
		
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Compare floats and doubles... 
	public static boolean m3dCloseEnough(float fCandidate, float fCompare, float fEpsilon)
	    {
	    return (Math.abs(fCandidate - fCompare) < fEpsilon);
	    }
	    
	public static boolean m3dCloseEnough(double dCandidate, double dCompare, double dEpsilon)
	    {
	    return (Math.abs(dCandidate - dCompare) < dEpsilon);
	    }    
	 
	////////////////////////////////////////////////////////////////////////////
	// Used for normal mapping. Finds the tangent bases for a triangle...
	// Only a floating point implementation is provided.
	public static void m3dCalculateTangentBasis( M3DVector3f pvTriangle[],  M3DVector2f pvTexCoords[],  M3DVector3f N, M3DVector3f vTangent){
		
	}

	////////////////////////////////////////////////////////////////////////////
	// Smoothly step between 0 and 1 between edge1 and edge 2
	public static double m3dSmoothStep(double edge1, double edge2, double x){
		return 0;
	}
	public static float m3dSmoothStep(float edge1, float edge2, float x){
		return 0;
	}

	/////////////////////////////////////////////////////////////////////////////
	// Planar shadow Matrix
	public static void m3dMakePlanarShadowMatrix(M3DMatrix44d proj,  M3DVector4d planeEq,  M3DVector3d vLightPos){
		// These just make the code below easier to read. They will be 
		// removed by the optimizer.	
		double a = planeEq.xyzw[0];
		double b = planeEq.xyzw[1];
		double c = planeEq.xyzw[2];
		double d = planeEq.xyzw[3];

		double dx = -vLightPos.xyz[0];
		double dy = -vLightPos.xyz[1];
		double dz = -vLightPos.xyz[2];

		// Now build the projection matrix
		proj.matrix44[0] = b * dy + c * dz;
		proj.matrix44[1] = -a * dy;
		proj.matrix44[2] = -a * dz;
		proj.matrix44[3] = 0.0f;

		proj.matrix44[4] = -b * dx;
		proj.matrix44[5] = a * dx + c * dz;
		proj.matrix44[6] = -b * dz;
		proj.matrix44[7] = 0.0f;

		proj.matrix44[8] = -c * dx;
		proj.matrix44[9] = -c * dy;
		proj.matrix44[10] = a * dx + b * dy;
		proj.matrix44[11] = 0.0f;

		proj.matrix44[12] = -d * dx;
		proj.matrix44[13] = -d * dy;
		proj.matrix44[14] = -d * dz;
		proj.matrix44[15] = a * dx + b * dy + c * dz;
		// Shadow matrix ready
	}
	public static void m3dMakePlanarShadowMatrix(M3DMatrix44f proj,  M3DVector4f planeEq,  M3DVector3f vLightPos){
		// These just make the code below easier to read. They will be 
		// removed by the optimizer.	
		float a = planeEq.xyzw[0];
		float b = planeEq.xyzw[1];
		float c = planeEq.xyzw[2];
		float d = planeEq.xyzw[3];

		float dx = -vLightPos.xyz[0];
		float dy = -vLightPos.xyz[1];
		float dz = -vLightPos.xyz[2];

		// Now build the projection matrix
		proj.matrix44[0] = b * dy + c * dz;
		proj.matrix44[1] = -a * dy;
		proj.matrix44[2] = -a * dz;
		proj.matrix44[3] = 0.0f;

		proj.matrix44[4] = -b * dx;
		proj.matrix44[5] = a * dx + c * dz;
		proj.matrix44[6] = -b * dz;
		proj.matrix44[7] = 0.0f;

		proj.matrix44[8] = -c * dx;
		proj.matrix44[9] = -c * dy;
		proj.matrix44[10] = a * dx + b * dy;
		proj.matrix44[11] = 0.0f;

		proj.matrix44[12] = -d * dx;
		proj.matrix44[13] = -d * dy;
		proj.matrix44[14] = -d * dz;
		proj.matrix44[15] = a * dx + b * dy + c * dz;
		// Shadow matrix ready
	}

	public static double m3dClosestPointOnRay(M3DVector3d vPointOnRay,  M3DVector3d vRayOrigin,  M3DVector3d vUnitRayDir, 
								 M3DVector3d vPointInSpace){
		return 0;
	}

	public static float m3dClosestPointOnRay(M3DVector3f vPointOnRay,  M3DVector3f vRayOrigin,  M3DVector3f vUnitRayDir, 
								 M3DVector3f vPointInSpace){
		return 0;
	}

}
