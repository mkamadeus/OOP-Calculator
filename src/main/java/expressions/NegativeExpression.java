package expressions;

import exceptions.BaseException;
import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class NegativeExpression extends UnaryExpression{
    
    public NegativeExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve() throws BaseException {
        return new RealNumber(-e.solve().value());
    }
}