package com.microservice.Main.producer;

import com.microservice.Main.model.RequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

//    @Autowired
//    private SignUpRequestsRepository signUpRequestsRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountProducer.class);
    public void validationCheck(RequestLog requestLog) throws JmsException {
        try{
            jmsTemplate.convertAndSend("validationQueue", requestLog);
            log.info("Successful message sent for validation. Request ID:"+ requestLog.getId());
        }catch (JmsException jmsException){
            throw jmsException;
        }
    }

//    public void retryAccountCreation(SignUpRequest signUpRequest) {
//        try{
//            log.info("Request ID: "+signUpRequest.getId());
//            jmsTemplate.convertAndSend("validationQueue", signUpRequest);
//
//            signUpRequest.setCount(signUpRequest.getCount()+1);
//            signUpRequest.setStatus("ACCEPTED");
//            signUpRequest.setRemark("Request is accepted and will be validated.");
//            signUpRequest.setLastUpdate(new Date());
//
//            signUpRequestsRepository.save(signUpRequest);
//
//        }catch (JmsException jmsException){
//            signUpRequest.setCount(signUpRequest.getCount()+1);
//            signUpRequest.setLastUpdate(new Date());
//            signUpRequestsRepository.save(signUpRequest);
//            throw jmsException;
//        }
//    }
}
