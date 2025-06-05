package is.shapes.view;

import is.TestGraphics2;
import is.command.CommandHandler;
import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.concreteCommand.NewObjectConcreteCommand;
import is.shapes.concreteCommand.ZoomConcreteCommand;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;


public class CommandLineAction {
    CommandHandler handler;
    GraphicObjectPanelReceiver panel;
    public CommandLineAction(CommandHandler handler, GraphicObjectPanelReceiver panel) {
        this.handler = handler;
        this.panel = panel;
    }

    public boolean commandChoser(StringTokenizer command){
        if(!command.hasMoreTokens()) return false;
        String token=command.nextToken().toLowerCase();
        System.out.println(token);
        if(token.equals("add")){

            token=command.nextToken().toLowerCase();
            System.out.println(token);
            if(token.equals("circle")) {
                AbstractGraphicObject obj = new CircleObject(new Point(70, 70), 10);
                handler.handle(new NewObjectConcreteCommand(panel, obj));
                return true;
            }
            else if(token.equals("rectangle")) {
                AbstractGraphicObject obj= new RectangleObject(new Point(70,70),20,60);
                handler.handle(new NewObjectConcreteCommand(panel, obj));
                return true;
                }
            else if(token.equals("image")) {
                AbstractGraphicObject obj= new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
                        new Point(90, 90));
                handler.handle(new NewObjectConcreteCommand(panel, obj));
                return true;
            }
        }

        else if(token.equals("delete")) {
            if(!command.hasMoreTokens()) return false;
            token=command.nextToken().toLowerCase();
            try{
                GraphicObjectController goc= new GraphicObjectController(handler);
                if(panel.getObjGlobal(Integer.parseInt(token))==null) return false;
                goc.setControlledObject(panel.getObjGlobal(Integer.parseInt(token)));
                handler.handle(new DeleteConcreteCommand(panel,goc.getSubject()));
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }

        else if(token.equals("zoom")) {
            if(!command.hasMoreTokens()) return false;
            token=command.nextToken().toLowerCase();
            if(token.equals("+")) {
                if(!command.hasMoreTokens()) return false;
                token=command.nextToken().toLowerCase();
                GraphicObjectController goc= new GraphicObjectController(handler);
                goc.setControlledObject(panel.getObjGlobal(Integer.parseInt(token)));
                handler.handle(new ZoomConcreteCommand(goc.getSubject(), 1.0 + 0.1));
                return true;
            }
            else if(token.equals("-")) {
                if(!command.hasMoreTokens()) return false;
                token=command.nextToken().toLowerCase();
                GraphicObjectController goc= new GraphicObjectController(handler);
                goc.setControlledObject(panel.getObjGlobal(Integer.parseInt(token)));
                handler.handle(new ZoomConcreteCommand(goc.getSubject(), 1.0 - 0.1));
                return true;
            }
        }

        return false;
    }
}
