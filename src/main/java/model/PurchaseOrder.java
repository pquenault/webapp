/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author paul
 */
public class PurchaseOrder {

    private final int orderNum;
    private final int customerId;
    private final int productId;
    private final int quantity;
    private final float shippingCost;
    private final String salesDate;
    private final String shippingDate;
    private final String freightCompany;

    public PurchaseOrder(int orderNum, int customerId, int productId,
            int quantity, float shippingCost, String salesDate,
            String shippingDate, String freightCompany) {
        this.orderNum = orderNum;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.shippingCost = shippingCost;
        this.salesDate = salesDate;
        this.shippingDate = shippingDate;
        this.freightCompany = freightCompany;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public String getFreightCompany() {
        return freightCompany;
    }
}
