package expressions;

import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class CosineExpression extends UnaryExpression{
    
    public CosineExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve()
    {
        return new RealNumber(Math.cos(e.solve().value()));
    }
}