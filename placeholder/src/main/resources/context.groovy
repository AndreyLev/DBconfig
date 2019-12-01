import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.sqlite.SQLiteDataSource
import ru.rosbank.javaschool.groovy.GroovyConnector

beans {
    propertyPlaceholderConfigurer PropertySourcesPlaceholderConfigurer, {
        location = 'classpath:db.properties'
    }

    source SQLiteDataSource
    connector GroovyConnector, source, '${db.url}', '${db.username}', '${db.password}'
}