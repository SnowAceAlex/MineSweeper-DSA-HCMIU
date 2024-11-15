package Main;

import Logic.World;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class GameMouseListener implements MouseListener {
    private GamePanel gamePanel;
    private World world;
    private GameFrame gameFrame;
    private int w;
    private int h;
    private int boom;

    public GameMouseListener(GamePanel gamePanel, World world, GameFrame gameFrame, int w, int h, int boom) {
        this.gamePanel = gamePanel;
        this.world = world;
        this.gameFrame = gameFrame;
        this.w = w;
        this.h = h;
        this.boom = boom;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click events
    }

    @Override
    public void mousePressed(MouseEvent e) {

        ButtonPlay[][] arrayButton = gamePanel.getpanelPlayer().getArrayButton();
        for (int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton[i].length; j++) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getSource() == arrayButton[i][j] && !world.getArrayPutFlag()[i][j]) {
                    if (!gamePanel.getpanelNotification().getTimer().isRunning()) {
                        gamePanel.getpanelNotification().getTimer().start();
                    }

                    if (!world.open(i, j)) {
                        if (world.isFail()) {
                            gamePanel.getpanelNotification().getTimer().stop();
                            int option = JOptionPane.showConfirmDialog(gamePanel, "You lost, play again?", "Notification", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                gameFrame.setVisible(false);
                                new GameFrame(w, h, boom);
                            } else {
                                world.fullTrue();
                            }
                        } else if (world.isWin()) {
                            gamePanel.getpanelNotification().getTimer().stop();
                            int option = JOptionPane.showConfirmDialog(gamePanel, "You win, play again?", "Notification", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                gameFrame.setVisible(false);
                                new GameFrame(w, h, boom);
                            }
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3 && e.getSource() == arrayButton[i][j]) {
                    world.putFlag(i, j);
                }
                if (e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] && world.getArrayBooleanChecker()[i][j]) {
                    if (!world.doubleClick(i, j)) {
                        int option = JOptionPane.showConfirmDialog(gamePanel, "You lost, play again?", "Notification", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            gameFrame.setVisible(false);
                            new GameFrame(w, h, boom);
                        } else {
                            world.fullTrue();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse release events
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter events
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit events
    }
}