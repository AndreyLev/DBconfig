package ru.rosbank.javaschool.groovy;

import lombok.Getter;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;



@Getter
@Setter
public class GroovyConnector {

    public GroovyConnector(SQLiteDataSource dataSource, String username, String password) {
        this.dataSource = dataSource;
        this.username = username;
        this.password = password;
    }

    private SQLiteDataSource dataSource;
    private String username;
    private String password;

}

