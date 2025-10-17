package com.aditya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.aditya"})
@EnableAspectJAutoProxy
public class TurfApplication implements Runnable {
  public static void main(String[] args) {
    SpringApplication.run(TurfApplication.class, args);
  }

  @Override
  public void run() {
    System.out.println("Turf Application Started");
  }
}
