package is.shapes.view;

import is.interpreter.Context;
import is.interpreter.Expression;

public class lsImageContext implements Expression {
    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("image")).setVisible(true);
        return 505050;

    }
}
