package ru.rosbank.javaschool.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
@Getter
@Setter
public class JavaDataBaseConnector {

    private final SQLiteDataSource dataSource;
    private final String url;
    private final String username;
    private final String password;



    public DataSource doSQL() throws SQLException {

        dataSource.setUrl(url);
        try (Connection con = dataSource.getConnection(url, username)) {
            System.out.println("Мы успешно подключились к базе данных");
            return dataSource;
        }
    }

}
