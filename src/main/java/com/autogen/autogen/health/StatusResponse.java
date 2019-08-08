package com.autogen.autogen.health;

public class StatusResponse {

    public StatusResponse(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
