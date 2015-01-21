package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Persons {
    private int personId;
    private int counterpartyId;
    private int companyId;
    private String name;
    private String surname;
    private String parentalName;
    private String mark;
    private int identityCardId;
    private int birthplaceId;
    private Date birthDate;
    private int citizenshipId;
    private String workPhone;
    private String homePhone;

    @Id
    @Column(name = "person_id")
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "parental_name")
    public String getParentalName() {
        return parentalName;
    }

    public void setParentalName(String parentalName) {
        this.parentalName = parentalName;
    }

    @Basic
    @Column(name = "mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "identity_card_id")
    public int getIdentityCardId() {
        return identityCardId;
    }

    public void setIdentityCardId(int identityCardId) {
        this.identityCardId = identityCardId;
    }

    @Basic
    @Column(name = "birthplace_id")
    public int getBirthplaceId() {
        return birthplaceId;
    }

    public void setBirthplaceId(int birthplaceId) {
        this.birthplaceId = birthplaceId;
    }

    @Basic
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "citizenship_id")
    public int getCitizenshipId() {
        return citizenshipId;
    }

    public void setCitizenshipId(int citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    @Basic
    @Column(name = "work_phone")
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Basic
    @Column(name = "home_phone")
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persons persons = (Persons) o;

        if (birthplaceId != persons.birthplaceId) return false;
        if (citizenshipId != persons.citizenshipId) return false;
        if (companyId != persons.companyId) return false;
        if (counterpartyId != persons.counterpartyId) return false;
        if (identityCardId != persons.identityCardId) return false;
        if (personId != persons.personId) return false;
        if (birthDate != null ? !birthDate.equals(persons.birthDate) : persons.birthDate != null) return false;
        if (homePhone != null ? !homePhone.equals(persons.homePhone) : persons.homePhone != null) return false;
        if (mark != null ? !mark.equals(persons.mark) : persons.mark != null) return false;
        if (name != null ? !name.equals(persons.name) : persons.name != null) return false;
        if (parentalName != null ? !parentalName.equals(persons.parentalName) : persons.parentalName != null)
            return false;
        if (surname != null ? !surname.equals(persons.surname) : persons.surname != null) return false;
        if (workPhone != null ? !workPhone.equals(persons.workPhone) : persons.workPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId;
        result = 31 * result + counterpartyId;
        result = 31 * result + companyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (parentalName != null ? parentalName.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + identityCardId;
        result = 31 * result + birthplaceId;
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + citizenshipId;
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        return result;
    }
}
