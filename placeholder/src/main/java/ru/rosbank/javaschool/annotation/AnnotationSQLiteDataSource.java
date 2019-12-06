package ru.rosbank.javaschool.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;


@Component("dataSource")
public class AnnotationSQLiteDataSource extends SQLiteDataSource{
    public AnnotationSQLiteDataSource(@Value("${db.url}") String url) {
        setUrl(url);
    }
}
