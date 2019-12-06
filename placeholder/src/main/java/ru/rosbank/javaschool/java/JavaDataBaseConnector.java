package ru.rosbank.javaschool.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;

@AllArgsConstructor
@Getter
@Setter
public class JavaDataBaseConnector {

    private final DataSource dataSource;
    private final String username;
    private final String password;

}
