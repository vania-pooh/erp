package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Addresses {
    private int addressId;
    private int locationId;
    private int zipCode;
    private String streetName;
    private int streetNumber;
    private int numericSuffix;
    private String letterSuffix;
    private int apartmentNumber;
    private int apartmentType;

    @Id
    @Column(name = "address_id")
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
    @Column(name = "zip_code")
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "street_name")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Basic
    @Column(name = "street_number")
    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "numeric_suffix")
    public int getNumericSuffix() {
        return numericSuffix;
    }

    public void setNumericSuffix(int numericSuffix) {
        this.numericSuffix = numericSuffix;
    }

    @Basic
    @Column(name = "letter_suffix")
    public String getLetterSuffix() {
        return letterSuffix;
    }

    public void setLetterSuffix(String letterSuffix) {
        this.letterSuffix = letterSuffix;
    }

    @Basic
    @Column(name = "apartment_number")
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Basic
    @Column(name = "apartment_type")
    public int getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(int apartmentType) {
        this.apartmentType = apartmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addresses addresses = (Addresses) o;

        if (addressId != addresses.addressId) return false;
        if (apartmentNumber != addresses.apartmentNumber) return false;
        if (apartmentType != addresses.apartmentType) return false;
        if (locationId != addresses.locationId) return false;
        if (numericSuffix != addresses.numericSuffix) return false;
        if (streetNumber != addresses.streetNumber) return false;
        if (zipCode != addresses.zipCode) return false;
        if (letterSuffix != null ? !letterSuffix.equals(addresses.letterSuffix) : addresses.letterSuffix != null)
            return false;
        if (streetName != null ? !streetName.equals(addresses.streetName) : addresses.streetName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId;
        result = 31 * result + locationId;
        result = 31 * result + zipCode;
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + streetNumber;
        result = 31 * result + numericSuffix;
        result = 31 * result + (letterSuffix != null ? letterSuffix.hashCode() : 0);
        result = 31 * result + apartmentNumber;
        result = 31 * result + apartmentType;
        return result;
    }
}
