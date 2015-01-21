package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "reference_documents", schema = "public", catalog = "erp")
public class ReferenceDocuments {
    private int referenceId;
    private int companyId;
    private String referenceType;
    private Timestamp createDate;
    private int userId;

    @Id
    @Column(name = "reference_id")
    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    @Basic
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "reference_type")
    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferenceDocuments that = (ReferenceDocuments) o;

        if (companyId != that.companyId) return false;
        if (referenceId != that.referenceId) return false;
        if (userId != that.userId) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (referenceType != null ? !referenceType.equals(that.referenceType) : that.referenceType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = referenceId;
        result = 31 * result + companyId;
        result = 31 * result + (referenceType != null ? referenceType.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
