package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<>();
    private long idCounter = 1;

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(idCounter++);
        timeEntryHashMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntryHashMap.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>(timeEntryHashMap.values());
        return timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (find(id) == null) return null;
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
            );
        timeEntryHashMap.replace(id, updatedEntry);
        return updatedEntry;
    }

    public void delete(long id) {
        timeEntryHashMap.remove(id);
    }
}
