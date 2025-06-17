package is.interpreter;

import is.TestGraphics2;
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
        if(st.countTokens()>1)
            return addPersonalizzata(st,context);
        else return addStandard(st,context);
    }
    private int addPersonalizzata(StringTokenizer st,Context context) {
        try{
            String file="C:\\Users\\UTENTE\\Desktop\\WS INGS\\imgs\\"+st.nextToken()+".png";
            AbstractGraphicObject obj=new ImageObject(new ImageIcon(file),new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
            context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
            return obj.getID();
        }catch(NoSuchElementException | NumberFormatException e) {
            return -1;
        }


    }
    private int addStandard(StringTokenizer st,Context context){
        AbstractGraphicObject obj= new ImageObject(new ImageIcon("C:\\Users\\UTENTE\\Desktop\\WS INGS\\imgs\\NyaNya.png"),
                new Point(240, 187));
        context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
        return obj.getID();
    }
}
