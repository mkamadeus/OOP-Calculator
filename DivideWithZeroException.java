package Exception;

class DivideWithZeroException extends BaseException {
    protected String error() {
        return "Can't divide with zero";
    }
}