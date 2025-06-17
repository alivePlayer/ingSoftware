package is.shapes.concreteCommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.model.Gruppo;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MoveConcreteCommand implements Command {

	private Point2D oldPos;
	private Point2D newPos;
	ArrayList<Point2D> multipleOldPos;
	ArrayList<Point2D> multipleNewPos;

	private GraphicObject object;
	int scelta;
	Gruppo gruppo;



	public MoveConcreteCommand(Gruppo gruppo, ArrayList<Point2D> multipleOldPos, Point2D newPos) {
		this.multipleOldPos = multipleOldPos;
		this.newPos = newPos;
		this.gruppo = gruppo;
		scelta=0;
	}

	public MoveConcreteCommand(Gruppo gruppo, ArrayList<Point2D> multipleOldPos, ArrayList<Point2D> multipleNewPos) {
		this.multipleOldPos = multipleOldPos;
		this.multipleNewPos = multipleNewPos;
		this.gruppo = gruppo;
		scelta=1;
	}

	public MoveConcreteCommand(GraphicObject go, Point2D pos) {
		oldPos = go.getPosition();
		newPos = pos;
		this.object = go;
		scelta=2;
		
	}

	@Override
	public boolean doIt() {

		if(scelta==0) {
			for(int i=0;i<gruppo.getSize();i++)
				gruppo.getContenuto(i).moveTo(newPos);
		}
		else if(scelta==1) {
			for(int i=0;i<gruppo.getSize();i++)
				gruppo.getContenuto(i).moveTo(multipleNewPos.get(i));
		}
		else {
			object.moveTo(newPos);
		}
		return true;
	}

	@Override
	public boolean undoIt() {
		if(scelta==0 || scelta==1) {
			for(int i=0;i<gruppo.getSize();i++)
				gruppo.getContenuto(i).moveTo(multipleOldPos.get(i));
		}

		else {
			object.moveTo(oldPos);
		}
		return true;
	}

}
