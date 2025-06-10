package is.interpreter;

import is.shapes.concreteCommand.MoveConcreteCommand;
import is.shapes.controller.GraphicObjectController;

import java.awt.*;

public class MoveOffContext implements Expression{

    @Override
    public int interpret(Context context) {
        GraphicObjectController goc= new GraphicObjectController(context.handler);
        goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(context.st.nextToken())));

        int posX=Integer.parseInt(context.st.nextToken())+(int)goc.getSubject().getPosition().getX();
        int posY=Integer.parseInt(context.st.nextToken())+(int)goc.getSubject().getPosition().getY();
        context.handler.handle(new MoveConcreteCommand(goc.getSubject(),new Point(posX,posY)));
        return 505050;
    }
}
