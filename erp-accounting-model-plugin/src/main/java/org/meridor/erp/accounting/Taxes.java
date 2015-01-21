package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Taxes {
    private int taxId;
    private int companyId;
    private String name;
    private String displayName;
    private String timeInterval;
    private int taxType;
    private double rate;

    @Id
    @Column(name = "tax_id")
    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    @Basic
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Basic
    @Column(name = "time_interval")
    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Basic
    @Column(name = "tax_type")
    public int getTaxType() {
        return taxType;
    }

    public void setTaxType(int taxType) {
        this.taxType = taxType;
    }

    @Basic
    @Column(name = "rate")
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taxes taxes = (Taxes) o;

        if (companyId != taxes.companyId) return false;
        if (Double.compare(taxes.rate, rate) != 0) return false;
        if (taxId != taxes.taxId) return false;
        if (taxType != taxes.taxType) return false;
        if (displayName != null ? !displayName.equals(taxes.displayName) : taxes.displayName != null) return false;
        if (name != null ? !name.equals(taxes.name) : taxes.name != null) return false;
        if (timeInterval != null ? !timeInterval.equals(taxes.timeInterval) : taxes.timeInterval != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = taxId;
        result = 31 * result + companyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (timeInterval != null ? timeInterval.hashCode() : 0);
        result = 31 * result + taxType;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
