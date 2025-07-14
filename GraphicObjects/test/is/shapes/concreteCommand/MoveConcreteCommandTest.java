package is.shapes.concreteCommand;

import is.command.HistoryCommandHandlerInvoker;
import is.shapes.controller.GraphicObjectController;
import is.shapes.controller.interpreter.Context;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.Gruppo;
import is.shapes.view.GraphicObjectPanelReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.StringTokenizer;

class MoveConcreteCommandTest {
    GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
    GraphicObject go=new CircleObject(new Point(10,10),10);
    GraphicObject go1=new CircleObject(new Point(10,10),10);
    GraphicObject go2=new CircleObject(new Point(11,11),11);
    final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();
    GraphicObjectController goc=new GraphicObjectController(go,handler);
    int id=go.getID();
    StringTokenizer st= new StringTokenizer(Integer.toString(id));
    Gruppo gruppo=new Gruppo(01, new Context(st,panel,handler));

    @Test
    void doItSINGLEOBJ() {
        Point2D p2=new Point2D.Double(20,20);
        MoveConcreteCommand mcc=new MoveConcreteCommand(go,p2);
        mcc.doIt();
        Assertions.assertEquals(p2,go.getPosition());
    }

    @Test
    void undoItSINGLEOBJ() {
        Point2D p1=go.getPosition();
        Point2D p2=new Point2D.Double(20,20);
        MoveConcreteCommand mcc=new MoveConcreteCommand(go,p2);
        mcc.undoIt();
        Assertions.assertEquals(p1,go.getPosition());
    }

    @Test
    void doItGruppoUnaPos() {
       ArrayList<Point2D> p1=new ArrayList<>();
       p1.add(go.getPosition());
       p1.add(go1.getPosition());
       p1.add(go2.getPosition());
        Point2D p2=new Point2D.Double(20,20);
        MoveConcreteCommand mcc=new MoveConcreteCommand(gruppo,p1,p2);
        gruppo.add(go);
        gruppo.add(go1);
        gruppo.add(go2);
        mcc.doIt();
        for(int i=0;i<p1.size();i++) {
            Assertions.assertEquals(p2, gruppo.getContenuto(i).getPosition());
        }

    }

    @Test
    void undoItGruppoUnaPos() {
        ArrayList<Point2D> p1=new ArrayList<>();
        p1.add(go.getPosition());
        p1.add(go1.getPosition());
        p1.add(go2.getPosition());
        Point2D p2=new Point2D.Double(20,20);
        MoveConcreteCommand mcc=new MoveConcreteCommand(gruppo,p1,p2);
        gruppo.add(go);
        gruppo.add(go1);
        gruppo.add(go2);
        mcc.undoIt();
        for(int i=0;i<p1.size();i++) {
            Assertions.assertEquals(p1.get(i), gruppo.getContenuto(i).getPosition());
        }
    }
    @Test
    void doItGruppoPiuPos() {
        gruppo.add(go1);
        gruppo.add(go2);
        ArrayList<Point2D> p1=new ArrayList<>();
        p1.add(go1.getPosition());
        p1.add(go2.getPosition());
        ArrayList<Point2D> p2=new ArrayList<>();
        Point2D pos=new Point2D.Double(20,20);
        p2.add(pos);
        pos=new Point2D.Double(30,30);
        p2.add(pos);
        MoveConcreteCommand mcc=new MoveConcreteCommand(gruppo,p1,p2);
        mcc.doIt();
        for(int i=0;i<p1.size();i++) {
            Assertions.assertEquals(p2.get(i), gruppo.getContenuto(i).getPosition());
        }

    }

    @Test
    void undoItGruppoPiuPos() {
        gruppo.add(go1);
        gruppo.add(go2);
        ArrayList<Point2D> p1=new ArrayList<>();
        p1.add(go1.getPosition());
        p1.add(go2.getPosition());
        ArrayList<Point2D> p2=new ArrayList<>();
        Point2D pos=new Point2D.Double(20,20);
        p2.add(pos);
        pos=new Point2D.Double(30,30);
        p2.add(pos);
        MoveConcreteCommand mcc=new MoveConcreteCommand(gruppo,p1,p2);
        mcc.undoIt();
        for(int i=0;i<p1.size();i++) {
            Assertions.assertEquals(p1.get(i), gruppo.getContenuto(i).getPosition());
        }
    }
}