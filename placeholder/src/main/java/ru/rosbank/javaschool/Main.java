package ru.rosbank.javaschool;

import lombok.val;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
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

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    {
      val context = new ClassPathXmlApplicationContext("context.xml");
      DataBaseConnector connector = context.getBean("dbconnector", DataBaseConnector.class);
      DataSource datasource = connector.doSQL();
    }
    {
        val context = new ClassPathXmlApplicationContext("scan.xml");
        AnnotationConnector connector = context.getBean("dbconnector", AnnotationConnector.class);
        connector.doSQL();
    }
    {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.java");
        JavaDataBaseConnector connector = context.getBean("dataBaseConnector", JavaDataBaseConnector.class);
        DataSource dataSource = connector.doSQL();
    }
    {
        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });

        SQLiteDataSource dataSource = new SQLiteDataSource();
        context.registerBean("connector", ProgrammaticConnector.class, dataSource, "${db.url}", "${db.username}", "${db.password}");
        context.refresh();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");
        dataSource = connector.doSQL();
    }
    {
      val context = new GenericGroovyApplicationContext("context.groovy");
      GroovyConnector connector = (GroovyConnector) context.getBean("connector");
      connector.doSQL();

    }
    {
      val factory = new DefaultListableBeanFactory();
      factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
      val context = new GenericApplicationContext(factory);
      BeansKt.getBeans().initialize(context);
      context.refresh();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");
        connector.doSQL();
    }
  }
}
