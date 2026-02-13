package com.raeyncreations.silenthill.client.animation;

/**
 * Quaternion class for representing 3D rotations using Hamilton notation (w, x, y, z).
 * Provides smooth interpolation via spherical linear interpolation (slerp) and 
 * conversion to/from Euler angles for compatibility with existing animation systems.
 * 
 * <p>Quaternions are preferred over Euler angles for animation because they:
 * <ul>
 *   <li>Avoid gimbal lock</li>
 *   <li>Provide smoother interpolation (slerp)</li>
 *   <li>Are more numerically stable</li>
 *   <li>Enable efficient rotation composition</li>
 * </ul>
 * 
 * <p>All angles are in radians unless otherwise specified.
 * 
 * @author JS-Silent-Hill
 * @version 3.0.0
 */
public class Quaternion {
    private static final float EPSILON = 1e-6f;
    private static final float SLERP_DOT_THRESHOLD = 0.9995f;
    
    public final float x;
    public final float y;
    public final float z;
    public final float w;
    
    /**
     * Constructs a quaternion with the given components.
     * 
     * @param x The x component (i coefficient)
     * @param y The y component (j coefficient)
     * @param z The z component (k coefficient)
     * @param w The w component (scalar/real part)
     */
    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    /**
     * Creates an identity quaternion (no rotation).
     * 
     * @return A quaternion representing no rotation (0, 0, 0, 1)
     */
    public static Quaternion identity() {
        return new Quaternion(0, 0, 0, 1);
    }
    
    /**
     * Creates a quaternion from Euler angles using the YXZ rotation order.
     * This is the standard rotation order used in Minecraft and Bedrock animations.
     * 
     * @param pitch Rotation around X-axis in radians
     * @param yaw Rotation around Y-axis in radians
     * @param roll Rotation around Z-axis in radians
     * @return A quaternion representing the same rotation
     */
    public static Quaternion fromEuler(float pitch, float yaw, float roll) {
        // Using YXZ rotation order (Minecraft standard)
        float cy = (float) Math.cos(yaw * 0.5);
        float sy = (float) Math.sin(yaw * 0.5);
        float cp = (float) Math.cos(pitch * 0.5);
        float sp = (float) Math.sin(pitch * 0.5);
        float cr = (float) Math.cos(roll * 0.5);
        float sr = (float) Math.sin(roll * 0.5);
        
        float w = cr * cp * cy + sr * sp * sy;
        float x = cr * sp * cy + sr * cp * sy;
        float y = cr * cp * sy - sr * sp * cy;
        float z = sr * cp * cy - cr * sp * sy;
        
        return new Quaternion(x, y, z, w);
    }
    
    /**
     * Creates a quaternion from Euler angles in degrees.
     * 
     * @param pitchDeg Rotation around X-axis in degrees
     * @param yawDeg Rotation around Y-axis in degrees
     * @param rollDeg Rotation around Z-axis in degrees
     * @return A quaternion representing the same rotation
     */
    public static Quaternion fromEulerDegrees(float pitchDeg, float yawDeg, float rollDeg) {
        return fromEuler(
            (float) Math.toRadians(pitchDeg),
            (float) Math.toRadians(yawDeg),
            (float) Math.toRadians(rollDeg)
        );
    }
    
    /**
     * Creates a quaternion from an axis-angle representation.
     * 
     * @param axisX The x component of the rotation axis (should be normalized)
     * @param axisY The y component of the rotation axis (should be normalized)
     * @param axisZ The z component of the rotation axis (should be normalized)
     * @param angle The rotation angle in radians
     * @return A quaternion representing the rotation
     */
    public static Quaternion fromAxisAngle(float axisX, float axisY, float axisZ, float angle) {
        float halfAngle = angle * 0.5f;
        float s = (float) Math.sin(halfAngle);
        return new Quaternion(
            axisX * s,
            axisY * s,
            axisZ * s,
            (float) Math.cos(halfAngle)
        );
    }
    
    /**
     * Converts this quaternion to Euler angles using YXZ rotation order.
     * Handles gimbal lock cases appropriately.
     * 
     * @return An array of [pitch, yaw, roll] in radians
     */
    public float[] toEuler() {
        float[] euler = new float[3];
        
        // Roll (z-axis rotation)
        float sinr_cosp = 2 * (w * z + x * y);
        float cosr_cosp = 1 - 2 * (y * y + z * z);
        euler[2] = (float) Math.atan2(sinr_cosp, cosr_cosp);
        
        // Pitch (x-axis rotation) - handle gimbal lock
        float sinp = 2 * (w * x - y * z);
        if (Math.abs(sinp) >= 1) {
            // Gimbal lock case
            euler[0] = (float) Math.copySign(Math.PI / 2, sinp);
        } else {
            euler[0] = (float) Math.asin(sinp);
        }
        
        // Yaw (y-axis rotation)
        float siny_cosp = 2 * (w * y + x * z);
        float cosy_cosp = 1 - 2 * (x * x + y * y);
        euler[1] = (float) Math.atan2(siny_cosp, cosy_cosp);
        
        return euler; // [pitch, yaw, roll]
    }
    
