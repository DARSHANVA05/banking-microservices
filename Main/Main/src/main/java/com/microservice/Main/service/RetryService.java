package com.microservice.Main.service;

import com.microservice.Main.producer.AccountProducer;
import com.microservice.Main.model.RequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Service;

@Service
public class RetryService {
    @Autowired
    private AccountProducer accountProducer;

    public void retryAccountCreation(RequestLog requestLog)throws JmsException {
        try{
          //  accountProducer.retryAccountCreation(signUpRequest);
        }catch (JmsException jmsException){
            throw jmsException;
        }
    }

}
