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
   * Returns all the titles that match the specified name.
   * @param name The optional name of the title to search or filter on.
   * @param limit The maximum number of titles to return.
   * @return A list of all the titles that match or contain the specified name.
   */
  Stream<TitleModel> search(String name, int limit);

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
  
  /**
   * Saves the specified title to the backend datasource.
   * @param id The existing unique identifier of the title.
   * @param title The title to save.
   * @return The title if successful, otherwise an empty optional.
   */
  Optional<TitleModel> save(String id, TitleModel title);

  /**
   * Remove an existing title.
   * @param id The unique id of the title to remove.
   * @return The removed / existing title if successful, otherwise returns an empty optional.
   */
  Optional<TitleModel> remove(String id);
}
