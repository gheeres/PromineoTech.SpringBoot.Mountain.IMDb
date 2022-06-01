package com.promineotech.imdb.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.imdb.models.TitleModel;
import com.promineotech.imdb.services.TitleService;

@RestController
public class TitleController {
  private final int MAX_ITEMS_PER_REQUEST = 500;
  //@Autowired
  private TitleService service;
  
  public TitleController(TitleService service) {
    this.service = service;
  }
  
  @RequestMapping(value="/titles")
  public List<TitleModel> all() {
    return service.all(MAX_ITEMS_PER_REQUEST);
  }

  // Path vs QueryString
  //          ?name=value
  @RequestMapping(value="/titles/{id}")
  public TitleModel get(@PathVariable String id) {
    if (! id.startsWith("tt") ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request id was not valid. Must start with tt.");
    }
    
    TitleModel title = service.get(id);
    if (title != null) {
      return title;
    }
    
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested title was not found");
  }
}
