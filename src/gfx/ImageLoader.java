package gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImage(String path) {
		BufferedImage bufImage = null;
		try {
			bufImage = ImageIO.read(ImageLoader.class.getResource(path));
		} catch (Exception e) {
			System.out.println("Error to load image: " + path);
			e.printStackTrace();
			System.exit(1);
		}
		return bufImage;
	}
}
