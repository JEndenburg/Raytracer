package nl.sogyo.javaopdrachten.raytracer.shapes;

import nl.sogyo.javaopdrachten.raytracer.ArgumentOutOfRangeException;
import nl.sogyo.javaopdrachten.raytracer.Ray;
import nl.sogyo.javaopdrachten.raytracer.SceneObject;
import nl.sogyo.javaopdrachten.raytracer.shaders.*;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;
import nl.sogyo.javaopdrachten.raytracer.Color;

public class Triangle extends SceneObject implements IShape
{
	private Shader shader;
	private Vector3[] vertices;
	
	public Triangle(Vector3[] vertices, Shader shader)
	{
		super(Vector3.getCenter(vertices));
		if(vertices.length != 3)
			throw new ArgumentOutOfRangeException("Triangle vertices array length must be equal to 3.");
		this.vertices = vertices;
		this.shader = shader;
	}
	
	public Triangle(Vector3[] vertices)
	{
		this(vertices, new DefaultShader(Color.white));
	}
	
	public Triangle(Vector3 a, Vector3 b, Vector3 c, Shader shader)
	{
		this(new Vector3[] {a, b, c}, new DefaultShader(Color.white));
	}
	
	public Triangle(Vector3 a, Vector3 b, Vector3 c)
	{
		this(new Vector3[] {a, b, c});
	}

	@Override
	public Vector3[] getIntersectPoints(Ray ray)
	{
		Vector3 intersect = getIntersect(ray);
		
		if(doesIntersect(intersect))
			return new Vector3[] {intersect};
		else
			return new Vector3[] {};
	}
	
	private boolean doesIntersect(Vector3 theoreticalHitPoint)
	{
		Vector3 edgeAB = vertices[1].subtract(vertices[0]);
		Vector3 edgeBC = vertices[2].subtract(vertices[1]);
		Vector3 edgeCA = vertices[0].subtract(vertices[2]);
		
		return (Vector3.dotProduct(edgeAB, theoreticalHitPoint.subtract(vertices[0])) > 0)
				&& (Vector3.dotProduct(edgeBC, theoreticalHitPoint.subtract(vertices[1])) > 0)
				&& (Vector3.dotProduct(edgeCA, theoreticalHitPoint.subtract(vertices[2])) > 0);
	}
	
	private Vector3 getIntersect(Ray ray)
	{
		Vector3 normal = getNormal(null);
		Vector3 rayDirection = ray.getDirection();
		double distanceToPlaneParallelToNormal = Math.abs(Vector3.dotProduct(normal, vertices[0]));
		double distanceToIntersect = (Vector3.dotProduct(normal, ray.start) + distanceToPlaneParallelToNormal) / Vector3.dotProduct(normal, rayDirection);
				
		return ray.start.add(rayDirection.multiply(distanceToIntersect));
	}

	@Override
	public Vector3 getNormal(Vector3 point)
	{
		return Vector3.crossProduct(vertices[1].subtract(vertices[0]), vertices[2].subtract(vertices[0])).getNormalizedVector();
	}

	@Override
	public Shader getShader()
	{
		return this.shader;
	}

	@Override
	public double getDiffuseCoefficient()
	{
		return 2.5;
	}

}
