package expressions;

import expressions.Expression;
import expressions.BinaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class PowerExpression extends BinaryExpression{
    
    public PowerExpression(Expression e1, Expression e2)
    {
        super(e1,e2);
    }

    public RealNumber solve()
    {
        return new RealNumber(Math.pow(e1.solve().value(),e2.solve().value()));
    }
}