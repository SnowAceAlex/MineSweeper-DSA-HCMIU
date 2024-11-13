package Main;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelPlayer extends JPanel {
	private GamePanel game;

	private ButtonPlay[][] arrayButton;

	public PanelPlayer(GamePanel game) {
		this.game = game;

		setLayout(new GridLayout(game.getW(), game.getH()));
		arrayButton = game.getWorld().getArrayButton();

		setBorder(BorderFactory.createLoweredBevelBorder());
		GameMouseListener gameMouseListener = new GameMouseListener(game, game.getWorld(), game.getGameFrame(), game.getW(), game.getH(), game.getBoom());
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				add(arrayButton[i][j] = new ButtonPlay(this));
				arrayButton[i][j].addMouseListener(gameMouseListener);
				add(arrayButton[i][j]);
			}
		}
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

}
