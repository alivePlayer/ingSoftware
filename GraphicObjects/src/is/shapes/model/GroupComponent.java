package is.shapes.model;

import is.shapes.controller.interpreter.Context;

public interface GroupComponent {
    Gruppo create(Context context);
    void remove(Context context);

}
