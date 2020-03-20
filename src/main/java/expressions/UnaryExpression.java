package expressions;

import expressions.Expression;
import numbers.Number;
import numbers.RealNumber;

abstract class UnaryExpression implements Expression{
    
    protected Expression e;

    public UnaryExpression(Expression e)
    {
        this.e = e;
    }

    abstract public RealNumber solve();
}