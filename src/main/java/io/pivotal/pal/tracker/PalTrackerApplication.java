package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.repositories.JdbcTimeEntryRepository;
import io.pivotal.pal.tracker.repositories.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public TimeEntryRepository timeEntryRepository(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }
}