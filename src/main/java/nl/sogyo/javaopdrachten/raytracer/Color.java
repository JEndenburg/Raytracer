package nl.sogyo.javaopdrachten.raytracer;

public class Color 
{
	private double r,g,b,a;
	
	private static final double CCIR601_R = 0.299, CCIR601_G = 0.587, CCIR601_B = 0.114;
	
	public static final Color red = new Color(1,0,0);
	public static final Color green = new Color(0,1,0);
	public static final Color blue = new Color(0,0,1);
	public static final Color cyan = new Color(0,1,1);
	public static final Color magenta = new Color(1,0,1);
	public static final Color yellow = new Color(1,1,0);
	public static final Color black = new Color(0,0,0);
	public static final Color white = new Color(1,1,1);
	
	public Color(double r, double g, double b, double a)
	{
		this.setRed(r);
		this.setGreen(g);
		this.setBlue(b);
		this.setAlpha(a);
	}
	
	public Color(double r, double g, double b)
	{
		this(r,g,b,1);
	}
	
	public Color(Color color)
	{
		this.r = color.r;
		this.g = color.g;
		this.b = color.b;
		this.a = color.a;
	}
	
	public double getAlpha()
	{
		return a;
	}
	
	public void setAlpha(double value)
	{
		if(value > 1)
			a = 1;
		else if(value < 0)
			a = 0;
		else
			a = value;
	}
	
	public double getRed()
	{
		return r;
	}
	
	public void setRed(double value)
	{
		if(value > 1)
			r = 1;
		else if(value < 0)
			r = 0;
		else
			r = value;
	}
	
	public double getGreen()
	{
		return g;
	}
	
	public void setGreen(double value)
	{
		if(value > 1)
			g = 1;
		else if(value < 0)
			g = 0;
		else
			g = value;
	}
	
	public double getBlue()
	{
		return b;
	}
	
	public void setBlue(double value)
	{
		if(value > 1)
			b = 1;
		else if(value < 0)
			b = 0;
		else
			b = value;
	}
	
	public double getLuma()
	{
		return r * CCIR601_R + g * CCIR601_G + b * CCIR601_B;
	}
	
	public int getValue()
	{
		int aInt = (int) (a * 255);
		int rInt = (int) (r * 255);
		int gInt = (int) (g * 255);
		int bInt = (int) (b * 255);
		return (aInt << 24) + (rInt << 16) + (gInt << 8) + bInt;
	}
	
	public Color add(Color otherColor)
	{
		return new Color(
				this.r + otherColor.r,
				this.g + otherColor.g,
				this.b + otherColor.b,
				this.a + otherColor.a
				);
	}
	
	public Color multiply(double value)
	{
		return new Color(r * value, g * value, b * value);
	}
	
	public Color multiply(Color otherColor)
	{
		return new Color(
				this.r * otherColor.r,
				this.g * otherColor.g,
				this.b * otherColor.b,
				this.a * otherColor.a
				);
	}
}
