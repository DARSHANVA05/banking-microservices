package com.microservice.Main.controller;

import com.microservice.Main.model.RequestLog;
import com.microservice.Main.service.AccountRequestService;
import com.microservice.Main.service.MessageQueue;
import com.microservice.Main.service.PayloadService;
import com.microservice.data.Address;
import com.microservice.data.Contact;
import com.microservice.data.RequestPayload;
import com.microservice.data.ResponsePayload;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Test
    public void createAccountTestAccepted() {

        MessageQueue messageQueueMock = mock(MessageQueue.class);
        AccountRequestService accountRequestServiceMock = mock(AccountRequestService.class);
        PayloadService payloadServiceMock = mock(PayloadService.class);

        AccountController accountController = new AccountController(
                messageQueueMock,
                accountRequestServiceMock,
                payloadServiceMock
        );

        RequestPayload requestPayload = new RequestPayload(
                100,
                "Zlatan",
                new Contact("9876543210", "9123456780"),
                new Date(90, 5, 24),
                new Address("A1", "A2", "A3", "City", "State", "Country")
        );

        RequestLog requestLog = new RequestLog(
                "req-id-001",
                new Date(90, 5, 24),
                "{\"account_number\": 100, \"name\": \"Zlatan\"}",
                "Initial request saved",
                new Date(90, 5, 24),
                0,
                "PENDING"
        );

        ResponsePayload expectedResponsePayload = new ResponsePayload(
                "req-id-001",
                new Date(90, 5, 24),
                "ACCEPTED",
                "Successfully processed"
        );

        when(payloadServiceMock.createRequestLog(requestPayload)).thenReturn(requestLog);
        when(payloadServiceMock.generateAcceptedResponsePayload(requestLog)).thenReturn(expectedResponsePayload);

        ResponseEntity<ResponsePayload> response = accountController.createAccount(requestPayload);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expectedResponsePayload.getRequestId(), response.getBody().getRequestId());
        assertEquals(expectedResponsePayload.getStatus(), response.getBody().getStatus());
        assertEquals(expectedResponsePayload.getRemarks(), response.getBody().getRemarks());

    }

    @Test
    public void createAccountTestJMSError() {

        MessageQueue messageQueueMock = mock(MessageQueue.class);
        AccountRequestService accountRequestServiceMock = mock(AccountRequestService.class);
        PayloadService payloadServiceMock = mock(PayloadService.class);

        AccountController accountController = new AccountController(
                messageQueueMock,
                accountRequestServiceMock,
                payloadServiceMock
        );


        RequestLog requestLogEx = new RequestLog(
                "req-001",
                new Date(125, 6, 1),
                "{\"account_number\": 100, \"name\": \"xxxx\"}",
                "Message broker not available.",
                new Date(125, 6, 1),
                0,
                "FAILED"
        );

        ResponsePayload responsePayload = new ResponsePayload(
                "req-20250701-001",
                new Date(125, 6, 1), // July 1, 2025
                "FAILED",
                "Message broker not available"
        );


        doThrow(new JmsException("Simulated JMS failure") {
        })
                .when(messageQueueMock)
                .createAccount(any(RequestLog.class));

        when(payloadServiceMock.generateErrorResponsePayload(any(RequestLog.class)))
                .thenReturn(responsePayload);

        when(payloadServiceMock.createRequestLog(any(RequestPayload.class)))
                .thenReturn(new RequestLog(
                        "req-001",
                        new Date(125, 6, 1),
                        "{\"account_number\": 100, \"name\": \"xxxx\"}",
                        "Message broker not available.",
                        new Date(125, 6, 1),
                        0,
                        "FAILED"
                ));


        RequestLog requestLog = new RequestLog(
                "req-001",
                new Date(125, 6, 1),
                "{\"account_number\": 100, \"name\": \"xxxx\"}",
                "Message broker not available.",
                new Date(125, 6, 1),
                0,
                "FAILED"
        );


        when(payloadServiceMock.generateErrorResponsePayload(requestLog)).thenReturn(responsePayload);

        RequestPayload requestPayload = new RequestPayload(
                100,
                "xxxx",
                new Contact("9876543210", "9123456780"),
                new Date(90, 5, 24),
                new Address("A1", "A2", "A3", "City", "State", "Country")
        );

        ResponseEntity<ResponsePayload> response = accountController.createAccount(requestPayload);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("FAILED", response.getBody().getStatus());
        assertEquals("Message broker not available", response.getBody().getRemarks());


    }

}
