package ru.rosbank.javaschool.annotation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Getter
@Setter
@Component("dbconnector")
public class AnnotationConnector {

    @Autowired
    @Qualifier("dataSource")
    private SQLiteDataSource dataSource;
    private String url;
    private String username;
    private String password;

    public AnnotationConnector(@Value("${db.url}") String url,
                               @Value("${db.username}") String username,
                               @Value("${db.password}") String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }






    public DataSource doSQL() throws SQLException {

        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection(username, password)) {
            System.out.println("Мы успешно подключились к базе данных");
            return dataSource;
        }
    }
}
