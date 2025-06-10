package is.interpreter;

import is.shapes.controller.GraphicObjectController;
import is.shapes.view.calcoloWindow;

public class AreaContext implements Expression {
    int id;
    @Override
    public int interpret(Context context) {
        if (!context.st.hasMoreTokens()) return -1;
        id= Integer.parseInt(context.st.nextToken());
        String token = context.panel.getObjGlobal(id).getType().toLowerCase();

        Expression expr = null;

        switch (token) {
            case "circle":
                expr = new AreaContextCircle();
                break;
            case "rectangle":
                expr = new AreaContextRectangle();
                break;
            default:
                return -1;
        }
        return expr.interpret(context);
    }

    public class AreaContextCircle implements Expression {
        @Override
        public int interpret(Context context) {
                GraphicObjectController goc= new GraphicObjectController(context.handler);
                goc.setControlledObject(context.panel.getObjGlobal(id));
                double ris=(Math.pow((goc.getSubject().getDimension().getHeight()/2),2))*Math.PI;
                new calcoloWindow(goc.getSubject(),ris,"area").setVisible(true);
                return 505050;
        }
    }
    public class AreaContextRectangle implements Expression{
        @Override
        public int interpret(Context context) {
            GraphicObjectController goc= new GraphicObjectController(context.handler);

            goc.setControlledObject(context.panel.getObjGlobal(id));

            double ris=goc.getSubject().getDimension().getHeight()* goc.getSubject().getDimension().getWidth();
            new calcoloWindow(goc.getSubject(),ris,"area").setVisible(true);
            return 505050;
        }
    }
}
