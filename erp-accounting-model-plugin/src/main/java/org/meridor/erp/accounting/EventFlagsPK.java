package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class EventFlagsPK implements Serializable {
    private int eventId;
    private int flagId;

    @Column(name = "event_id")
    @Id
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Column(name = "flag_id")
    @Id
    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventFlagsPK that = (EventFlagsPK) o;

        if (eventId != that.eventId) return false;
        if (flagId != that.flagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + flagId;
        return result;
    }
}
