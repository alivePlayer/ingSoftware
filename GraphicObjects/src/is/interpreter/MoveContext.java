package is.interpreter;

import is.shapes.concreteCommand.MoveConcreteCommand;
import is.shapes.controller.GraphicObjectController;

import java.awt.*;

public class MoveContext implements Expression{
    @Override
    public int interpret(Context context) {
        GraphicObjectController goc= new GraphicObjectController(context.handler);
        goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(context.st.nextToken())));
        context.handler.handle(new MoveConcreteCommand(goc.getSubject(),
                               new Point(Integer.parseInt(context.st.nextToken()),Integer.parseInt(context.st.nextToken()))));
        return 505050;
    }
}
