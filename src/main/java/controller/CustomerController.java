/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author paul
 */
@WebServlet(name = "customer", urlPatterns = {"/customer"})
public class CustomerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Quelle action a servi à appeler la servlet ? (Ajouter, Supprimer ou aucune = afficher)
        String action = request.getParameter("action");
        // Pour le switch qui n'aime pas les null
        action = (action == null) ? "" : action;
        // On récupère les paramètres de la requête
        // String customerId = request.getParameter("customerId");
        String customerId = "1"; // En attendant la gestion des sessions
        String orderNum = request.getParameter("orderNum");
        String quantity = request.getParameter("quantity");
        String productId = request.getParameter("productId");
        try {
            // Affichage par défaut
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            switch (action) {
                // Requête de suppression (vient du lien hypertexte)
                case "DELETE":
                    dao.deletePurchaseOrder(orderNum, quantity, productId);
                    request.setAttribute("message", "Commande n° " + orderNum
                            + " supprimée.");
                    break;
            }
            request.setAttribute("customer", dao.getCustomer(customerId));
            request.setAttribute("purchaseOrders",
                    dao.getCustomerPurchaseOrders(customerId));
            request.setAttribute("products", dao.getProducts());
            request.setAttribute("productCodes", dao.getProductCodes());
            request.setAttribute("discountCodes", dao.getDiscountCodes());
        } catch (SQLException ex) {
            Logger.getLogger("customer").log(Level.SEVERE, "Action en erreur", ex);
            request.setAttribute("message", ex.getMessage());
        }
        // On continue vers la page JSP sélectionnée
        request.getRequestDispatcher("customer.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
