package net.quike.examples.repository;

import java.net.URI;
import lombok.extern.log4j.Log4j2;
import net.quike.examples.config.ApplicationProperties;
import net.quike.examples.exception.SdkApiException;
import net.quike.examples.model.Bars;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class represents a repository to obtain data from a downstream service. In this case it
 * assumes a REST service where Base Url is defined in the properties, under an specific resource
 * path. We assume Repository naming as it would be used for a datalayer like a DB, and in that case
 * we could potentially use JPA definitions and rely on JPA Repositories.
 */
@Log4j2
public class BarRepository extends CommonRepository {
  private final ApplicationProperties properties;
  private final RestTemplate restTemplate;
  private final String resourcePath;

  public BarRepository(ApplicationProperties properties, RestTemplate restTemplate) {
    this.properties = properties;
    this.restTemplate = restTemplate;
    this.resourcePath = properties.getResourcePaths().barPath();
  }

  /**
   * Returns {@link Bars} from downstream service based on request details
   *
   * @return {@link Bars}
   * @throws SdkApiException Under any downstream error.
   */
  @Cacheable("getBars")
  public Bars getBars() throws SdkApiException {
    ResponseEntity<Bars> responseEntity;
    try {
      responseEntity = getResponse();
    } catch (SdkApiException e) {
      throw e;
    } catch (HttpClientErrorException e) {
      if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
        throw new SdkApiException("Not found");
      }
      throw new SdkApiException("Api Error");
    } catch (Exception e) {
      throw new SdkApiException("Service Error");
    }
    if (!responseEntity.hasBody()) {
      throw new SdkApiException("Empty response error.");
    }
    return responseEntity.getBody();
  }

  private ResponseEntity<Bars> getResponse() {
    var requestUri = buildRequestUri();
    log.info("Request URI: {}", requestUri);
    return restTemplate.getForEntity(requestUri, Bars.class);
  }

  private URI buildRequestUri() {
    if (StringUtils.isBlank(properties.getBaseUrl())) {
      throw new SdkApiException("Not base url found");
    }

    var uriBuilder =
        UriComponentsBuilder.fromUriString(StringUtils.join(properties.getBaseUrl(), resourcePath))
            .queryParam(COMMON_PARAM, properties.getCommonParam());

    return uriBuilder.build().toUri();
  }
}
