package Exception;

class InvalidSyntaxException extends BaseException {
    protected String error() {
        return "Invalid syntax";
    }
}