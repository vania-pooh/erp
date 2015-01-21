package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Payments {
    private int paymentId;
    private int fromAccountId;
    private int toAccountId;
    private int documentType;
    private int paymentType;
    private int status;
    private double amount;
    private Timestamp createDate;

    @Id
    @Column(name = "payment_id")
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "from_account_id")
    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    @Basic
    @Column(name = "to_account_id")
    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    @Basic
    @Column(name = "document_type")
    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    @Basic
    @Column(name = "payment_type")
    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payments payments = (Payments) o;

        if (Double.compare(payments.amount, amount) != 0) return false;
        if (documentType != payments.documentType) return false;
        if (fromAccountId != payments.fromAccountId) return false;
        if (paymentId != payments.paymentId) return false;
        if (paymentType != payments.paymentType) return false;
        if (status != payments.status) return false;
        if (toAccountId != payments.toAccountId) return false;
        if (createDate != null ? !createDate.equals(payments.createDate) : payments.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = paymentId;
        result = 31 * result + fromAccountId;
        result = 31 * result + toAccountId;
        result = 31 * result + documentType;
        result = 31 * result + paymentType;
        result = 31 * result + status;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
