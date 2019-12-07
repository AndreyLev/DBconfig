package ru.rosbank.javaschool.annotation;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Getter
@Component("dbconnector")
public class AnnotationConnector{

    private final DataSource dataSource;
    private final String username;
    private final String password;

    public AnnotationConnector(DataSource dataSource,
                               @Value("${db.username}") String username,
                               @Value("${db.password}") String password) {
            this.dataSource = dataSource;
            this.username = username;
            this.password = password;
        }

}
