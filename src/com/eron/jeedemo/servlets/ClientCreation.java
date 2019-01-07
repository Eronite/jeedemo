package com.eron.jeedemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eron.jeedemo.beans.Client;
import com.eron.jeedemo.forms.ClientCreationForm;

/**
 * Servlet implementation class ClientCreation
 */
@WebServlet("/ClientCreation")
public class ClientCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//public static final String NAME_FIELD = "clientName";
	
	public static final String CLIENT_ATTRIBUTE = "client";
	public static final String FORM_ATTRIBUTE = "form";
	
	public static final String SUCCESS_VIEW = "/WEB-INF/displayClient.jsp";
	public static final String FORM_VIEW = "/WEB-INF/createClient.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET request : display form :		
		this.getServletContext().getRequestDispatcher(FORM_VIEW).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     /* create form object */
        ClientCreationForm form = new ClientCreationForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Client client = form.createClient( request );

        /* add bean to request object */
        request.setAttribute( CLIENT_ATTRIBUTE, client );
        /* add form object to request object */
        request.setAttribute( FORM_ATTRIBUTE, form );

        if ( form.getErrors().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( SUCCESS_VIEW ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( FORM_VIEW ).forward( request, response );
        }
	}

}
