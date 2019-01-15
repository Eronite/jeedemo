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
	public static final String FORM_ATTRIBUTE   = "form";
	
	public static final String CLIENTS_SESSION  = "clients";
	
	public static final String SUCCESS_VIEW     = "/WEB-INF/displayClient.jsp";
	public static final String FORM_VIEW        = "/WEB-INF/createClient.jsp";
       
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

        /* processes request and gets resulting bean */
        Client client = form.createClient( request );

        /* add bean to request object */
        request.setAttribute( CLIENT_ATTRIBUTE, client );
        /* add form object to request object */
        request.setAttribute( FORM_ATTRIBUTE, form );

        if ( form.getErrors().isEmpty() ) {
        	/* gets clients map (in session) */
            HttpSession session = request.getSession();
            Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute( CLIENTS_SESSION );
            /* if no existing map, creates a new one */
            if ( clients == null ) {
                clients = new HashMap<String, Client>();
            }
            /* add current client to map */
            clients.put( client.getName(), client );
            /* records map in session */
            session.setAttribute( CLIENTS_SESSION, clients );

            this.getServletContext().getRequestDispatcher( SUCCESS_VIEW ).forward( request, response );
        } else {
        	/* else display creation form (with errors) */
            this.getServletContext().getRequestDispatcher( FORM_VIEW ).forward( request, response );
        }
	}

}
