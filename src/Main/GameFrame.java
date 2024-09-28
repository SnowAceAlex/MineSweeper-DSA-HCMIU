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
	private JMenuBar jMenuBar;
	private JMenu menu;
	private JMenuItem basic,normal,hard,newGame,exit;


	public GameFrame(int w, int h, int boom) {

		loadData = new LoadData();

		setJMenuBar(jMenuBar = new JMenuBar());
		jMenuBar.add(menu = new JMenu("Game"));

		menu.add(newGame = new JMenuItem("New game"));
		menu.addSeparator();
		menu.add(basic = new JMenuItem("Basic"));
		menu.add(normal = new JMenuItem("Nomal"));
		menu.add(hard = new JMenuItem("Hard"));
		menu.addSeparator();
		menu.add(exit = new JMenuItem("Exit"));

		if (w == 8) {
			basic.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
		} else if (w == 16 && h == 16) {
			normal.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
		} else if(w == 16 && h == 30){
			hard.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
		}


		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(8, 8, 10);
			}
		});

		normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(16, 16, 40);
			}
		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(16, 30, 99);
			}
		});

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(w, h, boom);
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});




		add(gamePanel = new GamePanel(w, h, boom, this));

		setIconImage(loadData.getListImage().get("bomb"));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame(8, 8, 10);
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
