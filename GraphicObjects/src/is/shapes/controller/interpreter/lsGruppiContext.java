package is.shapes.controller.interpreter;

import is.shapes.view.clickedWindow;

public class lsGruppiContext implements Expression {
    @Override
    public int interpret(Context context) {
        new clickedWindow(context.panel.getObjectsGlobal("gruppi")).setVisible(true);
        return 505050;
    }
}
