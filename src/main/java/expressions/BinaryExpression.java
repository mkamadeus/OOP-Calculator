package expressions;

import exceptions.BaseException;
import expressions.Expression;
import numbers.Number;
import numbers.RealNumber;

abstract class BinaryExpression implements Expression{
    
    protected Expression e1;
    protected Expression e2;
    
    public BinaryExpression(Expression e1, Expression e2)
    {
        this.e1 = e1;
        this.e2 = e2;
    }

    abstract public RealNumber solve() throws BaseException;
}