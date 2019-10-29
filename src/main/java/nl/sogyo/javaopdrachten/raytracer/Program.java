package nl.sogyo.javaopdrachten.raytracer;

import java.io.File;

import nl.sogyo.javaopdrachten.raytracer.lights.*;
import nl.sogyo.javaopdrachten.raytracer.shaders.*;
import nl.sogyo.javaopdrachten.raytracer.shapes.*;
import nl.sogyo.javaopdrachten.raytracer.shapes.Sphere.Builder;
import nl.sogyo.javaopdrachten.raytracer.vectors.*;

public class Program 
{
	private static final String OUTPUT_DIRECTORY = "output\\";
	private static final String OUTPUT_FILE = OUTPUT_DIRECTORY + "GeneratedImage.png";

	public static void main(String[] args) 
	{
		long startTime = System.nanoTime();
		
		Rect rect = new Rect(
				new Vector3(-6.4, 3.6, -0), new Vector3(6.4, 3.6, -0),
										 new Vector3(6.4, -3.6, -0)
				);
		
		Viewport view = new Viewport(
				rect,
				new Vector2Int(1280, 720),
				new Vector3(rect.getCenter().add(rect.getFacingDirection().multiply(-25.0)))
				);
		
		Scene scene = new SceneBuilder(view)
				.withAmbientLight(new Color(0.05, 0.05, 0.08))
				.withBackgroundColor(new Color(0.1, 0.25, 0.25, 0.0))
				.withObject(
						new PointLight.Builder(new Vector3(1, 12, 40))
						.withColor(Color.green)
						.withIntensity(35.0)
						)
				.getScene();
		
		scene.addObject(new Triangle(
				new Vector3(-1, -1, 50), new Vector3(1, -1, 50), new Vector3(0, 1, 50)
				));
		
		scene.addObject(new Sphere(new Vector3(-5, 0, 50), 1.0));
				
		
		Color[] render = scene.renderScene();
		new File(OUTPUT_DIRECTORY).mkdir();
		ImageMaker.saveImage(OUTPUT_FILE, render, view.screenResolution.x, view.screenResolution.y);
		
		long endTime = System.nanoTime();
		long timeTaken = endTime - startTime;
		System.out.println(String.format("Run time: %d ns (%f s)", timeTaken, timeTaken / 1000000000.0));
	}

}
