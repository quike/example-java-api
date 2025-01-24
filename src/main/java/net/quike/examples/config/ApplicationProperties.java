package net.quike.examples.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@ConfigurationProperties("downstream.api")
@ConditionalOnMissingBean
public class ApplicationProperties {
  private final String baseUrl;
  private final String commonParam;
  private final ResourcePaths resourcePaths;

  public record ResourcePaths(String barPath) {}
}
