package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse_state", schema = "public", catalog = "erp")
@IdClass(WarehouseStatePK.class)
public class WarehouseState {
    private int warehouseId;
    private int productId;
    private double quantity;

    @Id
    @Column(name = "warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseState that = (WarehouseState) o;

        if (productId != that.productId) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (warehouseId != that.warehouseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = warehouseId;
        result = 31 * result + productId;
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
