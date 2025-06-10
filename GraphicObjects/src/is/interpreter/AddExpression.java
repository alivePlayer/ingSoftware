package is.interpreter;

public class AddExpression implements Expression {
    @Override
    public int interpret(Context context) {
        if (!context.st.hasMoreTokens()) return -1;
        String token = context.st.nextToken().toLowerCase();

        Expression expr = null;

        switch (token) {
            case "circle":
                expr = new AddContextCircle();
                break;
            case "rectangle":
                expr = new AddContextRectangle();
                break;
            case "image":
                expr = new AddContextImage();
                break;
            default:
                return -1;
        }
        return expr.interpret(context);
    }
}
