package org.meridor.erp.accounting;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class WarehouseStatePK implements Serializable {
    private int warehouseId;
    private int productId;
    private double quantity;

    @Column(name = "warehouse_id")
    @Id
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "quantity")
    @Id
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

        WarehouseStatePK that = (WarehouseStatePK) o;

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
