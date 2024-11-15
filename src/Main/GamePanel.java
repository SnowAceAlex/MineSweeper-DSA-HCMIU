package Main;

import Logic.World;

import java.awt.BorderLayout;
import java.util.Stack;

import javax.swing.JPanel;



public class GamePanel extends JPanel{
	private PanelNotification panelNotification;
	private PanelPlayer panelPlayer;
	private GameFrame gameFrame;
	private World world;
	private int width;
	private int height;
	private int boom;
	private Stack<World> undoStack = new Stack<>();


	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		this.boom = boom;
		this.width = w;
		this.height = h;

		world = new World(w, h, boom, this);

		setLayout(new BorderLayout(20, 20));

		add(panelNotification = new PanelNotification(this), BorderLayout.NORTH);
		add(panelPlayer = new PanelPlayer(this), BorderLayout.CENTER);

		// Add the GameMouseListener to handle mouse events
		GameMouseListener gameMouseListener = new GameMouseListener(this, world, gameFrame, w, h, boom);
		addMouseListener(gameMouseListener);
	}

	public void saveState() {
		try {
			World currentState = (World) world.clone();
			undoStack.push(currentState);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		if (!undoStack.isEmpty()) {
			world = undoStack.pop();
			for (int i = 0; i < world.getArrayButton().length; i++) {
				for (int j = 0; j < world.getArrayButton()[i].length; j++) {
					world.getArrayButton()[i][j].repaint();
				}
			}
			revalidate();
			repaint();
		}
	}



	public int getW() {
		return width;
	}

	public void setW(int w) {
		this.width = width;
	}

	public int getH() {
		return height;
	}

	public void setH(int h) {
		this.height = height;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public int getBoom() {
		return boom;
	}

	public void setBoom(int boom) {
		this.boom = boom;
	}

	public PanelNotification getpanelNotification() {
		return panelNotification;
	}

	public void setpanelNotification(PanelNotification panelNotification) {
		this.panelNotification = panelNotification;
	}

	public PanelPlayer getpanelPlayer() {
		return panelPlayer;
	}

	public void setpanelPlayer(PanelPlayer panelPlayer) {
		this.panelPlayer = panelPlayer;
	}


}
