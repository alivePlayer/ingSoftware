package is.shapes.controller.interpreter;

import is.shapes.controller.GraphicObjectController;
import is.shapes.model.GraphicObject;
import is.shapes.view.calcoloWindow;

import java.util.Map;

public class PerimetroContext implements Expression {
    int id;
    String all,token;
    @Override
    public int interpret(Context context) {
        if (!context.st.hasMoreTokens()) return -1;
        try {
            all = context.st.nextToken();
            id = Integer.parseInt(all);
            token = context.panel.getObjGlobal(id).getType().toLowerCase();
        } catch (NumberFormatException e) {
            token = all;
        }

        Expression expr = null;

        switch (token) {
            case "circle":
                expr = new PerimetroContextCircle();
                break;
            case "rectangle":
                expr = new PerimetroContextRectangle();
                break;
            case "image":
                expr=new PerimetroContextImage();
                break;
            case "all":
                expr=new PerimetroContextAll();

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

    public class PerimetroContextImage implements Expression {
        @Override
        public int interpret(Context context) {
            GraphicObjectController goc=new GraphicObjectController(context.handler);
            goc.setControlledObject(context.panel.getObjGlobal(id));
            double ris = (goc.getSubject().getDimension().getHeight() *2)+ (goc.getSubject().getDimension().getWidth()*2);
            new calcoloWindow(goc.getSubject(), ris, "perimetro").setVisible(true);
            return 505050;
        }
    }

    public class PerimetroContextAll implements Expression {
        double ris=0;
        @Override
        public int interpret(Context context) {
            GraphicObjectController goc= new GraphicObjectController(context.handler);
            Map<Integer,GraphicObject> oggetti=context.panel.getObjects();
            for (GraphicObject go : oggetti.values()) {
                goc.setControlledObject(go);
                if(go.getType().toLowerCase().equals("rectangle") || go.getType().toLowerCase().equals("image")) {
                    ris += (goc.getSubject().getDimension().getHeight() * 2) + (goc.getSubject().getDimension().getWidth() * 2);
                }
                else if (go.getType().toLowerCase().equals("circle")){
                    ris += goc.getSubject().getDimension().getHeight() * Math.PI;
                }
            }
            new calcoloWindow("tutti", ris, "perimetro").setVisible(true);
            return 505050;
        }


        }
}

