package ru.rosbank.javaschool.groovy;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericGroovyApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GroovyConnectorTest {

    @Test
    public void shouldReturnTrueUrl() {
        val context = new GenericGroovyApplicationContext("context.groovy");
        GroovyConnector connector = (GroovyConnector) context.getBean("connector");

        String result = connector.getUrl();
        String expectedResult = "jdbc:sqlite:C:\\Projects\\DBconfig\\db.sqlite";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTrueUser() {
        val context = new GenericGroovyApplicationContext("context.groovy");
        GroovyConnector connector = (GroovyConnector) context.getBean("connector");

        String result = connector.getUsername();
        String expectedResult = "admin";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTruePassword() {
        val context = new GenericGroovyApplicationContext("context.groovy");
        GroovyConnector connector = (GroovyConnector) context.getBean("connector");

        String result = connector.getPassword();
        String expectedResult = "12345678";

        assertEquals(expectedResult, result);
    }

    @Test
    void connectsToTheDataBaseByLoginAndPassword() throws SQLException {
        val context = new GenericGroovyApplicationContext("context.groovy");
        GroovyConnector connector = (GroovyConnector) context.getBean("connector");

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