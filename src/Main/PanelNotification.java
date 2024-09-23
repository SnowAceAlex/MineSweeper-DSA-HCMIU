package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class PanelNotification extends JPanel {
	private JPanel p11, p12, p13;
	private GamePanel game;
	private ButtonSmile btSmile;
	private LabelNumber lbTime, lbBoom;


	public PanelNotification(GamePanel game) {
		this.game = game;
		btSmile = game.getWorld().getButtonSmile();
		lbTime = game.getWorld().getLbTime();
		lbBoom = game.getWorld().getLbBoom();

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLoweredBevelBorder());

		add(p11 = new JPanel(), BorderLayout.WEST);
		add(p12 = new JPanel(), BorderLayout.EAST);
		add(p13 = new JPanel(), BorderLayout.CENTER);

		p11.add(lbBoom = new LabelNumber(this,"000"));


		p12.add(lbTime = new LabelNumber(this,"000"));

		p13.add(btSmile = new ButtonSmile(this));


	}


	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}



}
