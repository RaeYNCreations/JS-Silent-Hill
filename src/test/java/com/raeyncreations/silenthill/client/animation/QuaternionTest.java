package com.raeyncreations.silenthill.client.animation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for the Quaternion class.
 * Tests all key operations including creation, conversion, interpolation, and arithmetic.
 */
public class QuaternionTest {
    
    private static final float EPSILON = 1e-5f;
    
    /**
     * Asserts that two floats are approximately equal within epsilon tolerance.
     */
    private void assertApproxEquals(float expected, float actual, String message) {
        assertTrue(Math.abs(expected - actual) < EPSILON, 
            message + " - Expected: " + expected + ", Actual: " + actual);
    }
    
    /**
     * Asserts that two quaternions are approximately equal.
     */
    private void assertQuaternionEquals(Quaternion expected, Quaternion actual, String message) {
        assertApproxEquals(expected.x, actual.x, message + " (x)");
        assertApproxEquals(expected.y, actual.y, message + " (y)");
        assertApproxEquals(expected.z, actual.z, message + " (z)");
        assertApproxEquals(expected.w, actual.w, message + " (w)");
    }
    
    @Test
    public void testIdentity() {
        Quaternion identity = Quaternion.identity();
        assertEquals(0.0f, identity.x);
        assertEquals(0.0f, identity.y);
        assertEquals(0.0f, identity.z);
        assertEquals(1.0f, identity.w);
        assertTrue(identity.isNormalized(), "Identity quaternion should be normalized");
    }
    
    @Test
    public void testFromEulerZeroRotation() {
        Quaternion q = Quaternion.fromEuler(0, 0, 0);
        assertQuaternionEquals(Quaternion.identity(), q, "Zero Euler angles should create identity quaternion");
    }
    
    @Test
    public void testFromEuler90DegreeRotations() {
        // 90 degrees around X-axis
        Quaternion qx = Quaternion.fromEuler((float) Math.PI / 2, 0, 0);
        assertTrue(qx.isNormalized(), "Quaternion should be normalized");
        assertApproxEquals((float) Math.sin(Math.PI / 4), qx.x, "X component for 90° pitch");
        assertApproxEquals(0.0f, qx.y, "Y component for 90° pitch");
        assertApproxEquals(0.0f, qx.z, "Z component for 90° pitch");
        assertApproxEquals((float) Math.cos(Math.PI / 4), qx.w, "W component for 90° pitch");
        
        // 90 degrees around Y-axis
        Quaternion qy = Quaternion.fromEuler(0, (float) Math.PI / 2, 0);
        assertTrue(qy.isNormalized(), "Quaternion should be normalized");
        
        // 90 degrees around Z-axis
        Quaternion qz = Quaternion.fromEuler(0, 0, (float) Math.PI / 2);
        assertTrue(qz.isNormalized(), "Quaternion should be normalized");
    }
    
    @Test
    public void testFromEulerDegrees() {
        Quaternion q1 = Quaternion.fromEuler((float) Math.toRadians(45), 
                                            (float) Math.toRadians(30), 
                                            (float) Math.toRadians(60));
        Quaternion q2 = Quaternion.fromEulerDegrees(45, 30, 60);
        assertQuaternionEquals(q1, q2, "fromEuler and fromEulerDegrees should match");
    }
    
    @Test
    public void testToEulerRoundTrip() {
        float[] originalEuler = {
            (float) Math.toRadians(30),
            (float) Math.toRadians(45),
            (float) Math.toRadians(60)
        };
        
        Quaternion q = Quaternion.fromEuler(originalEuler[0], originalEuler[1], originalEuler[2]);
        float[] convertedEuler = q.toEuler();
        
        // Convert back to quaternion and compare
        Quaternion q2 = Quaternion.fromEuler(convertedEuler[0], convertedEuler[1], convertedEuler[2]);
        assertQuaternionEquals(q, q2, "Euler round-trip conversion should preserve rotation");
    }
    
    @Test
    public void testToEulerDegreesRoundTrip() {
        Quaternion q = Quaternion.fromEulerDegrees(45, 30, 60);
        float[] euler = q.toEulerDegrees();
        Quaternion q2 = Quaternion.fromEulerDegrees(euler[0], euler[1], euler[2]);
        assertQuaternionEquals(q, q2, "Euler degrees round-trip should preserve rotation");
    }
    
