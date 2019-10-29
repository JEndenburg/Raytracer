package nl.sogyo.javaopdrachten.raytracer.shaders;

import nl.sogyo.javaopdrachten.raytracer.Color;
import nl.sogyo.javaopdrachten.raytracer.RayHit;
import nl.sogyo.javaopdrachten.raytracer.Scene;
import nl.sogyo.javaopdrachten.raytracer.lights.Light;

public class UnlitShader extends Shader
{
	private final Color objectColor;
	
	public UnlitShader(Color color)
	{
		this.objectColor = color;
	}
	
	@Override
	public Color renderPixel(Scene scene, Light light, RayHit hit)
	{
		return objectColor;
	}

}
