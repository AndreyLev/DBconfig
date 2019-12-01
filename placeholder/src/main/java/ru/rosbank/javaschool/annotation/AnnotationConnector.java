package ru.rosbank.javaschool.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("dbconnector")
public class AnnotationConnector {

    @Autowired
    private SQLiteDataSource dataSource;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    public DataSource doSQL() throws SQLException {

        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection(username, password)) {
            System.out.println("Мы успешно подключились к базе данных");
            return dataSource;
        }
    }
}
