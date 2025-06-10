package is.interpreter;

import is.shapes.concreteCommand.ZoomConcreteCommand;
import is.shapes.controller.GraphicObjectController;

public class ScaleContext implements Expression{
    @Override
    public int interpret(Context context) {
        GraphicObjectController goc= new GraphicObjectController(context.handler);
        goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(context.st.nextToken())));
        context.handler.handle(new ZoomConcreteCommand(goc.getSubject(), Double.parseDouble(context.st.nextToken())));
        return 505050;
    }
}
