package is.shapes.view;

import is.command.CommandHandler;
import is.shapes.concreteCommand.NewObjectConcreteCommand;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;

public class CommandLine extends JTextField{
    String stringaComando;
    CommandHandler handler;
    GraphicObjectPanelReceiver panel;
    JTextField jtf;
    CommandLineAction cla;

    public CommandLine(CommandHandler ch,GraphicObjectPanelReceiver f){
        super(50);
        panel=f;
        jtf=this;
        handler=ch;
        cla=new CommandLineAction(handler,panel);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    StringTokenizer st=getString();
                    if(!cla.commandChoser(st))
                        new ErrorWindow().setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    private StringTokenizer getString(){
        stringaComando=jtf.getText();
        StringTokenizer st= new StringTokenizer(stringaComando);
        jtf.setText("");
        return st;
    }






}
