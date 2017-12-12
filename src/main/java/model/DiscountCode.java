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
public class DiscountCode {

    private final String discountCodeId;
    private final float rate;

    public DiscountCode(String discountCodeId, float rate) {
        this.discountCodeId = discountCodeId;
        this.rate = rate;
    }

    public String getDiscountCodeId() {
        return discountCodeId;
    }

    public float getRate() {
        return rate;
    }
}
