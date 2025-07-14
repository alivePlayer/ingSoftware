package is.shapes.view;

import is.command.CommandHandler;
import is.shapes.controller.interpreter.CommandParser;
import is.shapes.controller.interpreter.Context;
import is.shapes.controller.interpreter.Expression;
import java.util.StringTokenizer;


public class CommandLineAction {
    CommandHandler handler;
    GraphicObjectPanelReceiver panel;

    public CommandLineAction(CommandHandler handler, GraphicObjectPanelReceiver panel) {
        this.handler = handler;
        this.panel = panel;
    }

    public int commandChoser(StringTokenizer command) {
        Expression expr = CommandParser.parse(command);
        if (expr == null) return -1;

        Context context = new Context(command, panel, handler);
        return expr.interpret(context);
    }
}
