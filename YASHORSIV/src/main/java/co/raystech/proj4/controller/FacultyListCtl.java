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
 * Servlet implementation class FacultyListCtl
 */
@WebServlet(name = "/FacultyListCtl", urlPatterns = { "/ctl/FacultyListCtl" })
public class FacultyListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {

		int pageSize = 5;
		List<FacultyBean> list = FacultyModel.list();
		int buttonNumber = list.size() / pageSize;
		if (list.size() % pageSize != 0) {
			buttonNumber++;
		}
		request.setAttribute("buttonNumber", buttonNumber);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int pageNo = 1;
		int pageSize = 5;
		List<FacultyBean> list = FacultyModel.search(pageNo, pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("list", list);
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
		String op = request.getParameter("operation");
		if (op.equalsIgnoreCase("Next")) {

			int pageNo = Integer.parseInt(request.getParameter("pageNo")) == 0 ? 1
					: Integer.parseInt(request.getParameter("pageNo"));

			int pageSize = 5;
			if (op.equalsIgnoreCase("Next")) {

				pageNo++;

			}

			List<FacultyBean> list = FacultyModel.search(pageNo, pageSize);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("list", list);
			response.setContentType("text/html");
			ServletUtility.forward(getView(), request, response);
			;
		}

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_LIST_VIEW;
	}

}
