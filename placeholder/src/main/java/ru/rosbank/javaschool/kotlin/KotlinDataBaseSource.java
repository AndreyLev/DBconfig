package ru.rosbank.javaschool.kotlin;

import org.springframework.beans.factory.annotation.Value;
import org.sqlite.SQLiteDataSource;

public class KotlinDataBaseSource extends SQLiteDataSource {
    public KotlinDataBaseSource(@Value("${db.url}") String url) {
        setUrl(url);
    }
}
