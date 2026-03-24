package seedu.address.model.group.exceptions;

public class NotInGroupException extends RuntimeException {
    public NotInGroupException(String message) {
        super(message);
    }
}
