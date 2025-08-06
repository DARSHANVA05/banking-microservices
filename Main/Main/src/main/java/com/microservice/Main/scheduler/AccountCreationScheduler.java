//package com.microservice.Main.scheduler;
//
//import com.microservice.Main.service.RetryService;
//import com.microservice.Main.model.SignUpRequest;
//import com.microservice.Main.repository.SignUpRequestsRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class AccountCreationScheduler {
//
//    private static final Logger log = LoggerFactory.getLogger(AccountCreationScheduler.class);
//    @Autowired
//    private SignUpRequestsRepository signUpRequestsRepository;
//    @Autowired
//    private RetryService retryService;
//
//    @Scheduled(cron = "*/10 * * * * *")
////    @Scheduled(fixedRate = 5000)
//    public void retryAccountCreation(){
//        log.info("Retrying account creation for failed requests: "+new Date());
//
//        List<SignUpRequest> signUpRequestList = signUpRequestsRepository.getByStatus("FAILED");
//        log.info("Number of failed requests: "+signUpRequestList.size());
//
//        for(SignUpRequest signUpRequest :  signUpRequestList){
//            log.info("Request id: "+signUpRequest.getId()+"\t Retry count: "+signUpRequest.getCount());
//            retryService.retryAccountCreation(signUpRequest);
//            //Condition for count greater than 3.
//        }
//    }
//
//}
