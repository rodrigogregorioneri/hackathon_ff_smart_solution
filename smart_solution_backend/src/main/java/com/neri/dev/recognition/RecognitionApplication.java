package com.neri.dev.recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nu.pattern.OpenCV;

@SpringBootApplication
public class RecognitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecognitionApplication.class, args);
        OpenCV.loadShared();
    }

}
