package is.shapes.view;

import is.TestGraphics2;
import is.command.CommandHandler;
import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.concreteCommand.MoveConcreteCommand;
import is.shapes.concreteCommand.NewObjectConcreteCommand;
import is.shapes.concreteCommand.ZoomConcreteCommand;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


public class CommandLineAction {
    CommandHandler handler;
    GraphicObjectPanelReceiver panel;
    public CommandLineAction(CommandHandler handler, GraphicObjectPanelReceiver panel) {
        this.handler = handler;
        this.panel = panel;
    }

    public int commandChoser(StringTokenizer command){
        if(!command.hasMoreTokens()) return -1;
        String token=command.nextToken().toLowerCase();
        System.out.println(token);
        if(token.equals("add")){

            token=command.nextToken().toLowerCase();
            System.out.println(token);
            if(token.equals("circle")) {//cerchio
                if(command.hasMoreTokens()) {

                    try{ //creazione personalizzata cerchio
                        double raggio= Integer.parseInt(command.nextToken());
                        int x= Integer.parseInt(command.nextToken());
                        int y= Integer.parseInt(command.nextToken());
                        AbstractGraphicObject obj= new CircleObject(new Point(x,y),raggio);
                        handler.handle(new NewObjectConcreteCommand(panel,obj));
                        return obj.getID();
                    }catch(NoSuchElementException | NumberFormatException e){
                        return -1;
                    }//fine Creazione personalizzata cerchio
                }

                AbstractGraphicObject obj = new CircleObject(new Point(70, 70), 10);
                handler.handle(new NewObjectConcreteCommand(panel, obj));
                return obj.getID();
            }//fine cerchio

            else if(token.equals("rectangle")) {//rettangolo

                if(command.hasMoreTokens()) {//rettangolo personalizzato
                    try {
                        AbstractGraphicObject obj = new RectangleObject(new Point(Integer.parseInt(command.nextToken()), Integer.parseInt(command.nextToken())),
                                                                                    Integer.parseInt(command.nextToken()), Integer.parseInt(command.nextToken()));
                        handler.handle(new NewObjectConcreteCommand(panel, obj));

                        return obj.getID();
                    }catch(NoSuchElementException | NumberFormatException e) {
                        return -1;
                    }
                }//fine rettangolo personalizzato


                AbstractGraphicObject obj= new RectangleObject(new Point(70,70),20,60);
                handler.handle(new NewObjectConcreteCommand(panel, obj));
                return obj.getID();
                }//fine rettangolo

            else if(token.equals("image")) {
                if(command.hasMoreTokens()) {//immagine personalizzata

                    try{
                        AbstractGraphicObject obj=new ImageObject(new ImageIcon(TestGraphics2.class.getResource(command.nextToken())),new Point(Integer.parseInt(command.nextToken()),Integer.parseInt(command.nextToken())));
                        handler.handle(new NewObjectConcreteCommand(panel, obj));
                        return obj.getID();
                    }catch(NoSuchElementException | NumberFormatException e) {
                        return -1;
                    }
                }//fine personalizzata


                AbstractGraphicObject obj= new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
                        new Point(90, 90));
                handler.handle(new NewObjectConcreteCommand(panel, obj));
                return obj.getID();
            }//fine immagine
        }//fine add

        else if(token.equals("delete")) {
            if(!command.hasMoreTokens()) return -1;
            token=command.nextToken().toLowerCase();
            try{


                GraphicObjectController goc= new GraphicObjectController(handler);

                if(panel.getObjGlobal(Integer.parseInt(token))==null) return -1;

                goc.setControlledObject(panel.getObjGlobal(Integer.parseInt(token)));
                handler.handle(new DeleteConcreteCommand(panel,goc.getSubject()));
                return 404040;


            }catch(NumberFormatException e){
                return -1;
            }
        }

        else if(token.equals("zoom")) {
            if(!command.hasMoreTokens()) return -1;
            token=command.nextToken().toLowerCase();


            if(token.equals("+")) {
                if(!command.hasMoreTokens()) return -1;
                token=command.nextToken().toLowerCase();
                GraphicObjectController goc= new GraphicObjectController(handler);
                goc.setControlledObject(panel.getObjGlobal(Integer.parseInt(token)));
                handler.handle(new ZoomConcreteCommand(goc.getSubject(), 1.0 + 0.1));
                return 505050;
            }


            else if(token.equals("-")) {
                if(!command.hasMoreTokens()) return -1;
                token=command.nextToken().toLowerCase();
                GraphicObjectController goc= new GraphicObjectController(handler);
                goc.setControlledObject(panel.getObjGlobal(Integer.parseInt(token)));
                handler.handle(new ZoomConcreteCommand(goc.getSubject(), 1.0 - 0.1));
                return 505050;
            }
        }

    else if(token.equals("mv")) {
        if(!command.hasMoreTokens()) return -1;
        int id= Integer.parseInt(command.nextToken());
        int x= Integer.parseInt(command.nextToken());
        int y= Integer.parseInt(command.nextToken());
        GraphicObjectController goc= new GraphicObjectController(handler);
        goc.setControlledObject(panel.getObjGlobal(id));
        handler.handle(new MoveConcreteCommand(goc.getSubject(),new Point(x,y)));
        return 505050;
        }
    else if (token.equals("mvoff")) {
            if(!command.hasMoreTokens()) return -1;
            int id= Integer.parseInt(command.nextToken());
            int x= Integer.parseInt(command.nextToken());
            int y= Integer.parseInt(command.nextToken());
            GraphicObjectController goc= new GraphicObjectController(handler);
            goc.setControlledObject(panel.getObjGlobal(id));

            int posX=x+(int)goc.getSubject().getPosition().getX();
            int posY=y+(int)goc.getSubject().getPosition().getY();
            handler.handle(new MoveConcreteCommand(goc.getSubject(),new Point(posX,posY)));
            return 505050;
        }

        return -1;
    }
}
