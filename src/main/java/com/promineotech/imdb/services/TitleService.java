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
   * Gets a title by it's unique identifier.
   * @param id The unique identifier.
   * @return The title if found, otherwise returns null.
   */
  TitleModel get(String id);
}
