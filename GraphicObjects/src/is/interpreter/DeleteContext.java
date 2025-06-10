package is.interpreter;

import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.controller.GraphicObjectController;

import java.util.StringTokenizer;

public class DeleteContext implements Expression {

    @Override
    public int interpret(Context context) {
        StringTokenizer st=context.st;
        if(!st.hasMoreTokens()) return -1;
        String token=st.nextToken().toLowerCase();
        try{


            GraphicObjectController goc= new GraphicObjectController(context.handler);

            if(context.panel.getObjGlobal(Integer.parseInt(token))==null) return -1;

            goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(token)));
            context.handler.handle(new DeleteConcreteCommand(context.panel,goc.getSubject()));
            return 404040;


        }catch(NumberFormatException e){
            return -1;
        }
    }
}
