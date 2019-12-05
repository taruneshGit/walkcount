package co.raystech.proj4.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.raystech.proj4.bean.CourseBean;
import co.raystech.proj4.model.CourseModel;
import co.raystech.proj4.util.DataUtility;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;

@WebServlet(name = "CourseListCtl", urlPatterns = { "/ctl/CourseListCtl" })
public class CourseListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public CourseListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		CourseBean bean = new CourseBean();
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		List<CourseBean> list = CourseModel.search(pageNo, pageSize);
		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		// ServletUtility.setList(list, request);

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

		int pageNo = DataUtility.getInt(request.getParameter("pageNo")) == 0 ? 1
				: DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		String op = request.getParameter("operation");
		System.out.println("before next op pageNumber=" + pageNo);
		System.out.println("before next op pageSize=" + pageSize);
		if (op.equalsIgnoreCase(BaseCtl.OP_NEXT) || op.equalsIgnoreCase(BaseCtl.OP_PREVIOUS)) {

			if (op.equalsIgnoreCase(BaseCtl.OP_NEXT)) {

				pageNo++;

			}
			if (op.equalsIgnoreCase(BaseCtl.OP_PREVIOUS)) {

				pageNo--;
			}

		}
		List<CourseBean> list = CourseModel.search(pageNo, pageSize);
		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		// ServletUtility.setList(list, request);

		response.setContentType("text/html");
		ServletUtility.forward(getView(), request, response);
		// doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_LIST_VIEW;
	}

}
