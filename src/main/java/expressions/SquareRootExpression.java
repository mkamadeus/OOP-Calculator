package expressions;

import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class SquareRootExpression extends UnaryExpression{
    
    public SquareRootExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve()
    {
        return new RealNumber(Math.pow(e.solve().value(), 0.5));
    }
}