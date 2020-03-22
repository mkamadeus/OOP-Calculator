package exceptions;

public class ImbalancedParanthesesException extends BaseException {

		public ImbalancedParanthesesException(){
			super();
		}

    protected String error() {
        return "Pair of parantheses doesn't match";
    }
}