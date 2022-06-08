package com.promineotech.imdb.repositories;

import java.util.Optional;
import java.util.stream.Stream;
import com.promineotech.imdb.models.TitleModel;

public interface TitleRepository {
  /**
   * Returns all the titles.
   * @param limit The maximum number of titles to return.
   * @return A list of all the titles.
   */
  Stream<TitleModel> all(int limit);
  
  /**
   * Gets a title by it's unique identifier.
   * @param id The unique identifier.
   * @return The title if found, otherwise returns null.
   */
  Optional<TitleModel> get(String id);

  /**
   * Saves the specified title to the backend datasource.
   * @param title The title to save.
   * @return The title if successful, otherwise an empty optional.
   */
  Optional<TitleModel> save(TitleModel title);
}
