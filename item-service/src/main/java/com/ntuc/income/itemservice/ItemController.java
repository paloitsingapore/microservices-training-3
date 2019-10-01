package com.ntuc.income.itemservice;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  @GetMapping("/items")
  public List<String> getItems(){
    return Arrays.asList("iPhone-11-max-pro", "Samsung note 10");
  }

}
