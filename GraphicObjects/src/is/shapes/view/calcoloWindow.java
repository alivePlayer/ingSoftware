package is.shapes.view;

import is.shapes.model.GraphicObject;

import javax.swing.*;
import java.awt.*;

public class calcoloWindow extends JFrame {
    private Pannello panel;
    private GraphicObject oggetto;
    double risultato;
    String tipo;
    public calcoloWindow(GraphicObject go,double risultato,String tipo) {
        setTitle("Controllo oggetto");
        setSize(900, 700);
        setLocation(0,0);
        oggetto=go;
        add(panel = new Pannello());
        panel.setVisible(true);
        this.risultato=risultato;
        this.tipo=tipo;
    }

    private class Pannello extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            String messaggio="oggetto con id: "+oggetto.getID()+" calcolata "+tipo+"="+risultato;
            FontMetrics fm = g.getFontMetrics(font);
            int textWidth = fm.stringWidth(messaggio);
            int textHeight = fm.getHeight();


            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + fm.getAscent();

            g.drawString(messaggio, x, y);
        }
    }
}
