package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CounterpartyAddressesPK implements Serializable {
    private int counterpartyId;
    private String type;
    private int addressId;

    @Column(name = "counterparty_id")
    @Id
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Column(name = "type")
    @Id
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "address_id")
    @Id
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterpartyAddressesPK that = (CounterpartyAddressesPK) o;

        if (addressId != that.addressId) return false;
        if (counterpartyId != that.counterpartyId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counterpartyId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + addressId;
        return result;
    }
}
