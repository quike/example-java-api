package net.quike.examples.config;

import static java.util.Objects.isNull;

import org.springframework.web.client.RestTemplate;

public class ApplicationRestTemplateFactory {

  private static RestTemplate restTemplate;

  /** Utility class. Cannot instantiate. */
  private ApplicationRestTemplateFactory() {}

  public static RestTemplate initRestTemplate() {
    if (isNull(restTemplate)) {
      synchronized (RestTemplate.class) {
        if (isNull(restTemplate)) {
          restTemplate = new RestTemplate();
        }
      }
    }
    return restTemplate;
  }
}
