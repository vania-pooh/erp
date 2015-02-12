package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Locations {
    private UUID locationId;
    private String displayName;
    private UUID areaId;
    private short regionId;
    private short countryId;
    private Integer zipCode;
    private Integer municipalityId;
    private Integer fiscalAuthorityCode;

    @Id
    @Column(name = "location_id")
    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
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
    @Column(name = "zip_code")
    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "municipality_id")
    public Integer getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Basic
    @Column(name = "fiscal_authority_code")
    public Integer getFiscalAuthorityCode() {
        return fiscalAuthorityCode;
    }

    public void setFiscalAuthorityCode(Integer fiscalAuthorityCode) {
        this.fiscalAuthorityCode = fiscalAuthorityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locations locations = (Locations) o;

        if (countryId != locations.countryId) return false;
        if (regionId != locations.regionId) return false;
        if (areaId != null ? !areaId.equals(locations.areaId) : locations.areaId != null) return false;
        if (displayName != null ? !displayName.equals(locations.displayName) : locations.displayName != null)
            return false;
        if (fiscalAuthorityCode != null ? !fiscalAuthorityCode.equals(locations.fiscalAuthorityCode) : locations.fiscalAuthorityCode != null)
            return false;
        if (locationId != null ? !locationId.equals(locations.locationId) : locations.locationId != null) return false;
        if (municipalityId != null ? !municipalityId.equals(locations.municipalityId) : locations.municipalityId != null)
            return false;
        if (zipCode != null ? !zipCode.equals(locations.zipCode) : locations.zipCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locationId != null ? locationId.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (int) regionId;
        result = 31 * result + (int) countryId;
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (municipalityId != null ? municipalityId.hashCode() : 0);
        result = 31 * result + (fiscalAuthorityCode != null ? fiscalAuthorityCode.hashCode() : 0);
        return result;
    }
}
