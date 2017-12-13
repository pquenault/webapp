/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author paul
 */
public class ApplicationController extends HttpServlet {

    private DAO dao;

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
        // PIERRE
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // On initialise notre source de données
            dao = new DAO(DataSourceFactory.getDataSource());
            // Quelle action a appelé cette servlet ?
            String action = request.getParameter("action");
            // Pour le switch qui n'aime pas les null
            action = (action == null) ? "" : action;
            // Vers quelle jsp va t'on être redirigé
            String jspPath;

            // PIERRE
            Properties resultat = new Properties();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String gsonData;

            switch (action) {

                // Requête de connexion
                case "logIn":
                    logIn(request);
                    jspPath = checkLogIn(request);
                    setStandardAttributes(request, jspPath);
                    request.getRequestDispatcher(jspPath).forward(request, response);
                    break;

                // Requête de déconnexion
                case "logOut":
                    logOut(request);
                    jspPath = "login.jsp";
                    request.setAttribute("message", "Vous avez été déconnecté");
                    request.getRequestDispatcher(jspPath).forward(request, response);
                    break;

                //Requête d'ajout de commande
                case "add":
                    jspPath = "protectedUser/user.jsp";
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        String userId = String.valueOf(session.getAttribute("userId"));
                        int nextOrderNum = dao.nextOrderNum();
                        int customerId = Integer.parseInt(userId);
                        int productId1 = Integer.parseInt(request.getParameter("productId"));
                        int quantity1 = Integer.parseInt(request.getParameter("quantity"));
                        float shippingCost = 50;
                        String salesDate = "2011-05-24";
                        String shippingDate = "2011-05-24";
                        String freightCompany = "Fedex";
                        dao.addPurchaseOrder(nextOrderNum, customerId, productId1,
                                quantity1, shippingCost, salesDate, shippingDate,
                                freightCompany);
                    }
                    setStandardAttributes(request, jspPath);
                    request.getRequestDispatcher(jspPath).forward(request, response);
                    break;

                // Requête de suppression de commande
                case "delete":
                    jspPath = "protectedUser/user.jsp";
                    String orderNum2 = request.getParameter("orderNum");
                    String quantity2 = request.getParameter("quantity");
                    String productId2 = request.getParameter("productId");
                    dao.deletePurchaseOrder(orderNum2, quantity2, productId2);
                    request.setAttribute("message", "Commande n° " + orderNum2
                            + " supprimée.");
                    setStandardAttributes(request, jspPath);
                    request.getRequestDispatcher(jspPath).forward(request, response);
                    break;

                // PIERRE
                case "category":
                    resultat.put("records", dao.salesByCategory());
                    gsonData = gson.toJson(resultat);
                    out.println(gsonData);
                    break;
                // PIERRE
                case "state":
                    resultat.put("records", dao.salesByState());
                    gsonData = gson.toJson(resultat);
                    out.println(gsonData);
                    break;
                // PIERRE
                case "customer":
                    resultat.put("records", dao.salesByCustomer());
                    gsonData = gson.toJson(resultat);
                    out.println(gsonData);
                    break;
            }
        } catch (SQLException ex) {
            // Si ça se passe mal, on se déconnecte et on reviens à la page de connexion
            // On en profite pour afficher l'exception
            request.setAttribute("message", ex.getMessage());
            logOut(request);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logIn(HttpServletRequest request) throws SQLException {
        // Les paramètres transmis dans la requête
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String userName;

        // Le login/password de l'administrateur défini dans web.xml
        String adminLogin = getInitParameter("login");
        String adminPassword = getInitParameter("password");
        String adminName = getInitParameter("adminName");

        // On vérifie si c'est une connexion en admin, sinon en user
        if ((login.equals(adminLogin) && (password.equals(adminPassword)))) {
            // On a trouvé la combinaison login / password
            // On stocke l'information dans la session
            HttpSession session = request.getSession(); // Démarre la session
            session.setAttribute("kind", "admin");
            session.setAttribute("name", adminName);
        } else if ((userName = dao.authenticateUser(login, password)) != null) {
            HttpSession session = request.getSession(); // Démarre la session
            session.setAttribute("kind", "user");
            session.setAttribute("name", userName);
            session.setAttribute("userId", password);
        } else {
            // On positionne un message d'erreur pour l'afficher dans la JSP
            request.setAttribute("message", "Identifiant / Mot de passe incorrect");
        }
    }

    private String checkLogIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String name, jspPath;
        if (session == null) {
            name = null;
        } else {
            name = String.valueOf(session.getAttribute("name"));
        }
        // Quelqu'un est-il connecté ?
        if (name == null) {
            jspPath = "login.jsp";
        } else {
            // Oui, est-il admin ou user ?
            String kind = String.valueOf(session.getAttribute("kind"));
            switch (kind) {
                case "admin":
                    jspPath = "protectedAdmin/admin.jsp";
                    break;
                case "user":
                    jspPath = "protectedUser/user.jsp";
                    break;
                default:
                    jspPath = "login.jsp";
                    // On positionne un message d'erreur pour l'afficher dans la JSP
                    request.setAttribute("message", "Problème de connexion");
                    break;
            }
        }
        return jspPath;
    }

    private void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    private void setStandardAttributes(HttpServletRequest request, String path) throws SQLException {
        if (path.equals("protectedAdmin/admin.jsp")) {
            // Code de pierre si besoin
        } else if (path.equals("protectedUser/user.jsp")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = String.valueOf(session.getAttribute("userId"));
                request.setAttribute("customer", dao.getCustomer(userId));
                request.setAttribute("purchaseOrders",
                        dao.getCustomerPurchaseOrders(userId));
            }
            request.setAttribute("products", dao.getProducts());
            request.setAttribute("productCodes", dao.getProductCodes());
            request.setAttribute("discountCodes", dao.getDiscountCodes());
        }
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
