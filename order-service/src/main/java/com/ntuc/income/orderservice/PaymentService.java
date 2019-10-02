package com.ntuc.income.orderservice;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@EnableBinding(Source.class)
@Service
public class PaymentService {

  @Autowired Source source;

  public void createOrderEvent(List<String> items) {
    items.add(UUID.randomUUID().toString());
    Message orderEvent = MessageBuilder.withPayload(items).build();
    source.output().send(orderEvent);
  }
}
