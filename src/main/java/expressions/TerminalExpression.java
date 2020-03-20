package expressions;

import expressions.Expression;
import numbers.Number;
import numbers.RealNumber;

public class TerminalExpression implements Expression{
    
    protected RealNumber x;

    public TerminalExpression(RealNumber x)
    {
        this.x = x;
    }

    public RealNumber solve()
    {
        return x;
    }

}