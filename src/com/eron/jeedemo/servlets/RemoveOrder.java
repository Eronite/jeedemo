package com.eron.jeedemo.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eron.jeedemo.beans.Order;

/**
 * Servlet implementation class RemoveOrder
 */
@WebServlet("/RemoveOrder")
public class RemoveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String ORDER_DATE_PARAM = "orderDate";
    public static final String ORDERS_SESSION   = "orders";

    public static final String VIEW             = "/WEB-INF/listOrders.jsp";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération du paramètre */
        String orderDate = getParameterValue( request, ORDER_DATE_PARAM );

        /* Récupération de la Map des commandes enregistrées en session */
        HttpSession session = request.getSession();
        Map<String, Order> orders = (HashMap<String, Order>) session.getAttribute( ORDERS_SESSION );

        /* Si la date de la commande et la Map des commandes ne sont pas vides */
        if ( orderDate != null && orders != null ) {
            /* Alors suppression de la commande de la Map */
            orders.remove( orderDate );
            /* Et remplacement de l'ancienne Map en session par la nouvelle */
            session.setAttribute( ORDERS_SESSION, orders );
        }

        /* Redirection vers la fiche récapitulative */
        //response.sendRedirect( request.getContextPath() + VIEW );
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
	
	/*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * contenu sinon.
     */
    private static String getParameterValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }

}
