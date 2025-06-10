package is.interpreter;

import is.shapes.controller.GraphicObjectController;
import is.shapes.model.GraphicObject;
import is.shapes.view.clickedWindow;

import java.util.ArrayList;
import java.util.List;

public class lsCircleContext implements Expression {

    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("circle")).setVisible(true);
        return 505050;

    }
}
