package expressions;

import expressions.Expression;
import numbers.Number;
import numbers.RealNumber;

public class BinaryExpression extends Expression{
    
    protected Expression e1;
    protected Expression e2;
    
    public BinaryExpression(Expression e1, Expression e2)
    {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Number solve()
    {
        return new RealNumber(e1.solve().value() + e2.solve().value());
    }
}