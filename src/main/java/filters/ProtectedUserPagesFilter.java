/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author paul
 */
public class ProtectedUserPagesFilter implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (session != null && session.getAttribute("kind").equals("user")) {
                // connecté, on traite la requête			
                chain.doFilter(request, response);
            } else {
                // Pas connecté, on va vers la page de login (racine)
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.jsp");
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProtectedAdminPagesFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
