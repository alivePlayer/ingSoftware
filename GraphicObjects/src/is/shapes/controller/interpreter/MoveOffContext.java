package is.shapes.controller.interpreter;

import is.shapes.concreteCommand.MoveConcreteCommand;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.Gruppo;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MoveOffContext implements Expression {

    @Override
    public int interpret(Context context) {
        ArrayList<Point2D> multipleOldPos = new ArrayList<>();
        ArrayList<Point2D> multipleNewPos = new ArrayList<>();
        try {
            StringTokenizer st1=context.st;
            String id=st1.nextToken().toLowerCase();
            if('0'==id.charAt(0)){
                String gruppoId=id.substring(1,id.length());
                Gruppo gruppo= context.panel.getGruppo(Integer.parseInt(gruppoId));
                int x=Integer.parseInt(st1.nextToken());
                int y=Integer.parseInt(st1.nextToken());
                for(int i=0;i<gruppo.getSize();i++){
                    multipleOldPos.add(gruppo.getContenuto(i).getPosition());
                    multipleNewPos.add(
                            new Point(
                                    (int)(gruppo.getContenuto(i).getPosition().getX()+x),
                                    (int)(gruppo.getContenuto(i).getPosition().getY()+y)
                            )
                    );
                }

                context.handler.handle(new MoveConcreteCommand(gruppo,multipleOldPos,multipleNewPos));
                return 505050;

            }

            GraphicObjectController goc= new GraphicObjectController(context.handler);
            goc.setControlledObject(context.panel.getObjGlobal(Integer.parseInt(id)));

            int posX=Integer.parseInt(st1.nextToken())+(int)goc.getSubject().getPosition().getX();
            int posY=Integer.parseInt(st1.nextToken())+(int)goc.getSubject().getPosition().getY();
            context.handler.handle(new MoveConcreteCommand(goc.getSubject(),new Point(posX,posY)));
            return 505050;
        } catch (NoSuchElementException e) {
            return -1;
        }
    }
    }


