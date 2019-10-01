package com.ntuc.income.itemservice;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  @GetMapping("/items")
  public List<String> getItems(HttpServletRequest request){
    return Arrays.asList("iPhone-11-max-pro", "Samsung note 10", Integer.toString(request.getServerPort()));
  }

}
