package io.pivotal.pal.tracker.controllers;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.repositories.TimeEntryRepository;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class TimeEntriesController {
    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntryRepository;

    public TimeEntriesController(TimeEntryRepository timeEntryRepository,
                                 CounterService counter,
                                 GaugeService gauge) {
        this.timeEntryRepository = timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }

    @GetMapping("/timeEntries")
    public ResponseEntity list() {
        counter.increment("TimeEntry.listed");
        return ok(timeEntryRepository.list());
    }

    @GetMapping("/timeEntries/{id}")
    public ResponseEntity read(@PathVariable("id") Long id) {
        Optional<TimeEntry> timeEntry = Optional.ofNullable(timeEntryRepository.get(id));

        if (timeEntry.isPresent()) {
            counter.increment("TimeEntry.read");
            return ok(timeEntry.get());
        } else {
            return notFound().build();
        }
    }

    @PostMapping("/timeEntries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return status(201).body(createdTimeEntry);
    }

    @PutMapping("/timeEntries/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TimeEntry timeEntry) {
        Optional<TimeEntry> existingTimeEntry = Optional.ofNullable(timeEntryRepository.update(id, timeEntry));

        if(existingTimeEntry.isPresent()) {
            counter.increment("TimeEntry.updated");
            return ok(existingTimeEntry.get());
        } else {
            return notFound().build();
        }
    }

    @DeleteMapping("/timeEntries/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return noContent().build();
    }
}
