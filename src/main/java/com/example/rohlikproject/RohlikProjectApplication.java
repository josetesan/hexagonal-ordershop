package com.example.rohlikproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RohlikProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(RohlikProjectApplication.class, args);
  }
}
