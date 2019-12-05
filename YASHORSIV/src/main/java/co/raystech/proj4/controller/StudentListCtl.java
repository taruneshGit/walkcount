package co.raystech.proj4.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.raystech.proj4.bean.BaseBean;
//import co.raystech.proj4.bean.CollegeBean;
import co.raystech.proj4.bean.StudentBean;
import co.raystech.proj4.model.StudentModel;
import co.raystech.proj4.util.DataUtility;
// co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

/**
 * Servlet implementation class StudentCtl
 */
@WebServlet(name = "StudentListCtl", urlPatterns = "/ctl/StudentListCtl")
public class StudentListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		StudentBean bean = new StudentBean();
		bean.setFirstName(DataUtility.getString(request.getParameter("search")));

		System.out.println("stulistctl pop" + bean.getFirstName());
		populateDTO(bean, request);
		return bean;

	}

	@Override
	protected void preload(HttpServletRequest request) {
		int pageNo = 1;
		int pageSize = 5;
		List<StudentBean> list = StudentModel.list();
		int buttonNumber = list.size() / pageSize;

		if (list.size() % pageSize != 0) {
			buttonNumber++;

		}
		request.setAttribute("buttonNumber", buttonNumber);
	}

	public StudentListCtl() {
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

		StudentBean bean = new StudentBean();

		int pageNo = 1;
		// int pageSize=Integer.parseInt(PropertyReader.getValue("page.size"));
		int pageSize = 5;
		System.out.println("pageNo=" + pageNo);
		System.out.println("pageSize=" + pageSize);
		List<StudentBean> list = StudentModel.search(bean, pageNo, pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("list", list);

		response.setContentType("text/html");
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StudentBean bean = (StudentBean) populateBean(request);
		String op = request.getParameter("operation");
		System.out.println(op + "  in studentilist post");

		int pageNo = Integer.parseInt(request.getParameter("pageNo")) == 0 ? 1
				: Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = 5;

		System.out.println("pageNo " + pageNo);
		System.out.println("pageSize =" + pageSize);

		if (op.equalsIgnoreCase("Next") || op.equalsIgnoreCase("previous")) {

			if (op.equalsIgnoreCase("Next")) {

				pageNo++;
				System.out.println("pageNo " + pageNo);

			}
			if (op.equalsIgnoreCase("Previous")) {
				pageNo--;

			}
		}

		else if (op.equalsIgnoreCase("Delete")) {

			System.out.println("in delete");

			String[] id = request.getParameterValues("ids");

			if (id != null) {

				for (String string : id) {

					bean.setId(DataUtility.getLong(string));
					StudentModel.delete(bean);
					// System.out.println("");

				}
			}

		}
		bean = (StudentBean) populateBean(request);

		List<StudentBean> list = StudentModel.search(bean, pageNo, pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		System.out.println("pageSize =" + pageSize);
		request.setAttribute("list", list);
		ServletUtility.forward(getView(), request, response);
		response.setContentType("text/html");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.STUDENT_LIST_VIEW;
	}

}
