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
public class ProductCode {

    private final String productCodeId;
    private final String discountCodeId;
    private final String description;

    public ProductCode(String productCodeId, String discountCodeId,
            String description) {
        this.productCodeId = productCodeId;
        this.discountCodeId = discountCodeId;
        this.description = description;
    }

    public String getProductCodeId() {
        return productCodeId;
    }

    public String getDiscountCodeId() {
        return discountCodeId;
    }

    public String getDescription() {
        return description;
    }
}
