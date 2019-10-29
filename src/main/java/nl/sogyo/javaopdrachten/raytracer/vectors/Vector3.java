package nl.sogyo.javaopdrachten.raytracer.vectors;

import nl.sogyo.javaopdrachten.raytracer.MathHelper;

public class Vector3
{
	public double x,y,z;
	
	public static final Vector3 ZERO = new Vector3(0,0,0);
	public static final Vector3 RIGHT = new Vector3(1,0,0);
	public static final Vector3 LEFT = new Vector3(-1,0,0);
	public static final Vector3 UP = new Vector3(0,1,0);
	public static final Vector3 DOWN = new Vector3(0,-1,0);
	public static final Vector3 FORWARD = new Vector3(0,0,1);
	public static final Vector3 BACKWARD = new Vector3(0,0,-1);
	
	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3()
	{
		this(0,0,0);
	}
	
	public Vector3(Vector3 other)
	{
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	public Vector3 add(Vector3 v3)
	{
		return new Vector3(
				this.x + v3.x,
				this.y + v3.y,
				this.z + v3.z
				);
	}
	
	public Vector3 subtract(Vector3 v3)
	{
		return new Vector3(
				this.x - v3.x,
				this.y - v3.y,
				this.z - v3.z
				);
	}
	
	public Vector3 multiply(double amount)
	{
		return new Vector3(
				this.x * amount,
				this.y * amount,
				this.z * amount
				);
	}
	
	public Vector3 divide(double amount)
	{
		double fraction = 1.0 / amount;
		return this.multiply(fraction);
	}
	
	/**
	 * @return The length of the vector.
	 */
	public double getMagnitude()
	{
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * @return The vector with a magnitude of 1.
	 */
	public Vector3 getNormalizedVector()
	{
		double magnitude = getMagnitude();
		return new Vector3(
				x / magnitude,
				y / magnitude,
				z / magnitude
				);
	}
	
	public static Vector3 crossProduct(Vector3 a, Vector3 b)
	{
		return new Vector3(
				(a.y * b.z - a.z * b.y),
				(a.z * b.x - a.x * b.z),
				(a.x * b.y - a.y * b.x)
				);
	}
	
	public static double dotProduct(Vector3 a, Vector3 b)
	{
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	public static Vector3 lerp(Vector3 a, Vector3 b, double t)
	{
		return a.add(b.subtract(a).multiply(t));
	}
	
	public static double getAngle(Vector3 a, Vector3 b)
	{
		return Math.toDegrees(Math.acos(dotProduct(a.getNormalizedVector(),b.getNormalizedVector())));
	}
	
	public static double getDistance(Vector3 a, Vector3 b)
	{
		return b.subtract(a).getMagnitude();
	}
	
	public static Vector3 getCenter(Vector3...vectors)
	{
		Vector3 center = new Vector3();
		for(Vector3 v3 : vectors)
			center.add(v3);
		
		return center.divide(vectors.length);
	}
	
	@Override
	public String toString()
	{
		return String.format("( %.1f ; %.1f ; %.1f )", x, y, z);
	}
	
	@Override
	public boolean equals(java.lang.Object other)
	{
		if(other instanceof Vector3)
		{
			Vector3 otherV3 = (Vector3)other;
			return  MathHelper.doubleCloseto(this.x, otherV3.x) && 
					MathHelper.doubleCloseto(this.y, otherV3.y) && 
					MathHelper.doubleCloseto(this.z, otherV3.z);
		}
		else
			return false;
	}
}
