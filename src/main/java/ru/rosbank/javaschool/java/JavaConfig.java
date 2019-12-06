package ru.rosbank.javaschool.java;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

public class JavaConfig {
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    val configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocation(new ClassPathResource("db.properties"));
    return configurer;
  }

  @Bean
  public DataSource dataSource(@Value("${db.url}")String url) {
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl(url);
    return dataSource;
  }

  @Bean
  public JavaDataBaseConnector dataBaseConnector(DataSource sqliteDataSource,
                                                 @Value("${db.username}") String username,
                                                 @Value("${db.password}") String password) {
    return new JavaDataBaseConnector(sqliteDataSource, username, password);
  }

}
