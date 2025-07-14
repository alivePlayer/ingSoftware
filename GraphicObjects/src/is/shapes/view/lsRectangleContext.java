package is.shapes.view;

import is.shapes.controller.interpreter.Context;
import is.shapes.controller.interpreter.Expression;

public class lsRectangleContext implements Expression {
    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("rectangle")).setVisible(true);
        return 505050;

    }
}
