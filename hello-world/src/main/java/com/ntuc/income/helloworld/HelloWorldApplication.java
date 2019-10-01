package com.ntuc.income.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloWorldApplication implements ApplicationRunner {

  @Autowired
  UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(HelloWorldApplication.class, args);
  }

  @GetMapping("/greet/{name}")
  public String greet(@PathVariable String name) {
    return "Hello " + name + "!";
  }


  @GetMapping("/user/{name}")
  public User getUser(@PathVariable String name) {
    return userRepository.findByName(name);
  }


  @Override public void run(ApplicationArguments args) throws Exception {
    userRepository.save(new User("john", 33));
    userRepository.save(new User("ben", 22));
    userRepository.save(new User("Tim", 67));
  }
}
