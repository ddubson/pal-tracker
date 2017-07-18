package io.pivotal.pal.tracker.indicators;

import io.pivotal.pal.tracker.repositories.TimeEntryRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator  implements HealthIndicator{
    private final TimeEntryRepository timeEntryRepository;
    private final int MAX_ENTRIES = 5;

    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @Override
    public Health health() {
        return timeEntryRepository.list().size() < MAX_ENTRIES ?
                Health.up().build() : Health.down().build();
    }
}
