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
import java.util.StringTokenizer;



class DeleteConcreteCommandTest {
    GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
    GraphicObject go=new CircleObject(new Point(10,10),10);
    final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();
    GraphicObjectController goc=new GraphicObjectController(go,handler);
    int id=go.getID();
    StringTokenizer st= new StringTokenizer(Integer.toString(id));
    Gruppo gruppo=new Gruppo(01, new Context(st,panel,handler));
    DeleteConcreteCommand dccGR=new DeleteConcreteCommand(panel,gruppo);
    DeleteConcreteCommand dccOBJ=new DeleteConcreteCommand(panel,go);

    @Test
    void doItDelete() {
        dccOBJ.doIt();
        Assertions.assertFalse(panel.getObjGlobal(id) != null);
    }

    @Test
    void undoItDelete() {
        dccOBJ.undoIt();
        Assertions.assertTrue(panel.getObjGlobal(id) != null);
    }
    @Test
    void undoItDeleteGruppi() {
        dccGR.undoIt();
        Assertions.assertTrue(panel.getGruppo(01) != null);
    }
    @Test
    void doItDeleteGruppi() {
        dccGR.doIt();
        Assertions.assertFalse(panel.getGruppo(01) != null);
    }
}