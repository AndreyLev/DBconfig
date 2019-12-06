package ru.rosbank.javaschool.groovy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;


@AllArgsConstructor
@Getter
@Setter
public class GroovyConnector {

    private SQLiteDataSource dataSource;
    private String username;
    private String password;

}

