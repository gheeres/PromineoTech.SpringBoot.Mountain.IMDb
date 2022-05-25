package com.promineotech.imdb.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.imdb.models.TitleModel;

@RestController
public class TitleController {
  @RequestMapping(value="/titles")
  public List<TitleModel> all() {
    return List.of(
        new TitleModel("tt0451279", "Wonder Woman", 2017),
        new TitleModel("tt0903624", "The Hobbit: An Unexpected Journey", 2012)
        );
  }
}
