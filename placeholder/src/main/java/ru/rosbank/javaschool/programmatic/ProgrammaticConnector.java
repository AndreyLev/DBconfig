package ru.rosbank.javaschool.programmatic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

@AllArgsConstructor
@Getter
@Setter
public class ProgrammaticConnector {

    private SQLiteDataSource dataSource;
    private String username;
    private String password;
}

