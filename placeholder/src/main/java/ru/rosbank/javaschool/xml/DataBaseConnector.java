package ru.rosbank.javaschool.xml;

import lombok.*;
import org.sqlite.SQLiteDataSource;

@Getter
@AllArgsConstructor
public class DataBaseConnector {

    private SQLiteDataSource dataSource;
    private String username;
    private String password;

}
