package expressions;

import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class TangentExpression extends UnaryExpression{
    
    public TangentExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve()
    {
        return new RealNumber(Math.tan(e.solve().value()));
    }
}