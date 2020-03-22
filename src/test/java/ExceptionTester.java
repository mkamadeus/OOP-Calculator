import static org.junit.Assert.assertEquals;

import expressions.*;
import exceptions.*;
import numbers.RealNumber;
import org.junit.Test;



public class ExceptionTester {

    @Test(expected  = DivideWithZeroException.class)
    public void firsTest() throws BaseException {
        EvaluateExpression tes = new EvaluateExpression("(1^3-(5/(7-8+1)))");
        RealNumber re = new RealNumber(tes.parse().value());
    }

    @Test(expected = ImbalancedParanthesesException.class)
    public void secondTest() throws BaseException {
        EvaluateExpression tes = new EvaluateExpression("(((((2-3))+5^7-3*2+1))");
        RealNumber re = new RealNumber(tes.parse().value());
    }

    @Test(expected = NegativeRootException.class)
    public void thirdTest() throws BaseException {
        EvaluateExpression tes = new EvaluateExpression("2-sqrt(-3)");
        RealNumber re = new RealNumber(tes.parse().value());
    }

    @Test(expected = InvalidSyntaxException.class)
    public void fourthTest() throws BaseException {
        EvaluateExpression tes = new EvaluateExpression("1+2+3+5.5.5");
        RealNumber re = new RealNumber(tes.parse().value());
    }

    @Test(expected = InvalidSyntaxException.class)
    public void fifthTest() throws BaseException {
        EvaluateExpression tes = new EvaluateExpression("--12");
        RealNumber re = new RealNumber(tes.parse().value());
    }

}
