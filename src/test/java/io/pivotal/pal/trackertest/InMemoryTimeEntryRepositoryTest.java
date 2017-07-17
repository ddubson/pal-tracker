package io.pivotal.pal.trackertest;

import io.pivotal.pal.tracker.InMemoryTimeEntryRepository;
import io.pivotal.pal.tracker.TimeEntry;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTimeEntryRepositoryTest {
    @Test
    public void create() throws Exception {
        InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry today = new TimeEntry(123, 456, "today", 8);

        TimeEntry createdTimeEntry = repo.create(today);

        today.setId(1L);
        TimeEntry expected = today;
        assertThat(createdTimeEntry).isEqualTo(expected);

        TimeEntry readEntry = repo.get(createdTimeEntry.getId());
        assertThat(readEntry).isEqualTo(expected);
    }

    @Test
    public void get() throws Exception {
        InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry today = new TimeEntry(123, 456, "today", 8);
        repo.create(today);

        today.setId(1L);
        TimeEntry expected = today;
        TimeEntry readEntry = repo.get(1L);
        assertThat(readEntry).isEqualTo(expected);
    }

    @Test
    public void list() throws Exception {
        InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry timeEntry1 = new TimeEntry(123, 456, "today", 8);
        TimeEntry timeEntry2 = new TimeEntry(789, 654, "yesterday", 4);

        repo.create(timeEntry1);
        repo.create(timeEntry2);

        List<TimeEntry> expected = asList(timeEntry1, timeEntry2);
        assertThat(repo.list()).isEqualTo(expected);
    }

    @Test
    public void update() throws Exception {
        InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry today = new TimeEntry(123, 456, "today", 8);
        TimeEntry created = repo.create(today);

        TimeEntry toUpdate = new TimeEntry(321, 654, "tomorrow", 5);
        TimeEntry updatedEntry = repo.update(
                created.getId(), toUpdate);

        toUpdate.setId(created.getId());
        TimeEntry expected = toUpdate;
        assertThat(updatedEntry).isEqualTo(expected);
        assertThat(repo.get(created.getId())).isEqualTo(expected);
    }

    @Test
    public void delete() throws Exception {
        InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry created = repo.create(new TimeEntry(123, 456, "today", 8));

        repo.delete(created.getId());
        assertThat(repo.list()).isEmpty();
    }
}
