package dev.isaac.tests.springjdbc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import dev.isaac.tests.springjdbc.domain.ConnectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DatasourceConfig {

    private ConnectionDetails connectionDetails;

    public DatasourceConfig(ObjectMapper objectMapper, @Value("${db-properties.file}") String dbPropertiesFile) {
        try {
            this.connectionDetails = objectMapper.readValue(new ClassPathResource(dbPropertiesFile).getFile(), ConnectionDetails.class);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    @Bean
    DataSource dataSource() {
        var datasource = new HikariDataSource();
        datasource.setJdbcUrl(connectionDetails.parseConnectionString());
        datasource.setUsername(connectionDetails.getUsername());
        datasource.setPassword(connectionDetails.getPassword());
        datasource.setDriverClassName(connectionDetails.getDriverClassName());
        return datasource;
    }


    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