    @Test
    public void testFromAxisAngleXAxis() {
        float angle = (float) Math.PI / 2;
        Quaternion q = Quaternion.fromAxisAngle(1, 0, 0, angle);
        
        assertTrue(q.isNormalized(), "Axis-angle quaternion should be normalized");
        assertApproxEquals((float) Math.sin(angle / 2), q.x, "X axis rotation");
        assertApproxEquals(0.0f, q.y, "Y should be 0");
        assertApproxEquals(0.0f, q.z, "Z should be 0");
        assertApproxEquals((float) Math.cos(angle / 2), q.w, "W component");
    }
    
    @Test
    public void testFromAxisAngleYAxis() {
        float angle = (float) Math.PI / 2;
        Quaternion q = Quaternion.fromAxisAngle(0, 1, 0, angle);
        
        assertTrue(q.isNormalized(), "Axis-angle quaternion should be normalized");
        assertApproxEquals(0.0f, q.x, "X should be 0");
        assertApproxEquals((float) Math.sin(angle / 2), q.y, "Y axis rotation");
        assertApproxEquals(0.0f, q.z, "Z should be 0");
    }
    
    @Test
    public void testSlerpIdentityToRotation() {
        Quaternion q1 = Quaternion.identity();
        Quaternion q2 = Quaternion.fromEulerDegrees(90, 0, 0);
        
        Quaternion halfway = Quaternion.slerp(q1, q2, 0.5f);
        assertTrue(halfway.isNormalized(), "Slerp result should be normalized");
        
        // At t=0, should be q1
        Quaternion atStart = Quaternion.slerp(q1, q2, 0.0f);
        assertQuaternionEquals(q1, atStart, "Slerp at t=0 should be first quaternion");
        
        // At t=1, should be q2
        Quaternion atEnd = Quaternion.slerp(q1, q2, 1.0f);
        assertQuaternionEquals(q2, atEnd, "Slerp at t=1 should be second quaternion");
    }
    
    @Test
    public void testSlerpClampingOutOfRange() {
        Quaternion q1 = Quaternion.identity();
        Quaternion q2 = Quaternion.fromEulerDegrees(45, 0, 0);
        
        // Test clamping below 0
        Quaternion below = Quaternion.slerp(q1, q2, -0.5f);
        assertQuaternionEquals(q1, below, "Slerp should clamp t < 0 to 0");
        
        // Test clamping above 1
        Quaternion above = Quaternion.slerp(q1, q2, 1.5f);
        assertQuaternionEquals(q2, above, "Slerp should clamp t > 1 to 1");
    }
    
    @Test
    public void testSlerpNegativeDot() {
        // Create two quaternions that are opposite (negative dot product)
        Quaternion q1 = Quaternion.fromEulerDegrees(0, 0, 0);
        Quaternion q2 = Quaternion.fromEulerDegrees(180, 0, 0);
        
        // Slerp should take the shorter path
        Quaternion mid = Quaternion.slerp(q1, q2, 0.5f);
        assertTrue(mid.isNormalized(), "Slerp with negative dot should be normalized");
    }
    
    @Test
    public void testSlerpVeryClose() {
        Quaternion q1 = Quaternion.fromEulerDegrees(45.0f, 30.0f, 15.0f);
        Quaternion q2 = Quaternion.fromEulerDegrees(45.001f, 30.001f, 15.001f);
        
        Quaternion mid = Quaternion.slerp(q1, q2, 0.5f);
        assertTrue(mid.isNormalized(), "Slerp of very close quaternions should be normalized");
    }
    
    @Test
    public void testMultiplyIdentity() {
        Quaternion q = Quaternion.fromEulerDegrees(45, 30, 60);
        Quaternion identity = Quaternion.identity();
        
        Quaternion result1 = q.multiply(identity);
        assertQuaternionEquals(q, result1, "Multiplying by identity (right) should not change quaternion");
        
        Quaternion result2 = identity.multiply(q);
        assertQuaternionEquals(q, result2, "Multiplying by identity (left) should not change quaternion");
    }
    
