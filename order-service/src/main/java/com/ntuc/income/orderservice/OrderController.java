package com.ntuc.income.orderservice;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  private static Logger log = LoggerFactory.getLogger(OrderController.class);

  @Autowired ItemService itemService;

  @GetMapping("/order/items")
  public ResponseEntity<List<String>> getItems(){
    return ResponseEntity.ok(itemService.getItems());

  }
}
