package net.quike.examples.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Foo implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
  @EqualsAndHashCode.Include private String name;
  @EqualsAndHashCode.Include private LocalDate randomDate;
}
