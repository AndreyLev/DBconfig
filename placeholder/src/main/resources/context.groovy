import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.sqlite.SQLiteDataSource
import ru.rosbank.javaschool.groovy.GroovyConnector

beans {
    propertyPlaceholderConfigurer PropertySourcesPlaceholderConfigurer, {
        location = 'classpath:db.properties'
    }

    dataSource SQLiteDataSource, {
        url = '${db.url}'
    }

    connector GroovyConnector, ref(dataSource), '${db.username}', '${db.password}'
}