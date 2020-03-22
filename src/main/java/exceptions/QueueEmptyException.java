package exceptions;

public class QueueEmptyException extends BaseException {

    public QueueEmptyException(){
        super();
    }

    public String error() {
        return "The history memory is currently empty";
    }

}
