package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Companies {
    private int companyId;

    @Id
    @javax.persistence.Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    private int counterpartyId;

    @Basic
    @javax.persistence.Column(name = "counterparty_id")
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    private String shortName;

    @Basic
    @javax.persistence.Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private String fullName;

    @Basic
    @javax.persistence.Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String displayName;

    @Basic
    @javax.persistence.Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    private int registryId;

    @Basic
    @javax.persistence.Column(name = "registry_id")
    public int getRegistryId() {
        return registryId;
    }

    public void setRegistryId(int registryId) {
        this.registryId = registryId;
    }

    private int taxNumber;

    @Basic
    @javax.persistence.Column(name = "tax_number")
    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    private int fiscalCode;

    @Basic
    @javax.persistence.Column(name = "fiscal_code")
    public int getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(int fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    private Date registrationDate;

    @Basic
    @javax.persistence.Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    private int fiscalAuthorityCode;

    @Basic
    @javax.persistence.Column(name = "fiscal_authority_code")
    public int getFiscalAuthorityCode() {
        return fiscalAuthorityCode;
    }

    public void setFiscalAuthorityCode(int fiscalAuthorityCode) {
        this.fiscalAuthorityCode = fiscalAuthorityCode;
    }

    private int fiscalAuthorityId;

    @Basic
    @javax.persistence.Column(name = "fiscal_authority_id")
    public int getFiscalAuthorityId() {
        return fiscalAuthorityId;
    }

    public void setFiscalAuthorityId(int fiscalAuthorityId) {
        this.fiscalAuthorityId = fiscalAuthorityId;
    }

    private String pensionFundNumber;

    @Basic
    @javax.persistence.Column(name = "pension_fund_number")
    public String getPensionFundNumber() {
        return pensionFundNumber;
    }

    public void setPensionFundNumber(String pensionFundNumber) {
        this.pensionFundNumber = pensionFundNumber;
    }

    private int pensionFundId;

    @Basic
    @javax.persistence.Column(name = "pension_fund_id")
    public int getPensionFundId() {
        return pensionFundId;
    }

    public void setPensionFundId(int pensionFundId) {
        this.pensionFundId = pensionFundId;
    }

    private int socialInsuranceFundNumber;

    @Basic
    @javax.persistence.Column(name = "social_insurance_fund_number")
    public int getSocialInsuranceFundNumber() {
        return socialInsuranceFundNumber;
    }

    public void setSocialInsuranceFundNumber(int socialInsuranceFundNumber) {
        this.socialInsuranceFundNumber = socialInsuranceFundNumber;
    }

    private int socialInsuranceFundId;

    @Basic
    @javax.persistence.Column(name = "social_insurance_fund_id")
    public int getSocialInsuranceFundId() {
        return socialInsuranceFundId;
    }

    public void setSocialInsuranceFundId(int socialInsuranceFundId) {
        this.socialInsuranceFundId = socialInsuranceFundId;
    }

    private int typeCode;

    @Basic
    @javax.persistence.Column(name = "type_code")
    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    private int propertyForm;

    @Basic
    @javax.persistence.Column(name = "property_form")
    public int getPropertyForm() {
        return propertyForm;
    }

    public void setPropertyForm(int propertyForm) {
        this.propertyForm = propertyForm;
    }

    private String mainActivity;

    @Basic
    @javax.persistence.Column(name = "main_activity")
    public String getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(String mainActivity) {
        this.mainActivity = mainActivity;
    }

    private int statisticsCode;

    @Basic
    @javax.persistence.Column(name = "statistics_code")
    public int getStatisticsCode() {
        return statisticsCode;
    }

    public void setStatisticsCode(int statisticsCode) {
        this.statisticsCode = statisticsCode;
    }

    private int statisticsBranchId;

    @Basic
    @javax.persistence.Column(name = "statistics_branch_id")
    public int getStatisticsBranchId() {
        return statisticsBranchId;
    }

    public void setStatisticsBranchId(int statisticsBranchId) {
        this.statisticsBranchId = statisticsBranchId;
    }

    private int territoryId;

    @Basic
    @javax.persistence.Column(name = "territory_id")
    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    private int municipalityId;

    @Basic
    @javax.persistence.Column(name = "municipality_id")
    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    private int mainCurrencyId;

    @Basic
    @javax.persistence.Column(name = "main_currency_id")
    public int getMainCurrencyId() {
        return mainCurrencyId;
    }

    public void setMainCurrencyId(int mainCurrencyId) {
        this.mainCurrencyId = mainCurrencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Companies companies = (Companies) o;

        if (companyId != companies.companyId) return false;
        if (counterpartyId != companies.counterpartyId) return false;
        if (fiscalAuthorityCode != companies.fiscalAuthorityCode) return false;
        if (fiscalAuthorityId != companies.fiscalAuthorityId) return false;
        if (fiscalCode != companies.fiscalCode) return false;
        if (mainCurrencyId != companies.mainCurrencyId) return false;
        if (municipalityId != companies.municipalityId) return false;
        if (pensionFundId != companies.pensionFundId) return false;
        if (propertyForm != companies.propertyForm) return false;
        if (registryId != companies.registryId) return false;
        if (socialInsuranceFundId != companies.socialInsuranceFundId) return false;
        if (socialInsuranceFundNumber != companies.socialInsuranceFundNumber) return false;
        if (statisticsBranchId != companies.statisticsBranchId) return false;
        if (statisticsCode != companies.statisticsCode) return false;
        if (taxNumber != companies.taxNumber) return false;
        if (territoryId != companies.territoryId) return false;
        if (typeCode != companies.typeCode) return false;
        if (displayName != null ? !displayName.equals(companies.displayName) : companies.displayName != null)
            return false;
        if (fullName != null ? !fullName.equals(companies.fullName) : companies.fullName != null) return false;
        if (mainActivity != null ? !mainActivity.equals(companies.mainActivity) : companies.mainActivity != null)
            return false;
        if (pensionFundNumber != null ? !pensionFundNumber.equals(companies.pensionFundNumber) : companies.pensionFundNumber != null)
            return false;
        if (registrationDate != null ? !registrationDate.equals(companies.registrationDate) : companies.registrationDate != null)
            return false;
        if (shortName != null ? !shortName.equals(companies.shortName) : companies.shortName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + counterpartyId;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + registryId;
        result = 31 * result + taxNumber;
        result = 31 * result + fiscalCode;
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + fiscalAuthorityCode;
        result = 31 * result + fiscalAuthorityId;
        result = 31 * result + (pensionFundNumber != null ? pensionFundNumber.hashCode() : 0);
        result = 31 * result + pensionFundId;
        result = 31 * result + socialInsuranceFundNumber;
        result = 31 * result + socialInsuranceFundId;
        result = 31 * result + typeCode;
        result = 31 * result + propertyForm;
        result = 31 * result + (mainActivity != null ? mainActivity.hashCode() : 0);
        result = 31 * result + statisticsCode;
        result = 31 * result + statisticsBranchId;
        result = 31 * result + territoryId;
        result = 31 * result + municipalityId;
        result = 31 * result + mainCurrencyId;
        return result;
    }
}
