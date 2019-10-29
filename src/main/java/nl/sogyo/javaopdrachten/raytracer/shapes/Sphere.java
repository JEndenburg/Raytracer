package nl.sogyo.javaopdrachten.raytracer.shapes;

import nl.sogyo.javaopdrachten.raytracer.Color;
import nl.sogyo.javaopdrachten.raytracer.MathHelper;
import nl.sogyo.javaopdrachten.raytracer.Ray;
import nl.sogyo.javaopdrachten.raytracer.SceneBuilder;
import nl.sogyo.javaopdrachten.raytracer.SceneObject;
import nl.sogyo.javaopdrachten.raytracer.shaders.DefaultShader;
import nl.sogyo.javaopdrachten.raytracer.shaders.Shader;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class Sphere extends SceneObject implements IShape 
{
	private Shader shader;
	
	public double radius;
	
	public Sphere(Vector3 center, double radius, Shader shader)
	{
		super(center);
		this.radius = radius;
		this.shader = shader;
	}
	
	public Sphere(Vector3 center, double radius)
	{
		this(center, radius, new DefaultShader(Color.white));
	}

	@Override
	public Vector3[] getIntersectPoints(Ray ray)
	{
		Vector3 rayDirection = ray.getDirection();
		
		Vector3 rayToCenter = this.position.subtract(ray.start);
		
		// Note: "Corner" refers to a (straight/90deg) corner that (assuming the ray is to the left of the sphere, pointing to the
		// right (1,0,0) and the center of the sphere is lower than the ray's origin) shares the
		// x-coordinate of the sphere's center and the y-coordinate of the ray's origin (effectively making
		// it the corner of a theoretical triangle between the ray's origin and sphere's center).
		double rayOriginToCorner = Vector3.dotProduct(rayToCenter, rayDirection);
		
		if(rayOriginToCorner < 0) //Going in opposite direction
			return new Vector3[0];
		
		double centerToCornerMagnitude = MathHelper.reversePythagoras(rayToCenter.getMagnitude(), rayOriginToCorner);
		
		if(centerToCornerMagnitude > this.radius) //Means the straight corner of the theoretical triangle is outside of the sphere (thus the ray misses).
			return new Vector3[0];
		
		double cornerToIntersectMagnitude = MathHelper.reversePythagoras(this.radius, centerToCornerMagnitude);
		double rayToIntersect1 = rayOriginToCorner - cornerToIntersectMagnitude;
		double rayToIntersect2 = rayOriginToCorner + cornerToIntersectMagnitude;
		
		Vector3 intersect1 = ray.start.add(rayDirection.multiply(rayToIntersect1));
		Vector3 intersect2 = ray.start.add(rayDirection.multiply(rayToIntersect2));
		
		if(intersect1.equals(intersect2))
			return new Vector3[] { intersect1 };
		else
			return new Vector3[] { intersect1, intersect2 };
	}
	
	@Override
	public Vector3 getNormal(Vector3 point)
	{
		return point.subtract(position).getNormalizedVector();
	}
	
	@Override
	public Shader getShader()
	{
		return shader;
	}
	
	@Override
	public double getDiffuseCoefficient()
	{
		return 2.5;
	}
	
	@Override
	public String toString()
	{
		return String.format("position : %s ; radius : %.1f", position.toString(), radius);
	}
	
	public static class Builder extends SceneBuilder.ObjectBuilder<Sphere>
	{
		public Builder(Vector3 position, double radius) 
		{
			object = new Sphere(position, radius);
		}
		
		public Builder withShader(Shader shader)
		{
			object.shader = shader;
			return this;
		}
	}
}
