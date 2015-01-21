package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "counterparty_currencies", schema = "public", catalog = "erp")
@IdClass(CounterpartyCurrenciesPK.class)
public class CounterpartyCurrencies {
    private int counterpartyId;
    private int currencyId;

    @Id
    @Column(name = "counterparty_id")
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Id
    @Column(name = "currency_id")
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

        CounterpartyCurrencies that = (CounterpartyCurrencies) o;

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
