package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

    this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

        System.out.print(timeEntry);

        timeEntry = timeEntryRepository.create(timeEntry);
        System.out.print(timeEntry);
        return new ResponseEntity<>(  timeEntry,HttpStatus.CREATED);
    }


    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {

      TimeEntry timeEntry =  timeEntryRepository.find(timeEntryId);
      if (timeEntry!=null){
          return new ResponseEntity<>(  timeEntry,HttpStatus.OK);
      }
        return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        return  new ResponseEntity<>( timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable  long timeEntryId,@RequestBody TimeEntry expected) {

       TimeEntry dupEntry = timeEntryRepository.update(timeEntryId,expected);
       if(dupEntry!=null)
        return  new ResponseEntity<>(expected, HttpStatus.OK);

        return  new ResponseEntity<>(expected, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
       TimeEntry timeEntry = timeEntryRepository.delete(timeEntryId);


       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
