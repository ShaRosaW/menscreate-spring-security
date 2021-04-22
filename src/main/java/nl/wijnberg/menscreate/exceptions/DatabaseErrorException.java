package nl.wijnberg.menscreate.exceptions;

public class DatabaseErrorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DatabaseErrorException() {
        super("A problem occurs in the database");
    }
}
