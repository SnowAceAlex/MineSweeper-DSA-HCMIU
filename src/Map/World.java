package Map;

import Main.ButtonPlay;
import Main.ButtonSmile;
import Main.GamePanel;
import Main.LabelNumber;

import javax.swing.*;
import java.util.Random;



public class World extends JPanel {
	private Random rd;
	private ButtonPlay[][] arrayButton;
	private int[][] arrayOfBomb; //Bomb is "-1";
	private ButtonSmile buttonSmile;
	private LabelNumber lbTime, lbBoom;
	private int boom;
	private GamePanel game;


	public World(int w, int h, int boom, GamePanel game) {

		this.game = game;
		this.boom = boom;


		arrayButton = new ButtonPlay[w][h];
		arrayOfBomb = new int[w][h];

		rd = new Random();

		createArrayOfBomb(boom,w,h);
		setNumber();

		for(int i = 0; i < arrayOfBomb.length; i++){
			for(int j = 0; j < arrayOfBomb[i].length; j++){
				System.out.print(arrayOfBomb[i][j] + " ");
			}
			System.out.println();
		}
	}

	//Use to randomize a location of bomb in map
	public void createArrayOfBomb(int boom, int w, int h){
		int locationX = rd.nextInt(w);
		int locationY = rd.nextInt(h);

		arrayOfBomb[locationX][locationY] = -1;
		int count = 1;
		while(count != boom){
			locationX = rd.nextInt(w);
			locationY = rd.nextInt(h);
			if(arrayOfBomb[locationX][locationY] != -1){

				arrayOfBomb[locationX][locationY] = -1;

				count = 0;
				for(int i = 0; i < arrayOfBomb.length; i++){
					for(int j = 0; j < arrayOfBomb[i].length; j++){
						if(arrayOfBomb[i][j] == -1)
							count++;
					}
				}
			}
		}
	}

	//Use to create slot with number detect bomb around them
	public void setNumber(){
		for(int i = 0; i < arrayOfBomb.length; i++){
			for(int j = 0; j < arrayOfBomb[i].length; j++){
				if(arrayOfBomb[i][j] == 0){
					int count = 0;
					for(int l = i-1; l <= i+1; l++){
						for(int k = j-1; k <= j+1; k++){
							if(l >= 0 && l <= arrayOfBomb.length - 1 && k >= 0 && k <= arrayOfBomb.length -1){
								if(arrayOfBomb[l][k] == -1){
									count++;
								}
							}
						}
					}
					arrayOfBomb[i][j] = count;
				}
			}
		}
	}

	public boolean open(int i, int j){
		int number = arrayOfBomb[i][j];

		if(number != -1){
			arrayButton[i][j].setNumber(number);
			arrayButton[i][j].repaint();
			return true;
		}else {
			return false;
		}
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
