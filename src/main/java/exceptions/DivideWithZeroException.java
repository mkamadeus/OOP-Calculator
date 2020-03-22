package exceptions;

public class DivideWithZeroException extends BaseException {

		public DivideWithZeroException(){
			super();
		}

    public String error() {
        return "Can't divide with zero";
    }
}