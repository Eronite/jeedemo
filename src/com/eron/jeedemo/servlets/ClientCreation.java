package com.eron.jeedemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eron.jeedemo.beans.Client;

/**
 * Servlet implementation class ClientCreation
 */
@WebServlet("/ClientCreation")
public class ClientCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// get param
		String name = request.getParameter("clientName");
		
		// create bean
		Client client = new Client();
		client.setName(name);
		
		// add bean to request object
		request.setAttribute("client", client);
		
		this.getServletContext().getRequestDispatcher("/displayClient.jsp").forward(request, response);
	}

}
