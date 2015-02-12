package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Areas {
    private UUID areaId;
    private short regionId;
    private short countryId;
    private String displayName;
    private String level;

    @Id
    @Column(name = "area_id")
    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "region_id")
    public short getRegionId() {
        return regionId;
    }

    public void setRegionId(short regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "country_id")
    public short getCountryId() {
        return countryId;
    }

    public void setCountryId(short countryId) {
        this.countryId = countryId;
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
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Areas areas = (Areas) o;

        if (countryId != areas.countryId) return false;
        if (regionId != areas.regionId) return false;
        if (areaId != null ? !areaId.equals(areas.areaId) : areas.areaId != null) return false;
        if (displayName != null ? !displayName.equals(areas.displayName) : areas.displayName != null) return false;
        if (level != null ? !level.equals(areas.level) : areas.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId != null ? areaId.hashCode() : 0;
        result = 31 * result + (int) regionId;
        result = 31 * result + (int) countryId;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
