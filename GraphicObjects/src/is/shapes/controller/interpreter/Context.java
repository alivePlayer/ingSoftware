package is.shapes.controller.interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanelReceiver;

import java.awt.*;
import java.util.StringTokenizer;

public class Context {
    public StringTokenizer st;
    public GraphicObjectPanelReceiver panel;
    public CommandHandler handler;

    public Context(StringTokenizer st,GraphicObjectPanelReceiver panel, CommandHandler handler) {
        this.st = st;
        this.panel = panel;
        this.handler = handler;
    }
}
