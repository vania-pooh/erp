package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Employees {
    private int employeeId;
    private int companyId;
    private int counterpartyId;
    private int personId;
    private int postId;
    private String displayName;
    private double grossPay;
    private double advancePercent;
    private int employmentStatus;
    private Date hiredDate;
    private Date firedDate;
    private boolean visible;

    @Id
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
    @Column(name = "counterparty_id")
    public int getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(int counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    @Basic
    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "post_id")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Basic
    @Column(name = "gross_pay")
    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    @Basic
    @Column(name = "advance_percent")
    public double getAdvancePercent() {
        return advancePercent;
    }

    public void setAdvancePercent(double advancePercent) {
        this.advancePercent = advancePercent;
    }

    @Basic
    @Column(name = "employment_status")
    public int getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(int employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    @Basic
    @Column(name = "hired_date")
    public Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    @Basic
    @Column(name = "fired_date")
    public Date getFiredDate() {
        return firedDate;
    }

    public void setFiredDate(Date firedDate) {
        this.firedDate = firedDate;
    }

    @Basic
    @Column(name = "visible")
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employees employees = (Employees) o;

        if (Double.compare(employees.advancePercent, advancePercent) != 0) return false;
        if (companyId != employees.companyId) return false;
        if (counterpartyId != employees.counterpartyId) return false;
        if (employeeId != employees.employeeId) return false;
        if (employmentStatus != employees.employmentStatus) return false;
        if (Double.compare(employees.grossPay, grossPay) != 0) return false;
        if (personId != employees.personId) return false;
        if (postId != employees.postId) return false;
        if (visible != employees.visible) return false;
        if (displayName != null ? !displayName.equals(employees.displayName) : employees.displayName != null)
            return false;
        if (firedDate != null ? !firedDate.equals(employees.firedDate) : employees.firedDate != null) return false;
        if (hiredDate != null ? !hiredDate.equals(employees.hiredDate) : employees.hiredDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = employeeId;
        result = 31 * result + companyId;
        result = 31 * result + counterpartyId;
        result = 31 * result + personId;
        result = 31 * result + postId;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        temp = Double.doubleToLongBits(grossPay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(advancePercent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + employmentStatus;
        result = 31 * result + (hiredDate != null ? hiredDate.hashCode() : 0);
        result = 31 * result + (firedDate != null ? firedDate.hashCode() : 0);
        result = 31 * result + (visible ? 1 : 0);
        return result;
    }
}
