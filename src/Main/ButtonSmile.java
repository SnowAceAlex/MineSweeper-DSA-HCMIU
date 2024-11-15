package Main;

import javax.swing.*;
import java.awt.*;

public class ButtonSmile extends JButton {

    private PanelNotification p;

    public ButtonSmile(PanelNotification p){
        this.p = p;
        setPreferredSize(new Dimension(75,75));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smile"), 0, 0,
                getPreferredSize().width, getPreferredSize().height, null);
    }
}