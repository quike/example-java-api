package net.quike.examples.mapper;

import java.util.ArrayList;
import java.util.List;
import net.quike.examples.model.Bars;
import net.quike.examples.model.Foo;
import net.quike.examples.model.Foos;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FooBarMapper {

  FooBarMapper MAPPER = Mappers.getMapper(FooBarMapper.class);

  default Foos map(Bars bars) {
    Foos foos = new Foos();

    List<Foo> results = new ArrayList<>(bars.getBars().size());

    bars.getBars()
        .forEach(
            bar ->
                results.add(
                    Foo.builder()
                        .name(bar.getId() + ":" + bar.getDetail())
                        .randomDate(bar.getAnyDates().getFirst())
                        .build()));

    foos.setFoos(results);
    return foos;
  }
}
