package io.pivotal.pal.tracker.controllers;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TimeEntriesController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntriesController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping("/timeEntries")
    public ResponseEntity list() {
        return ok(timeEntryRepository.list());
    }

    @GetMapping("/timeEntries/{id}")
    public ResponseEntity read(@PathVariable("id") Long id) {
        Optional<TimeEntry> timeEntry = Optional.ofNullable(timeEntryRepository.get(id));
        return timeEntry.isPresent() ? ok(timeEntry.get()) : notFound().build();
    }

    @PostMapping("/timeEntries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        return status(201).body(timeEntryRepository.create(timeEntry));
    }

    @PutMapping("/timeEntries/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TimeEntry timeEntry) {
        Optional<TimeEntry> existingTimeEntry = Optional.ofNullable(timeEntryRepository.update(id, timeEntry));

        return existingTimeEntry.isPresent() ? ok().body(existingTimeEntry.get()) : notFound().build();
    }

    @DeleteMapping("/timeEntries/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        timeEntryRepository.delete(id);
        return noContent().build();
    }
}
