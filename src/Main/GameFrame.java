package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Utility.LoadData;
public class GameFrame extends JFrame {

	private LoadData loadData;

	private GamePanel gamePanel;


	public GameFrame(int w, int h, int boom) {

		loadData = new LoadData();

		add(gamePanel = new GamePanel(w, h, boom, this));

		setIconImage(loadData.getListImage().get("bomb"));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame(9, 9, 10);
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

}
