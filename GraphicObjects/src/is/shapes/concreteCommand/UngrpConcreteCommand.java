package is.shapes.concreteCommand;

import is.command.Command;
import is.interpreter.Context;
import is.shapes.model.Composite;
import is.shapes.model.Gruppo;
import is.shapes.view.GraphicObjectPanelReceiver;

public class UngrpConcreteCommand implements Command {
    Gruppo gruppo;
    GraphicObjectPanelReceiver panel;
    public UngrpConcreteCommand(GraphicObjectPanelReceiver panel, Gruppo gruppo) {
        this.panel = panel;
        this.gruppo = gruppo;
    }
    @Override
    public boolean doIt() {
        panel.remove(gruppo);
        return true;
    }

    @Override
    public boolean undoIt() {
        panel.add(gruppo);
        return true;
    }
}