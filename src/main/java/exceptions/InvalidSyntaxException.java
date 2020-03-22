package exceptions;

public class InvalidSyntaxException extends BaseException {

		public InvalidSyntaxException(){
			super();
		}

    protected String error() {
        return "Invalid syntax";
    }
}