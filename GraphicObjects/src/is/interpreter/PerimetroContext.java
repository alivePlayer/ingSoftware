package is.interpreter;

import is.shapes.controller.GraphicObjectController;
import is.shapes.view.calcoloWindow;

public class PerimetroContext implements Expression {
    int id;

    @Override
    public int interpret(Context context) {
        if (!context.st.hasMoreTokens()) return -1;
        id = Integer.parseInt(context.st.nextToken());
        String token = context.panel.getObjGlobal(id).getType().toLowerCase();

        Expression expr = null;

        switch (token) {
            case "circle":
                expr = new PerimetroContextCircle();
                break;
            case "rectangle":
                expr = new PerimetroContextRectangle();
                break;
            default:
                return -1;
        }
        return expr.interpret(context);
    }

    public class PerimetroContextCircle implements Expression {

        @Override
        public int interpret(Context context) {
            GraphicObjectController goc = new GraphicObjectController(context.handler);
            goc.setControlledObject(context.panel.getObjGlobal(id));
            double ris = goc.getSubject().getDimension().getHeight() * Math.PI;
            new calcoloWindow(goc.getSubject(), ris, "perimetro").setVisible(true);
            return 505050;
        }

    }

    public class PerimetroContextRectangle implements Expression {
        @Override
        public int interpret(Context context) {
            GraphicObjectController goc = new GraphicObjectController(context.handler);

            goc.setControlledObject(context.panel.getObjGlobal(id));

            double ris = (goc.getSubject().getDimension().getHeight() *2)+ (goc.getSubject().getDimension().getWidth()*2);
            new calcoloWindow(goc.getSubject(), ris, "perimetro").setVisible(true);
            return 505050;
        }
    }
}

