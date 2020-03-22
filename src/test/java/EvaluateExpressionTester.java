import static org.junit.Assert.assertEquals;

import exceptions.BaseException;
import expressions.EvaluateExpression;
import org.junit.Test;

public class EvaluateExpressionTester {

    @Test
    public void tester1() throws BaseException {
        EvaluateExpression tes1 = new EvaluateExpression("(2-(2+(5-2*3)))");
        assertEquals(1,tes1.parse().value(),0);
    }

    @Test
    public void tester2() throws BaseException {
        EvaluateExpression tes1 = new EvaluateExpression("1+5(3-7)-6");
        assertEquals(-25,tes1.parse().value(),0);
    }

    @Test
    public void tester3() throws BaseException {
        EvaluateExpression tes1 = new EvaluateExpression("3^2*1+2*3");
        assertEquals(15,tes1.parse().value(),0);
    }




}
