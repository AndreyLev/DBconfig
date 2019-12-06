package ru.rosbank.javaschool.java;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JavaDataBaseConnectorTest {

    @Test
    public void shouldReturnTrueUrl() {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.java");
        JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);

        String result = connector.getUrl();
        String expectedResult = "jdbc:sqlite:C:\\Projects\\DBconfig\\db.sqlite";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTrueUser() {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.java");
        JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);

        String result = connector.getUsername();
        String expectedResult = "admin";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTruePassword() {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.java");
        JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);

        String result = connector.getPassword();
        String expectedResult = "12345678";

        assertEquals(expectedResult, result);
    }

    @Test
    void connectsToTheDataBaseByLoginAndPassword() throws SQLException {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.java");
        JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);

        val dataSource = connector.doSQL();
        val username = connector.getUsername();
        val password = connector.getPassword();
        boolean result;
        try (val conn = dataSource.getConnection(username, password)) {
            result = true;
        }

        assertTrue(result);
    }
}