package Exception;

class NegativeRootException extends BaseException {
    protected String error() {
        return "This calculator doesn't support complex number";
    }
}