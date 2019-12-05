package co.raystech.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.raystech.proj4.bean.FacultyBean;
import co.raystech.proj4.util.JDBCDataSource;

public class FacultyModel {

	public static List<FacultyBean> search(int pageNo, int pageSize) {
		List<FacultyBean> list = new ArrayList<FacultyBean>();
		FacultyBean bean = null;
		StringBuffer buffer = new StringBuffer(
				"SELECT f.`ID`,u.`FIRST_NAME`,u.`LAST_NAME`,u.`MOBILE_NO`,u.`LOGIN`,c.`NAME`,s.`NAME`  FROM st_faculty AS f INNER JOIN st_user AS u  ON f.`USER_ID`=u.`ID` INNER JOIN st_college AS c ON f.`COLLEGE_ID`=c.`ID` INNER JOIN st_subject AS s ON f.`SUBJECT_ID`=s.`ID` where TRUE");
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			buffer.append(" LIMIT " + pageNo + " , " + pageSize);

		}
		System.out.println(buffer + " query ");

		try {
			Connection connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean = new FacultyBean();
				bean.setId(resultSet.getLong(1));
				bean.setFirstName(resultSet.getString(2));
				bean.setLastName(resultSet.getString(3));
				bean.setMobileNo(resultSet.getString(4));
				bean.setEmailId(resultSet.getString(5));
				bean.setCollegeName(resultSet.getString(6));
				bean.setSubjectName(resultSet.getString(7));
				list.add(bean);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		// TODO Auto-generated method stub

	}

	public static List<FacultyBean> list() {
		List<FacultyBean> list = new ArrayList<FacultyBean>();
		FacultyBean bean = null;
		StringBuffer buffer = new StringBuffer(
				"SELECT f.`ID`,u.`FIRST_NAME`,u.`LAST_NAME`,u.`MOBILE_NO`,u.`LOGIN`,c.`NAME`,s.`NAME`  FROM st_faculty AS f INNER JOIN st_user AS u  ON f.`USER_ID`=u.`ID` INNER JOIN st_college AS c ON f.`COLLEGE_ID`=c.`ID` INNER JOIN st_subject AS s ON f.`SUBJECT_ID`=s.`ID` where TRUE");

		try {
			Connection connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean = new FacultyBean();
				bean.setId(resultSet.getLong(1));
				bean.setFirstName(resultSet.getString(2));
				bean.setLastName(resultSet.getString(3));
				bean.setMobileNo(resultSet.getString(4));
				bean.setEmailId(resultSet.getString(5));
				bean.setCollegeName(resultSet.getString(6));
				bean.setSubjectName(resultSet.getString(7));
				list.add(bean);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		// TODO Auto-generated method stub

	}

	public static void add(FacultyBean bean) {
		// TODO Auto-generated method stub
		FacultyBean bean2 = null;

	}
}