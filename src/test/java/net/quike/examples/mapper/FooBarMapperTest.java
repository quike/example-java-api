package net.quike.examples.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import net.quike.examples.config.ApplicationJacksonBeanFactory;
import net.quike.examples.model.Bars;
import net.quike.examples.model.Foo;
import net.quike.examples.model.Foos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FooBarMapperTest {
  private static final ObjectMapper objectMapper = ApplicationJacksonBeanFactory.initObjectMapper();

  @BeforeAll
  public static void setup() {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  void shouldMap() throws IOException {
    byte[] data =
        Files.readAllBytes(new File("src/test/resources/data/mapper-dataset.json").toPath());
    String parsedData = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(data)).toString();
    Bars bars = objectMapper.readValue(parsedData, Bars.class);
    Foos foos = FooBarMapper.MAPPER.map(bars);
    assertNotNull(foos);
    assertFalse(foos.getFoos().isEmpty());
    for (Foo foo : foos.getFoos()) {
      assertNotNull(foo.getRandomDate());
    }
  }
}
