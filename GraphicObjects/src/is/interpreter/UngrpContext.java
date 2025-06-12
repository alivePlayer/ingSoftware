package is.interpreter;

import is.shapes.model.Composite;

public class UngrpContext implements Expression{
    @Override
    public int interpret(Context context) {
        Composite ce=new Composite();
        ce.remove(context);
        return 505050;
    }
}
