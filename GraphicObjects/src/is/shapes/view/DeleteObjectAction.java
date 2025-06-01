package is.shapes.view;

import is.command.CommandHandler;
import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.Serial;

public class DeleteObjectAction extends AbstractAction {
    @Serial
    private static final long serialVersionUID = 254953548601413011L;
    AbstractGraphicObject prototype;
    GraphicObjectPanelReceiver panel;
    CommandHandler ch;
    public DeleteObjectAction(AbstractGraphicObject prototype,
                              GraphicObjectPanelReceiver panel, CommandHandler ch) {
        super();
        this.prototype = prototype;
        this.panel = panel;
        this.ch = ch;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicObject go = prototype;
        ch.handle(new DeleteConcreteCommand(panel,go));
    }
}
