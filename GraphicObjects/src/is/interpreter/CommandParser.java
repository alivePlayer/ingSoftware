package is.interpreter;

import is.shapes.concreteCommand.ZoomConcreteCommand;

import java.util.StringTokenizer;

public class CommandParser {
    public static Expression parse(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) return null;

        String command = tokenizer.nextToken().toLowerCase();

        switch (command) {
            case "add":
                return new AddExpression();
            case "delete":
                return new DeleteContext();
            case "zoom":
                return new ZoomContext();
            case "mv":
                return new MoveContext();
            case "mvoff":
                return new MoveOffContext();
            case "scale":
                return new ScaleContext();
            case "area" :
                return new AreaContext();
            case "perimetro":
                return new PerimetroContext();
            case "ls":
                return new lsContext();
            default:
                return null;
        }
    }
}
