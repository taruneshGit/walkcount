package co.raystech.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.raystech.proj4.bean.RoleBean;
import co.raystech.proj4.bean.UserBean;
import co.raystech.proj4.model.RoleModel;
import co.raystech.proj4.model.UserModel;
import co.raystech.proj4.util.DataValidator;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

/**
 * Servlet implementation class UserCtl
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {

		List<RoleBean> roleList = RoleModel.list();

		request.setAttribute("roleList", roleList);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean userBean = new UserBean();
		String gangs = request.getParameter("gangs");
		userBean.setFirstName(gangs);
		UserModel.add(userBean);

	}

	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

}
