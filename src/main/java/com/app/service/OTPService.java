package com.app.service;

import com.app.payload.OTPDetails;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private final Map<String, OTPDetails> otpStroge = new HashMap<>();
    private final  int OTP_EXPIRY_TIME =5;

    public String generateOTP(String mobile){
        String otp = String.format("%06d",new Random().nextInt(999999));
        OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());
        otpStroge.put(mobile,otpDetails);
        return otp;
    }

   public boolean validateOTP(String mobile,String otp){
       OTPDetails otpDetails = otpStroge.get(mobile);
       if(otpDetails==null){
         return false;
       }
       long currentTime = System.currentTimeMillis();
       long otpTime = otpDetails.getTimestamp();
       long differentTime = TimeUnit.MILLISECONDS.toMinutes(otpTime - currentTime);
       if(differentTime>OTP_EXPIRY_TIME){
           otpStroge.remove(mobile);
           return false;
       }
       return otpDetails.getOtp().equals(otp);
   }

}
