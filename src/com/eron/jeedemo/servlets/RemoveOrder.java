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
        String orderDate = getParameterValue( request, ORDER_DATE_PARAM );

        /* get orders map stored in session */
        HttpSession session = request.getSession();
        Map<String, Order> orders = (HashMap<String, Order>) session.getAttribute( ORDERS_SESSION );

        if ( orderDate != null && orders != null ) {
            /* remove order from map (key is orderDate) */
            orders.remove( orderDate );
            /* replace existing map (in session) by the new one */
            session.setAttribute( ORDERS_SESSION, orders );
        }

        /* redirection */
        //response.sendRedirect( request.getContextPath() + VIEW );
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
	
	/*
     * returns null if the parameter is empty, or returns its value
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
