package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanelReceiver;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

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
        if (go==null) System.out.println("go vuoto");
        System.out.println("undoIt: "+go);
        panel.add(go);

        return true;
    }
}
