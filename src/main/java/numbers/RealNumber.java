package numbers;

import exceptions.BaseException;
import exceptions.InvalidSyntaxException;

public class RealNumber implements Number {
    private Double value;

    public RealNumber() {this.value = 0.0;}
    public RealNumber(Double v) {this.value = v;}
    public RealNumber(String v) throws BaseException {
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
	public String parse() {
    	if(this.value.intValue() == this.value){
    		return Integer.toString(this.value.intValue());
		}
    	return String.valueOf(this.value);
	}
}