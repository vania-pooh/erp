package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Banks {
    private int bankId;
    private String name;
    private long correspondentAccountNumber;
    private int locationId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "correspondent_account_number")
    public long getCorrespondentAccountNumber() {
        return correspondentAccountNumber;
    }

    public void setCorrespondentAccountNumber(long correspondentAccountNumber) {
        this.correspondentAccountNumber = correspondentAccountNumber;
    }

    @Basic
    @Column(name = "location_id")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
        if (correspondentAccountNumber != banks.correspondentAccountNumber) return false;
        if (locationId != banks.locationId) return false;
        if (name != null ? !name.equals(banks.name) : banks.name != null) return false;
        if (parentId != null ? !parentId.equals(banks.parentId) : banks.parentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bankId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (correspondentAccountNumber ^ (correspondentAccountNumber >>> 32));
        result = 31 * result + locationId;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        return result;
    }
}
