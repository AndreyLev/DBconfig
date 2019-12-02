package ru.rosbank.javaschool.java;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;

@Configuration
@ComponentScan("ru.rosbank.javaschool")
public class JavaConfig {
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    val configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocation(new ClassPathResource("db.properties"));
    return configurer;
  }

  @Bean
  public SQLiteDataSource dataSource() {
    return new SQLiteDataSource();
  }

  @Bean
  public JavaDataBaseConnector dataBaseConnector(SQLiteDataSource dataSource,
                                                 @Value("${db.url}") String url,
                                                 @Value("${db.username}") String username,
                                                 @Value("${db.password}") String password) {
    return new JavaDataBaseConnector(dataSource(), url, username, password);
  }

}
