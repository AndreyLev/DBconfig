package ru.rosbank.javaschool.xml;

import lombok.*;
import org.sqlite.SQLiteDataSource;

@Getter
@AllArgsConstructor
public class DataBaseConnector {

    private final SQLiteDataSource dataSource;
    private final String username;
    private final String password;

}
