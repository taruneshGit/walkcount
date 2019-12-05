package co.raystech.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.raystech.proj4.bean.StudentBean;
import co.raystech.proj4.util.JDBCDataSource;

public class StudentModel {
	public static List<StudentBean> list() {
		// TODO Auto-generated method stub
		// return null;
		List<StudentBean> list = new ArrayList<>();
		StringBuffer buffer = new StringBuffer("Select * from student where true ");

		System.out.println(buffer);
		try {
			StudentBean bean = null;

			Connection connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				bean = new StudentBean();
				bean.setId(resultSet.getLong(1));
				bean.setCollegeName(resultSet.getString(3));
				bean.setFirstName(resultSet.getString(4));
				bean.setLastName(resultSet.getString(5));
				bean.setMobileNo(resultSet.getString(7));
				bean.setEmail(resultSet.getString(8));
				list.add(bean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("student catch");

		}

		return list;

	}

	public static List<StudentBean> search(StudentBean bean, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		// return null;
		List<StudentBean> list = new ArrayList<StudentBean>();
		StringBuffer buffer = new StringBuffer("Select * from student where true ");
		if (bean != null) {

			if (bean.getFirstName() != null) {

				buffer.append("and first_name like '" + bean.getFirstName() + "%'");
			}
			System.out.println(buffer);

		}
		// System.out.println(buffer);
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;
			buffer.append(" LIMIT " + pageNo + " , " + pageSize);
			System.out.println(buffer + "  query   ");
		}
		System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYY");
		System.out.println(buffer);
		try {

			Connection connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				bean = new StudentBean();
				bean.setId(resultSet.getLong(1));
				bean.setCollegeName(resultSet.getString(3));
				bean.setFirstName(resultSet.getString(4));
				bean.setLastName(resultSet.getString(5));
				bean.setMobileNo(resultSet.getString(7));
				bean.setEmail(resultSet.getString(8));
				list.add(bean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("student catch");

		}

		return list;

	}

	public static int delete(StudentBean bean) {
		Connection connection = null;
		int i = 0;
		try {
			connection = JDBCDataSource.getConnection();
			StringBuffer buffer = new StringBuffer("delete from student where id=?");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			preparedStatement.setLong(1, bean.getId());
			System.out.println(buffer);
			i = preparedStatement.executeUpdate();
			System.out.println(i + "delete record");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return i;

	}

}
