package is.interpreter;

import is.shapes.controller.GraphicObjectController;
import is.shapes.view.*;

public class lsContext implements Expression {
    int id;
    @Override
    public int interpret(Context context) {
        if (!context.st.hasMoreTokens()) return -1;
        String token = context.st.nextToken().toLowerCase();

        Expression expr = null;
        try{
            id = Integer.parseInt(token);
            expr= new lsIdContext();
            return expr.interpret(context);
        }catch(NumberFormatException e){

            switch (token) {
                case "circle":
                    expr = new lsCircleContext();
                    break;
                case "rectangle":
                    expr = new lsRectangleContext();
                    break;
                case "image":
                    expr = new lsImageContext();
                    break;
                case "gruppi":
                    expr= new lsGruppiContext();
                    break;
                case "all":
                    expr = new lsAllContext();
                    break;

                default:
                    return -1;
            }
            return expr.interpret(context);

        }


    }
    public class lsIdContext implements Expression {

        @Override
        public int interpret(Context context) {
            GraphicObjectController goc= new GraphicObjectController(context.handler);
            goc.setControlledObject(context.panel.getObjGlobal(id));
            System.out.println(goc.toString());
            new clickedWindow(goc.getSubject()).setVisible(true);
            return 505050;
        }
    }
}

