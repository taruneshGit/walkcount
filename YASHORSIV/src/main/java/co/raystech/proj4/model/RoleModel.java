package co.raystech.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.raystech.proj4.bean.RoleBean;
import co.raystech.proj4.util.JDBCDataSource;

public class RoleModel {

	public static List list() {
		RoleBean bean = null;
		List<RoleBean> list = new ArrayList<RoleBean>();
		StringBuffer sql = new StringBuffer("select * from st_role");

		try {
			Connection connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				bean = new RoleBean();
				bean.setId(resultSet.getLong(1));
				bean.setName(resultSet.getString(2));
				bean.setDescription(resultSet.getString(3));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
