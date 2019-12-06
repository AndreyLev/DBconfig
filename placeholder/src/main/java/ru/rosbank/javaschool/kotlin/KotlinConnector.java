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
  private String username;
  private String password;

  public KotlinConnector(SQLiteDataSource dataSource, @Value("${db.username}") String username,@Value("${db.password}") String password) {
    this.dataSource = dataSource;
    this.username = username;
    this.password = password;
  }

}

