package com.jobservice.jobservicems.job.implementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobservice.jobservicems.job.Dto.JobwithCompanyDto;
import com.jobservice.jobservicems.job.Job;
import com.jobservice.jobservicems.job.JobRepository;
import com.jobservice.jobservicems.job.JobService;
import com.jobservice.jobservicems.job.external.Company;

@Service
public class JobServiceImplementation implements JobService {


    JobRepository jobrepo;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImplementation(JobRepository jobrepo) {
        this.jobrepo = jobrepo;
    }

    @Override
    public List<JobwithCompanyDto> findALL() {

        List<Job> jobs = jobrepo.findAll();
        List<JobwithCompanyDto> jobwithCompanyDtos = new ArrayList<>();

//        RestTemplate restTemplate = new RestTemplate();

        for (Job job : jobs) {
            JobwithCompanyDto jobwithCompanyDto = new JobwithCompanyDto();
            jobwithCompanyDto.setJob(job);
            Company company = restTemplate.getForObject("http://COMPANYSERVICEMS:5002/companies/" + job.getCompanyId(), Company.class);

            jobwithCompanyDto.setCompany(company);
            jobwithCompanyDtos.add(jobwithCompanyDto);
        }

        return jobwithCompanyDtos;
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
