package expressions;

import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class NegativeExpression extends UnaryExpression{
    
    public NegativeExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve()
    {
        return new RealNumber(-e.solve().value());
    }
}