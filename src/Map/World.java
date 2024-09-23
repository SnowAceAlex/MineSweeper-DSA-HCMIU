package Map;

import Main.ButtonPlay;
import Main.ButtonSmile;
import Main.GamePanel;
import Main.LabelNumber;

import java.util.Random;



public class World {
	private Random rd;
	private ButtonPlay[][] arrayButton;
	private ButtonSmile buttonSmile;
	private LabelNumber lbTime, lbBoom;
	private int boom;
	private GamePanel game;


	public World(int w, int h, int boom, GamePanel game) {

		this.game = game;
		this.boom = boom;

		arrayButton = new ButtonPlay[w][h];
	}

	public ButtonSmile getButtonSmile() {
		return buttonSmile;
	}

	public void setButtonSmile(ButtonSmile buttonSmile) {
		this.buttonSmile = buttonSmile;
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public LabelNumber getLbTime() {
		return lbTime;
	}

	public void setLbTime(LabelNumber lbTime) {
		this.lbTime = lbTime;
	}

	public LabelNumber getLbBoom() {
		return lbBoom;
	}

	public void setLbBoom(LabelNumber lbBoom) {
		this.lbBoom = lbBoom;
	}
}
