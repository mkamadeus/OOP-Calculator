package expressions;

import expressions.Expression;
import expressions.UnaryExpression;
import numbers.Number;
import numbers.RealNumber;
import exceptions.BaseException;
import exceptions.NegativeRootException;

public class SquareRootExpression extends UnaryExpression{
    
    public SquareRootExpression(Expression e1)
    {
        super(e1);
    }

    public RealNumber solve() throws BaseException {
    		if(e.solve().value() < 0)
    		{
    			BaseException exp = new NegativeRootException();
    			throw exp;
    		}
    		else
    		{
    			return new RealNumber(Math.pow(e.solve().value(), 0.5));
    		}
        
    }
}