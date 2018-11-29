package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    /**
     *
     * @param timeEntryId
     * @return
     */

    TimeEntry find(long timeEntryId);

    /**
     *
     * @param timeEntry
     */
    TimeEntry  create(TimeEntry timeEntry);

    TimeEntry update(long timeEntryId , TimeEntry timeEntry);

    TimeEntry delete(long timeEntryId);

    List<TimeEntry> list();

}
