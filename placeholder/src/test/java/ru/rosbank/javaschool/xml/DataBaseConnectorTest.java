package ru.rosbank.javaschool.xml;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseConnectorTest {

    @Test
    public void shouldReturnTrueUrl() {
        val context = new ClassPathXmlApplicationContext("context.xml");
        DataBaseConnector connector = context.getBean("dbconnector", DataBaseConnector.class);

        String result = connector.getUrl();
        String expectedResult = "jdbc:sqlite:C:\\Projects\\DBconfig\\db.sqlite";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTrueUser() {
        val context = new ClassPathXmlApplicationContext("context.xml");
        DataBaseConnector connector = context.getBean("dbconnector", DataBaseConnector.class);

        String result = connector.getUsername();
        String expectedResult = "admin";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTruePassword() {
        val context = new ClassPathXmlApplicationContext("context.xml");
        DataBaseConnector connector = context.getBean("dbconnector", DataBaseConnector.class);

        String result = connector.getPassword();
        String expectedResult = "12345678";

        assertEquals(expectedResult, result);
    }

    @Test
    void connectsToTheDataBaseByLoginAndPassword() throws SQLException {
        val context = new ClassPathXmlApplicationContext("context.xml");
        DataBaseConnector connector = context.getBean("dbconnector", DataBaseConnector.class);

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