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

/**
 * Servlet implementation class RemoveClient
 */
@WebServlet("/RemoveClient")
public class RemoveClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CLIENT_NAME_PARAM = "clientName";
    public static final String CLIENTS_SESSION   = "clients";

    public static final String VIEW              = "/WEB-INF/listClients.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientName = getParameterValue( request, CLIENT_NAME_PARAM );

        /* get clients map stored in session */
        HttpSession session = request.getSession();
        Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute( CLIENTS_SESSION );

        if ( clientName != null && clients != null ) {
            /* removes client from map */
            clients.remove( clientName );
            /* replace existing map (in session) by the new one */
            session.setAttribute( CLIENTS_SESSION, clients );
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
