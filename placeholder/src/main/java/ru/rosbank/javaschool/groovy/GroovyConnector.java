package ru.rosbank.javaschool.groovy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroovyConnector {

    private SQLiteDataSource dataSource;
    private String url;
    private String username;
    private String password;


    public SQLiteDataSource doSQL() throws SQLException {

        dataSource.setUrl(url);
        try (Connection con = dataSource.getConnection(username, password)) {
            System.out.println("Мы успешно подключились к базе данных");
            return dataSource;
        }
    }
}

