package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accounts {
    private short accountId;
    private int companyId;
    private String code;
    private String accountType;
    private String displayName;
    private Integer parentId;
    private Boolean isGroup;
    private short unitId;

    @Id
    @Column(name = "account_id")
    public short getAccountId() {
        return accountId;
    }

    public void setAccountId(short accountId) {
        this.accountId = accountId;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "account_type")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "is_group")
    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    @Basic
    @Column(name = "unit_id")
    public short getUnitId() {
        return unitId;
    }

    public void setUnitId(short unitId) {
        this.unitId = unitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accounts accounts = (Accounts) o;

        if (accountId != accounts.accountId) return false;
        if (companyId != accounts.companyId) return false;
        if (unitId != accounts.unitId) return false;
        if (accountType != null ? !accountType.equals(accounts.accountType) : accounts.accountType != null)
            return false;
        if (code != null ? !code.equals(accounts.code) : accounts.code != null) return false;
        if (displayName != null ? !displayName.equals(accounts.displayName) : accounts.displayName != null)
            return false;
        if (isGroup != null ? !isGroup.equals(accounts.isGroup) : accounts.isGroup != null) return false;
        if (parentId != null ? !parentId.equals(accounts.parentId) : accounts.parentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) accountId;
        result = 31 * result + companyId;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (isGroup != null ? isGroup.hashCode() : 0);
        result = 31 * result + (int) unitId;
        return result;
    }
}
