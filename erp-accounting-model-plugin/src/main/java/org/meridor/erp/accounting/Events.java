package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Events {
    private int eventId;
    private String eventType;
    private int companyId;
    private Timestamp createDate;
    private int priority;
    private String status;

    @Id
    @Column(name = "event_id")
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "event_type")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events = (Events) o;

        if (companyId != events.companyId) return false;
        if (eventId != events.eventId) return false;
        if (priority != events.priority) return false;
        if (createDate != null ? !createDate.equals(events.createDate) : events.createDate != null) return false;
        if (eventType != null ? !eventType.equals(events.eventType) : events.eventType != null) return false;
        if (status != null ? !status.equals(events.status) : events.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + companyId;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