    @Test
    public void testMultiplyComposition() {
        // Rotate 90° around X, then 90° around Y
        Quaternion qx = Quaternion.fromEulerDegrees(90, 0, 0);
        Quaternion qy = Quaternion.fromEulerDegrees(0, 90, 0);
        
        Quaternion combined = qx.multiply(qy);
        assertTrue(combined.isNormalized(), "Combined rotation should be normalized");
    }
    
    @Test
    public void testDotProduct() {
        Quaternion q1 = Quaternion.fromEulerDegrees(45, 30, 15);
        Quaternion q2 = Quaternion.fromEulerDegrees(45, 30, 15);
        
        float dot = q1.dot(q2);
        assertApproxEquals(1.0f, dot, "Dot product of same quaternion should be 1");
        
        Quaternion identity = Quaternion.identity();
        float dotIdentity = identity.dot(identity);
        assertEquals(1.0f, dotIdentity, EPSILON);
    }
    
    @Test
    public void testLength() {
        Quaternion identity = Quaternion.identity();
        assertApproxEquals(1.0f, identity.length(), "Identity length should be 1");
        
        Quaternion q = new Quaternion(1, 0, 0, 0);
        assertApproxEquals(1.0f, q.length(), "Unit quaternion length should be 1");
        
        Quaternion q2 = new Quaternion(1, 1, 1, 1);
        assertApproxEquals(2.0f, q2.length(), "Length of (1,1,1,1) should be 2");
    }
    
    @Test
    public void testLengthSquared() {
        Quaternion q = new Quaternion(1, 1, 1, 1);
        assertEquals(4.0f, q.lengthSquared(), EPSILON);
        
        Quaternion identity = Quaternion.identity();
        assertEquals(1.0f, identity.lengthSquared(), EPSILON);
    }
    
    @Test
    public void testNormalize() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        Quaternion normalized = q.normalize();
        
        assertApproxEquals(1.0f, normalized.length(), "Normalized quaternion should have length 1");
        assertTrue(normalized.isNormalized(), "Normalized quaternion should pass isNormalized check");
        
