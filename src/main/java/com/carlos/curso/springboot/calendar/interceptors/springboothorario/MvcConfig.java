package com.carlos.curso.springboot.calendar.interceptors.springboothorario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  private HandlerInterceptor calendar;

  @Autowired
  @Qualifier("calendarInterceptor")
  public void setCalendar(HandlerInterceptor calendar) {
    this.calendar = calendar;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
      .addInterceptor(this.calendar)
      .addPathPatterns("/foo");
  }
}
