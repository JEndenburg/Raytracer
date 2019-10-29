package nl.sogyo.javaopdrachten.raytracer;

import java.util.ArrayList;
import java.util.List;

import nl.sogyo.javaopdrachten.raytracer.lights.Light;
import nl.sogyo.javaopdrachten.raytracer.shapes.IShape;
import nl.sogyo.javaopdrachten.raytracer.vectors.*;

public class Scene 
{
	public Viewport viewport;
	
	public List<SceneObject> objects = new ArrayList<SceneObject>();
	
	public Color backgroundColor = new Color(0.15, 0.15, 0.25);
	public Color ambientLight = new Color(0.03, 0.05, 0.15);
	
	public Scene(Viewport viewport)
	{
		this.viewport = viewport;
	}
	
	public Scene addObject(SceneObject sceneObject)
	{
		objects.add(sceneObject);
		return this;
	}
	
	public Color[] renderScene()
	{
		Color[] render = new Color[viewport.screenResolution.x * viewport.screenResolution.y];
		
		for(int y = 0, i = 0; y < viewport.screenResolution.y; y++)
		{
			for(int x = 0; x < viewport.screenResolution.x; x++, i++)
			{
				render[i] = renderPixel(new Vector2Int(x,y));
			}
		}
		
		return render;
	}
	
	private Color renderPixel(Vector2Int screenPosition)
	{
		Ray ray = new Ray(viewport.viewPoint, viewport.screenToWorldPosition(screenPosition));
		RayHit hit = getRaycastHit(ray);
		if(hit == null)
			return backgroundColor;
		else
			return getLightFromCoordinate(hit);
	}
	
	public RayHit getRaycastHit(Ray ray, Vector3 blacklistVector)
	{
		RayHit hit = null;
		for(SceneObject sceneObject : objects)
		{
			if(sceneObject instanceof IShape)
			{
				IShape shape = (IShape)sceneObject;
				Vector3[] intersects = shape.getIntersectPoints(ray);
				
				if(intersects.length > 0)
				{
					int intersectId = -1;
					
					if(blacklistVector != null)
					{
						for(int i = 0; i < intersects.length; i++)
						{
							if(!intersects[i].equals(blacklistVector))
							{
								intersectId = i;
								break;
							}
						}
					}
					else
						intersectId = 0;
					
					if(intersectId >= 0)
					{
						if(hit != null)
						{
							if(Vector3.getDistance(ray.start, intersects[0]) < Vector3.getDistance(ray.start, hit.point))
								hit = new RayHit(intersects[intersectId], shape);
						}
						else
							hit = new RayHit(intersects[intersectId], shape);
					}
				}
			}
		}
		return hit;
	}
	
	public RayHit getRaycastHit(Ray ray)
	{
		return getRaycastHit(ray, null);
	}
	
	private Color getLightFromCoordinate(RayHit hit)
	{
		Color color = new Color(0, 0, 0);
		
		for(SceneObject sceneObject : objects)
		{
			if(sceneObject instanceof Light)
			{
				color = color.add(hit.hitObject.getShader().renderPixel(this, (Light)sceneObject, hit));
			}
		}
		
		return color;
	}
	
	@Override
	public String toString()
	{
		String output = "Scene :\n{\n";
		
		output += String.format("\tviewport : { %s }\n", viewport.toString());
		
		for(SceneObject sceneObject : objects)
		{
			output += String.format(
					"\t%s : \n\t{\n\t\tposition : %s\n\t\tstringValue : { %s }\n\t}\n",
					sceneObject.getClass().getTypeName(),
					sceneObject.position.toString(),
					sceneObject.toString()
					);
		}
		
		output += "}";
		return output;
	}
}
