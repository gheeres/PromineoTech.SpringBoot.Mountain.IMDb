package com.promineotech.imdb.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.promineotech.imdb.models.TitleModel;

@Repository
@Primary
public class JdbcTitleRepository implements TitleRepository {
  //@Autowired
  private NamedParameterJdbcTemplate provider;
  
  public JdbcTitleRepository(NamedParameterJdbcTemplate provider) {
    this.provider = provider;
  }
  
  @Override
  public Stream<TitleModel> all(int limit) {
    String sql = "SELECT title_id,primary_title,start_year FROM title "
               + "LIMIT " + limit + ";";
    
    List<TitleModel> titles = provider.query(sql, new RowMapper<TitleModel>() {
      @Override
      public TitleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("title_id");
        String name = rs.getString("primary_title");
        int releasedYear = rs.getInt("start_year");
        
        return new TitleModel(id, name, releasedYear);
      }
    });

    return titles.stream();
  }

  @Override
  public Optional<TitleModel> get(String id) {
    String sql = "SELECT title_id,primary_title,start_year FROM title\n"
               + "WHERE title_id = :title_id;";
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("title_id", id);
    
    List<TitleModel> titles = provider.query(sql, parameters, new RowMapper<TitleModel>() {
      @Override
      public TitleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TitleModel(rs.getString("title_id"),
                              rs.getString("primary_title"),
                              rs.getInt("start_year"));
      }
    });
    
    if (! titles.isEmpty()) {
      return Optional.of(titles.get(0));
    }

    return Optional.empty();
  }
}
