package is.shapes.view;

import javax.swing.*;
import java.awt.*;

public class DeleteWindow extends JFrame {
    private Pannello panel;

    public DeleteWindow() {

        setTitle("DELETE AVVENUTA");
        setSize(600, 400);
        setLocation(0,0);

        add(panel = new Pannello());
        panel.setVisible(true);
    }
    private class Pannello extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            String messaggio="OGGETTO ELIMINATO CON SUCCESSO";
            FontMetrics fm = g.getFontMetrics(font);
            int textWidth = fm.stringWidth(messaggio);
            int textHeight = fm.getHeight();


            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + fm.getAscent();

            g.drawString(messaggio, x, y);
        }
    }
}
