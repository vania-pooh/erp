package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zip_codes", schema = "public", catalog = "erp")
public class ZipCodes {
    private int code;
    private int locationId;

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "location_id")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZipCodes zipCodes = (ZipCodes) o;

        if (code != zipCodes.code) return false;
        if (locationId != zipCodes.locationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + locationId;
        return result;
    }
}
