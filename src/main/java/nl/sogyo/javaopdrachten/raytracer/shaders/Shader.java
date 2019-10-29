package nl.sogyo.javaopdrachten.raytracer.shaders;

import nl.sogyo.javaopdrachten.raytracer.Color;
import nl.sogyo.javaopdrachten.raytracer.RayHit;
import nl.sogyo.javaopdrachten.raytracer.Scene;
import nl.sogyo.javaopdrachten.raytracer.lights.Light;

public abstract class Shader 
{
	public abstract Color renderPixel(Scene scene, Light light, RayHit hit);
}
