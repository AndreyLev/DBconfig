package ru.rosbank.javaschool.kotlin;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class KotlinConnectorTest {

    @Test
    public void shouldReturnTrueUrl() {

        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");

        String result = connector.getUrl();
        String expectedResult = "jdbc:sqlite:C:\\Projects\\DBconfig\\db.sqlite";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTrueUser() {

        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");

        String result = connector.getUsername();
        String expectedResult = "admin";

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTruePassword() {

        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");

        String result = connector.getPassword();
        String expectedResult = "12345678";

        assertEquals(expectedResult, result);
    }

    @Test
    void connectsToTheDataBaseByLoginAndPassword() throws SQLException {

        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");

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