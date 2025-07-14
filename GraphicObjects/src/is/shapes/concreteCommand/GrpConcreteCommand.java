package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.Gruppo;
import is.shapes.view.GraphicObjectPanelReceiver;



public class GrpConcreteCommand implements Command {
    Gruppo gruppo;
    GraphicObjectPanelReceiver panel;
    public GrpConcreteCommand(GraphicObjectPanelReceiver panel, Gruppo gruppo) {
        this.gruppo=gruppo;
        this.panel=panel;
    }
    @Override
    public boolean doIt() {
        panel.add(gruppo);
        panel.setGrpNum(panel.getGrpNum()+1);
        return true;
    }

    @Override
    public boolean undoIt() {
        panel.remove(gruppo);
        panel.setGrpNum(panel.getGrpNum()-1);
        return true;
    }
}
