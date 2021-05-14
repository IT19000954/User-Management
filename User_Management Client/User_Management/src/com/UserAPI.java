package com;

import model.UserManagement;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManagement user = new UserManagement();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static Map getParasMap(HttpServletRequest request)
    {
	    Map<String, String> map = new HashMap<String, String>();
	    try
	    {
		    Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		    String queryString = scanner.hasNext() ?
		    scanner.useDelimiter("\\A").next() : "";
		    scanner.close();
		    String[] params = queryString.split("&");
		    for (String param : params)
		    {
		    	String[] p = param.split("=");
		    	map.put(p[0], p[1]);
		    }
		}
	    catch (Exception e)
	    {		    
	    	
	    }
		return map;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String output = user.insertUser(request.getParameter("userName"), 
				request.getParameter("userType"),
				request.getParameter("Password"), 
				request.getParameter("Email"), 
				request.getParameter("Phone"), 
				request.getParameter("Address"));
				response.getWriter().write(output);
//				request.getParameter("itemCode")
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = user.updateUser(paras.get("userID").toString(),
				paras.get("userName").toString(),
				paras.get("userType").toString(),
				paras.get("Password").toString(),
				paras.get("Email").toString(),
				paras.get("Phone").toString(),
				paras.get("Address").toString());
				response.getWriter().write(output);
//				paras.get("hidItemIDSave").toString(),
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = user.deleteUser(paras.get("userID").toString());
		response.getWriter().write(output);
	}

}
