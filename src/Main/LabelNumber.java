package Main;

import javax.swing.*;
import java.awt.*;

public class LabelNumber extends JLabel {
    private PanelNotification p;
    private String number;
    public LabelNumber(PanelNotification p, String number) {
        this.p = p;
        this.number = number;
        setPreferredSize(new Dimension(156,92));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(0))), 0, 0, 52, 92, null);
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(1))), 52, 0, 52, 92, null);
        g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(2))), 104, 0, 52, 92, null);
    }
}
