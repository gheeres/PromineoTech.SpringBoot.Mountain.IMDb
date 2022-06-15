package com.promineotech.imdb.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.promineotech.imdb.models.TitleModel;
import com.promineotech.imdb.repositories.TitleRepository;

@Service
public class DefaultTitleService implements TitleService {
  private final int MAX_ITEMS_PER_REQUEST = 500;
  
  //@Autowired
  private TitleRepository repository;
  
  public DefaultTitleService(TitleRepository repository) {
    this.repository = repository;
  }
  
  @Override
  public List<TitleModel> all(int limit) {
    if (limit <= 0) {
      return Collections.emptyList();
    }
    if (limit > MAX_ITEMS_PER_REQUEST) {
      limit = MAX_ITEMS_PER_REQUEST;
    }
    
    return repository.all(limit)
                     .collect(Collectors.toList());
  }
  

  @Override
  public List<TitleModel> all(String name, int limit) {
    if ((name == null) || (name.isEmpty())) {
      return all(limit);
    }
    
    if (limit <= 0) {
      return Collections.emptyList();
    }
    if (limit > MAX_ITEMS_PER_REQUEST) {
      limit = MAX_ITEMS_PER_REQUEST;
    }

    return repository.search(name, limit)
                     .collect(Collectors.toList());
  }  

  @Override
  public TitleModel get(String id) {
    if ((id == null) || (id.isEmpty())) {
      return null;
    }
    
    Optional<TitleModel> title = repository.get(id);
    if (title.isPresent()) {
      return title.get();
    }
    return null;
  }
  
  @Override
  public TitleModel create(TitleModel newTitle) {
    if (! TitleModel.isValid(newTitle)) {
      return null;
    }
    
    Optional<TitleModel> result = repository.save(newTitle);
    if (result.isPresent()) {
      return result.get();
    }
    return null;
  }

  @Override
  public TitleModel update(String id, TitleModel updatedTitle) {
    TitleModel existing = get(id);
    if (existing != null) {
      if (TitleModel.isValid(updatedTitle)) {
        Optional<TitleModel> result = repository.save(id, updatedTitle);
        if (result.isPresent()) {
          return result.get();
        }
      }
    }
    return null;
  }

  @Override
  public TitleModel delete(String id) {
    TitleModel existing = get(id);
    if (existing != null) {
      Optional<TitleModel> result = repository.remove(id);
      if (result.isPresent()) {
        return result.get();
      }
    }
    return null;
  }
}
