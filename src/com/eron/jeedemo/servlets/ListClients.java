package com.eron.jeedemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListClients
 */
@WebServlet("/ListClients")
public class ListClients extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CLIENT_ATTRIBUTE = "client";
    public static final String FORM_ATTRIBUTE   = "form";

    public static final String VUE              = "/WEB-INF/listClients.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListClients() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /* À la réception d'une requête GET, affichage de la liste des clients */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
