package com.carlos.curso.springboot.calendar.interceptors.springboothorario.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

  @Value("${config.calendar.open}")
  private Integer open;

  @GetMapping("/foo")
  public ResponseEntity<?> foo() {
    Map<String, Object> data = new HashMap<>();
    data.put("title", "Bienvenidos al sistema de atención");
    data.put("time", new Date());
    return ResponseEntity.ok(data);
  }

}
