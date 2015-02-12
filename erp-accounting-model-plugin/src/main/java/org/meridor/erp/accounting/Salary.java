package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Salary {
    private int salaryId;
    private int employeeId;
    private short currencyId;
    private String salaryType;
    private String way;
    private int year;
    private int month;
    private double amount;
    private Integer userId;

    @Id
    @Column(name = "salary_id")
    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
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
    @Column(name = "currency_id")
    public short getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(short currencyId) {
        this.currencyId = currencyId;
    }

    @Basic
    @Column(name = "salary_type")
    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    @Basic
    @Column(name = "way")
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salary salary = (Salary) o;

        if (Double.compare(salary.amount, amount) != 0) return false;
        if (currencyId != salary.currencyId) return false;
        if (employeeId != salary.employeeId) return false;
        if (month != salary.month) return false;
        if (salaryId != salary.salaryId) return false;
        if (year != salary.year) return false;
        if (salaryType != null ? !salaryType.equals(salary.salaryType) : salary.salaryType != null) return false;
        if (userId != null ? !userId.equals(salary.userId) : salary.userId != null) return false;
        if (way != null ? !way.equals(salary.way) : salary.way != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = salaryId;
        result = 31 * result + employeeId;
        result = 31 * result + (int) currencyId;
        result = 31 * result + (salaryType != null ? salaryType.hashCode() : 0);
        result = 31 * result + (way != null ? way.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + month;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
