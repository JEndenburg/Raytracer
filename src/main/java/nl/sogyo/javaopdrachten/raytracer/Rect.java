package nl.sogyo.javaopdrachten.raytracer;

import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class Rect 
{
	private final Vector3[] vertices;
	
	public Rect(Vector3 topLeft, Vector3 topRight, Vector3 bottomRight)
	{
		vertices = new Vector3[4];
		
		vertices[0] = topLeft;
		vertices[1] = topRight;
		vertices[2] = bottomRight;
		
		Vector3 midPoint = getCenter();
		vertices[3] = new Vector3(
				midPoint.x * 2 - topRight.x,
				midPoint.y * 2 - topRight.y,
				midPoint.z * 2 - topRight.z
				);
	}
	
	public Vector3 getTopLeft() { return vertices[0]; }
	public Vector3 getTopRight() { return vertices[1]; }
	public Vector3 getBottomLeft() { return vertices[3]; }
	public Vector3 getBottomRight() { return vertices[2]; }
	
	public Vector3 getCenter()
	{
		return Vector3.lerp(getTopLeft(), getBottomRight(), 0.5);
	}
	
	public Vector3 getFacingDirection()
	{
		Vector3 topLeftToTopRightDirection = getTopLeft().subtract(getTopRight()).getNormalizedVector();
		Vector3 topLeftToBottomLeftDirection = getBottomLeft().subtract(getTopLeft()).getNormalizedVector();
		
		return Vector3.crossProduct(topLeftToTopRightDirection, topLeftToBottomLeftDirection);
	}
	
	@Override
	public String toString()
	{
		return String.format("[ %s ; %s ; %s ; %s ]", vertices[0], vertices[1], vertices[3], vertices[2]);
	}
}
