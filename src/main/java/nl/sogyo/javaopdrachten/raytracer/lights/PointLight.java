package nl.sogyo.javaopdrachten.raytracer.lights;

import nl.sogyo.javaopdrachten.raytracer.Color;
import nl.sogyo.javaopdrachten.raytracer.Ray;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class PointLight extends Light
{
	public PointLight(Vector3 position, Color color, double intensity)
	{
		super(position, color, intensity);
	}
	
	public Ray getRayFromPosition(Vector3 from)
	{
		return new Ray(from, this.position);
	}
	
	public static class Builder extends Light.Builder<PointLight>
	{
		public Builder(Vector3 position)
		{
			object = new PointLight(position, Color.white, 1.0);
		}
	}
}
