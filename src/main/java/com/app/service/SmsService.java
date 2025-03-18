package com.app.service;

import com.app.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String to, String body) {
        Message.creator(
                new PhoneNumber(to),  // Corrected PhoneNumber import
                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),  // Corrected import
                body
        ).create();

//        System.out.println("SMS sent successfully. Message SID: " + message.getSid());
    }
}
