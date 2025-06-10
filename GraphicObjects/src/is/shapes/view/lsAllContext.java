package is.shapes.view;

import is.interpreter.Context;
import is.interpreter.Expression;

public class lsAllContext implements Expression {
    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("all")).setVisible(true);
        return 505050;

    }

}
