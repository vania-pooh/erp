package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flags {
    private int flagId;
    private int companyId;
    private String name;
    private String displayName;

    @Id
    @Column(name = "flag_id")
    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flags flags = (Flags) o;

        if (companyId != flags.companyId) return false;
        if (flagId != flags.flagId) return false;
        if (displayName != null ? !displayName.equals(flags.displayName) : flags.displayName != null) return false;
        if (name != null ? !name.equals(flags.name) : flags.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flagId;
        result = 31 * result + companyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }
}
