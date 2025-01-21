package net.quike.examples.config;

import static java.util.Objects.isNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ApplicationJacksonBeanFactory {

  private static ObjectMapper objectMapper;

  /** Utility class. Cannot instantiate. */
  private ApplicationJacksonBeanFactory() {}

  public static ObjectMapper initObjectMapper() {
    if (isNull(objectMapper)) {
      synchronized (ObjectMapper.class) {
        if (isNull(objectMapper)) {
          objectMapper =
              JsonMapper.builder()
                  .addModule(new JavaTimeModule())
                  .visibility(PropertyAccessor.FIELD, Visibility.ANY)
                  .serializationInclusion(Include.NON_NULL)
                  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                  .build();
        }
      }
    }
    return objectMapper;
  }
}
