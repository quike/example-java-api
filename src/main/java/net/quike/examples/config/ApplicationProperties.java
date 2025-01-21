package net.quike.examples.config;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ApplicationProperties {
  private final String baseUrl;
  private final String commonParam;
}
