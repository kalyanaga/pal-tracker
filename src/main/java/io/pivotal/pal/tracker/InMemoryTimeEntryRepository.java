package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long,TimeEntry> timeEntries = new HashMap<>();
    /**
     * @param timeEntryId
     * @return
     */
    @Override
    public TimeEntry find(long timeEntryId) {

       return  timeEntries.get(timeEntryId);

    }

    /**
     * @param timeEntry
     */
    @Override
    public  TimeEntry  create(TimeEntry timeEntry) {
        System.out.print("11111111111111111111"+timeEntry);
        timeEntry.setId((timeEntries.size())+1);

        timeEntries.put(timeEntry.getId(),timeEntry);
        System.out.print("11111111111111111111"+timeEntries);
        return timeEntry;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry dupTimeEntry =  timeEntries.get(timeEntryId);
        if(dupTimeEntry!=null) {
            timeEntry.setId(timeEntryId);
            timeEntries.put(timeEntryId, timeEntry);
        }else{
            return null;
        }
        return timeEntry;
    }

    @Override
    public TimeEntry delete(long timeEntryId) {
        System.out.println("111"+timeEntryId);
        TimeEntry timeEntry = timeEntries.get(timeEntryId);
        if(timeEntry!=null) {
            System.out.println("222"+timeEntries);
            timeEntries.remove(timeEntryId);
            System.out.print("333"+timeEntries);
            return timeEntry;
        }
        return null;

    }

    @Override
   public List list(){
        return Arrays.asList(timeEntries.values().toArray());
    }
}
