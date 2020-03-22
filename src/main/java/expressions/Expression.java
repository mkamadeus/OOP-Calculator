package expressions;

import exceptions.BaseException;
import numbers.Number;
import numbers.RealNumber;

public interface Expression {
    public RealNumber solve() throws BaseException;
}