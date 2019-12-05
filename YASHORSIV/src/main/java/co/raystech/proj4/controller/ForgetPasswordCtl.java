package co.raystech.proj4.controller;

import java.io.IOException;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.raystech.proj4.bean.UserBean;
import co.raystech.proj4.model.UserModel;
import co.raystech.proj4.util.ServletUtility;

@WebServlet(name = "ForgetPasswordCtl", urlPatterns = { "/ForgetPasswordCtl" })
public class ForgetPasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public ForgetPasswordCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = (String) request.getParameter("operation");
		String email = request.getParameter("emailId");
		UserBean bean = new UserBean();
		bean.setLogin(email);
		try {
			UserModel.forgetPassword(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FORGET_PASSWORD_VIEW;
	}

}
