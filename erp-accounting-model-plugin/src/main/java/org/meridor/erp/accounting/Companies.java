package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
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

    private Integer taxNumber;

    @Basic
    @javax.persistence.Column(name = "tax_number")
    public Integer getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

    private Integer fiscalCode;

    @Basic
    @javax.persistence.Column(name = "fiscal_code")
    public Integer getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(Integer fiscalCode) {
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

    private Integer fiscalAuthorityCode;

    @Basic
    @javax.persistence.Column(name = "fiscal_authority_code")
    public Integer getFiscalAuthorityCode() {
        return fiscalAuthorityCode;
    }

    public void setFiscalAuthorityCode(Integer fiscalAuthorityCode) {
        this.fiscalAuthorityCode = fiscalAuthorityCode;
    }

    private Integer fiscalAuthorityId;

    @Basic
    @javax.persistence.Column(name = "fiscal_authority_id")
    public Integer getFiscalAuthorityId() {
        return fiscalAuthorityId;
    }

    public void setFiscalAuthorityId(Integer fiscalAuthorityId) {
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

    private Integer pensionFundId;

    @Basic
    @javax.persistence.Column(name = "pension_fund_id")
    public Integer getPensionFundId() {
        return pensionFundId;
    }

    public void setPensionFundId(Integer pensionFundId) {
        this.pensionFundId = pensionFundId;
    }

    private Integer socialInsuranceFundNumber;

    @Basic
    @javax.persistence.Column(name = "social_insurance_fund_number")
    public Integer getSocialInsuranceFundNumber() {
        return socialInsuranceFundNumber;
    }

    public void setSocialInsuranceFundNumber(Integer socialInsuranceFundNumber) {
        this.socialInsuranceFundNumber = socialInsuranceFundNumber;
    }

    private Integer socialInsuranceFundId;

    @Basic
    @javax.persistence.Column(name = "social_insurance_fund_id")
    public Integer getSocialInsuranceFundId() {
        return socialInsuranceFundId;
    }

    public void setSocialInsuranceFundId(Integer socialInsuranceFundId) {
        this.socialInsuranceFundId = socialInsuranceFundId;
    }

    private Integer typeCode;

    @Basic
    @javax.persistence.Column(name = "type_code")
    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    private BigInteger propertyForm;

    @Basic
    @javax.persistence.Column(name = "property_form")
    public BigInteger getPropertyForm() {
        return propertyForm;
    }

    public void setPropertyForm(BigInteger propertyForm) {
        this.propertyForm = propertyForm;
    }

    private Integer stateAuthorityCode;

    @Basic
    @javax.persistence.Column(name = "state_authority_code")
    public Integer getStateAuthorityCode() {
        return stateAuthorityCode;
    }

    public void setStateAuthorityCode(Integer stateAuthorityCode) {
        this.stateAuthorityCode = stateAuthorityCode;
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

    private Integer statisticsCode;

    @Basic
    @javax.persistence.Column(name = "statistics_code")
    public Integer getStatisticsCode() {
        return statisticsCode;
    }

    public void setStatisticsCode(Integer statisticsCode) {
        this.statisticsCode = statisticsCode;
    }

    private Integer statisticsBranchId;

    @Basic
    @javax.persistence.Column(name = "statistics_branch_id")
    public Integer getStatisticsBranchId() {
        return statisticsBranchId;
    }

    public void setStatisticsBranchId(Integer statisticsBranchId) {
        this.statisticsBranchId = statisticsBranchId;
    }

    private Integer municipalityId;

    @Basic
    @javax.persistence.Column(name = "municipality_id")
    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    private short mainCurrencyId;

    @Basic
    @javax.persistence.Column(name = "main_currency_id")
    public short getMainCurrencyId() {
        return mainCurrencyId;
    }

    public void setMainCurrencyId(short mainCurrencyId) {
        this.mainCurrencyId = mainCurrencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Companies companies = (Companies) o;

        if (companyId != companies.companyId) return false;
        if (counterpartyId != companies.counterpartyId) return false;
        if (mainCurrencyId != companies.mainCurrencyId) return false;
        if (displayName != null ? !displayName.equals(companies.displayName) : companies.displayName != null)
            return false;
        if (fiscalAuthorityCode != null ? !fiscalAuthorityCode.equals(companies.fiscalAuthorityCode) : companies.fiscalAuthorityCode != null)
            return false;
        if (fiscalAuthorityId != null ? !fiscalAuthorityId.equals(companies.fiscalAuthorityId) : companies.fiscalAuthorityId != null)
            return false;
        if (fiscalCode != null ? !fiscalCode.equals(companies.fiscalCode) : companies.fiscalCode != null) return false;
        if (fullName != null ? !fullName.equals(companies.fullName) : companies.fullName != null) return false;
        if (mainActivity != null ? !mainActivity.equals(companies.mainActivity) : companies.mainActivity != null)
            return false;
        if (municipalityId != null ? !municipalityId.equals(companies.municipalityId) : companies.municipalityId != null)
            return false;
        if (pensionFundId != null ? !pensionFundId.equals(companies.pensionFundId) : companies.pensionFundId != null)
            return false;
        if (pensionFundNumber != null ? !pensionFundNumber.equals(companies.pensionFundNumber) : companies.pensionFundNumber != null)
            return false;
        if (propertyForm != null ? !propertyForm.equals(companies.propertyForm) : companies.propertyForm != null)
            return false;
        if (registrationDate != null ? !registrationDate.equals(companies.registrationDate) : companies.registrationDate != null)
            return false;
        if (shortName != null ? !shortName.equals(companies.shortName) : companies.shortName != null) return false;
        if (socialInsuranceFundId != null ? !socialInsuranceFundId.equals(companies.socialInsuranceFundId) : companies.socialInsuranceFundId != null)
            return false;
        if (socialInsuranceFundNumber != null ? !socialInsuranceFundNumber.equals(companies.socialInsuranceFundNumber) : companies.socialInsuranceFundNumber != null)
            return false;
        if (stateAuthorityCode != null ? !stateAuthorityCode.equals(companies.stateAuthorityCode) : companies.stateAuthorityCode != null)
            return false;
        if (statisticsBranchId != null ? !statisticsBranchId.equals(companies.statisticsBranchId) : companies.statisticsBranchId != null)
            return false;
        if (statisticsCode != null ? !statisticsCode.equals(companies.statisticsCode) : companies.statisticsCode != null)
            return false;
        if (taxNumber != null ? !taxNumber.equals(companies.taxNumber) : companies.taxNumber != null) return false;
        if (typeCode != null ? !typeCode.equals(companies.typeCode) : companies.typeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + counterpartyId;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (taxNumber != null ? taxNumber.hashCode() : 0);
        result = 31 * result + (fiscalCode != null ? fiscalCode.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (fiscalAuthorityCode != null ? fiscalAuthorityCode.hashCode() : 0);
        result = 31 * result + (fiscalAuthorityId != null ? fiscalAuthorityId.hashCode() : 0);
        result = 31 * result + (pensionFundNumber != null ? pensionFundNumber.hashCode() : 0);
        result = 31 * result + (pensionFundId != null ? pensionFundId.hashCode() : 0);
        result = 31 * result + (socialInsuranceFundNumber != null ? socialInsuranceFundNumber.hashCode() : 0);
        result = 31 * result + (socialInsuranceFundId != null ? socialInsuranceFundId.hashCode() : 0);
        result = 31 * result + (typeCode != null ? typeCode.hashCode() : 0);
        result = 31 * result + (propertyForm != null ? propertyForm.hashCode() : 0);
        result = 31 * result + (stateAuthorityCode != null ? stateAuthorityCode.hashCode() : 0);
        result = 31 * result + (mainActivity != null ? mainActivity.hashCode() : 0);
        result = 31 * result + (statisticsCode != null ? statisticsCode.hashCode() : 0);
        result = 31 * result + (statisticsBranchId != null ? statisticsBranchId.hashCode() : 0);
        result = 31 * result + (municipalityId != null ? municipalityId.hashCode() : 0);
        result = 31 * result + (int) mainCurrencyId;
        return result;
    }
}
