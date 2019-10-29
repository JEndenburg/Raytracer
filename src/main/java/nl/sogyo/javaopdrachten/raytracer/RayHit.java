package nl.sogyo.javaopdrachten.raytracer;

import nl.sogyo.javaopdrachten.raytracer.shapes.IShape;
import nl.sogyo.javaopdrachten.raytracer.vectors.*;

public class RayHit 
{
	public final Vector3 point;
	public final IShape hitObject;
	
	public RayHit(Vector3 point, IShape hitObject)
	{
		this.point = point;
		this.hitObject = hitObject;
	}
	
	public Vector3 getNormal()
	{
		return hitObject.getNormal(point);
	}
}
