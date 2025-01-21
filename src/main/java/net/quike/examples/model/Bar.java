package net.quike.examples.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
public class Bar implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
  @EqualsAndHashCode.Include Integer id;
  @EqualsAndHashCode.Include String detail;
  List<LocalDate> anyDates;
}
