package com.microservice.Main.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "request_log")
public class RequestLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Request_id")
    private String requestId;
    @Column(name = "Request_date")
    private Date requestDate;
    @Column(name = "Request_payload")
    private String requestPayload;
    @Column(name = "Remark")
    private String remark;
    @Column(name = "Last_update")
    private Date lastUpdate;
    @Column(name = "Retry_count")
    private int retryCount;
    //private responsePayload;
    @Column(name = "Status")
    private String status;

    public RequestLog(String requestId, Date requestDate, String requestPayload, String remark, Date lastUpdate, int retryCount, String status) {
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.requestPayload = requestPayload;
        this.remark = remark;
        this.lastUpdate = lastUpdate;
        this.retryCount = retryCount;
        this.status = status;
    }


    public RequestLog(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
