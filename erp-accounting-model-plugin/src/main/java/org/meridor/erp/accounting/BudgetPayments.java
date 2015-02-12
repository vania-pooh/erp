package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "budget_payments", schema = "public", catalog = "erp")
public class BudgetPayments {
    private int paymentId;
    private int budgetAdministratorCode;
    private int budgetClassificationCode;
    private int reasonId;
    private String fiscalPeriod;
    private Integer documentId;
    private Date documentDate;

    @Id
    @Column(name = "payment_id")
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "budget_administrator_code")
    public int getBudgetAdministratorCode() {
        return budgetAdministratorCode;
    }

    public void setBudgetAdministratorCode(int budgetAdministratorCode) {
        this.budgetAdministratorCode = budgetAdministratorCode;
    }

    @Basic
    @Column(name = "budget_classification_code")
    public int getBudgetClassificationCode() {
        return budgetClassificationCode;
    }

    public void setBudgetClassificationCode(int budgetClassificationCode) {
        this.budgetClassificationCode = budgetClassificationCode;
    }

    @Basic
    @Column(name = "reason_id")
    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    @Basic
    @Column(name = "fiscal_period")
    public String getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setFiscalPeriod(String fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    @Basic
    @Column(name = "document_id")
    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    @Basic
    @Column(name = "document_date")
    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BudgetPayments that = (BudgetPayments) o;

        if (budgetAdministratorCode != that.budgetAdministratorCode) return false;
        if (budgetClassificationCode != that.budgetClassificationCode) return false;
        if (paymentId != that.paymentId) return false;
        if (reasonId != that.reasonId) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (fiscalPeriod != null ? !fiscalPeriod.equals(that.fiscalPeriod) : that.fiscalPeriod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentId;
        result = 31 * result + budgetAdministratorCode;
        result = 31 * result + budgetClassificationCode;
        result = 31 * result + reasonId;
        result = 31 * result + (fiscalPeriod != null ? fiscalPeriod.hashCode() : 0);
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        return result;
    }
}
