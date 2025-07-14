package is.shapes.concreteCommand;

import is.command.Command;
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
        if(panel.getGrpNum()==0)
            return false;
        panel.remove(gruppo);
        panel.setGrpNum(panel.getGrpNum()-1);
        return true;
    }

    @Override
    public boolean undoIt() {
        panel.add(gruppo);
        panel.setGrpNum(panel.getGrpNum()+1);
        return true;
    }
}