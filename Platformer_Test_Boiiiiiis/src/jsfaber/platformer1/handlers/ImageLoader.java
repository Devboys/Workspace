package jsfaber.platformer1.handlers;

import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;



public class ImageLoader {
	
	public static BufferedImage[][] Level1Tiles = load("/Level1/L1Tilesheet.png", 30, 30);
	public static BufferedImage[][] PlayerSheet = load("/Player/tile1.png", 20, 40);
	
	private static BufferedImage[][] load(String path, int tileWidth, int tileHeight) {
		BufferedImage[][] temp;
		try {
			BufferedImage sheet = ImageIO.read(ImageLoader.class.getResourceAsStream(path));
			int numColumns = sheet.getWidth() / tileWidth;
			int numRows = sheet.getHeight() / tileHeight;
			temp = new BufferedImage[numRows][numColumns];
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numColumns; j++) {
					temp[i][j] = sheet.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
				}
			}
			return temp;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in ImageHandler when loading images");
			System.exit(0);
		}
		return null;
		
	}
}
