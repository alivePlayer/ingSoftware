package is.interpreter;


import is.shapes.concreteCommand.ZoomConcreteCommand;
import is.shapes.controller.GraphicObjectController;

public class ZoomContext implements Expression{


    @Override
    public int interpret(Context context) {
        String txt =context.st.nextToken();
        if(txt.equals("+")) return bigger(context);
        else if(txt.equals("-")) return smaller(context);
        return -1;

    }
    private int smaller(Context context) {
        GraphicObjectController goc= new GraphicObjectController(context.handler);
        goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(context.st.nextToken())));
        context.handler.handle(new ZoomConcreteCommand(goc.getSubject(), 1.0 - 0.1));
        return 505050;
    }
    private int bigger(Context context) {
        GraphicObjectController goc= new GraphicObjectController(context.handler);
        goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(context.st.nextToken())));
        context.handler.handle(new ZoomConcreteCommand(goc.getSubject(), 1.0 + 0.1));
        return 505050;
    }
}
