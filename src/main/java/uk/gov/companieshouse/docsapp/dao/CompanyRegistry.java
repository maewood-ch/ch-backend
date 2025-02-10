package uk.gov.companieshouse.docsapp.dao;

import uk.gov.companieshouse.docsapp.model.company.Company;

import java.util.List;

// a contract that defines methods that a company registry must use, but not the details of how those methods are
// implemented. In this app, we define an InMemoryCompanyRegistry which implements this interface to handle in memory
// (i.e. local) data. We also define a JpaCompanyRegistry which does the same for persisted prod data using the JPA.
public interface CompanyRegistry {
    enum Sort { NAME, DATE, NUMBER }
    enum Type { FOREIGN, LTD, LLP, NONPROFIT }
    default List<Company> getCompanies() {
        return getCompanies(null);
    }
    default List<Company> getCompanies(Sort sortBy) {
        return getCompanies(null, null, null, null, sortBy);
    }
    List<Company> getCompanies(String namePattern, Integer yearOfIncorporation, Boolean activeStatus, Type companyType, Sort sortBy);
    Company getCompany(String number);
    void deleteCompany(String number);
    Company addCompany(Company data);
    void editCompany(String number, Company data);
    void patchCompany(String number, Company data);
}
