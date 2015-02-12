package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Units {
    private short unitId;
    private String shortName;
    private String displayName;

    @Id
    @Column(name = "unit_id")
    public short getUnitId() {
        return unitId;
    }

    public void setUnitId(short unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

        Units units = (Units) o;

        if (unitId != units.unitId) return false;
        if (displayName != null ? !displayName.equals(units.displayName) : units.displayName != null) return false;
        if (shortName != null ? !shortName.equals(units.shortName) : units.shortName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) unitId;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }
}
