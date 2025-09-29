
package com.companyservice.companyservicems.company;
import java.util.List;


public interface CompanyService {
    List<Company> getAllCompanies();
    void  createCompany(Company company);
    boolean updateCompany(Company company, Long id);
    boolean deletebyid(Long id);
    Company getCompanyById(Long id);
}
