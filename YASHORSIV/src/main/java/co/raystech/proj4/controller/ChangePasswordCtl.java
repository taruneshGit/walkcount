package co.raystech.proj4.controller;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.catalina.User;

import co.raystech.proj4.bean.BaseBean;
import co.raystech.proj4.bean.UserBean;
import co.raystech.proj4.model.UserModel;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

@WebServlet(name = "ChangePasswordCtl", urlPatterns = { "/ctl/ChangePasswordCtl" })

public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public ChangePasswordCtl() {
		super();

	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		String confirmPassword = request.getParameter("confirmPassword");

		String newPassword = request.getParameter("newPassword");
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.password.not.matches", "Password"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		HttpSession session = request.getSession();
		bean = (UserBean) session.getAttribute("user");
		bean.setId(bean.getId());
		bean.setPassword(bean.getPassword());
		// bean.populateDTO(bean, request);
		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletUtility.forward(getView(), request, response);
		response.setContentType("text/html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean bean = (UserBean) populateBean(request);
		String operation = request.getParameter("operation");
		String oldPassword = request.getParameter("oldPassword");
		// String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		bean.setPassword(oldPassword);
		bean.setConfirmPassword(confirmPassword);
		UserModel.changePassword(bean);
		ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		/*
		 * if (bean.getPassword().equals(oldPassword)) { UserModel.changePassword(bean);
		 * } else {
		 * 
		 * ServletUtility.setErrorMessage(msg, request);
		 * 
		 * }
		 */ }

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
