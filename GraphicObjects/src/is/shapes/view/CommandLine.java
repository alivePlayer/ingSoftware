package is.shapes.view;

import is.command.CommandHandler;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandLine extends JTextField{
    String stringaComando;
    CommandHandler handler;
    JTextField jtf;
    public CommandLine(CommandHandler ch){
        super(50);
        jtf=this;
        handler=ch;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    getString();

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public String getString(){
        stringaComando=jtf.getText();
        jtf.setText("");
        return stringaComando;
    }



}
