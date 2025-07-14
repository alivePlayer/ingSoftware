package is.shapes.controller.interpreter;

import is.shapes.view.clickedWindow;

public class lsCircleContext implements Expression {

    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("circle")).setVisible(true);
        return 505050;
    }
}
