package co.raystech.proj4.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestJDBCDataSource1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String sql = "select id,first_name,last_name,login from st_user";

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery(sql);
		System.out.println(sql);
		System.out.println(rs);

		while (rs.next()) {

			System.out.print(rs.getString(1));
			System.out.print("\t" + rs.getString(2));
			System.out.print("\t" + rs.getString(3));
			System.out.println(rs.getString(4));

		}
		JDBCDataSource.closeConnection(conn);
	}

}
