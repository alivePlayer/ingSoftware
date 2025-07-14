package is.shapes.controller.interpreter;

import is.shapes.concreteCommand.NewObjectConcreteCommand;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AddContextCircle implements Expression{
    @Override
    public int interpret(Context context) {
        StringTokenizer st= context.st;
        if(st.countTokens()>1)
            return addPersonalizzata(st,context);
        else return addStandard(st,context);
    }

    private int addPersonalizzata(StringTokenizer st,Context context) {
            try {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                double raggio = Integer.parseInt(st.nextToken());
                AbstractGraphicObject obj = new CircleObject(new Point(x, y), raggio);
                context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
                return obj.getID();
            } catch (NoSuchElementException | NumberFormatException e) {
                return -1;
            }


    }
    private int addStandard(StringTokenizer st,Context context){
        AbstractGraphicObject obj= new CircleObject(new Point(200, 100), 10);
        context.handler.handle(new NewObjectConcreteCommand(context.panel, obj));
        return obj.getID();
    }
}
