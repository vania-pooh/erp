package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "warehouses_warehouse_id_seq", schema = "public", catalog = "erp")
public class WarehousesWarehouseIdSeq {
    private String sequenceName;
    private long lastValue;
    private long startValue;
    private long incrementBy;
    private long maxValue;
    private long minValue;
    private long cacheValue;
    private long logCnt;
    private boolean isCycled;
    private boolean isCalled;

    @Basic
    @Column(name = "sequence_name")
    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    @Basic
    @Column(name = "last_value")
    public long getLastValue() {
        return lastValue;
    }

    public void setLastValue(long lastValue) {
        this.lastValue = lastValue;
    }

    @Basic
    @Column(name = "start_value")
    public long getStartValue() {
        return startValue;
    }

    public void setStartValue(long startValue) {
        this.startValue = startValue;
    }

    @Basic
    @Column(name = "increment_by")
    public long getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(long incrementBy) {
        this.incrementBy = incrementBy;
    }

    @Basic
    @Column(name = "max_value")
    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    @Basic
    @Column(name = "min_value")
    public long getMinValue() {
        return minValue;
    }

    public void setMinValue(long minValue) {
        this.minValue = minValue;
    }

    @Basic
    @Column(name = "cache_value")
    public long getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(long cacheValue) {
        this.cacheValue = cacheValue;
    }

    @Basic
    @Column(name = "log_cnt")
    public long getLogCnt() {
        return logCnt;
    }

    public void setLogCnt(long logCnt) {
        this.logCnt = logCnt;
    }

    @Basic
    @Column(name = "is_cycled")
    public boolean isCycled() {
        return isCycled;
    }

    public void setCycled(boolean isCycled) {
        this.isCycled = isCycled;
    }

    @Basic
    @Column(name = "is_called")
    public boolean isCalled() {
        return isCalled;
    }

    public void setCalled(boolean isCalled) {
        this.isCalled = isCalled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehousesWarehouseIdSeq that = (WarehousesWarehouseIdSeq) o;

        if (cacheValue != that.cacheValue) return false;
        if (incrementBy != that.incrementBy) return false;
        if (isCalled != that.isCalled) return false;
        if (isCycled != that.isCycled) return false;
        if (lastValue != that.lastValue) return false;
        if (logCnt != that.logCnt) return false;
        if (maxValue != that.maxValue) return false;
        if (minValue != that.minValue) return false;
        if (startValue != that.startValue) return false;
        if (sequenceName != null ? !sequenceName.equals(that.sequenceName) : that.sequenceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sequenceName != null ? sequenceName.hashCode() : 0;
        result = 31 * result + (int) (lastValue ^ (lastValue >>> 32));
        result = 31 * result + (int) (startValue ^ (startValue >>> 32));
        result = 31 * result + (int) (incrementBy ^ (incrementBy >>> 32));
        result = 31 * result + (int) (maxValue ^ (maxValue >>> 32));
        result = 31 * result + (int) (minValue ^ (minValue >>> 32));
        result = 31 * result + (int) (cacheValue ^ (cacheValue >>> 32));
        result = 31 * result + (int) (logCnt ^ (logCnt >>> 32));
        result = 31 * result + (isCycled ? 1 : 0);
        result = 31 * result + (isCalled ? 1 : 0);
        return result;
    }
}
