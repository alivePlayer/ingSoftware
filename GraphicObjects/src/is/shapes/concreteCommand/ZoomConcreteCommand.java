package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.GraphicObject;

public class ZoomConcreteCommand implements Command {
	
	private final GraphicObject object;
	private final double factor;

	public ZoomConcreteCommand(GraphicObject obj, double factor) {
		object = obj;
		this.factor = factor;
		
	}

	@Override
	public boolean doIt() {
		object.scale(factor);
		return true;
	}

	@Override
	public boolean undoIt() {
		object.scale(1.0 / factor);
		return true;
	}

}
