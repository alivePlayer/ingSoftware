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

class GrpConcreteCommandTest {
    GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
    GraphicObject go=new CircleObject(new Point(10,10),10);
    final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();
    GraphicObjectController goc=new GraphicObjectController(go,handler);
    int id=go.getID();
    StringTokenizer st= new StringTokenizer(Integer.toString(id));
    Gruppo gruppo=new Gruppo(01, new Context(st,panel,handler));
    GrpConcreteCommand gcc=new GrpConcreteCommand(panel,gruppo);

    @Test
    void doItGrp() {
        int idGrp=panel.getGrpNum()+1;
        gcc.doIt();
        Assertions.assertTrue(panel.getGruppo(01) != null);
        Assertions.assertEquals(idGrp,panel.getGrpNum());
    }

    @Test
    void undoItGrp() {
        int idGrp=panel.getGrpNum();
        gcc.undoIt();
        Assertions.assertFalse(panel.getGruppo(01) != null);
        Assertions.assertEquals(idGrp,panel.getGrpNum());
    }

}