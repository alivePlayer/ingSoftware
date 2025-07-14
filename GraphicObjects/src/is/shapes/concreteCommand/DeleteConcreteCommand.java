package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.model.Gruppo;
import is.shapes.view.GraphicObjectPanelReceiver;

public class DeleteConcreteCommand implements Command {
    private final GraphicObjectPanelReceiver panel;
    private GraphicObject go=null;
    Gruppo gruppo;
    int scelta;

    public DeleteConcreteCommand(GraphicObjectPanelReceiver panel, Gruppo gruppo){
        this.panel = panel;
        this.gruppo = gruppo;
        scelta =0;
    }
    public DeleteConcreteCommand(GraphicObjectPanelReceiver panel, GraphicObject go) {
        this.panel = panel;
        this.go = go;
        scelta =1;
    }

    @Override
    public boolean doIt() {
        if(scelta ==0 ){
            if(panel.getGrpNum()==0) return false;
            for(int i=0;i< gruppo.getSize();i++)
                panel.remove(gruppo.getContenuto(i));
            panel.remove(gruppo);
            panel.setGrpNum(panel.getGrpNum()-1);
        }
        else{
            panel.remove(go);
        }
        return true;
    }

    @Override
    public boolean undoIt() {
        if(scelta ==0) {
            for(int i=0;i< gruppo.getSize();i++)
                panel.add(gruppo.getContenuto(i));
            panel.add(gruppo);
            panel.setGrpNum(panel.getGrpNum()+1);
        }
        else {
            panel.add(go);
        }
        return true;
    }
}
