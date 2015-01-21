package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AppliedTaxesPK implements Serializable {
    private int companyId;
    private int taxId;

    @Column(name = "company_id")
    @Id
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Column(name = "tax_id")
    @Id
    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppliedTaxesPK that = (AppliedTaxesPK) o;

        if (companyId != that.companyId) return false;
        if (taxId != that.taxId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + taxId;
        return result;
    }
}
