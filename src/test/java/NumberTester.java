import exceptions.BaseException;
import exceptions.DivideWithZeroException;
import exceptions.InvalidSyntaxException;
import expressions.AddExpression;
import expressions.EvaluateExpression;
import expressions.Expression;
import expressions.TerminalExpression;
import numbers.RealNumber;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NumberTester {

    @Test(expected  = InvalidSyntaxException.class)
    public void firstTest() throws BaseException {
        String num1 = "-1231231.2342.3";
        RealNumber re1 = new RealNumber(num1);
    }

    @Test(expected  = InvalidSyntaxException.class)
    public void secondTest() throws BaseException {
        String num1 = "--12";
        RealNumber re1 = new RealNumber(num1);
    }

    @Test
    public void thirdTest() throws BaseException {
        String num = "0000000001.2";
        RealNumber re = new RealNumber(num);
        assertEquals(1.200,re.value(),0);
    }


}
