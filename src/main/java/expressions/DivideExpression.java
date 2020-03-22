package expressions;

import expressions.Expression;
import expressions.BinaryExpression;
import numbers.Number;
import numbers.RealNumber;
import exceptions.BaseException;
import exceptions.DivideWithZeroException;

public class DivideExpression extends BinaryExpression{
    
    public DivideExpression(Expression e1, Expression e2)
    {
        super(e1,e2);
    }

    public RealNumber solve()
    {	
    		if (e2.solve().value() == 0)
    		{
    			BaseException exp = new DivideWithZeroException();
    			throw exp;
    		} 
            else
    		{
    			return new RealNumber(e1.solve().value() / e2.solve().value());
    		}
        
    }
}