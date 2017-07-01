package com.springapp.mvc.response;

public class SimpleResponse extends BaseResponse {
    private SimpleResponse() {
        super(null);
    }

    public SimpleResponse(String errorMessage) {
        super(null, errorMessage);
    }
}
