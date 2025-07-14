package is.shapes.controller;

import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanelReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class GraphicObjectControllerTest {

    @Test
    void setControlledObject() {
        GraphicObjectPanelReceiver panel = new GraphicObjectPanelReceiver();
        GraphicObject go=new CircleObject(new Point(10,10),10);
        GraphicObjectController goc=new GraphicObjectController(go,null);
        goc.setControlledObject(go);
        panel.add(go);
        Assertions.assertEquals(go,goc.getSubject());
        Assertions.assertTrue(panel.getObjGlobal(go.getID())!=null);
    }

    @Test
    void getSubject() {
    }
}