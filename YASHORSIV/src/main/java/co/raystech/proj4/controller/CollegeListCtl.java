package co.raystech.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mchange.v2.log.PackageNames;

import co.raystech.proj4.bean.BaseBean;
import co.raystech.proj4.bean.CollegeBean;
import co.raystech.proj4.model.CollegeModel;
import co.raystech.proj4.util.DataUtility;
import co.raystech.proj4.util.PropertyReader;
import co.raystech.proj4.util.ServletUtility;
import javafx.scene.chart.PieChart.Data;

@WebServlet(name = "CollegeListCtl", urlPatterns = { "/ctl/CollegeListCtl" })
public class CollegeListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CollegeBean bean = new CollegeBean();
		bean.setName(request.getParameter("search"));
		populateDTO(bean, request);
		return bean;

	}

	@Override
	protected void preload(HttpServletRequest request) {

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		List<CollegeBean> list = (List<CollegeBean>) CollegeModel.list();
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
		// int pageNo=DataUtility.getLong(val)
		CollegeBean bean = new CollegeBean();
		int pageNo = 1;
		int pageSize = Integer.parseInt(PropertyReader.getValue("page.size"));
		List<CollegeBean> list = CollegeModel.search(bean, pageNo, pageSize);
		System.out.println("pagNO" + pageNo);
		System.out.println("pageSize" + pageSize);
		// System.out.println("list",list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("list", list);
		// ServletUtility.forward(getView(), request, response);

		ServletUtility.forward(getView(), request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getParameter("operation");
		System.out.println("before next operation in post method");
		int pageSize = Integer.parseInt(PropertyReader.getValue("page.size"));
		int pageNo = DataUtility.getInt(request.getParameter("pageNo")) == 0 ? 1
				: DataUtility.getInt(request.getParameter("pageNo"));
		System.out.println("before next op pageNumber=" + pageNo);
System.out.println("before Next operation");
		if (op.equalsIgnoreCase("Next") || op.equalsIgnoreCase("Previous")) {
			if (op.equalsIgnoreCase("Next")) {
				pageNo++;
			}
			if (op.equalsIgnoreCase("Previous")) {
				pageNo--;
			}
		}

		else if (op.equalsIgnoreCase("Delete")) {
			int i = 0;
			String[] id = request.getParameterValues("ids");
			if (id != null) {
				for (String string : id) {
					CollegeBean bean = new CollegeBean();
					bean.setId(DataUtility.getLong(string));
					i = CollegeModel.delete(bean);

				}
				System.out.println(i + "record deleted");
			}
		}

		System.out.println("in post clglistctl searchop " + op);
		CollegeBean bean = (CollegeBean) populateBean(request);
		List<CollegeBean> list = CollegeModel.search(bean, pageNo, pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("list", list);
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_LIST_VIEW;
	}

}
