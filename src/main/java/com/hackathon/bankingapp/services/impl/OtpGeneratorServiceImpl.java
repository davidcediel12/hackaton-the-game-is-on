package com.hackathon.bankingapp.services.impl;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpGeneratorServiceImpl implements com.hackathon.bankingapp.services.OtpGeneratorService {

    private final Random random;

    public OtpGeneratorServiceImpl() {
        random = new Random();
    }

    @Override
    public String generateOtp(){
        return String.format("%06d", random.nextInt(1000000));
    }
}
