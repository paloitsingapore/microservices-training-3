package com.ntuc.income.paymentservice;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@EnableBinding(Sink.class)
public class PaymentServiceApplication {

  private static final Logger log = LoggerFactory.getLogger(PaymentServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
  }

  @StreamListener(Sink.INPUT)
  public void readOrderEvent(@Payload List<String> orderEvent){
    log.info("Order Event received {}", orderEvent);
  }
}
