package nl.sogyo.javaopdrachten.raytracer.shaders;

import nl.sogyo.javaopdrachten.raytracer.*;
import nl.sogyo.javaopdrachten.raytracer.lights.Light;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class PosterizedShadowShader extends Shader 
{
	private final Color objectColor;
	private final double metallicity;
	private final int posterization;
	
	public PosterizedShadowShader(Color color, int posterization)
	{
		this(color, 0.0, posterization);
	}
	
	public PosterizedShadowShader(Color color, double metallicity, int posterization)
	{
		this.objectColor = color;
		this.metallicity = metallicity;
		this.posterization = posterization;
	}
	
	@Override
	public Color renderPixel(Scene scene, Light light, RayHit hit) 
	{
		Color color = new Color(Color.black);
		Ray ray = light.getRayFromPosition(hit.point);
		RayHit hit2 = scene.getRaycastHit(ray, hit.point);
				
		if(hit2 == null || hit2.hitObject.equals(hit.hitObject))
		{
			double lightPowerFromDistance = light.getIntensityAt(hit.point);
			
			double brightness = 1 - hit.hitObject.getDiffuseCoefficient() * Math.max(0, Vector3.dotProduct(hit.getNormal(), new Ray(light.getPosition(hit.point), hit.point).getDirection()));
			
			double angleFromNormal = Vector3.getAngle(hit.getNormal(), ray.getDirection());
			
			double metalReflection = Math.log10(Math.pow(angleFromNormal, 2) / 360.0) * -metallicity;
			
			if(lightPowerFromDistance > 0)
			{
				color = color.add(light.color.add(light.color.multiply(metalReflection)).multiply(brightness * lightPowerFromDistance));
				double r = Math.round(color.getRed() * (double)posterization) / (double)posterization;
				double g = Math.round(color.getGreen() * (double)posterization) / (double)posterization;
				double b = Math.round(color.getBlue() * (double)posterization) / (double)posterization;
				color = new Color(r,g,b);
			}
		}
		
		color = color.add(scene.ambientLight).multiply(objectColor);
		return color;
	}

}
