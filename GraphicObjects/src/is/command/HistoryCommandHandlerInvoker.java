package is.command;

import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.model.GraphicObject;

import java.util.LinkedList;

public class HistoryCommandHandlerInvoker implements CommandHandler {

	private int maxHistoryLength = 100;

	private final LinkedList<Command> history = new LinkedList<>();

	private final LinkedList<Command> redoList = new LinkedList<>();

	public HistoryCommandHandlerInvoker() {
		this(100);
	}

	public HistoryCommandHandlerInvoker(int maxHistoryLength) {

		if (maxHistoryLength < 0)
			throw new IllegalArgumentException();
		this.maxHistoryLength = maxHistoryLength;
	}

	public void handle(Command cmd) {

		if (cmd.doIt()) {
			// restituisce true: può essere annullato
			addToHistory(cmd);
		} else {
			// restituisce false: non può essere annullato
			history.clear();
		}
		if (redoList.size() > 0)
			redoList.clear();
	}

	public void redo() {
		if (redoList.size() > 0) {
			Command redoCmd = redoList.removeFirst();
			redoCmd.doIt();
			history.addFirst(redoCmd);

		}
	}

	public void undo() {
		if (history.size() > 0) {
			Command undoCmd = history.removeFirst();
			undoCmd.undoIt();
			redoList.addFirst(undoCmd);
		}
	}

	private void addToHistory(Command cmd) {
		history.addFirst(cmd);
		System.out.println("added to "+ cmd);
		if (history.size() > maxHistoryLength) {
			history.removeLast();
		}

	}

}
