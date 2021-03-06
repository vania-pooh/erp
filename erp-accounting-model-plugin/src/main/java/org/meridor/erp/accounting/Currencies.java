package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currencies {
    private short currencyId;
    private String code;
    private String displayName;

    @Id
    @Column(name = "currency_id")
    public short getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(short currencyId) {
        this.currencyId = currencyId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currencies that = (Currencies) o;

        if (currencyId != that.currencyId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) currencyId;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }
}
