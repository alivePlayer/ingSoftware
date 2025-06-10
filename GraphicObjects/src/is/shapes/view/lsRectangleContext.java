package is.shapes.view;

import is.interpreter.Context;
import is.interpreter.Expression;

public class lsRectangleContext implements Expression {
    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("rectangle")).setVisible(true);
        return 505050;

    }
}
