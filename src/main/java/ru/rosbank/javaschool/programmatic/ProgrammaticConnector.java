package ru.rosbank.javaschool.programmatic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sqlite.SQLiteDataSource;

@AllArgsConstructor
@Getter
public class ProgrammaticConnector {

    private final SQLiteDataSource dataSource;
    private final String username;
    private final String password;
}

