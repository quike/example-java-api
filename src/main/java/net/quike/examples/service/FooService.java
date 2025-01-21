package net.quike.examples.service;

import lombok.extern.log4j.Log4j2;
import net.quike.examples.mapper.FooBarMapper;
import net.quike.examples.model.Bars;
import net.quike.examples.model.Foos;
import net.quike.examples.repository.BarRepository;

@Log4j2
public class FooService {
  private final BarRepository barRepository;

  public FooService(BarRepository barRepository) {
    this.barRepository = barRepository;
  }

  /**
   * This method returns {@link Foos} mapping received {@link Bars} from the repository
   *
   * @return {@link Foos}
   */
  public Foos getFoos() {
    Bars bars = barRepository.getBars();
    log.info(bars);
    return FooBarMapper.MAPPER.map(bars);
  }
}
