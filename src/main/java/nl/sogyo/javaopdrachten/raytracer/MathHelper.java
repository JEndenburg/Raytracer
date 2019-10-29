package nl.sogyo.javaopdrachten.raytracer;

public class MathHelper 
{
	/**
	 * Calculates the length of a side that makes a straight (90deg) corner with the side of parameter a.
	 * @param c - The hypotenuse.
	 * @param a - The known other edge.
	 * @return b - The unknown other edge.
	 */
	public static double reversePythagoras(double c, double a)
	{
		return Math.sqrt(Math.abs(c*c - a*a));
	}
	
	public static boolean doubleCloseTo(double a, double b, double margin)
	{
		return Math.abs(a - b) <= margin;
	}
	
	public static boolean doubleCloseto(double a, double b) 
	{
		return doubleCloseTo(a,b,0.0001);
	}
}
