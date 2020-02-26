package net.neonlotus.chungusadmin.networking.models;

public class ErrorResponse {
    private ErrorBody Error;

    public String getErrorMessage() {
        return Error.getMessage();
    }
}

class ErrorBody {
    String Message;

    public String getMessage() {
        return Message;
    }
}