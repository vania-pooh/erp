package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Counterparties {
    private int counterpartyId;
    private String counterpartyType;

    @Id
    @Column(name = "counterparty_id")
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Basic
    @Column(name = "counterparty_type")
    public String getCounterpartyType() {
        return counterpartyType;
    }

    public void setCounterpartyType(String counterpartyType) {
        this.counterpartyType = counterpartyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Counterparties that = (Counterparties) o;

        if (counterpartyId != that.counterpartyId) return false;
        if (counterpartyType != null ? !counterpartyType.equals(that.counterpartyType) : that.counterpartyType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counterpartyId;
        result = 31 * result + (counterpartyType != null ? counterpartyType.hashCode() : 0);
        return result;
    }
}
