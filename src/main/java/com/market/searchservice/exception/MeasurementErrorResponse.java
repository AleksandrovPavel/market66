package com.market.searchservice.exception;

public class MeasurementErrorResponse {
    private String message;

    public MeasurementErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}