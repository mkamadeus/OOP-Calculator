package exceptions;

public class ImbalancedParanthesesException extends BaseException {

		public ImbalancedParanthesesException(){
			super();
		}

    public String error() {
        return "Pair of parantheses doesn't match";
    }
}