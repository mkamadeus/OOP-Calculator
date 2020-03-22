package Exception;

class ImbalancedParanthesesException extends BaseException {
    protected String error() {
        return "Pair of paranthesis doesn't match";
    }
}