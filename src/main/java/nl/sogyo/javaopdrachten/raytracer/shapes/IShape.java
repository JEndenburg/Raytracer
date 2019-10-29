package nl.sogyo.javaopdrachten.raytracer.shapes;

import nl.sogyo.javaopdrachten.raytracer.*;
import nl.sogyo.javaopdrachten.raytracer.shaders.Shader;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public interface IShape 
{
	Vector3[] getIntersectPoints(Ray ray);
	
	Vector3 getNormal(Vector3 point);
	
	Shader getShader();
	
	double getDiffuseCoefficient();

}
