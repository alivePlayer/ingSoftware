package is.shapes.concreteCommand;

import is.command.HistoryCommandHandlerInvoker;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanelReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.StringTokenizer;

class NewObjectConcreteCommandTest {
    GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
    GraphicObject go=new CircleObject(new Point(10,10),10);
    final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();
    GraphicObjectController goc=new GraphicObjectController(go,handler);
    int id=go.getID();
    StringTokenizer st= new StringTokenizer(Integer.toString(id));
    NewObjectConcreteCommand ncc =new NewObjectConcreteCommand(panel,go);

    @Test
    void doItAdd() {
        ncc.doIt();
        Assertions.assertTrue(panel.getObjGlobal(id) != null);
        Assertions.assertEquals(go,panel.getObjGlobal(id));
    }

    @Test
    void undoItAdd() {
        ncc.undoIt();
        Assertions.assertFalse(panel.getObjGlobal(id) != null);
    }
}