package net.quike.examples.controller;

import lombok.extern.log4j.Log4j2;
import net.quike.examples.model.Foos;
import net.quike.examples.service.FooService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Validated
@RestController
@RequestMapping(value = "/v1/foos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FooController {

  private final FooService fooService;

  public FooController(FooService fooService) {
    this.fooService = fooService;
  }

  @GetMapping
  public ResponseEntity<Foos> getFoos() {
    Foos foos = fooService.getFoos();

    if (CollectionUtils.isEmpty(foos.getFoos())) {
      log.error("Unable to get data");
      return null;
    }
    return ResponseEntity.ok(foos);
  }
}
