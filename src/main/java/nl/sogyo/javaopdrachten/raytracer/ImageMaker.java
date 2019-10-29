package nl.sogyo.javaopdrachten.raytracer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageMaker 
{
	
	public static void saveImage(String filepath, Color[] render, int width, int height)
	{
		BufferedImage bufferedImage = createBufferedImageFromRender(render, width, height);
		
		File outputFile = new File(filepath);
		try {
			ImageIO.write(bufferedImage, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static BufferedImage createBufferedImageFromRender(Color[] render, int width, int height)
	{
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for(int y = 0, i = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++, i++)
			{
				bufferedImage.setRGB(x, y, render[i].getValue());
			}
		}
		
		return bufferedImage;
	}
}
