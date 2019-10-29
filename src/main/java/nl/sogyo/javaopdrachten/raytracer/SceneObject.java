package nl.sogyo.javaopdrachten.raytracer;

import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public abstract class SceneObject 
{
	public Vector3 position;
	
	public SceneObject(Vector3 position)
	{
		this.position = position;
	}
}
