package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanelReceiver;

public class DeleteConcreteCommand implements Command {
    private final GraphicObjectPanelReceiver panel;
    private final GraphicObject go;
    public DeleteConcreteCommand(GraphicObjectPanelReceiver panel, GraphicObject go) {
        this.panel = panel;
        this.go = go;
    }
    @Override
    public boolean doIt() {
        panel.remove(go);
        return true;
    }

    @Override
    public boolean undoIt() {
        panel.add(go);
        return true;
    }
}
