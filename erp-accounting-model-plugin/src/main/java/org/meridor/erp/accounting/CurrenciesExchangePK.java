package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class CurrenciesExchangePK implements Serializable {
    private int fromId;
    private int toId;
    private Date actualOn;

    @Column(name = "from_id")
    @Id
    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    @Column(name = "to_id")
    @Id
    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    @Column(name = "actual_on")
    @Id
    public Date getActualOn() {
        return actualOn;
    }

    public void setActualOn(Date actualOn) {
        this.actualOn = actualOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrenciesExchangePK that = (CurrenciesExchangePK) o;

        if (fromId != that.fromId) return false;
        if (toId != that.toId) return false;
        if (actualOn != null ? !actualOn.equals(that.actualOn) : that.actualOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromId;
        result = 31 * result + toId;
        result = 31 * result + (actualOn != null ? actualOn.hashCode() : 0);
        return result;
    }
}
