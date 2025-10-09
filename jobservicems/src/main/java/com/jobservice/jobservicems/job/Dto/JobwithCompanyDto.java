package com.jobservice.jobservicems.job.Dto;

import com.jobservice.jobservicems.job.Job;
import com.jobservice.jobservicems.job.external.Company;

public class JobwithCompanyDto {

    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
