package nl.sogyo.javaopdrachten.raytracer;

import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class Ray 
{
	public Vector3 start, end;
	
	public Ray(Vector3 start, Vector3 end)
	{
		this.start = start;
		this.end = end;
	}
	
	public static Ray fromOriginAndDirection(Vector3 origin, Vector3 direction)
	{
		return new Ray(
				origin,
				origin.add(direction)
				);
	}
	
	public Vector3 getDirection()
	{
		return end.subtract(start).getNormalizedVector();
	}
	
	public Vector3 getPointFromDistance(double distance)
	{
		return getDirection().getNormalizedVector().multiply(distance).add(start);
	}
}
