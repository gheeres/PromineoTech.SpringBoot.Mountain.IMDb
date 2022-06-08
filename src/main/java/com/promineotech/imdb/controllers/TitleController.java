package com.promineotech.imdb.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.imdb.models.TitleModel;
import com.promineotech.imdb.services.TitleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@OpenAPIDefinition(info = @Info(title = "IMDb: The Next Generation"))
@Tag(name="Titles")
public class TitleController {
  private final int MAX_ITEMS_PER_REQUEST = 500;
  //@Autowired
  private TitleService service;
  
  public TitleController(TitleService service) {
    this.service = service;  
  }
  
  @Operation(summary = "Get all titles")
  @RequestMapping(value="/titles", method=RequestMethod.GET)
  public List<TitleModel> all() {
    return service.all(MAX_ITEMS_PER_REQUEST);
  }

  // Path vs QueryString
  //          ?name=value
  @Operation(summary ="Gets a title by it's unique id")
  @RequestMapping(value="/titles/{id}", method=RequestMethod.GET)
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
  
  @Operation(summary="Creates a new title")
  @RequestMapping(value= "/titles", method=RequestMethod.POST)
  public TitleModel create(@RequestBody TitleModel title) {

    ///TODO - Add validation, error handling
    
    return service.create(title);
  }
}
