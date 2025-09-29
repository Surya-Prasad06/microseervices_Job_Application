package com.companyservice.companyservicems.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.companyservice.companyservicems.company.Company;
import com.companyservice.companyservicems.company.CompanyRepository;
import com.companyservice.companyservicems.company.CompanyService;

@Service
public class CompanyImplement implements CompanyService {

    private CompanyRepository companyrepo;

    public CompanyImplement(CompanyRepository companyrepo) {
        this.companyrepo = companyrepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyrepo.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyoptional = companyrepo.findById(id);
        if (companyoptional.isPresent()) {
            Company companyupdate = companyoptional.get();
            companyupdate.setName(company.getName());
            companyupdate.setDescription(company.getDescription());
            
            companyrepo.save(companyupdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyrepo.save(company);
    }

    @Override
    public boolean deletebyid(Long id) {
        if (companyrepo.existsById(id)) {
            companyrepo.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public Company getCompanyById(Long id) {
       return companyrepo.findById(id).orElse(null);
    }

}
