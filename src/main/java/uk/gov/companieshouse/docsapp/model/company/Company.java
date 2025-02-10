package uk.gov.companieshouse.docsapp.model.company;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@JsonTypeInfo(
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ForeignCompany.class, name = "FXC"),
        @JsonSubTypes.Type(value = LimitedLiabilityPartnership.class, name = "LLP"),
        @JsonSubTypes.Type(value = NonProfitOrganization.class, name = "NPO"),
        @JsonSubTypes.Type(value = LimitedCompany.class, name = "LTD")
})
// this inheritance annotation is added once we add the hc2 database. InheritanceType.JOINED ensures our database
// takes the structure of a companies table with FXC, LLP, NPO, LTD as company sub-tables, because we defined those
// 4 types as subclasses of the Company class.
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Company {
    private String companyName;
    @Id
    private String registrationNumber;
    private String registeredAddress;
    private Boolean active;
    private LocalDate incorporatedOn;

    public Company() {
        this(null, null);
    }

    public Company(String companyName, Boolean active) {
        this.companyName = companyName;
        this.active = active;
    }

    public LocalDate getIncorporatedOn() {
        return incorporatedOn;
    }

    public void setIncorporatedOn(LocalDate incorporatedOn) {
        this.incorporatedOn = incorporatedOn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
