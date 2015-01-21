package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Invoices {
    private int invoiceId;
    private int billId;
    private Timestamp createDate;

    @Id
    @Column(name = "invoice_id")
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Basic
    @Column(name = "bill_id")
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
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

        Invoices invoices = (Invoices) o;

        if (billId != invoices.billId) return false;
        if (invoiceId != invoices.invoiceId) return false;
        if (createDate != null ? !createDate.equals(invoices.createDate) : invoices.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoiceId;
        result = 31 * result + billId;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
