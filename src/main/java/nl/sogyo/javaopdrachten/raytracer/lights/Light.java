package nl.sogyo.javaopdrachten.raytracer.lights;

import nl.sogyo.javaopdrachten.raytracer.Color;
import nl.sogyo.javaopdrachten.raytracer.Ray;
import nl.sogyo.javaopdrachten.raytracer.SceneBuilder;
import nl.sogyo.javaopdrachten.raytracer.SceneObject;
import nl.sogyo.javaopdrachten.raytracer.lights.PointLight.Builder;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public abstract class Light extends SceneObject
{
	private static final double LIGHT_FALL_OFF = 0.85;
	
	public Color color;
	private double intensity;

	public Light(Vector3 position, Color color, double intensity)
	{
		super(position);
		this.color = color;
		this.intensity = intensity;
	}
	
	public double getIntensity()
	{
		return intensity;
	}
	
	public double getIntensityAt(Vector3 position)
	{
		double inverseIntensity = Math.pow(Vector3.getDistance(position, this.position) * (1 - LIGHT_FALL_OFF), 3) / this.getIntensity();
		if(inverseIntensity > 1)
			inverseIntensity = 1;
		return 1 - inverseIntensity;
	}
	
	public void setIntensity(double value)
	{
		if(value < 0.0000000001)
			intensity = 0.0000000001;
		else
			intensity = value;
	}
	
	public Vector3 getPosition(Vector3 from)
	{
		return this.position;
	}
	
	public abstract Ray getRayFromPosition(Vector3 from);
	
	@Override
	public String toString()
	{
		return String.format("%s ; position : %s ; intensity : %.1f", this.getClass().getName(), position.toString(), intensity);
	}
	
	public static abstract class Builder<T extends Light> extends SceneBuilder.ObjectBuilder<T>
	{	
		public Builder<T> withColor(Color color)
		{
			object.color = color;
			return this;
		}
		
		public Builder<T> withIntensity(double intensity)
		{
			object.setIntensity(intensity);
			return this;
		}
	}
}
