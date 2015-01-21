package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tax_authorities", schema = "public", catalog = "erp")
public class TaxAuthorities {
    private int authorityId;
    private int companyId;

    @Id
    @Column(name = "authority_id")
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxAuthorities that = (TaxAuthorities) o;

        if (authorityId != that.authorityId) return false;
        if (companyId != that.companyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorityId;
        result = 31 * result + companyId;
        return result;
    }
}
