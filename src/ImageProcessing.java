// this code need to be fixed in readImage...It is just given to make it easy.

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

public class ImageProcessing {
	

	public static ArrayList<Integer> readImage(File inFile) {
		
		System.out.println("read: " + inFile);
		BufferedImage image = null;
		try {
			image = ImageIO.read(inFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int width = image.getWidth();
		int height = image.getHeight();
		ArrayList<Integer> pixels = new ArrayList<Integer>();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rgb = image.getRGB(x, y);
				int r = (int) ((rgb >> 16) & 0xff);
				int g = (rgb >> 8) & 0xff;
				int b = (rgb >> 0) & 0xff;
				
				if(r == 0)
					pixels.add(-1);
				else
					pixels.add(1);
				
			}
		}

		return pixels;
	}
	
	////////////////////////////////////////
	public static Vector<Integer>getNumberInVector(int n, int size)
	{
		
		Vector<Integer>v = new Vector<Integer>();
		v.setSize(size);
		for(int i = size -1;i>=0;i--)
		{
			v.set(i, n%2);
			n/=2;
		}
		
		return v;
	}
}
