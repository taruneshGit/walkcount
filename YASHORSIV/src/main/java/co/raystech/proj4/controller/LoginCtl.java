package co.raystech.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.raystech.proj4.bean.BaseBean;
import co.raystech.proj4.bean.UserBean;
import co.raystech.proj4.model.UserModel;
import co.raystech.proj4.util.DataUtility;
import co.raystech.proj4.util.DataValidator;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		String login = DataUtility.getString(request.getParameter("login"));
		bean.setLogin(login);
		String password = DataUtility.getString(request.getParameter("login"));
		bean.setPassword(password);
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		UserBean bean = (UserBean) populateBean(request);
		if (DataValidator.isNull(bean.getLogin())) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login"));
			pass = false;
		} else if (DataValidator.isNotNull(bean.getLogin())) {

			if (!DataValidator.isEmail(bean.getLogin())) {

				request.setAttribute("login", PropertyReader.getValue("error.email", "Login"));
				pass = false;
			}
		}
		return pass;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		
		
		//s.append("abc");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String op = request.getParameter("operation");

		UserBean bean = new UserBean();
		bean.setLogin(login);
		bean.setPassword(password);

		System.out.println(login);
		System.out.println(password);
		// if (op.equalsIgnoreCase(BaseCtl.OP_LOGIN)) {
		if (op.equalsIgnoreCase("Login")) {

			UserModel model = new UserModel();
			UserBean bean2 = model.authenticate(bean);

			if (bean2 != null) {

				HttpSession session = request.getSession(true);
				session.setAttribute("user", bean2);
				ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
			} else {
				System.out.println("null.. no val");

			}

		}

	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
