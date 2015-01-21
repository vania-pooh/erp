package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AssignedTaxAuthoritiesPK implements Serializable {
    private int companyId;
    private int authorityId;

    @Column(name = "company_id")
    @Id
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Column(name = "authority_id")
    @Id
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssignedTaxAuthoritiesPK that = (AssignedTaxAuthoritiesPK) o;

        if (authorityId != that.authorityId) return false;
        if (companyId != that.companyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + authorityId;
        return result;
    }
}
