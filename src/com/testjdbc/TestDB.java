package com.testjdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 * Servlet implementation class TestDB
 */
@WebServlet("/testmydb")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String user="jetwebapp";
		String pass="jetwebapp";
		String jdbcUrl="jdbc:oracle:thin:@localhost:1521:orcl";
		//At first it didn't work 
		// I executed dbms_xdb.sethttpport('1158'); , but tnsnames port stayed the same , I thought it's the one 
		//not database url control , it's the port in ..tnsnames.ora in APP directory
		String driver="oracle.jdbc.driver.OracleDriver";
		
		try {
			Class.forName(driver);
			Connection myConn=DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("Connection successful "+user+" "+pass);
			myConn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e); // to observe error on web page 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
