package expressions;

import expressions.Expression;

abstract class TerminalExpression extends Expression{
    
    protected Number x;

    public TerminalExpression(Number x)
    {
        this.x = x;
    }

    abstract public Number solve();
}