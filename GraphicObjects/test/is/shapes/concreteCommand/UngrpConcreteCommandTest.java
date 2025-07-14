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

class UngrpConcreteCommandTest {
    GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
    GraphicObject go=new CircleObject(new Point(10,10),10);
    final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();
    GraphicObjectController goc=new GraphicObjectController(go,handler);
    int id=go.getID();
    StringTokenizer st= new StringTokenizer(Integer.toString(id));
    Gruppo gruppo=new Gruppo(01, new Context(st,panel,handler));
    UngrpConcreteCommand gcc=new UngrpConcreteCommand(panel,gruppo);

    @Test
    void doIt() {
        int idGrp=panel.getGrpNum();
        gcc.doIt();
        Assertions.assertFalse(panel.getGruppo(01) != null);
        Assertions.assertEquals(idGrp,panel.getGrpNum());
    }

    @Test
    void undoIt() {
        int idGrp=panel.getGrpNum()+1;
        gcc.undoIt();
        Assertions.assertTrue(panel.getGruppo(01) != null);
        Assertions.assertEquals(idGrp,panel.getGrpNum());
    }
}