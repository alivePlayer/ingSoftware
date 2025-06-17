package is.interpreter;

import is.shapes.concreteCommand.MoveConcreteCommand;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.Gruppo;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MoveContext implements Expression{
    @Override
    public int interpret(Context context) {
        StringTokenizer st= context.st;
        String token = st.nextToken().toLowerCase();
        ArrayList<Point2D> multipleOldPos = new ArrayList<>();
        try {
            if (token.charAt(0) == '0') {
                String gruppoId = token.substring(1, token.length());
                Gruppo gruppo=context.panel.getGruppo(Integer.parseInt(gruppoId));
                for(int i =0;i<gruppo.getSize();i++)
                    multipleOldPos.add(gruppo.getContenuto(i).getPosition());
                Point newPos= new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                context.handler.handle(new MoveConcreteCommand(gruppo,multipleOldPos,newPos));
                return 505050;

            }
            GraphicObjectController goc = new GraphicObjectController(context.handler);
            goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(token)));
            context.handler.handle(new MoveConcreteCommand(goc.getSubject(),
                    new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))));
            return 505050;

        }catch(NoSuchElementException e) {
            return -1;
        }
    }
}
