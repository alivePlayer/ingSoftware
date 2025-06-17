package is.interpreter;

import is.shapes.concreteCommand.UngrpConcreteCommand;
import is.shapes.model.Composite;
import is.shapes.model.Gruppo;

public class UngrpContext implements Expression{
    Composite co=new Composite();
    @Override
    public int interpret(Context context) {
        Gruppo gr=co.create(context);
        context.handler.handle(new UngrpConcreteCommand(context.panel,gr));
        return 505050;
    }
}
