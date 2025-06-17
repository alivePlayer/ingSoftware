package is.interpreter;

import is.shapes.concreteCommand.GrpConcreteCommand;
import is.shapes.model.Composite;
import is.shapes.model.Gruppo;

public class GrpContext implements Expression{
    Composite co=new Composite();
    @Override
    public int interpret(Context context) {
        Gruppo gr=co.create(context);
        context.handler.handle(new GrpConcreteCommand(context.panel,gr));
        return 505050;
    }
}
