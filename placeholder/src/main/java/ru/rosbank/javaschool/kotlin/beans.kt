package ru.rosbank.javaschool.kotlin

// после компиляции превратиться в BeansKt.class
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.beans
import org.springframework.core.io.ClassPathResource
import org.sqlite.SQLiteDataSource

val beans = beans {
    bean {
        PropertySourcesPlaceholderConfigurer().apply {
            // не всё так гладко в Java <-> Kotlin, здесь нужно явно вызывать set
            setLocation(ClassPathResource("db.properties"))
        }
    }
    bean<SQLiteDataSource>("dbsource")
    bean<KotlinConnector>("connector")
}
