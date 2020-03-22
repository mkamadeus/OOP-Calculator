package numbers;

import exceptions.BaseException;
import exceptions.InvalidSyntaxException;

public class RealNumber implements Number {
    private Double value;

    public RealNumber() {this.value = 0.0;}
    public RealNumber(Double v) {this.value = v;}
    public RealNumber(String v) {
    	try
    	{
    		this.value = Double.parseDouble(v);
    	}
    	catch(NumberFormatException e)
    	{
    		BaseException exp = new InvalidSyntaxException();
    		throw exp;
    	}
    }

    public Double value() {
        return this.value;
    }

}