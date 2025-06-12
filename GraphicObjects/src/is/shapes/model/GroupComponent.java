package is.shapes.model;

import is.interpreter.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public interface GroupComponent {
    void create(Context context);
    void remove(Context context);

}
