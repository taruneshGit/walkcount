package co.raystech.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.CoyoteOutputStream;

import co.raystech.proj4.bean.BaseBean;
import co.raystech.proj4.bean.CourseBean;
import co.raystech.proj4.model.CourseModel;
import co.raystech.proj4.util.DataUtility;
import co.raystech.proj4.util.DataValidator;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean flag = true;
	
		String courseName = request.getParameter("courseName");
		if (DataValidator.isNull(courseName) || courseName.length() < 0) {
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course NAme"));
			flag = false;
		}

		return flag;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CourseBean bean = new CourseBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseName(request.getParameter("courseName"));
		bean.setCourseDescription(request.getParameter("courseDescription"));
		bean.setCourseDuration(request.getParameter("courseDuration"));
		populateDTO(bean, request);
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseBean bean = (CourseBean) populateBean(request);
		System.out.println(bean.getId());
		CourseModel model = new CourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			System.out.println("in id > 0  condition");
			CourseBean bean2;
			bean2 = CourseModel.findByPk(id);
			ServletUtility.setBean(bean2, request);
		}
		ServletUtility.forward(getView(), request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long pk = 0;
		CourseBean bean = (CourseBean) populateBean(request);
		String op = request.getParameter("operation");
		if (op.equalsIgnoreCase(BaseCtl.OP_SAVE)) {

			if (bean.getId() != 0) {

				pk = CourseModel.update(bean);
				ServletUtility.setSuccessMessage("record successfully updated", request);

			} else {
				pk = CourseModel.add(bean);
				ServletUtility.setSuccessMessage("record successfully saved", request);
			}
			ServletUtility.forward(getView(), request, response);

		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_VIEW;
	}

}
