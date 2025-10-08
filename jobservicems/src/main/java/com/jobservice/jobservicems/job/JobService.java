package com.jobservice.jobservicems.job;


import java.util.List;

import com.jobservice.jobservicems.Dto.JobwithCompanyDto;

public interface JobService {

    List<JobwithCompanyDto> findALL();


    void  createJob(Job job);

    Job getJobById(Long id);


    boolean deletebyid(Long id);


    boolean updatejob(Long id, Job updatedjob);


    
}
