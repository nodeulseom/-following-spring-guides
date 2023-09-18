package springdemo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

//@TestConfiguration(proxyBeanMethods = false)
//public class TestBootifulApplication {
//
//    @Bean
//    @ServiceConnection
//    PostgreSQLContainer<?> postgresContainer() {
//        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.from(BootifulApplication::main).with(TestBootifulApplication.class).run(args);
//    }
//
//}

@Configuration
public class TestServiceApplication {

    @Bean
    @RestartScope
    @ServiceConnection
    PostgreSQLContainer postgreSQLContainer() {
        return new PostgreSQLContainer("postgres:15");
    }

    public static void main(String[] args) {
        SpringApplication
                .from(ServiceApplication::main)
                .with(TestServiceApplication.class)
                .run(args);
    }
}
