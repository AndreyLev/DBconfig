package ru.rosbank.javaschool.xml;

import lombok.*;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Getter
@AllArgsConstructor
public class DataBaseConnector {

    private SQLiteDataSource dataSource;
    private String username;
    private String password;

}
