package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "counterparty_addresses", schema = "public", catalog = "erp")
@IdClass(CounterpartyAddressesPK.class)
public class CounterpartyAddresses {
    private int counterpartyId;
    private String type;
    private int addressId;

    @Id
    @Column(name = "counterparty_id")
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Id
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "address_id")
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

        CounterpartyAddresses that = (CounterpartyAddresses) o;

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
