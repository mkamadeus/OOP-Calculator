package expressions;

import exceptions.BaseException;
import numbers.Number;
import numbers.RealNumber;

interface Expression {
    public RealNumber solve() throws BaseException;
}