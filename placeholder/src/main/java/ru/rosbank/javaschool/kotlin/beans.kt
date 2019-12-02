package ru.rosbank.javaschool.kotlin


import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.beans
import org.springframework.core.io.ClassPathResource
import org.sqlite.SQLiteDataSource

val beans = beans {
    bean {
        PropertySourcesPlaceholderConfigurer().apply {
            setLocation(ClassPathResource("db.properties"))
        }
    }
    bean<SQLiteDataSource>("dbsource")
    bean<KotlinConnector>("connector")
}
