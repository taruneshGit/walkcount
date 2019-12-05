package co.raystech.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.raystech.proj4.bean.FacultyBean;
import co.raystech.proj4.model.FacultyModel;
import co.raystech.proj4.util.ServletUtility;

/**
 * Servlet implementation class FacultyCtl
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {

		List<FacultyBean> facultyList = FacultyModel.list();

		request.setAttribute("facultyList", facultyList);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletUtility.forward(getView(), request, response);

		response.setContentType("text/html");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		FacultyBean bean = new FacultyBean();
		// FacultyModel facultyModel=new FacultyModel();
		FacultyModel.add(bean);

		doGet(request, response);

		response.setContentType("text/html");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
