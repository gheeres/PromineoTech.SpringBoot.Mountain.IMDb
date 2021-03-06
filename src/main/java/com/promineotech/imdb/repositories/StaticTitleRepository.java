package com.promineotech.imdb.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.promineotech.imdb.models.TitleModel;

@Repository
public class StaticTitleRepository implements TitleRepository {
  private static List<TitleModel> titles;
  
  public StaticTitleRepository() {
    initialize();  
  }
  
  private void initialize() {
    titles = List.of(
        new TitleModel("tt0451279", "Wonder Woman", 2017),
        new TitleModel("tt0903624", "The Hobbit: An Unexpected Journey", 2012),
        new TitleModel("tt0458339", "Captain America: The First Avenger", 2011)
        );
  }
  
  @Override
  public Stream<TitleModel> all(int limit) {
    return titles.stream();
  }

  @Override
  public Stream<TitleModel> search(String name, int limit) {
    // TODO: Incomplete - Should use regular expression.
    return titles.stream().filter(title -> title.getName().equalsIgnoreCase(name));
  }
  
  @Override
  public Optional<TitleModel> get(String id) {
    return titles.stream().filter(title -> title.getId().equalsIgnoreCase(id))
                          .findFirst();
  }

  @Override
  public Optional<TitleModel> save(TitleModel title) {
    if (title == null) {
      return Optional.empty();
    }
    
    return save(title.getId(), title);
  }

  @Override
  public Optional<TitleModel> save(String id, TitleModel title) {
    if (title == null) {
      return Optional.empty();
    }
    
    // Look up existing title by id,
    // If existing, update existing values in memory
    // Otherwise add new title.
    titles.add(title);
    return Optional.of(title);
  }

  @Override
  public Optional<TitleModel> remove(String id) {
    return Optional.empty();
  }
}
