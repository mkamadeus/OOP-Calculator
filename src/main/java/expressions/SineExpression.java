package expressions;

import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class SineExpression extends UnaryExpression{
    
    public SineExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve()
    {
        return new RealNumber(Math.sin(e.solve().value()));
    }
}