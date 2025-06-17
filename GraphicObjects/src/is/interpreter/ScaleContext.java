package is.interpreter;

import is.shapes.concreteCommand.ZoomConcreteCommand;
import is.shapes.controller.GraphicObjectController;

import java.util.StringTokenizer;

public class ScaleContext implements Expression{
    @Override
    public int interpret(Context context) {
        try {
            StringTokenizer st1= context.st;
            String id= st1.nextToken().toLowerCase();
            if('0'==id.charAt(0)){
                String gruppoId=id.substring(1,id.length());
                context.handler.handle(new ZoomConcreteCommand(Double.parseDouble(st1.nextToken()),context.panel.getGruppo(Integer.parseInt(id))));
                return 505050;
            }
            GraphicObjectController goc = new GraphicObjectController(context.handler);
            goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(id)));
            context.handler.handle(new ZoomConcreteCommand(goc.getSubject(), Double.parseDouble(st1.nextToken())));
            return 505050;


        }catch (Exception e) {return -1;}
    }
}
