package co.raystech.proj4.controller;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.raystech.proj4.bean.UserBean;
import co.raystech.proj4.model.UserModel;
import co.raystech.proj4.util.DataUtility;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
/**
 * Servlet implementation class UserListCtl
 */
public class UserListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserBean bean = new UserBean();

		int pageNo = 1;
		int pageSize = 5;

		List<UserBean> l = UserModel.search(pageNo, pageSize);

		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("list", l);
		ServletUtility.forward(getView(), request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("delete");
		String[] ids = request.getParameterValues("ids");

		if (op.equalsIgnoreCase(OP_DELETE)) {
			for (String id : ids) {
				System.out.println(id + "   ids userlisctlpost");

				UserBean bean = new UserBean();
				bean.setId(DataUtility.getLong(id));
				UserModel.delete(bean);
			}
			int pageNo = 1;
			int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

			List<UserBean> l = UserModel.search(pageNo, pageSize);

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);

			request.setAttribute("list", l);
			ServletUtility.forward(getView(), request, response);
			response.setContentType("text/html");
		}

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.USER_LIST_VIEW;
	}

}
