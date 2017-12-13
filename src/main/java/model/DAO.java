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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public String authenticateUser(String email, String customerId)
            throws SQLException {

        String name = null;
        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ? AND EMAIL = ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("NAME");
            }
        }
        return name;
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

    public List<PurchaseOrder> getCustomerPurchaseOrders(String customerId)
            throws SQLException {

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

    public int addPurchaseOrder(int orderNum, int customerId, int productId,
            int quantity, float shippingCost, String salesDate,
            String shippingDate, String freightCompany)
            throws SQLException {

        int result = 0;
        String sql = "INSERT INTO PURCHASE_ORDER (ORDER_NUM, CUSTOMER_ID, "
                + "PRODUCT_ID, QUANTITY, SHIPPING_COST, SALES_DATE, "
                + "SHIPPING_DATE, FREIGHT_COMPANY) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderNum);
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);
            stmt.setInt(4, quantity);
            stmt.setFloat(5, shippingCost);
            stmt.setString(6, salesDate);
            stmt.setString(7, shippingDate);
            stmt.setString(8, freightCompany);
            int newQuantity = getProductQuantity(String.valueOf(productId)) - quantity;
            int update = updateProductQuantity(newQuantity, String.valueOf(productId));
            result = stmt.executeUpdate();
        }
        return result;
    }

    public int deletePurchaseOrder(String orderNum, String quantity,
            String productId)
            throws SQLException {

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

    public int nextOrderNum() throws SQLException {

        int nextOrderNum = 0;
        String sql = "SELECT MAX(ORDER_NUM) AS MAX_ORDER_NUM FROM PURCHASE_ORDER";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nextOrderNum = rs.getInt("MAX_ORDER_NUM");
            }
        }
        return nextOrderNum + 1;
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

    public int updateProductQuantity(int quantity, String productId)
            throws SQLException {

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

    /**
     *
     * @return @throws SQLException
     */
    public Map<String, Double> salesByCustomer() throws SQLException {
        Map<String, Double> result = new HashMap<>();
        String sql = "SELECT NAME, SUM(PURCHASE_COST * QUANTITY) AS SALES"
                + "	      FROM CUSTOMER c"
                + "             INNER JOIN PURCHASE_ORDER o ON (o.CUSTOMER_ID = c.CUSTOMER_ID)"
                + "	      INNER JOIN PRODUCT p ON (o.PRODUCT_ID = p.PRODUCT_ID)"
                + "	      GROUP BY NAME";
        /*
                "SELECT NAME, SUM(PURCHASE_COST * QUANTITY) AS SALES" +
		"	      FROM CUSTOMER c" +
		"	      INNER JOIN PURCHASE_ORDER o ON (? <= o.SALES_DATE" +
                "             AND  o.SALES_DATE <= ?)" +
		"	      INNER JOIN PRODUCT p ON (o.PRODUCT_ID = p.PRODUCT_ID)" +
		"	      GROUP BY NAME"
         */
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // On récupère les champs nécessaires de l'enregistrement courant
                String name = rs.getString("NAME");
                double sales = rs.getDouble("SALES");
                // On l'ajoute à la liste des résultats
                result.put(name, sales);
            }
        }
        return result;
    }

    /**
     *
     * @return @throws SQLException
     */
    public Map<String, Double> salesByState() throws SQLException {
        Map<String, Double> result = new HashMap<>();
        String dateDeb = null;
        String dateFin = null;
        String sql = "SELECT STATE, SUM(PURCHASE_COST * QUANTITY) AS SALES FROM CUSTOMER c\n"
                + "INNER JOIN PURCHASE_ORDER o ON (c.CUSTOMER_ID = o.CUSTOMER_ID)\n"
                + "INNER JOIN PRODUCT p ON (o.PRODUCT_ID = p.PRODUCT_ID)\n"
                + "GROUP BY STATE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // On récupère les champs nécessaires de l'enregistrement courant
                String state = rs.getString("STATE");
                double sales = rs.getDouble("SALES");
                // On l'ajoute à la liste des résultats
                result.put(state, sales);
            }
        }
        return result;
    }

    /**
     *
     * @return @throws SQLException
     */
    public Map<String, Double> salesByCategory() throws SQLException {
        Map<String, Double> result = new HashMap<>();
        String dateDeb = null;
        String dateFin = null;
        String sql = "SELECT PROD_CODE, SUM(PURCHASE_COST * QUANTITY) AS SALES FROM PRODUCT_CODE c\n"
                + "INNER JOIN PRODUCT p ON (p.PRODUCT_CODE = c.PROD_CODE)\n"
                + "INNER JOIN PURCHASE_ORDER po ON (p.PRODUCT_ID = po.PRODUCT_ID)\n"
                + "GROUP BY PROD_CODE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // On récupère les champs nécessaires de l'enregistrement courant
                String name = rs.getString("PROD_CODE");
                double sales = rs.getDouble("SALES");
                // On l'ajoute à la liste des résultats
                result.put(name, sales);
            }
        }
        return result;
    }
}
