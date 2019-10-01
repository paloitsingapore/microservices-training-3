package com.ntuc.income.orderservice;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "item-service")
public interface ItemService {
  @GetMapping("/items")
  public List<String> getItems();
}
