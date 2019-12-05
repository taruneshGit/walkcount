package co.raystech.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.log.PackageNames;

import co.raystech.proj4.bean.CollegeBean;
import co.raystech.proj4.util.JDBCDataSource;

public class CollegeModel {

	public static List<CollegeBean> search(CollegeBean bean, int pageNo, int pageSize) {
		// CollegeBean bean=new CollegeBean();
		List<CollegeBean> list = new ArrayList<CollegeBean>();
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			StringBuffer buffer = new StringBuffer("select * from st_college where TRUE ");
			if (bean != null) {

				if (bean.getName() != null) {
					buffer.append(" and name like '" + bean.getName() + "%'");
				}

			}

			pageNo = (pageNo - 1) * pageSize;
			buffer.append("LIMIT " + pageNo + " , " + pageSize);
			System.out.println(buffer);
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean = new CollegeBean();
				bean.setId(resultSet.getLong(1));
				bean.setName(resultSet.getString(2));
				bean.setAddress(resultSet.getString(3));
				bean.setState(resultSet.getString(4));
				bean.setCity(resultSet.getString(5));
				bean.setMobileNo(resultSet.getString(6));
				list.add(bean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public static List<CollegeBean> list() {
		List<CollegeBean> list = new ArrayList<CollegeBean>();

		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			StringBuffer buffer = new StringBuffer("select * from st_college where TRUE ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			CollegeBean bean = null;
			while (resultSet.next()) {
				bean = new CollegeBean();
				bean.setId(resultSet.getLong(1));
				bean.setName(resultSet.getString(2));
				bean.setAddress(resultSet.getString(3));
				bean.setState(resultSet.getString(4));
				bean.setCity(resultSet.getString(5));
				bean.setMobileNo(resultSet.getString(6));
				list.add(bean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Integer delete(CollegeBean bean) {
		// List<CollegeBean> list = new ArrayList<CollegeBean>();
		Connection connection = null;
		int i = 0;
		try {
			connection = JDBCDataSource.getConnection();
			StringBuffer buffer = new StringBuffer("delete from st_college where id=? ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			preparedStatement.setLong(1, bean.getId());
			i = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;

	}
}
