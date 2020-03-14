package expressions;

import expressions.Expression;
import numbers.Number;

public class TerminalExpression extends Expression{
    
    protected Number x;

    public TerminalExpression(Number x)
    {
        this.x = x;
    }

    public Number solve()
    {
        return x;
    }

}