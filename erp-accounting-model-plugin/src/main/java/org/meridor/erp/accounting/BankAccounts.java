package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts", schema = "public", catalog = "erp")
public class BankAccounts {
    private int accountId;
    private int bankId;
    private int counterpartyId;
    private String accountType;
    private int number;

    @Id
    @Column(name = "account_id")
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "bank_id")
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Basic
    @Column(name = "counterparty_id")
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Basic
    @Column(name = "account_type")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccounts that = (BankAccounts) o;

        if (accountId != that.accountId) return false;
        if (bankId != that.bankId) return false;
        if (counterpartyId != that.counterpartyId) return false;
        if (number != that.number) return false;
        if (accountType != null ? !accountType.equals(that.accountType) : that.accountType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + bankId;
        result = 31 * result + counterpartyId;
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }
}
