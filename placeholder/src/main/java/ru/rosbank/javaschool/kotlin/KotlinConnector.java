package ru.rosbank.javaschool.kotlin;

import lombok.*;
import org.sqlite.SQLiteDataSource;
import org.springframework.beans.factory.annotation.Value;
import java.sql.Connection;
import java.sql.SQLException;


@Getter
@Setter
public class KotlinConnector {

  private SQLiteDataSource dataSource;
  private String url;
  private String username;
  private String password;

  public KotlinConnector(SQLiteDataSource dataSource,@Value("${db.url}")  String url, @Value("${db.username}") String username,@Value("${db.password}") String password) {
    this.dataSource = dataSource;
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public SQLiteDataSource doSQL() throws SQLException {

    dataSource.setUrl(url);
    try (Connection con = dataSource.getConnection(username, password)) {
      System.out.println("Мы успешно подключились к базе данных");
      return dataSource;
    }
  }
}

