package is.shapes.controller.interpreter;

import is.shapes.concreteCommand.NewObjectConcreteCommand;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.ImageObject;

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AddContextImage implements Expression{
    @Override
    public int interpret(Context context) {
        StringTokenizer st= context.st;
        int val=st.countTokens();
        if(st.countTokens()>=1)
            return addPersonalizzata(st,context);
        else return addStandard(st,context);
    }
    private int addPersonalizzata(StringTokenizer st,Context context) {
        try{
            String file="C:\\Users\\UTENTE\\Desktop\\WS INGS\\ingsoftware\\imgs\\"+st.nextToken()+".png";
            if(st.hasMoreTokens()) {
                AbstractGraphicObject obj = new ImageObject(new ImageIcon(file), new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
                return obj.getID();
            }
            AbstractGraphicObject obj = new ImageObject(new ImageIcon(file), new Point(240, 187));
            context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
            return obj.getID();
        }catch(NoSuchElementException | NumberFormatException e) {
            return -1;
        }


    }
    private int addStandard(StringTokenizer st,Context context){
        String file="C:\\Users\\UTENTE\\Desktop\\WS INGS\\ingsoftware\\imgs\\NyaNya.gif";
        AbstractGraphicObject obj= new ImageObject(new ImageIcon(file), new Point(240, 187));
        context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
        return obj.getID();
    }
}
