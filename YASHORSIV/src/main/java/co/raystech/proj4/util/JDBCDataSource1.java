package co.raystech.proj4.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource1 {
	private static JDBCDataSource1 jds = null;

	private ComboPooledDataSource ds = null;

	private JDBCDataSource1() {
		try {
			ds = new ComboPooledDataSource();

			ResourceBundle rb = ResourceBundle.getBundle("co.raystech.proj4.bundle.system");
			ds = new ComboPooledDataSource();

			ds.setDriverClass(rb.getString("driver"));
			ds.setJdbcUrl(rb.getString("url"));
			ds.setUser(rb.getString("username"));
			ds.setPassword("password");
			ds.setInitialPoolSize(new Integer((String) rb.getString("initialPoolSize")));
			ds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
			ds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
			ds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
			ds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));

		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static JDBCDataSource1 getInstance() {

		if (jds == null) {
			jds = new JDBCDataSource1();
		}
		return jds;
	}

	public static Connection getConnection() throws Exception {
		try {
			return getInstance().ds.getConnection();

		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
	}

	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/*
	 * public static void trnRollback(Connection connection)throws
	 * ApplicationException{
	 * 
	 * if(connection !=null) { try { connection.rollback();
	 * 
	 * } catch (SQLException ex) { // TODO: handle exception throw new
	 * ApplicationException(ex.toString()); }
	 * 
	 * } }
	 */
}