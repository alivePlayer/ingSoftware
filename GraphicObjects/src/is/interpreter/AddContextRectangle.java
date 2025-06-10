package is.interpreter;

import is.shapes.concreteCommand.NewObjectConcreteCommand;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.RectangleObject;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AddContextRectangle implements Expression{
    @Override
    public int interpret(Context context) {
        StringTokenizer st= context.st;
        if(st.countTokens()>1)
            return addPersonalizzata(st,context);
        else return addStandard(st,context);
    }
    private int addPersonalizzata(StringTokenizer st,Context context) {
        try {
            AbstractGraphicObject obj = new RectangleObject(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));

            return obj.getID();
        }catch(NoSuchElementException | NumberFormatException e) {
            return -1;
        }


    }
    private int addStandard(StringTokenizer st,Context context){
        AbstractGraphicObject obj= new RectangleObject(new Point(200, 100), 10,20);
        context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
        return obj.getID();
    }
}