        // Normalizing an already normalized quaternion
        Quaternion identity = Quaternion.identity();
        Quaternion normalizedIdentity = identity.normalize();
        assertQuaternionEquals(identity, normalizedIdentity, "Normalizing identity should return identity");
    }
    
    @Test
    public void testNormalizeZeroQuaternion() {
        Quaternion zero = new Quaternion(0, 0, 0, 0);
        Quaternion normalized = zero.normalize();
        assertQuaternionEquals(Quaternion.identity(), normalized, 
            "Normalizing zero quaternion should return identity");
    }
    
    @Test
    public void testConjugate() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        Quaternion conj = q.conjugate();
        
        assertEquals(-1.0f, conj.x);
        assertEquals(-2.0f, conj.y);
        assertEquals(-3.0f, conj.z);
        assertEquals(4.0f, conj.w);
        
        // Conjugate of conjugate should be original
        Quaternion conjConj = conj.conjugate();
        assertEquals(q.x, conjConj.x, EPSILON);
        assertEquals(q.y, conjConj.y, EPSILON);
        assertEquals(q.z, conjConj.z, EPSILON);
        assertEquals(q.w, conjConj.w, EPSILON);
    }
    
    @Test
    public void testInverse() {
        Quaternion q = Quaternion.fromEulerDegrees(45, 30, 60);
        Quaternion inv = q.inverse();
        
        // Multiplying by inverse should give identity
        Quaternion result = q.multiply(inv);
        assertQuaternionEquals(Quaternion.identity(), result, 
            "Quaternion multiplied by its inverse should be identity");
    }
    
    @Test
    public void testInverseForUnitQuaternion() {
        Quaternion q = Quaternion.fromEulerDegrees(45, 30, 60);
        Quaternion inv = q.inverse();
        Quaternion conj = q.conjugate();
        
        // For unit quaternions, inverse should equal conjugate
        assertQuaternionEquals(conj, inv, "For unit quaternions, inverse equals conjugate");
    }
    
    @Test
    public void testInverseZeroQuaternion() {
        Quaternion zero = new Quaternion(0, 0, 0, 0);
        Quaternion inv = zero.inverse();
        assertQuaternionEquals(Quaternion.identity(), inv, 
            "Inverse of zero quaternion should return identity");
    }
    
    @Test
    public void testIsNormalized() {
        Quaternion normalized = Quaternion.fromEulerDegrees(45, 30, 60);
        assertTrue(normalized.isNormalized(), "Quaternion from Euler should be normalized");
        
        Quaternion notNormalized = new Quaternion(1, 2, 3, 4);
        assertFalse(notNormalized.isNormalized(), "Quaternion (1,2,3,4) should not be normalized");
        
        Quaternion identity = Quaternion.identity();
        assertTrue(identity.isNormalized(), "Identity should be normalized");
    }
    
    @Test
    public void testEquality() {
        Quaternion q1 = Quaternion.fromEulerDegrees(45, 30, 60);
        Quaternion q2 = Quaternion.fromEulerDegrees(45, 30, 60);
        Quaternion q3 = Quaternion.fromEulerDegrees(45, 30, 61);
        
        assertEquals(q1, q2, "Same quaternions should be equal");
        assertNotEquals(q1, q3, "Different quaternions should not be equal");
        
        assertEquals(q1, q1, "Quaternion should equal itself");
        assertNotEquals(q1, null, "Quaternion should not equal null");
        assertNotEquals(q1, "not a quaternion", "Quaternion should not equal other types");
    }
    
    @Test
    public void testHashCode() {
        Quaternion q1 = Quaternion.fromEulerDegrees(45, 30, 60);
        Quaternion q2 = Quaternion.fromEulerDegrees(45, 30, 60);
        
        assertEquals(q1.hashCode(), q2.hashCode(), "Equal quaternions should have same hash code");
    }
    
    @Test
    public void testToString() {
        Quaternion q = new Quaternion(0.1f, 0.2f, 0.3f, 0.4f);
        String str = q.toString();
        
        assertTrue(str.contains("0.1"), "String should contain x value");
        assertTrue(str.contains("0.2"), "String should contain y value");
        assertTrue(str.contains("0.3"), "String should contain z value");
        assertTrue(str.contains("0.4"), "String should contain w value");
    }
    
    @Test
    public void testGimbalLockCase() {
        // Test gimbal lock at +90 degrees pitch
        Quaternion q1 = Quaternion.fromEulerDegrees(90, 45, 30);
        float[] euler = q1.toEuler();
        
        // The pitch should be approximately 90 degrees
        assertApproxEquals((float) Math.PI / 2, euler[0], "Pitch should be 90 degrees");
        
        // Test gimbal lock at -90 degrees pitch
        Quaternion q2 = Quaternion.fromEulerDegrees(-90, 45, 30);
        float[] euler2 = q2.toEuler();
        assertApproxEquals(-(float) Math.PI / 2, euler2[0], "Pitch should be -90 degrees");
    }
    
    @Test
    public void testSlerpSmoothness() {
        Quaternion q1 = Quaternion.fromEulerDegrees(0, 0, 0);
        Quaternion q2 = Quaternion.fromEulerDegrees(90, 0, 0);
        
        // Test that slerp produces smooth intermediate values
        Quaternion q25 = Quaternion.slerp(q1, q2, 0.25f);
        Quaternion q50 = Quaternion.slerp(q1, q2, 0.50f);
        Quaternion q75 = Quaternion.slerp(q1, q2, 0.75f);
        
        // All should be normalized
        assertTrue(q25.isNormalized(), "25% slerp should be normalized");
        assertTrue(q50.isNormalized(), "50% slerp should be normalized");
        assertTrue(q75.isNormalized(), "75% slerp should be normalized");
        
        // Check that we're moving monotonically towards q2
        float dot1 = q1.dot(q25);
        float dot2 = q1.dot(q50);
        float dot3 = q1.dot(q75);
        
        assertTrue(dot1 > dot2, "Dot product should decrease as we move away from q1");
        assertTrue(dot2 > dot3, "Dot product should continue decreasing");
    }
    
    @Test
    public void testRotationCompositionCommutative() {
        // Test that rotation order matters (quaternion multiplication is not commutative)
        Quaternion qx = Quaternion.fromEulerDegrees(90, 0, 0);
        Quaternion qy = Quaternion.fromEulerDegrees(0, 90, 0);
        
        Quaternion xy = qx.multiply(qy);
        Quaternion yx = qy.multiply(qx);
        
        // These should NOT be equal (rotation order matters)
        assertFalse(xy.equals(yx), "Quaternion multiplication should not be commutative");
    }
}
