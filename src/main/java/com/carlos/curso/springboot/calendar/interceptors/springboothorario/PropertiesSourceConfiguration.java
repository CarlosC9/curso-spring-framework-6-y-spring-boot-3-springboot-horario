package com.carlos.curso.springboot.calendar.interceptors.springboothorario;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
  @PropertySource("classpath:config.properties")
})
public class PropertiesSourceConfiguration {
}
