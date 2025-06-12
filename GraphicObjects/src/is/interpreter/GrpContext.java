package is.interpreter;

import is.shapes.model.Composite;

public class GrpContext implements Expression{
    @Override
    public int interpret(Context context) {
        Composite ce= new Composite();
        ce.create(context);
        return 505050;
    }
}
