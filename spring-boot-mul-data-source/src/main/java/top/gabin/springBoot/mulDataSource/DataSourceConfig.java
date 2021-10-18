package top.gabin.springBoot.mulDataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource probationDataSource(ProbationDataSourceProperties properties) {
        return DataSourceBuilder.create()
                .username(properties.getUsername())
                .password(properties.getPassword())
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource.questionnaire", name = "enabled", havingValue = "true")
    public DataSource questionnaireDataSource(ProbationDataSourceProperties properties) {
        return DataSourceBuilder.create()
                .username(properties.getUsername())
                .password(properties.getPassword())
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .build();
    }

    @Bean(name = "routeDataSource")
    @Primary
    public RouteDataSource dataSource(DataSource probationDataSource, DataSource questionnaireDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.PROBATION.name(), probationDataSource);
        targetDataSources.put(DataSourceType.QUESTIONNAIRE.name(), questionnaireDataSource);
        return new RouteDataSource(probationDataSource, targetDataSources);
    }
}