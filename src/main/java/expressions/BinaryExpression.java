package expressions;

import expressions.Expression;

abstract class BinaryExpression extends Expression{
    
    protected Expression e1;
    protected Expression e2;

    public BinaryExpression(Expression e1, Expression e2)
    {
        this.e1 = e1;
        this.e2 = e2;
    }

    abstract public Number solve();
}