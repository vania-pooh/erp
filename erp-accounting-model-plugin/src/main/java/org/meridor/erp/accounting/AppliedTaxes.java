package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "applied_taxes", schema = "public", catalog = "erp")
@IdClass(AppliedTaxesPK.class)
public class AppliedTaxes {
    private int companyId;
    private int taxId;
    private Double rateOverride;

    @Id
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Id
    @Column(name = "tax_id")
    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    @Basic
    @Column(name = "rate_override")
    public Double getRateOverride() {
        return rateOverride;
    }

    public void setRateOverride(Double rateOverride) {
        this.rateOverride = rateOverride;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppliedTaxes that = (AppliedTaxes) o;

        if (companyId != that.companyId) return false;
        if (taxId != that.taxId) return false;
        if (rateOverride != null ? !rateOverride.equals(that.rateOverride) : that.rateOverride != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + taxId;
        result = 31 * result + (rateOverride != null ? rateOverride.hashCode() : 0);
        return result;
    }
}
