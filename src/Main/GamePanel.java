package Main;

import Map.World;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.management.Notification;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GamePanel extends JPanel{
	private PanelNotification p1;
	private PanelPlayer p2;
	private GameFrame gameFrame;
	private World world;
	private int w;
	private int h;
	private int boom;


	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		this.boom = boom;
		this.w = w;
		this.h = h;

		world = new World(w, h, boom, this);

		setLayout(new BorderLayout(20, 20));

		add(p1 = new PanelNotification(this), BorderLayout.NORTH);
		add(p2 = new PanelPlayer(this), BorderLayout.CENTER);

		// Add the GameMouseListener to handle mouse events
		GameMouseListener gameMouseListener = new GameMouseListener(this, world, gameFrame, w, h, boom);
		addMouseListener(gameMouseListener);
	}




	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
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

	public PanelNotification getP1() {
		return p1;
	}

	public void setP1(PanelNotification p1) {
		this.p1 = p1;
	}

	public PanelPlayer getP2() {
		return p2;
	}

	public void setP2(PanelPlayer p2) {
		this.p2 = p2;
	}


}
