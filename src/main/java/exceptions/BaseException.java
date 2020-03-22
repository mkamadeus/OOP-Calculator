package exceptions;

public abstract class BaseException extends Exception {

		public BaseException() {}

    abstract protected String error();
}