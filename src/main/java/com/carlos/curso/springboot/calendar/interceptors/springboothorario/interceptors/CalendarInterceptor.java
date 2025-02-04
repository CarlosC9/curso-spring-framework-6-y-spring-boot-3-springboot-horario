package com.carlos.curso.springboot.calendar.interceptors.springboothorario.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

  private Logger logger;

  public CalendarInterceptor() {
    this.logger = LoggerFactory.getLogger(CalendarInterceptor.class);
  }

  @Value("${config.calendar.open}")
  private Integer open;

  @Value("${config.calendar.close}")
  private Integer close;

  private void inTime(HttpServletRequest request) {
    StringBuilder message = new StringBuilder("Bienvenidos al horario de atencion al cliente");
    message.append(", atendemos desde las ");
    message.append(this.open);
    message.append("hrs. hasta las ");
    message.append(this.close);
    message.append("hrs. Gracias por su visita");
    request.setAttribute("message", message.toString());
  }

  private void outTime(HttpServletResponse response) throws IOException {
    Map<String, Object> data = new HashMap<>();
    StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atención por favor visitenos desde las");

    message.append(this.open);
    message.append(" y las ");
    message.append(this.close);
    message.append(" hrs. Gracias!");

    data.put("message", message.toString());

    response.setContentType("application/json");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(new ObjectMapper().writeValueAsString(data));
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    logger.info(Integer.toString(hour));

    if (hour >= this.open && hour < this.close) {
      this.inTime(request);
      return true;
    }
    this.outTime(response);
    return false;
  }
}
