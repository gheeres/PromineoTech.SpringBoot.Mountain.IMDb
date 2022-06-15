package com.promineotech.imdb.services;

import java.util.List;
import com.promineotech.imdb.models.TitleModel;

public interface TitleService {
  /**
   * Returns all the titles.
   * @param limit The maximum number of titles to return.
   * @return A list of all the titles.
   */
  List<TitleModel> all(int limit);
  
  /**
   * Returns all the titles.
   * @param limit The maximum number of titles to return.
   * @param name The optional name to search or limit results for.
   * @return A list of all the titles.
   */
  List<TitleModel> all(String name, int limit);
  
  /**
   * Gets a title by it's unique identifier.
   * @param id The unique identifier.
   * @return The title if found, otherwise returns null.
   */
  TitleModel get(String id);
  
  /**
   * Creates a new title.
   * @param newTitle The new title information.
   * @return The created title, or null if failed.
   */
  TitleModel create(TitleModel newTitle);
  
  /**
   * Updates or modifies an existing title.
   * @param id The existing id of the title.
   * @param updatedTitle The updated title information/
   * @return The updated title if successful, otherwise returns null.
   */
  TitleModel update(String id, TitleModel updatedTitle);
  
  /**
   * Deletes an existing title.
   * @param id The existing id of the title.
   * @return The removed title if successful, otherwise returns null.
   */
  TitleModel delete(String id);
}
