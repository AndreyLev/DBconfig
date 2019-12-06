package ru.rosbank.javaschool.programmatic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sqlite.SQLiteDataSource;

@AllArgsConstructor
@Getter
public class ProgrammaticConnector {

    private SQLiteDataSource dataSource;
    private String username;
    private String password;
}

