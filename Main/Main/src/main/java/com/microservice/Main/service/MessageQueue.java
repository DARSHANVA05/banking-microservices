package com.microservice.Main.service;

import com.microservice.Main.model.RequestLog;
import com.microservice.Main.producer.AccountProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Service;

@Service
public class MessageQueue {

    private static final Logger log = LoggerFactory.getLogger(MessageQueue.class);
    @Autowired
    private AccountProducer accountProducer;



    public void createAccount(RequestLog requestLog)throws JmsException {
        try{
            accountProducer.validationCheck(requestLog);
        }catch (JmsException jmsException){
            throw jmsException;
        }
    }
//    public void retryAccountCreation(SignUpRequest signUpRequest)throws JmsException {
//        try{
//            accountProducer.retryAccountCreation(signUpRequest);
//        }catch (JmsException jmsException){
//            throw jmsException;
//        }
//    }



}
