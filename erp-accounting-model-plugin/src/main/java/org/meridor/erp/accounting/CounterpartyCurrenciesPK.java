package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CounterpartyCurrenciesPK implements Serializable {
    private int counterpartyId;
    private int currencyId;

    @Column(name = "counterparty_id")
    @Id
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Column(name = "currency_id")
    @Id
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterpartyCurrenciesPK that = (CounterpartyCurrenciesPK) o;

        if (counterpartyId != that.counterpartyId) return false;
        if (currencyId != that.currencyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counterpartyId;
        result = 31 * result + currencyId;
        return result;
    }
}
