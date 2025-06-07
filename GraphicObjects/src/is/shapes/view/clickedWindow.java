package is.shapes.view;

import is.shapes.model.GraphicObject;

import javax.swing.*;
import java.awt.*;

public class clickedWindow extends JFrame {
    private Pannello panel;
    private GraphicObject oggetto;
    public clickedWindow(GraphicObject go) {
        setTitle("Controllo oggetto");
        setSize(900, 700);
        setLocation(0,0);
        oggetto=go;
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
            String messaggio=oggetto.toString();
            FontMetrics fm = g.getFontMetrics(font);
            int textWidth = fm.stringWidth(messaggio);
            int textHeight = fm.getHeight();


            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + fm.getAscent();

            g.drawString(messaggio, x, y);
        }
    }
}
