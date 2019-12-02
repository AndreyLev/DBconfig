package ru.rosbank.javaschool.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@NoArgsConstructor
@Setter
@Getter
public class DataBaseConnector {

    private SQLiteDataSource dataSource;
    private String url;
    private String username;
    private String password;

    public DataBaseConnector(SQLiteDataSource dbsource) {
        this.dataSource = dbsource;
    }

    public DataSource doSQL() throws SQLException {

        dataSource.setUrl(url);
        try (Connection con = dataSource.getConnection(username, password)) {
            System.out.println("Мы успешно подключились к базе данных");
            return dataSource;
        }
    }

}
