package com.jobservice.jobservicems.job.implementaion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobservice.jobservicems.job.Job;
import com.jobservice.jobservicems.job.JobRepository;
import com.jobservice.jobservicems.job.JobService;
import com.jobservice.jobservicems.job.external.Company;

@Service
public class JobServiceImplementation implements JobService {
    JobRepository jobrepo;

    public JobServiceImplementation(JobRepository jobrepo) {
        this.jobrepo = jobrepo;
    }

    @Override
    public List<Job> findALL() {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:5002/companies/1", Company.class);
        System.out.println("Company Name :- "+ company.getName());
        return jobrepo.findAll();
    }

    @Override
    public void createJob(Job job) {

        jobrepo.save(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobrepo.findById(id).orElse(null);
    }

    @Override
    public boolean deletebyid(Long id) {
        try {
            jobrepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatejob(Long id, Job updatedjob) {
        Optional<Job> joboptional = jobrepo.findById(id);
        if (joboptional.isPresent()) {
            Job job = joboptional.get();
            job.setJobTtitle(updatedjob.getJobTtitle());
            job.setDescription(updatedjob.getDescription());
            job.setLocation(updatedjob.getLocation());
            job.setMaxSalary(updatedjob.getMaxSalary());
            job.setMinSalary(updatedjob.getMinSalary());
            jobrepo.save(job);
            return true;
        }
        return false;

    }

}
