package uk.gov.companieshouse.docsapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.companieshouse.docsapp.dao.CompanyRegistry;
import uk.gov.companieshouse.docsapp.dao.InMemoryCompanyRegistry;
import uk.gov.companieshouse.docsapp.model.company.Company;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CompaniesController {

    //creating instance of the inMemoryCompanyRegistry - this is instead of a db for now.
    private InMemoryCompanyRegistry inMemoryCompanyRegistry = new InMemoryCompanyRegistry();

    @GetMapping("/companies")
    public List<Company> listCompanies(@RequestParam(required = false)String namePattern, Integer yearOfIncorporation,
                                       Boolean activeState, CompanyRegistry.Type companyType, CompanyRegistry.Sort sortBy) {
        return inMemoryCompanyRegistry.getCompanies(namePattern, yearOfIncorporation, activeState, companyType, sortBy);
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> addCompany(@RequestBody Company newCompany){
        inMemoryCompanyRegistry.addCompany(newCompany);
        return ResponseEntity.ok().body(newCompany);
    }

    @GetMapping("/companies/{companyNumber}")
    public ResponseEntity<Object> getCompany(@PathVariable(required = true) String companyNumber) {
        Company retrievedCompany = inMemoryCompanyRegistry.getCompany(companyNumber);
        if (retrievedCompany == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company " + companyNumber + " not found");
        return ResponseEntity.ok(retrievedCompany);
    }

    @PutMapping("/companies/{companyNumber}")
    public ResponseEntity<Object> updateCompany(@PathVariable(required = true) String companyNumber,
                                                 @RequestBody(required = true) Company data) {
        Company retrievedCompany = inMemoryCompanyRegistry.getCompany(companyNumber);
        // company not found
        if (retrievedCompany == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company " + companyNumber + " not found");
        // bad request (companyNumber and incorporatedOn should be immutable)
        if (!Objects.equals(companyNumber, retrievedCompany.getRegistrationNumber())
        || !Objects.equals(retrievedCompany.getIncorporatedOn(), data.getIncorporatedOn())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You cannot edit registration number or date of incorporation");
        }
        inMemoryCompanyRegistry.editCompany(companyNumber, data);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/companies/{companyNumber}")
    public ResponseEntity<Object> patchCompany(@PathVariable String companyNumber, @RequestBody Map<String, Object> updates) {
        Company retrievedCompany = inMemoryCompanyRegistry.getCompany(companyNumber);

        if (retrievedCompany == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company " + companyNumber + " not found");
        }

        // check for immutable fields and throw badRequest if found
        if (updates.containsKey("registrationNumber") || updates.containsKey("incorporatedOn")) {
            return ResponseEntity.badRequest().body("You cannot edit the registration number or incorporation date");
        }

        // apply the updates dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "companyName":
                    retrievedCompany.setCompanyName((String) value);
                    break;
                case "registeredAddress":
                    retrievedCompany.setRegisteredAddress((String) value);
                    break;
                case "active":
                    retrievedCompany.setActive((Boolean) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        inMemoryCompanyRegistry.editCompany(companyNumber, retrievedCompany);
        return ResponseEntity.ok(retrievedCompany);
    }

    @DeleteMapping("/companies/{companyNumber}")
    public ResponseEntity<Object> deleteCompany(@PathVariable(required = true) String companyNumber) {

        Company retrievedCompany = inMemoryCompanyRegistry.getCompany(companyNumber);
        if (retrievedCompany == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company " + companyNumber + " not found");
        }

        inMemoryCompanyRegistry.deleteCompany(companyNumber);
        return ResponseEntity.noContent().build();
    }
}

