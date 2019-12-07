package ru.rosbank.javaschool.groovy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sqlite.SQLiteDataSource;


@AllArgsConstructor
@Getter
public class GroovyConnector {

    private final SQLiteDataSource dataSource;
    private final String username;
    private final String password;

}

