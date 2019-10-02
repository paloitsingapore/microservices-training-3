package com.ntuc.income.orderservice;

import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
  private static Logger log = LoggerFactory.getLogger(OrderController.class);

  @Autowired ItemService itemService;
  @Autowired LoadBalancerClient loadBalancerClient;

  private PaymentService paymentService;

  OrderController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @GetMapping("/greet")
  public String greet() {
    return "Hello World!";
  }

  @GetMapping("/order/items")
  public ResponseEntity<List<String>> getItemsFromOrder() {
    return ResponseEntity.ok(itemService.getItems());
  }

  @GetMapping("/order/items2")
  public ResponseEntity<List<String>> getItemsUsingRibbon() {

    return ResponseEntity.ok(getItems());
  }

  @PostMapping("/order")
  @Async
  public ResponseEntity createOrder() {
    paymentService.createOrderEvent(getItems());
    return ResponseEntity.accepted().build();
  }

  private List<String> getItems() {
    ServiceInstance serviceInstance = loadBalancerClient.choose("item-service");
    RestTemplate restTemplate = new RestTemplate();
    URI uri = URI.create(
        "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/items");
    log.info("URI constructed {}  ## Service Instance {} ", uri.toString(),
        serviceInstance.toString());
    return restTemplate.getForObject(uri, List.class);
  }
}
