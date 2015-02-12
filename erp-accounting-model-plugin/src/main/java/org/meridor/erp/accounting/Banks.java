package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Banks {
    private int bankId;
    private int companyId;
    private int correspondentAccountNumber;
    private Integer parentId;

    @Id
    @Column(name = "bank_id")
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
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
    @Column(name = "correspondent_account_number")
    public int getCorrespondentAccountNumber() {
        return correspondentAccountNumber;
    }

    public void setCorrespondentAccountNumber(int correspondentAccountNumber) {
        this.correspondentAccountNumber = correspondentAccountNumber;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Banks banks = (Banks) o;

        if (bankId != banks.bankId) return false;
        if (companyId != banks.companyId) return false;
        if (correspondentAccountNumber != banks.correspondentAccountNumber) return false;
        if (parentId != null ? !parentId.equals(banks.parentId) : banks.parentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bankId;
        result = 31 * result + companyId;
        result = 31 * result + correspondentAccountNumber;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        return result;
    }
}
