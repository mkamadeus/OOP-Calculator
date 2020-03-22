package expressions;

import exceptions.BaseException;
import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class TangentExpression extends UnaryExpression{
    
    public TangentExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve() throws BaseException {
        return new RealNumber(Math.tan(e.solve().value()));
    }
}