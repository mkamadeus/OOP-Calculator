package expressions;

import expressions.Expression;
import expressions.BinaryExpression;
import numbers.Number;
import numbers.RealNumber;

public class AddExpression extends BinaryExpression{
    
    public AddExpression(Expression e1, Expression e2)
    {
        super(e1,e2);
    }

    public RealNumber solve()
    {
        return new RealNumber(e1.solve().value() + e2.solve().value());
    }
}