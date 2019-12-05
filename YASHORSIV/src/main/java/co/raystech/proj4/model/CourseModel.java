package co.raystech.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.raystech.proj4.bean.CourseBean;
import co.raystech.proj4.util.JDBCDataSource;

public class CourseModel {

	public static Long nextPk() {
		long pk = 0;
		Connection connection = null;

		try {
			connection = JDBCDataSource.getConnection();

			String sql = "select max(id) from st_course";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pk = resultSet.getLong(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pk + 1;

		// TODO Auto-generated method stub

	}

	public static Long add(CourseBean bean) {
		long pk = 0;
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			String sql = "insert into st_course values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, nextPk());
			preparedStatement.setString(2, bean.getCourseName());
			preparedStatement.setString(3, bean.getCourseDescription());
			preparedStatement.setString(4, bean.getCourseDuration());
			preparedStatement.setString(5, bean.getCreatedBy());
			preparedStatement.setString(6, bean.getModifiedBy());
			preparedStatement.setTimestamp(7, bean.getCreatedDatetime());
			preparedStatement.setTimestamp(8, bean.getModifiedDatetime());
			pk = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pk;
	}

	public static Long update(CourseBean bean) {
		long pk = 0;
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			String sql = "UPDATE ST_COURSE SET NAME =?, DESCRIPTION=?, DURATION=? WHERE ID =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bean.getCourseName());
			preparedStatement.setString(2, bean.getCourseDescription());
			preparedStatement.setString(3, bean.getCourseDuration());
			preparedStatement.setLong(4, bean.getId());
			pk = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public static List<CourseBean> search(int pageNo, int pageSize) {
		List<CourseBean> list = new ArrayList<CourseBean>();

		Connection connection = null;
		CourseBean bean = null;
		try {
			connection = JDBCDataSource.getConnection();

			StringBuffer buffer = new StringBuffer("select * from st_course where true");
			buffer.append(" Limit " + pageNo + "," + pageSize);
			pageNo = (pageNo - 1) * pageSize;
			System.out.println("query:-" + buffer);
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean = new CourseBean();
				bean.setId(resultSet.getLong(1));
				bean.setCourseName(resultSet.getString(2));
				bean.setCourseDescription(resultSet.getString(3));
				bean.setCourseDuration(resultSet.getString(4));
				list.add(bean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static CourseBean findByPk(long id) {
		// TODO Auto-generated method stub
		// long pk=0;
		CourseBean bean = new CourseBean();
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			String sql = "select * from st_course where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean.setId(resultSet.getLong(1));
				bean.setCourseName(resultSet.getString(2));
				bean.setCourseDescription(resultSet.getString(3));
				bean.setCourseDuration(resultSet.getString(4));
				bean.setCreatedBy(resultSet.getString(5));
				bean.setModifiedBy(resultSet.getString(6));
				bean.setCreatedDatetime(resultSet.getTimestamp(7));
				bean.setModifiedDatetime(resultSet.getTimestamp(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bean;

	}
}