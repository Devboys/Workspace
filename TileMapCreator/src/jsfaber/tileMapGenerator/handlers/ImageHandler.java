package jsfaber.tileMapGenerator.handlers;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageHandler {
	
	private static BufferedImage[][] tiles = load("/AltTiles/TileSheet.png", 30, 30);
	public static BufferedImage columnBG = loadSingle("/ColumnPanel/ColumnBG.png");
	public static BufferedImage rowBG = loadSingle("/RowPanel/RowBG.png");
	
	
	
	
	private static BufferedImage[][] load(String path, int tileWidth, int tileHeight) {
		BufferedImage[][] temp;
		try {
			BufferedImage sheet = ImageIO.read(ImageHandler.class.getResourceAsStream(path));
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
			System.out.println("Error in ImageHandler when loading image sheet");
			System.exit(0);
		}
		return null;
		
	}
	
	private static BufferedImage loadSingle(String path) {
		BufferedImage temp;
		try {
			temp = ImageIO.read(ImageHandler.class.getResourceAsStream(path));
			return temp;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in ImageHandler when loading single image");
			System.exit(0);
		}
		return null;
	}
	
	public BufferedImage getImage(int index, int type) {
		BufferedImage temp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		temp = tiles[index][type];
		return temp;
		
	}
}
