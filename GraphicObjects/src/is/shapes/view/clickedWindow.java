package is.shapes.view;

import is.shapes.model.GraphicObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class clickedWindow extends JFrame {
    private Pannello panel;
    private List<GraphicObject> oggetti = new ArrayList<>();
    private GraphicObject oggetto;

    public clickedWindow(GraphicObject oggetto) {
        setTitle("Controllo oggetto");
        setSize(900, 700);
        setLocation(0, 0);
        this.oggetto = oggetto;
        add(panel = new Pannello());
        panel.setVisible(true);

    }

    public clickedWindow(ArrayList<GraphicObject> oggetti) {
        setTitle("Controllo oggetto");
        setSize(900, 700);
        setLocation(0, 0);
        this.oggetti = oggetti;
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

            // Ottieni le metriche del font una sola volta
            FontMetrics fm = g.getFontMetrics();
            int lineHeight = fm.getHeight();

            // Preparazione del testo
            String[] lines;
            if (!oggetti.isEmpty()) {
                lines = new String[oggetti.size()];
                int i = 0;
                for (GraphicObject oggetto : oggetti) {
                    lines[i++] = oggetto.toString();
                }
            } else {
                lines = new String[]{oggetto.toString()};
            }

            // Calcola la posizione Y iniziale (centrata verticalmente)
            int totalTextHeight = lines.length * lineHeight;
            int y = (getHeight() - totalTextHeight) / 2 + fm.getAscent();

            // Disegna ogni riga centrata orizzontalmente
            for (String line : lines) {
                int textWidth = fm.stringWidth(line);
                int x = (getWidth() - textWidth) / 2;
                g.drawString(line, x, y);
                y += lineHeight; // Vai alla riga successiva
            }
        }
    }
}