    /**
     * Converts this quaternion to Euler angles in degrees.
     * 
     * @return An array of [pitch, yaw, roll] in degrees
     */
    public float[] toEulerDegrees() {
        float[] euler = toEuler();
        return new float[] {
            (float) Math.toDegrees(euler[0]),
            (float) Math.toDegrees(euler[1]),
            (float) Math.toDegrees(euler[2])
        };
    }
    
    /**
     * Performs spherical linear interpolation between two quaternions.
     * This provides the smoothest possible rotation interpolation.
     * 
     * @param q1 The starting quaternion
     * @param q2 The ending quaternion
     * @param t The interpolation factor (0.0 to 1.0)
     * @return The interpolated quaternion
     */
    public static Quaternion slerp(Quaternion q1, Quaternion q2, float t) {
        // Clamp t to [0, 1]
        t = Math.max(0.0f, Math.min(1.0f, t));
        
        // Compute dot product
        float dot = q1.dot(q2);
        
        // If negative dot, negate one quaternion to take shorter path
        Quaternion q2adjusted = q2;
        if (dot < 0.0f) {
            q2adjusted = new Quaternion(-q2.x, -q2.y, -q2.z, -q2.w);
            dot = -dot;
        }
        
        // If quaternions are very close, use linear interpolation to avoid division by zero
        if (dot > SLERP_DOT_THRESHOLD) {
            return new Quaternion(
                q1.x + t * (q2adjusted.x - q1.x),
                q1.y + t * (q2adjusted.y - q1.y),
                q1.z + t * (q2adjusted.z - q1.z),
                q1.w + t * (q2adjusted.w - q1.w)
            ).normalize();
        }
        
        // Perform spherical linear interpolation
        float theta = (float) Math.acos(dot);
        float sinTheta = (float) Math.sin(theta);
        
        float w1 = (float) Math.sin((1.0f - t) * theta) / sinTheta;
        float w2 = (float) Math.sin(t * theta) / sinTheta;
        
        return new Quaternion(
            w1 * q1.x + w2 * q2adjusted.x,
            w1 * q1.y + w2 * q2adjusted.y,
            w1 * q1.z + w2 * q2adjusted.z,
            w1 * q1.w + w2 * q2adjusted.w
        );
    }
    
    /**
     * Multiplies this quaternion with another (composes rotations).
     * The result represents applying this rotation first, then the other.
     * 
     * @param other The quaternion to multiply with
     * @return The product quaternion
     */
    public Quaternion multiply(Quaternion other) {
        return new Quaternion(
            w * other.x + x * other.w + y * other.z - z * other.y,
            w * other.y - x * other.z + y * other.w + z * other.x,
            w * other.z + x * other.y - y * other.x + z * other.w,
            w * other.w - x * other.x - y * other.y - z * other.z
        );
    }
    
    /**
     * Computes the dot product with another quaternion.
     * 
     * @param other The other quaternion
     * @return The dot product
     */
    public float dot(Quaternion other) {
        return x * other.x + y * other.y + z * other.z + w * other.w;
    }
    
    /**
     * Computes the length (magnitude) of this quaternion.
     * 
     * @return The length
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }
    
    /**
     * Computes the squared length of this quaternion.
     * This is more efficient than length() when only comparisons are needed.
     * 
     * @return The squared length
     */
    public float lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }
    
    /**
     * Normalizes this quaternion to unit length.
     * 
     * @return A new normalized quaternion
     */
    public Quaternion normalize() {
        float len = length();
        if (len < EPSILON) {
            return identity();
        }
        float invLen = 1.0f / len;
        return new Quaternion(x * invLen, y * invLen, z * invLen, w * invLen);
    }
    
    /**
     * Computes the conjugate of this quaternion.
     * For unit quaternions, the conjugate equals the inverse.
     * 
     * @return The conjugate quaternion
     */
    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }
    
    /**
     * Computes the inverse of this quaternion.
     * For unit quaternions, this is equivalent to conjugate().
     * 
     * @return The inverse quaternion
     */
    public Quaternion inverse() {
        float lenSq = lengthSquared();
        if (lenSq < EPSILON) {
            return identity();
        }
        float invLenSq = 1.0f / lenSq;
        return new Quaternion(-x * invLenSq, -y * invLenSq, -z * invLenSq, w * invLenSq);
    }
    
    /**
     * Checks if this quaternion represents a valid rotation (is normalized).
     * 
     * @return true if the quaternion is normalized within epsilon tolerance
     */
    public boolean isNormalized() {
        return Math.abs(lengthSquared() - 1.0f) < EPSILON;
    }
    
    @Override
    public String toString() {
        return String.format("Quaternion(x=%.4f, y=%.4f, z=%.4f, w=%.4f)", x, y, z, w);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quaternion)) return false;
        Quaternion other = (Quaternion) obj;
        return Math.abs(x - other.x) < EPSILON &&
               Math.abs(y - other.y) < EPSILON &&
               Math.abs(z - other.z) < EPSILON &&
               Math.abs(w - other.w) < EPSILON;
    }
    
    @Override
    public int hashCode() {
        int result = Float.floatToIntBits(x);
        result = 31 * result + Float.floatToIntBits(y);
        result = 31 * result + Float.floatToIntBits(z);
        result = 31 * result + Float.floatToIntBits(w);
        return result;
    }
}
