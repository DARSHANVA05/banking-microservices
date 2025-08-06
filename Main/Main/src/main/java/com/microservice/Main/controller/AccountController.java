package com.microservice.Main.controller;

import com.microservice.Main.model.RequestLog;
import com.microservice.Main.service.AccountRequestService;
import com.microservice.Main.service.MessageQueue;
import com.microservice.Main.service.PayloadService;
import com.microservice.data.RequestPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/mybank/v1/")
//@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private MessageQueue messageQueue;
    @Autowired
    private AccountRequestService accountRequestService;
    @Autowired
    private PayloadService payloadService;

    public AccountController(MessageQueue messageQueue, AccountRequestService accountRequestService, PayloadService payloadService) {
        this.messageQueue = messageQueue;
        this.accountRequestService = accountRequestService;
        this.payloadService = payloadService;
    }

    @PostMapping("/account")
    @ResponseBody
    public ResponseEntity createAccount(@RequestBody RequestPayload requestPayload) {

        try {

            RequestLog requestLog = payloadService.createRequestLog(requestPayload);

            messageQueue.createAccount(requestLog);
            accountRequestService.saveRequest(requestLog);
            log.info("Request is accepted and yet is to be validated.");

            return new ResponseEntity(payloadService.generateAcceptedResponsePayload(requestLog), HttpStatus.ACCEPTED);

        } catch (JmsException jmsException) { // JMS unavailable
            log.error("Error occurred with the Message broker.");
            RequestLog requestLog = new RequestLog(
                    UUID.randomUUID().toString(),
                    new Date(),
                    requestPayload.toString(),
                    "Message broker not available.",
                    new Date(),
                    0,
                    "FAILED");
            accountRequestService.saveRequest(requestLog);
            return new ResponseEntity(payloadService.generateErrorResponsePayload(requestLog), HttpStatus.INTERNAL_SERVER_ERROR);


        } catch (JpaSystemException jpsException) {  // DB related issues
            return null;
        } catch (Exception exception) {  // All other system exceptions
            return null;
        }

    }
/*
    @PostMapping("/account-creation")
    @ResponseBody
    public ResponseEntity uiIntegration(@RequestBody UiIntegration uiIntegration){
        log.info(uiIntegration.toString());
        return new ResponseEntity(null,HttpStatus.OK);
    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity loginValidation(@RequestBody LoginRequest loginRequest){
        log.info(loginRequest.toString());
        return  ResponseEntity.ok("Credentials Matched");
    }
*/
}

