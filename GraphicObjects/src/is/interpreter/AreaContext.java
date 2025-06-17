package is.interpreter;

import is.shapes.controller.GraphicObjectController;
import is.shapes.model.GraphicObject;
import is.shapes.view.calcoloWindow;

import java.util.Map;

public class AreaContext implements Expression {
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
                expr = new AreaContextCircle();
                break;
            case "rectangle":
                expr = new AreaContextRectangle();
                break;
            case "image":
                expr=new AreaContextImage();
                break;
            case "all":
                expr=new AreaContextAll();
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

    public class AreaContextImage implements Expression{
        @Override
        public int interpret(Context context) {
            GraphicObjectController goc= new GraphicObjectController(context.handler);

            goc.setControlledObject(context.panel.getObjGlobal(id));

            double ris=goc.getSubject().getDimension().getHeight()* goc.getSubject().getDimension().getWidth();
            new calcoloWindow(goc.getSubject(),ris,"area").setVisible(true);
            return 505050;
        }
    }
    public class AreaContextAll implements Expression{
        double ris=0;
        @Override
        public int interpret(Context context) {
            GraphicObjectController goc = new GraphicObjectController(context.handler);
            Map<Integer, GraphicObject> oggetti = context.panel.getObjects();
            for(GraphicObject go : oggetti.values()){
                goc.setControlledObject(go);
                if(go.getType().toLowerCase().equals("rectangle") || go.getType().toLowerCase().equals("image")){
                    ris+=goc.getSubject().getDimension().getHeight()* goc.getSubject().getDimension().getWidth();
                }
                else if(go.getType().toLowerCase().equals("circle")){
                    ris+=(Math.pow((goc.getSubject().getDimension().getHeight()/2),2))*Math.PI;
                }
            }
            new calcoloWindow("tutti",ris,"area").setVisible(true);
            return 505050;
        }
    }

}
