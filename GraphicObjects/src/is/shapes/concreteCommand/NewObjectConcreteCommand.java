package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanelReceiver;

public class NewObjectConcreteCommand implements Command {

	private final GraphicObjectPanelReceiver panel;
	private final GraphicObject go;

	public NewObjectConcreteCommand(GraphicObjectPanelReceiver panel, GraphicObject go) {
		
		this.panel = panel;
		this.go = go;
		
	}

	@Override
	public boolean doIt() {
		double x = go.getPosition().getX();
		double y =  go.getPosition().getY();

		go.moveTo(x, y);
		panel.add(go);

		return true;
	}

	@Override
	public boolean undoIt() {
		panel.remove(go);
		return true;
	}

}
