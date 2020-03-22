package expressions;

import expressions.Expression;
import expressions.BinaryExpression;
import numbers.Number;
import numbers.RealNumber;
import exceptions.BaseException;
import exceptions.NegativeRootException;

public class PowerExpression extends BinaryExpression{
    
    public PowerExpression(Expression e1, Expression e2)
    {
        super(e1,e2);
    }

    public RealNumber solve()
    {		
    	Double res;

    	try
    	{
    		res = Math.pow(e1.solve().value(),e2.solve().value());
    	}
    	catch(Exception e)
    	{
    		BaseException exp = new NegativeRootException();
    		throw exp;
    	}

      return new RealNumber(res);
    }
}