package com.aston.bank_processing.exceptionhandler;

public class AppErrorMessage {
    private int status;
    private String detail;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public AppErrorMessage(int status, String detail) {
        this.status = status;
        this.detail = detail;
    }
}
