package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Attorneys {
    private int attorneyId;
    private int companyId;
    private int employeeId;
    private String attorneyType;
    private Date fromDate;
    private Integer duration;
    private Date toDate;
    private boolean transferable;
    private int templateId;

    @Id
    @Column(name = "attorney_id")
    public int getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(int attorneyId) {
        this.attorneyId = attorneyId;
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
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "attorney_type")
    public String getAttorneyType() {
        return attorneyType;
    }

    public void setAttorneyType(String attorneyType) {
        this.attorneyType = attorneyType;
    }

    @Basic
    @Column(name = "from_date")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "to_date")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Basic
    @Column(name = "transferable")
    public boolean isTransferable() {
        return transferable;
    }

    public void setTransferable(boolean transferable) {
        this.transferable = transferable;
    }

    @Basic
    @Column(name = "template_id")
    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attorneys attorneys = (Attorneys) o;

        if (attorneyId != attorneys.attorneyId) return false;
        if (companyId != attorneys.companyId) return false;
        if (employeeId != attorneys.employeeId) return false;
        if (templateId != attorneys.templateId) return false;
        if (transferable != attorneys.transferable) return false;
        if (attorneyType != null ? !attorneyType.equals(attorneys.attorneyType) : attorneys.attorneyType != null)
            return false;
        if (duration != null ? !duration.equals(attorneys.duration) : attorneys.duration != null) return false;
        if (fromDate != null ? !fromDate.equals(attorneys.fromDate) : attorneys.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(attorneys.toDate) : attorneys.toDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attorneyId;
        result = 31 * result + companyId;
        result = 31 * result + employeeId;
        result = 31 * result + (attorneyType != null ? attorneyType.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (transferable ? 1 : 0);
        result = 31 * result + templateId;
        return result;
    }
}
