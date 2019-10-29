package nl.sogyo.javaopdrachten.raytracer;

public class ArgumentOutOfRangeException extends RuntimeException 
{
	private static final long serialVersionUID = 8447551555800881688L;
	
	public ArgumentOutOfRangeException()
	{
		super();
	}
	
	public ArgumentOutOfRangeException(String message)
	{
		super(message);
	}
	
	public ArgumentOutOfRangeException(Throwable cause)
	{
		super(cause);
	}
	
	public ArgumentOutOfRangeException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
