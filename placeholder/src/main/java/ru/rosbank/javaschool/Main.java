package ru.rosbank.javaschool;

import lombok.val;
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
import ru.rosbank.javaschool.java.JavaDataBaseConnector;
import ru.rosbank.javaschool.kotlin.BeansKt;
import ru.rosbank.javaschool.kotlin.KotlinConnector;
import ru.rosbank.javaschool.programmatic.ProgrammaticConnector;
import ru.rosbank.javaschool.xml.DataBaseConnector;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        {
            val context = new ClassPathXmlApplicationContext("context.xml");
            DataBaseConnector connector = context.getBean("dbconnector", DataBaseConnector.class);
            val dataSource = connector.doSQL();
        }
        {
            val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.annotation");
            val connector = (AnnotationConnector) context.getBean("dbconnector");
            val dataSource = connector.doSQL();
        }
        {
            val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.java");
            JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);
            val dataSource = connector.doSQL();
        }
        {
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
        }
        {
            val context = new GenericGroovyApplicationContext("context.groovy");
            GroovyConnector connector = (GroovyConnector) context.getBean("connector");
            val dataSource = connector.doSQL();
        }
        {
            val factory = new DefaultListableBeanFactory();
            factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
            val context = new GenericApplicationContext(factory);
            BeansKt.getBeans().initialize(context);
            context.refresh();
            KotlinConnector connector = (KotlinConnector) context.getBean("connector");
            val dataSource = connector.doSQL();

        }
    }
}
