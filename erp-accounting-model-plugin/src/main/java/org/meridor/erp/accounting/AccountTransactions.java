package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "account_transactions", schema = "public", catalog = "erp")
public class AccountTransactions {
    private int transactionId;
    private Integer groupId;
    private short fromId;
    private short toId;
    private double amount;
    private Timestamp createDate;
    private boolean applied;

    @Id
    @Column(name = "transaction_id")
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "from_id")
    public short getFromId() {
        return fromId;
    }

    public void setFromId(short fromId) {
        this.fromId = fromId;
    }

    @Basic
    @Column(name = "to_id")
    public short getToId() {
        return toId;
    }

    public void setToId(short toId) {
        this.toId = toId;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "applied")
    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountTransactions that = (AccountTransactions) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (applied != that.applied) return false;
        if (fromId != that.fromId) return false;
        if (toId != that.toId) return false;
        if (transactionId != that.transactionId) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = transactionId;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (int) fromId;
        result = 31 * result + (int) toId;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (applied ? 1 : 0);
        return result;
    }
}
