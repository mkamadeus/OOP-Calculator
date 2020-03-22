package exceptions;

public class DivideWithZeroException extends BaseException {

		public DivideWithZeroException(){
			super();
		}

    protected String error() {
        return "Can't divide with zero";
    }
}