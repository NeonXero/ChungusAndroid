package net.neonlotus.chungusadmin.networking;

public class ResponseCarrier {
    private String response;
    private boolean success;
    private String errorMessage;

    public ResponseCarrier(String resp, boolean success, String errorMessage) {
        this.response = resp;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public String getResponse() {
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}