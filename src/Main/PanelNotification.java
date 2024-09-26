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
	private Timer timer;
	private int time;

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
		updateLbBoom();

		p12.add(lbTime = new LabelNumber(this,"000"));
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time++;
				updateLbTime();
			}
		});

		p13.add(btSmile = new ButtonSmile(this));


	}

	public void updateLbTime(){
		if(time > 999){
			lbTime.setNumber("infinite");
		} else {
			String cTime = String.valueOf(time);
			if (cTime.length() == 1) {
				lbTime.setNumber("00" + cTime);
			} else if (cTime.length() == 2) {
				lbTime.setNumber("0" + cTime);
			} else {
				lbTime.setNumber(cTime);
			}

			lbTime.repaint();
		}
	}

	public void updateLbBoom(){
		String boom = String.valueOf(game.getBoom() - game.getWorld().getFlag());
		if(boom.length() == 1){
			lbBoom.setNumber("00" + boom);
		} else if(boom.length() == 2) {
			lbBoom.setNumber("0" + boom);
		} else {
			lbBoom.setNumber("0"+ boom);
		}
		lbBoom.repaint();
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
