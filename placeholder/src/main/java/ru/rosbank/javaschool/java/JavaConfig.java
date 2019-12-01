package ru.rosbank.javaschool.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.sqlite.SQLiteDataSource;

@Configuration
@ComponentScan("ru.rosbank.javaschool")
@PropertySource("classpath:db.properties")
public class JavaConfig {
  @Bean
  public SQLiteDataSource dataSource() {
    return new SQLiteDataSource();
  }
  @Bean
  public JavaDataBaseConnector dataBaseConnector() {
    return new JavaDataBaseConnector(dataSource());
  }

}
