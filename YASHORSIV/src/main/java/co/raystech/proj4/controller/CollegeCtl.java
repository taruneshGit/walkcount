package co.raystech.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.raystech.proj4.util.ServletUtility;

/**
 * Servlet implementation class CollegeCtl
 */
@WebServlet(name="CollegeCtl", urlPatterns= {"/ctl/CollegeCtl"})

public class CollegeCtl extends BaseCtl{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	ServletUtility.forward(getView(), request, response);
	response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_VIEW;
	}

}
