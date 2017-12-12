/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author paul
 */
public class DAO {

    private final DataSource myDataSource;

    /**
     * Constructs the DAO with his data source
     *
     * @param dataSource data source to use
     */
    public DAO(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    public List<DiscountCode> getDiscountCodes() throws SQLException {
        List<DiscountCode> result = new LinkedList<>();
        String sql = "SELECT * FROM DISCOUNT_CODE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String discountCodeId = rs.getString("DISCOUNT_CODE");
                float rate = rs.getFloat("RATE");
                DiscountCode discountCode = new DiscountCode(discountCodeId, rate);
                result.add(discountCode);
            }
        }
        return result;
    }

    public Customer getCustomer(String customerId) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String discountCodeId = rs.getString("DISCOUNT_CODE");
                String zip = rs.getString("ZIP");
                String name = rs.getString("NAME");
                String adressLine1 = rs.getString("ADDRESSLINE1");
                String adressLine2 = rs.getString("ADDRESSLINE2");
                String city = rs.getString("CITY");
                String state = rs.getString("STATE");
                String phone = rs.getString("PHONE");
                String fax = rs.getString("FAX");
                String email = rs.getString("EMAIL");
                int creditLimit = rs.getInt("CREDIT_LIMIT");
                customer = new Customer(Integer.parseInt(customerId),
                        discountCodeId, zip, name, adressLine1, adressLine2,
                        city, state, phone, fax, email, creditLimit);
            }
        }
        return customer;
    }

    public List<PurchaseOrder> getCustomerPurchaseOrders(String customerId) throws SQLException {
        List<PurchaseOrder> result = new LinkedList<>();
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderNum = rs.getInt("ORDER_NUM");
                // int customerId = rs.getInt("CUSTOMER_ID");
                int productId = rs.getInt("PRODUCT_ID");
                int quantity = rs.getInt("QUANTITY");
                float shippingCost = rs.getFloat("SHIPPING_COST");
                String salesDate = rs.getString("SALES_DATE");
                String shippingDate = rs.getString("SHIPPING_DATE");
                String freightCompany = rs.getString("FREIGHT_COMPANY");
                PurchaseOrder purchaseOrder = new PurchaseOrder(orderNum,
                        Integer.parseInt(customerId), productId, quantity,
                        shippingCost, salesDate, shippingDate, freightCompany);
                result.add(purchaseOrder);
            }
        }
        return result;
    }

    public int deletePurchaseOrder(String orderNum, String quantity, String productId) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orderNum);
            int newQuantity = getProductQuantity(productId) + Integer.parseInt(quantity);
            int update = updateProductQuantity(newQuantity, productId);
            result = stmt.executeUpdate();
        }
        return result;
    }

    public int getProductQuantity(String productId) throws SQLException {
        int quantity = 0;
        String sql = "SELECT QUANTITY_ON_HAND FROM PRODUCT WHERE PRODUCT_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("QUANTITY_ON_HAND");
            }
        }
        return quantity;
    }

    public int updateProductQuantity(int quantity, String productId) throws SQLException {
        int result = 0;
        String sql = "UPDATE PRODUCT SET QUANTITY_ON_HAND = ? WHERE PRODUCT_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, productId);
            result = stmt.executeUpdate();
        }
        return result;
    }

    public List<Product> getProducts() throws SQLException {
        List<Product> result = new LinkedList<>();
        String sql = "SELECT * FROM PRODUCT";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("PRODUCT_ID");
                int manufacturerId = rs.getInt("MANUFACTURER_ID");
                String productCodeId = rs.getString("PRODUCT_CODE");
                float purchaseCost = rs.getFloat("PURCHASE_COST");
                int quantityOnHand = rs.getInt("QUANTITY_ON_HAND");
                float markup = rs.getFloat("MARKUP");
                String available = rs.getString("AVAILABLE");
                String description = rs.getString("DESCRIPTION");
                Product product = new Product(productId, manufacturerId,
                        productCodeId, purchaseCost, quantityOnHand,
                        markup, available, description);
                result.add(product);
            }
        }
        return result;
    }

    public List<ProductCode> getProductCodes() throws SQLException {
        List<ProductCode> result = new LinkedList<>();
        String sql = "SELECT * FROM PRODUCT_CODE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String productCodeId = rs.getString("PROD_CODE");
                String discountCodeId = rs.getString("DISCOUNT_CODE");
                String description = rs.getString("DESCRIPTION");
                ProductCode productCode = new ProductCode(productCodeId,
                        discountCodeId, description);
                result.add(productCode);
            }
        }
        return result;
    }
}
