import static org.junit.Assert.assertEquals;

import exceptions.BaseException;
import expressions.*;
import numbers.RealNumber;
import org.junit.Test;


public class ExpressionTester {


    @Test
    public void additionTester() throws BaseException {
        RealNumber val1 = new RealNumber(1200.0);
        RealNumber val2 = new RealNumber(30.0);
        Expression exp1 = new TerminalExpression(val1);
        Expression exp2 = new TerminalExpression(val2);
        AddExpression add = new AddExpression(exp1, exp2);
        assertEquals(1230.0,add.solve().value(),0);
    }

    @Test
    public void subtractionTester() throws BaseException {
        RealNumber val1 = new RealNumber(1200.0);
        RealNumber val2 = new RealNumber(30.0);
        Expression exp1 = new TerminalExpression(val1);
        Expression exp2 = new TerminalExpression(val2);
        SubtractExpression sub = new SubtractExpression(exp1, exp2);
        assertEquals(1170.0,sub.solve().value(),0);
    }

    @Test
    public void divisionTester() throws BaseException {
        RealNumber val1 = new RealNumber(1200.0);
        RealNumber val2 = new RealNumber(30.0);
        Expression exp1 = new TerminalExpression(val1);
        Expression exp2 = new TerminalExpression(val2);
        DivideExpression div = new DivideExpression(exp1, exp2);
        assertEquals(40.0,div.solve().value(),0);
    }

    @Test
    public void multiplicationTester() throws BaseException {
        RealNumber val1 = new RealNumber(1200.0);
        RealNumber val2 = new RealNumber(30.0);
        Expression exp1 = new TerminalExpression(val1);
        Expression exp2 = new TerminalExpression(val2);
        MultiplicationExpression mul = new MultiplicationExpression(exp1, exp2);
        assertEquals(36000.0,mul.solve().value(),0);
    }

    @Test
    public void powerTester() throws BaseException {
        RealNumber val1 = new RealNumber(1200.0);
        RealNumber val2 = new RealNumber(3.0);
        Expression exp1 = new TerminalExpression(val1);
        Expression exp2 = new TerminalExpression(val2);
        PowerExpression pow = new PowerExpression(exp1, exp2);
        assertEquals(1728000000.0,pow.solve().value(),0);
    }

    @Test
    public void sineTester() throws BaseException {
        RealNumber val1 = new RealNumber(0.0);
        Expression exp1 = new TerminalExpression(val1);
        SineExpression sine = new SineExpression(exp1);
        assertEquals(0.0,sine.solve().value(),0);
    }

    @Test
    public void cosineTester() throws BaseException {
        RealNumber val1 = new RealNumber(0.0);
        Expression exp1 = new TerminalExpression(val1);
        CosineExpression cosine = new CosineExpression(exp1);
        assertEquals(1.0,cosine.solve().value(),0);
    }

    @Test
    public void tangentTester() throws BaseException {
        RealNumber val1 = new RealNumber(0.0);
        Expression exp1 = new TerminalExpression(val1);
        TangentExpression tan = new TangentExpression(exp1);
        assertEquals(0.0,tan.solve().value(),0);
    }

    @Test
    public void negativeTester() throws BaseException {
        RealNumber val1 = new RealNumber(2020.0);
        Expression exp1 = new TerminalExpression(val1);
        NegativeExpression neg = new NegativeExpression(exp1);
        assertEquals(-2020.0,neg.solve().value(),0);
    }

    @Test
    public void sqrtTester() throws BaseException {
        RealNumber val1 = new RealNumber(4.0);
        Expression exp1 = new TerminalExpression(val1);
        SquareRootExpression sqrt = new SquareRootExpression(exp1);
        assertEquals(2.0,sqrt.solve().value(),0);
    }

}
