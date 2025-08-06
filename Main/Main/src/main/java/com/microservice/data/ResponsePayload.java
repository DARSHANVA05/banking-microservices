package com.microservice.data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ResponsePayload {
    private String requestId;
    private Date requestDate;
    private String status;
    private String remarks;
    private List<Errors> errors;


    public ResponsePayload(String requestId, Date requestDate, String status, String remarks, List<Errors> errors) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.status = status;
        this.remarks = remarks;
        this.errors = errors;
    }

    public ResponsePayload(String requestId, Date requestDate, String status, String remarks) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.status = status;
        this.remarks = remarks;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }
}

