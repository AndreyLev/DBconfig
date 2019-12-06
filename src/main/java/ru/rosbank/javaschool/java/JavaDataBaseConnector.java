package ru.rosbank.javaschool.java;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.sql.DataSource;

@AllArgsConstructor
@Getter
public class JavaDataBaseConnector {

    private final DataSource dataSource;
    private final String username;
    private final String password;

}
