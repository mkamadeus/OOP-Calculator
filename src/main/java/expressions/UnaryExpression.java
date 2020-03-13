package expressions;

import expressions.Expression;

abstract class UnaryExpression extends Expression{
    
    protected Expression e;

    public UnaryExpression(Expression e)
    {
        this.e = e;
    }

    abstract public Number solve();
}