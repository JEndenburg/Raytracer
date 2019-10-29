package nl.sogyo.javaopdrachten.raytracer.lights;

import nl.sogyo.javaopdrachten.raytracer.Color;
import nl.sogyo.javaopdrachten.raytracer.Ray;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class GlobalLight extends Light
{
	private static final double DISTANCE = -100000.0;
	
	private Vector3 direction;
	
	public GlobalLight(Vector3 position, Color color, double intensity, Vector3 direction)
	{
		super(position, color, intensity);
		this.direction = direction;
	}
	
	public Ray getRayFromPosition(Vector3 from)
	{
		return new Ray(from, direction.multiply(DISTANCE));
	}
	
	@Override
	public double getIntensityAt(Vector3 position)
	{
		return getIntensity();
	}
	
	@Override
	public Vector3 getPosition(Vector3 from)
	{
		return from.add(direction.multiply(DISTANCE));
	}
	
	public static class Builder extends Light.Builder<GlobalLight>
	{
		public Builder(Vector3 position)
		{
			object = new GlobalLight(position, Color.white, 1.0, Vector3.DOWN);
		}
		
		public Builder withDirection(Vector3 direction)
		{
			object.direction = direction;
			return this;
		}
	}
}
