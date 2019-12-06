package ru.rosbank.javaschool.programmatic;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammaticConnectorTest {

    @Test
    public void shouldReturnTrueUrl() {

        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        context.registerBean("datasource", SQLiteDataSource.class, bd -> {
            bd.getPropertyValues().addPropertyValue("url", "${db.url}");
        });
        context.registerBean("connector", ProgrammaticConnector.class, new RuntimeBeanReference("datasource"), "${db.url}", "${db.username}", "${db.password}");
        context.refresh();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");

        String result = connector.getUrl();
        String expectedResult = "jdbc:sqlite:C:\\Projects\\DBconfig\\db.sqlite";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTrueUser() {

        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        context.registerBean("datasource", SQLiteDataSource.class, bd -> {
            bd.getPropertyValues().addPropertyValue("url", "${db.url}");
        });
        context.registerBean("connector", ProgrammaticConnector.class, new RuntimeBeanReference("datasource"), "${db.url}", "${db.username}", "${db.password}");
        context.refresh();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");

        String result = connector.getUsername();
        String expectedResult = "admin";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTruePassword() {

        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        context.registerBean("datasource", SQLiteDataSource.class, bd -> {
            bd.getPropertyValues().addPropertyValue("url", "${db.url}");
        });
        context.registerBean("connector", ProgrammaticConnector.class, new RuntimeBeanReference("datasource"), "${db.url}", "${db.username}", "${db.password}");
        context.refresh();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");

        String result = connector.getPassword();
        String expectedResult = "12345678";

        assertEquals(expectedResult, result);
    }

    @Test
    void connectsToTheDataBaseByLoginAndPassword() throws SQLException {

        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        context.registerBean("datasource", SQLiteDataSource.class, bd -> {
            bd.getPropertyValues().addPropertyValue("url", "${db.url}");
        });
        context.registerBean("connector", ProgrammaticConnector.class, new RuntimeBeanReference("datasource"), "${db.url}", "${db.username}", "${db.password}");
        context.refresh();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");

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