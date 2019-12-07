package ru.rosbank.javaschool.kotlin;

import lombok.*;
import org.sqlite.SQLiteDataSource;
import org.springframework.beans.factory.annotation.Value;



@Getter
public class KotlinConnector {

  private final SQLiteDataSource dataSource;
  private final String username;
  private final String password;

  public KotlinConnector(SQLiteDataSource dataSource, @Value("${db.username}") String username,@Value("${db.password}") String password) {
    this.dataSource = dataSource;
    this.username = username;
    this.password = password;
  }

}

