package nl.sogyo.javaopdrachten.raytracer.vectors;

public class Vector2Int 
{
	public int x, y;
	
	public Vector2Int(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2Int()
	{
		this(0,0);
	}
	
	@Override
	public String toString()
	{
		return String.format("( %d ; %d )", x, y);
	}
}
