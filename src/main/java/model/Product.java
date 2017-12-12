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
public class Product {

    private final int productId;
    private final int manufacturerId;
    private final String productCodeId;
    private final float purchaseCost;
    private final int quantityOnHand;
    private final float markup;
    private final String available;
    private final String description;

    public Product(int productId, int manufacturerId, String productCodeId,
            float purchaseCost, int quantityOnHand, float markup,
            String available, String description) {
        this.productId = productId;
        this.manufacturerId = manufacturerId;
        this.productCodeId = productCodeId;
        this.purchaseCost = purchaseCost;
        this.quantityOnHand = quantityOnHand;
        this.markup = markup;
        this.available = available;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public String getProductCodeId() {
        return productCodeId;
    }

    public float getPurchaseCost() {
        return purchaseCost;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public float getMarkup() {
        return markup;
    }

    public String getAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }
}
