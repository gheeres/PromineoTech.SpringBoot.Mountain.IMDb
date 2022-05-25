package com.promineotech.imdb.models;

public class TitleModel {
  private String id;
  private String name;
  private int releaseYear;
  
  public TitleModel(String id, String name, int releaseYear) {
    setId(id);
    setName(name);
    setReleaseYear(releaseYear);
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getReleaseYear() {
    return releaseYear;
  }
  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }
}
