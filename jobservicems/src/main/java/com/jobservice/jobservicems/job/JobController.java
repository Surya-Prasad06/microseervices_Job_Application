package com.jobservice.jobservicems.job;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobservice.jobservicems.job.Dto.JobwithCompanyDto;

import java.util.List;



@RestController
@RequestMapping("/jobs")
public class JobController {
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobwithCompanyDto>> findAll() {
        return ResponseEntity.ok(jobService.findALL()); // return jobs;
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return new ResponseEntity<>("Job Added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {

        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<Job>(job, HttpStatus.OK);
        }
        return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deletebyid(id);

        if (deleted) {
            return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatejob(@PathVariable Long id, @RequestBody Job updatedjob) {
        boolean updated = jobService.updatejob(id, updatedjob);
        if (updated) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

}
