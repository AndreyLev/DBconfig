package ru.rosbank.javaschool;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;
import ru.rosbank.javaschool.annotation.AnnotationConnector;
import ru.rosbank.javaschool.groovy.GroovyConnector;
import ru.rosbank.javaschool.java.JavaConfig;
import ru.rosbank.javaschool.java.JavaDataBaseConnector;
import ru.rosbank.javaschool.kotlin.BeansKt;
import ru.rosbank.javaschool.kotlin.KotlinConnector;
import ru.rosbank.javaschool.programmatic.ProgrammaticConnector;
import ru.rosbank.javaschool.xml.DataBaseConnector;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationTest {
    private final String url = "jdbc:sqlite:C:\\Projects\\DBconfig\\db.sqlite";
    private final String username = "admin";
    private final String password = "12345678";

    @Test
    void createXmlConnector() {
        val context = new ClassPathXmlApplicationContext("context.xml");

        val connector = context.getBean("dbconnector", DataBaseConnector.class);

        assertEquals(url, ((SQLiteDataSource) connector.getDataSource()).getUrl());
        assertEquals(username, connector.getUsername());
        assertEquals(password, connector.getPassword());
    }

    @Test
    void createAnnotationConnector() {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.annotation");

        val connector = context.getBean("dbconnector", AnnotationConnector.class);

        assertEquals(url, ((SQLiteDataSource) connector.getDataSource()).getUrl());
        assertEquals(username, connector.getUsername());
        assertEquals(password, connector.getPassword());
    }


    @Test
    void createJavaConnector() {
        val context = new AnnotationConfigApplicationContext(JavaConfig.class);

        JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);

        assertEquals(url, ((SQLiteDataSource) connector.getDataSource()).getUrl());
        assertEquals(username, connector.getUsername());
        assertEquals(password, connector.getPassword());
    }


    @Test
    void createProgrammaticConnector() {
        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, cls -> cls.getPropertyValues().addPropertyValue("location", new ClassPathResource("db.properties")));
        context.registerBean("dataSource", SQLiteDataSource.class, db -> db.getPropertyValues().addPropertyValue("url", "${db.url}"));
        context.registerBean("connector", ProgrammaticConnector.class, "${db.username}", "${db.password}", new RuntimeBeanReference("dataSource"));
        context.refresh();

        val connector = context.getBean("connector", ProgrammaticConnector.class);

        assertEquals(url, ((SQLiteDataSource) connector.getDataSource()).getUrl());
        assertEquals(username, connector.getUsername());
        assertEquals(password, connector.getPassword());
    }


    @Test
    void createGroovyConnector() {
        val context = new GenericGroovyApplicationContext("context.groovy");

        val connector = context.getBean("connector", GroovyConnector.class);

        assertEquals(url, ((SQLiteDataSource) connector.getDataSource()).getUrl());
        assertEquals(username, connector.getUsername());
        assertEquals(password, connector.getPassword());
    }

    @Test
    void createKotlinConnector() {
        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();

        val connector = context.getBean("connector", KotlinConnector.class);

        assertEquals(url, ((SQLiteDataSource) connector.getDataSource()).getUrl());
        assertEquals(username, connector.getUsername());
        assertEquals(password, connector.getPassword());
    }

}