package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController

public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    private  CounterService counter ;
    private GaugeService gauge ;




    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            CounterService counter,
            GaugeService gauge
    ) {
        this.timeEntryRepository = timeEntriesRepo;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        System.out.print("test before log "+timeEntry);

        timeEntry = timeEntryRepository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());

        return new ResponseEntity<>(  timeEntry,HttpStatus.CREATED);
    }


    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {

      TimeEntry timeEntry =  timeEntryRepository.find(timeEntryId);
      if (timeEntry!=null){
          counter.increment("TimeEntry.read");
          return new ResponseEntity<>(  timeEntry,HttpStatus.OK);
      }
        return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return  new ResponseEntity<>( timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable  long timeEntryId,@RequestBody TimeEntry expected) {

       TimeEntry dupEntry = timeEntryRepository.update(timeEntryId,expected);
       if(dupEntry!=null) {
           counter.increment("TimeEntry.updated");
           return new ResponseEntity<>(dupEntry, HttpStatus.OK);
       }
        return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
       TimeEntry timeEntry = timeEntryRepository.delete(timeEntryId);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());


       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
