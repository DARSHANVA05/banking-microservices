package com.microservice.Main.service;

import com.microservice.Main.model.RequestLog;
import com.microservice.data.Errors;
import com.microservice.data.RequestPayload;
import com.microservice.data.ResponsePayload;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayloadService {

    public RequestLog createRequestLog(RequestPayload requestPayload){
        return new RequestLog(
                UUID.randomUUID().toString(),
                new Date(),
                requestPayload.toString(),
                "Request is accepted and will be validated.",
                new Date(),
                0,
                "ACCEPTED");
    }

    public ResponsePayload generateAcceptedResponsePayload(RequestLog requestLog) {
        return new ResponsePayload( requestLog.getRequestId(),
                                    requestLog.getRequestDate(),
                                    requestLog.getStatus(),
                                    requestLog.getRemark());
    }
    public ResponsePayload generateErrorResponsePayload(RequestLog requestLog) {

        List<Errors> errorList = new ArrayList<>();
        errorList.add(new Errors("MB404","Message broker not available."));

        return new ResponsePayload(requestLog.getRequestId(),
                requestLog.getRequestDate(),
                requestLog.getStatus(),
                requestLog.getRemark(),
                errorList
        );
    }
}
