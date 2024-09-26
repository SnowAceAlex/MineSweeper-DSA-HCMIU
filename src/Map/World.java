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
	private boolean[][] arrayBooleanChecker; //Use to keep track and check the state of array in type of boolean
	private boolean[][] arrayPutFlag;
	private int flag;
	private boolean isFail; //Use to mark that bomb has buummm for all program to always return retrytabel
	private boolean isWin;
	private ButtonSmile buttonSmile;
	private LabelNumber lbTime, lbBoom;
	private int boom;
	private GamePanel game;


	public World(int w, int h, int boom, GamePanel game) {

		this.game = game;
		this.boom = boom;


		arrayButton = new ButtonPlay[w][h];
		arrayOfBomb = new int[w][h];
		arrayBooleanChecker = new boolean[w][h];
		arrayPutFlag = new boolean[w][h];


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
//		if(!arrayPutFlag[i][j]) {
			if (!isFail && !isWin) {
				if (!arrayBooleanChecker[i][j]) {
					arrayBooleanChecker[i][j] = true;

					//Use to remove all blank square together
					if (arrayOfBomb[i][j] == 0) {
						arrayBooleanChecker[i][j] = true;
						arrayButton[i][j].setNumber(0);
						arrayButton[i][j].repaint();

						if (winnerChecker()) {
							isWin = true;

							fullTrue();

							return false;
						}

						for (int l = i - 1; l <= i + 1; l++) {
							for (int k = j - 1; k <= j + 1; k++) {
								if (l >= 0 && l <= arrayOfBomb.length - 1 && k >= 0 && k <= arrayOfBomb.length - 1) {
									if (!arrayBooleanChecker[l][k]) {
										open(l, k);
									}
								}
							}
						}
					}

					int number = arrayOfBomb[i][j];

					if (number != -1) {
						arrayButton[i][j].setNumber(number);
						arrayButton[i][j].repaint();

						if (winnerChecker()) {
							isWin = true;

							fullTrue();

							return false;
						}

						return true;
					}
				}

				if (arrayOfBomb[i][j] == -1) {
					arrayButton[i][j].setNumber(12);
					arrayButton[i][j].repaint();
					isFail = true;

					for (int l = 0; l < arrayBooleanChecker.length; l++) {
						for (int k = 0; k < arrayBooleanChecker[l].length; k++) {
							if (arrayOfBomb[l][k] == -1 && !arrayBooleanChecker[l][k]) {
								arrayButton[l][k].setNumber(11);
								arrayButton[l][k].repaint();
							}
						}
					}

					return false;
				} else {
					return true;
				}
			} else {
				return false; // return false to trigger the loser executed
			}
//		}
//		return true;
	}

	public void putFlag(int i, int j){
		if(!arrayBooleanChecker[i][j]){
			if(arrayPutFlag[i][j]){
				flag--;
				arrayPutFlag[i][j] = false;
				arrayButton[i][j].setNumber(-1);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			}else if(flag < boom){
				flag++;
				arrayPutFlag[i][j] = true;
				arrayButton[i][j].setNumber(9);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			}
		}
	}

	public boolean doubleClick(int i, int j){

		boolean isHaveMine = false;

		for (int l = i - 1; l <= i + 1; l++) {
			for (int k = j - 1; k <= j + 1; k++) {
				if (l >= 0 && l <= arrayOfBomb.length - 1 && k >= 0 && k <= arrayOfBomb[i].length - 1) {
					if (!arrayPutFlag[l][k]) {
						if (arrayOfBomb[l][k] == -1) {
							isHaveMine = true;
							arrayButton[l][k].setNumber(12);
							arrayButton[l][k].repaint();
							arrayBooleanChecker[l][k] = true;
						} else if (!arrayBooleanChecker[l][k]) {
							if (arrayOfBomb[l][k] == 0) {
								open(l, k);
							} else {
								arrayButton[l][k].setNumber(arrayOfBomb[l][k]);
								arrayButton[l][k].repaint();
								arrayBooleanChecker[l][k] = true;
							}
						}
					}
				}
			}
		}
		if (isHaveMine) {
			for (int l = 0; l < arrayBooleanChecker.length; l++) {
				for (int k = 0; k < arrayBooleanChecker[i].length; k++) {
					if (arrayOfBomb[l][k] == -1 && !arrayBooleanChecker[l][k]) {
						arrayButton[l][k].setNumber(10);
						arrayButton[l][k].repaint();
					}
				}
			}
			return false;
		}

		return true;
	}

	public boolean winnerChecker() {
		int count = 0;
		for (int i = 0; i < arrayBooleanChecker.length; i++) {
			for (int j = 0; j < arrayBooleanChecker[i].length; j++) {
				if (!arrayBooleanChecker[i][j]) {
					count++;
				}
			}
		}
		if (count == boom) {
			return true;
		} else {
			return false;
		}
	}

	//always return true to force arrayBoolean which i use to notification for program prevent them say no and cont play when fail
	public void fullTrue(){
		for (int i = 0; i < arrayBooleanChecker.length; i++) {
			for (int j = 0; j < arrayBooleanChecker[i].length; j++) {
				if(!arrayBooleanChecker[i][j]){
					arrayBooleanChecker[i][j] = true;
				}
			}
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

	public boolean[][] getArrayBooleanChecker() {
		return arrayBooleanChecker;
	}

	public void setArrayBooleanChecker(boolean[][] arrayBooleanChecker) {
		this.arrayBooleanChecker = arrayBooleanChecker;
	}

	public boolean isFail() {
		return isFail;
	}

	public void setFail(boolean fail) {
		isFail = fail;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean win) {
		isWin = win;
	}

	public boolean[][] getArrayPutFlag() {
		return arrayPutFlag;
	}

	public void setArrayPutFlag(boolean[][] arrayPutFlag) {
		this.arrayPutFlag = arrayPutFlag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
