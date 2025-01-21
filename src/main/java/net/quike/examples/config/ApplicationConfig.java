package net.quike.examples.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import net.quike.examples.repository.BarRepository;
import net.quike.examples.service.FooService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Configuration
public class ApplicationConfig {
  @Value("${downstream.api.base.url}")
  private String baseUrl;

  @Value("${downstream.api.common.param}")
  private String commonParam;

  @Bean("applicationProperties")
  @ConditionalOnMissingBean
  public ApplicationProperties applicationProperties() {
    return ApplicationProperties.builder().baseUrl(baseUrl).commonParam(commonParam).build();
  }

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
