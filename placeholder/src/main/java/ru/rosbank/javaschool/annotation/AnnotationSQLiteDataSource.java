package ru.rosbank.javaschool.annotation;

import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;


@Component("dataSource")
public class AnnotationSQLiteDataSource extends SQLiteDataSource{
}
