package ru.rosbank.javaschool.java;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Getter
@Setter
public class JavaDataBaseConnector {

    private SQLiteDataSource dataSource;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    public JavaDataBaseConnector(SQLiteDataSource dataSource) {
        this.dataSource = dataSource;
    }

    JavaDataBaseConnector() {
    }

    public DataSource doSQL() throws SQLException {

        dataSource.setUrl(url);
        try (Connection con = dataSource.getConnection(url, username)) {
            System.out.println("Мы успешно подключились к базе данных");
            return dataSource;
        }
    }

}
