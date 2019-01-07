package com.eron.jeedemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eron.jeedemo.forms.OrderCreationForm;
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
	public static final String FORM_ATTRIBUTE = "form";
	
	public static final String SUCCESS_VIEW = "/WEB-INF/displayOrder.jsp";
	public static final String FORM_VIEW = "/WEB-INF/createOrder.jsp";
	
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

        /* Traitement de la requête et récupération du bean en résultant */
        Order order = form.createOrder( request );

        /* add bean and form object to request object */
        request.setAttribute( ORDER_ATTRIBUTE, order );
        request.setAttribute( FORM_ATTRIBUTE, form );

        if ( form.getErrors().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( SUCCESS_VIEW ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( FORM_VIEW ).forward( request, response );
        }

	}
	
}
