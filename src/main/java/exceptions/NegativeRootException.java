package exceptions;

public class NegativeRootException extends BaseException {

		public NegativeRootException(){
			super();
		}

    public String error() {
        return "This calculator doesn't support complex number";
    }
}