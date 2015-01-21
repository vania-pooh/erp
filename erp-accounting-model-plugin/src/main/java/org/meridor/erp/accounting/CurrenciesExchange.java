package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "currencies_exchange", schema = "public", catalog = "erp")
@IdClass(CurrenciesExchangePK.class)
public class CurrenciesExchange {
    private int fromId;
    private int toId;
    private Date actualOn;
    private double rate;

    @Id
    @Column(name = "from_id")
    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    @Id
    @Column(name = "to_id")
    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    @Id
    @Column(name = "actual_on")
    public Date getActualOn() {
        return actualOn;
    }

    public void setActualOn(Date actualOn) {
        this.actualOn = actualOn;
    }

    @Basic
    @Column(name = "rate")
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrenciesExchange that = (CurrenciesExchange) o;

        if (fromId != that.fromId) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        if (toId != that.toId) return false;
        if (actualOn != null ? !actualOn.equals(that.actualOn) : that.actualOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = fromId;
        result = 31 * result + toId;
        result = 31 * result + (actualOn != null ? actualOn.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
