package exceptions;

public class InvalidSyntaxException extends BaseException {

		public InvalidSyntaxException(){
			super();
		}

    public String error() {
        return "Invalid syntax";
    }
}