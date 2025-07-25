package is.shapes.view;

import is.command.CommandHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.concreteCommand.NewObjectConcreteCommand;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CreateObjectAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399493440620423134L;
	AbstractGraphicObject prototype;
	GraphicObjectPanelReceiver panel;
	CommandHandler ch;

	public CreateObjectAction(AbstractGraphicObject prototype,
							  GraphicObjectPanelReceiver panel, CommandHandler ch) {
		super();
		this.prototype = prototype;
		this.panel = panel;
		this.ch = ch;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GraphicObject go = prototype.clone();
		ch.handle(new NewObjectConcreteCommand(panel, go));

	}

}
