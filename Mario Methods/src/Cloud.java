import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cloud {
	String path = "images/cloud.png";
	BufferedImage image;
	private int x;
	private int y;
	
	public Cloud(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		image = getImage(path);
	}

	private BufferedImage getImage(String path2) {
		BufferedImage b = null;
				
		try
		{
			b = ImageIO.read(new File(path2));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void draw(Graphics g)
	{
		
	}
}
