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

            FontMetrics fm = g.getFontMetrics();
            int lineHeight = fm.getHeight();

            int x = 10; // margine sinistro
            int y = 30; // margine superiore
            int maxWidth = getWidth() - 20; // larghezza disponibile per il testo

            if (!oggetti.isEmpty()) {
                for (GraphicObject oggetto : oggetti) {
                    String testo = oggetto.toString();
                    String[] words = testo.split(" ");
                    StringBuilder line = new StringBuilder();

                    for (String word : words) {
                        String testLine = line + word + " ";
                        int lineWidth = fm.stringWidth(testLine);
                        if (lineWidth > maxWidth) {
                            // Stampa la riga e vai a capo
                            g.drawString(line.toString(), x, y);
                            y += lineHeight;
                            line = new StringBuilder(word + " ");
                        } else {
                            line.append(word).append(" ");
                        }
                    }

                    // Stampa l'ultima riga
                    if (!line.isEmpty()) {
                        g.drawString(line.toString(), x, y);
                        y += lineHeight;
                    }

                    // Aggiungi una riga vuota tra gli oggetti
                    y += lineHeight;
                }
            } else {
                g.drawString(oggetto.toString(), x, y);
            }
        }
    }
}