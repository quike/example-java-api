package net.quike.examples.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import net.quike.examples.repository.BarRepository;
import net.quike.examples.service.FooService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfig {

  @Bean("objectMapper")
  public ObjectMapper objectMapper() {
    return ApplicationJacksonBeanFactory.initObjectMapper();
  }

  @Bean("restTemplate")
  public RestTemplate restTemplate() {
    return ApplicationRestTemplateFactory.initRestTemplate();
  }

  @Bean("barRepository")
  public BarRepository barRepository(
      ApplicationProperties applicationProperties, RestTemplate restTemplate) {
    return new BarRepository(applicationProperties, restTemplate);
  }

  @Bean("fooService")
  public FooService fooService(BarRepository barRepository) {
    return new FooService(barRepository);
  }
}
