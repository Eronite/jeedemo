package com.eron.jeedemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eron.jeedemo.beans.Client;
import com.eron.jeedemo.beans.Order;

/**
 * Servlet implementation class OrderCreation
 */
@WebServlet("/OrderCreation")
public class OrderCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// DateTime dateTime = new DateTime();
		
		// get params
		String name = request.getParameter("clientName");
		String date = request.getParameter("orderDate");
		
		// create beans
		Client client = new Client();
		client.setName(name);
		
		Order order = new Order();
		order.setClient(client);
		order.setDate(date);
		
		// add bean to request object
		request.setAttribute("order", order);
		
		this.getServletContext().getRequestDispatcher("/displayOrder.jsp").forward(request, response);
	}

}
