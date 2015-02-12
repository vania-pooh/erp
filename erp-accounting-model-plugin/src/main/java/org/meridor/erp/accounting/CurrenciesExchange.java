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
    private short fromId;
    private short toId;
    private Date actualOn;
    private double rate;

    @Id
    @Column(name = "from_id")
    public short getFromId() {
        return fromId;
    }

    public void setFromId(short fromId) {
        this.fromId = fromId;
    }

    @Id
    @Column(name = "to_id")
    public short getToId() {
        return toId;
    }

    public void setToId(short toId) {
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
        result = (int) fromId;
        result = 31 * result + (int) toId;
        result = 31 * result + (actualOn != null ? actualOn.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
