package is.shapes.view;

import is.command.HistoryCommandHandlerInvoker;
import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.controller.GraphicObjectController;
import is.shapes.controller.interpreter.Context;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.Gruppo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class lsAllContextTest {
    GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
    GraphicObject go=new CircleObject(new Point(10,10),10);
    GraphicObject go1=new CircleObject(new Point(10,10),10);
    GraphicObject go2=new CircleObject(new Point(11,11),11);
    final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();
    GraphicObjectController goc=new GraphicObjectController(go,handler);
    int id=go.getID();
    StringTokenizer st= new StringTokenizer(Integer.toString(id));
    Context context=new Context(st,panel,handler);
    Gruppo gruppo=new Gruppo(01, context);

    @Test
    void interpret() {
        panel.add(go);
        panel.add(go1);
        panel.add(go2);
        gruppo.add(go);
        gruppo.add(go1);
        gruppo.add(go2);
        panel.add(gruppo);
        ArrayList<GraphicObject> aggiunti=new ArrayList<>();
        aggiunti.add(go);
        aggiunti.add(go1);
        aggiunti.add(go2);
        aggiunti.add(gruppo);
        ArrayList<GraphicObject> ret = context.panel.getObjectsGlobal("all");
        for (int i=0;i<ret.size();i++) {
            Assertions.assertEquals(aggiunti.get(i),ret.get(i));
        }


    }
}