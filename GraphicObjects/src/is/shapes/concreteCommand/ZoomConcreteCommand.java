package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.model.Gruppo;

public class ZoomConcreteCommand implements Command {
	
	private GraphicObject object;
	private double factor;
    private int scelta;
	private Gruppo gruppo;
	public ZoomConcreteCommand(double factor,Gruppo gruppo){
		scelta=0;
		this.gruppo = gruppo;
		this.factor = factor;
	}

	public ZoomConcreteCommand(GraphicObject obj, double factor) {
		object = obj;
		this.factor = factor;
		scelta=1;
	}

	@Override
	public boolean doIt() {
		if(scelta==0){
			for(int i=0;i<gruppo.getSize();i++)
				gruppo.getContenuto(i).scale(factor);
		}
		else {
			object.scale(factor);
		}
		return true;
	}

	@Override
	public boolean undoIt() {
		if(scelta==0){
			for(int i=0;i<gruppo.getSize();i++)
				gruppo.getContenuto(i).scale(1.0/factor);
		}
		else {
			object.scale(1.0 / factor);
		}
		return true;
	}

}
