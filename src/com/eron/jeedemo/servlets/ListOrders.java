package com.eron.jeedemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListOrders
 */
@WebServlet("/ListOrders")
public class ListOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ORDER_ATTRIBUTE  = "commande";
    public static final String FORM_ATTRIBUTE   = "form";

    public static final String VUE              = "/WEB-INF/listOrders.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* GET : display orders list */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
