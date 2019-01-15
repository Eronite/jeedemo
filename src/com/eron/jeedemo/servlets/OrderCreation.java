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

import com.eron.jeedemo.forms.OrderCreationForm;
import com.eron.jeedemo.beans.Client;
import com.eron.jeedemo.beans.Order;

/**
 * Servlet implementation class OrderCreation
 */
@WebServlet("/OrderCreation")
public class OrderCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//public static final String NAME_FIELD = "clientName";
	//public static final String DATE_FIELD = "orderDate";
	
	public static final String ORDER_ATTRIBUTE = "order";
	public static final String FORM_ATTRIBUTE  = "form";
	
	public static final String CLIENTS_SESSION = "clients";
    public static final String ORDERS_SESSION  = "orders";
	
	public static final String SUCCESS_VIEW    = "/WEB-INF/displayOrder.jsp";
	public static final String FORM_VIEW       = "/WEB-INF/createOrder.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// GET : display form :
		this.getServletContext().getRequestDispatcher(FORM_VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* create form object */
        OrderCreationForm form = new OrderCreationForm();

        /* processes request and gets resulting bean */
        Order order = form.createOrder( request );

        /* add bean and form object to request object */
        request.setAttribute( ORDER_ATTRIBUTE, order );
        request.setAttribute( FORM_ATTRIBUTE, form );

        if ( form.getErrors().isEmpty() ) {
        	/* get clients map (in session) */
            HttpSession session = request.getSession();
            Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute( CLIENTS_SESSION );
            /* if no existing map,, creates a new one  */
            if ( clients == null ) {
                clients = new HashMap<String, Client>();
            }
            /* add current order's client to map */
            clients.put( order.getClient().getName(), order.getClient() );
            /* records map in session */
            session.setAttribute( CLIENTS_SESSION, clients );
 
            /* gets order's map (in session) */
            Map<String, Order> orders = (HashMap<String, Order>) session.getAttribute( ORDERS_SESSION );
            /* if no existing map,, creates a new one */
            if ( orders == null ) {
            	orders = new HashMap<String, Order>();
            }
            /* add current order to map */
            orders.put( order.getDate(), order );
            /* records map (in session) */
            session.setAttribute( ORDERS_SESSION, orders );

        	
            this.getServletContext().getRequestDispatcher( SUCCESS_VIEW ).forward( request, response );
        } else {
        	/* else display creation form (with errors) */
            this.getServletContext().getRequestDispatcher( FORM_VIEW ).forward( request, response );
        }

	}
	
}
