package Utility;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.imageio.ImageIO;

public class LoadData {

	private HashMap<String, BufferedImage> listImage;

	public LoadData() {
		listImage = new HashMap<String, BufferedImage>();

		try {
			BufferedImage img =ImageIO
					.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("minesweeper.png")));
			listImage.put("bomb", img.getSubimage(0,0,16,16));
			listImage.put("bombRed", img.getSubimage(16,0,16,16));
			listImage.put("flag", img.getSubimage(32,0,16,16));
			listImage.put("explode", img.getSubimage(48,0,16,16));
			listImage.put("bombX", img.getSubimage(17,48,15,16));
			listImage.put("noUse", img.getSubimage( 32,48,16,16));
			listImage.put("b0", img.getSubimage(48, 48, 16, 16));
			listImage.put("b1", img.getSubimage(0, 16, 16, 16));
			listImage.put("b2", img.getSubimage(16, 16, 16, 16));
			listImage.put("b3", img.getSubimage(32, 16, 16, 16));
			listImage.put("b4", img.getSubimage(48, 16, 16, 16));
			listImage.put("b5", img.getSubimage(0, 32, 16, 16));
			listImage.put("b6", img.getSubimage(16, 32, 16, 16));
			listImage.put("b7", img.getSubimage(32, 32, 16, 16));
			listImage.put("b8", img.getSubimage(48, 32, 16, 16));


			BufferedImage img2 =ImageIO
					.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream( "minesweeper2.png")));
			listImage.put("smile", img2.getSubimage(0,55,26,26));
			listImage.put("0", img2.getSubimage(0, 0, 13, 23));
			listImage.put("1", img2.getSubimage(13, 0, 13, 23));
			listImage.put("2", img2.getSubimage(26, 0, 13, 23));
			listImage.put("3", img2.getSubimage(39, 0, 13, 23));
			listImage.put("4", img2.getSubimage(52, 0, 13, 23));
			listImage.put("5", img2.getSubimage(65, 0, 13, 23));
			listImage.put("6", img2.getSubimage(78, 0, 13, 23));
			listImage.put("7", img2.getSubimage(91, 0, 13, 23));
			listImage.put("8", img2.getSubimage(104, 0, 13, 23));
			listImage.put("9", img2.getSubimage(117, 0, 13, 23));
			listImage.put("infinite", img2.getSubimage(120, 0, 13, 23));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, BufferedImage> getListImage() {
		return listImage;
	}

	public void setListImage(HashMap<String, BufferedImage> listImage) {
		this.listImage = listImage;
	}

}
