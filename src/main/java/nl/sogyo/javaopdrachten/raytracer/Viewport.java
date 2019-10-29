package nl.sogyo.javaopdrachten.raytracer;

import nl.sogyo.javaopdrachten.raytracer.vectors.Vector2Int;
import nl.sogyo.javaopdrachten.raytracer.vectors.Vector3;

public class Viewport 
{
	private static final double DEFAULT_VIEWPOINT_OFFSET = -5;
	
	public Rect viewRect;
	public Vector3 viewPoint;
	public final Vector2Int screenResolution;
	
	public Viewport(Rect rect, Vector2Int screenResolution, Vector3 viewPoint)
	{
		this.viewRect = rect;
		this.viewPoint = viewPoint;
		this.screenResolution = screenResolution;
	}
	
	public Viewport(Rect rect, Vector2Int screenResolution)
	{
		this(rect, screenResolution, rect.getCenter().add(rect.getFacingDirection().multiply(DEFAULT_VIEWPOINT_OFFSET)));
	}
	
	public Vector3 screenToWorldPosition(Vector2Int screenPosition)
	{
		if(screenPosition.x > screenResolution.x || screenPosition.y > screenResolution.y || screenPosition.x < 0 || screenPosition.y < 0)
			throw new ArgumentOutOfRangeException("Screen position can't be outside of viewport range.");
		
		double xPercentage = (double)screenPosition.x / screenResolution.x;
		double yPercentage = (double)screenPosition.y / screenResolution.y;
		
		Vector3 topLeft = viewRect.getTopLeft();
		Vector3 topRight = viewRect.getTopRight();
		Vector3 bottomLeft = viewRect.getBottomLeft();
		Vector3 bottomRight = viewRect.getBottomRight();
		
		Vector3 xCenterTop = Vector3.lerp(topLeft, topRight, xPercentage);
		Vector3 xCenterBottom = Vector3.lerp(bottomLeft, bottomRight, xPercentage);
		return Vector3.lerp(xCenterTop, xCenterBottom, yPercentage);
	}
	
//	@Override
//	public String toString()
//	{
//		return String.format("%s x %.1f @ %s from vp %s", screenResolution.toString(), worldScale, position.toString(), viewpoint.toString());
//	}
}
