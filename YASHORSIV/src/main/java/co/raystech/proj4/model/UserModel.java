package co.raystech.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.raystech.proj4.bean.UserBean;
import co.raystech.proj4.exception.DataBaseException;
import co.raystech.proj4.util.EmailBuilder;
import co.raystech.proj4.util.EmailMessage;
import co.raystech.proj4.util.EmailUtility;
import co.raystech.proj4.util.JDBCDataSource;

public class UserModel {

	public static void delete(UserBean bean) {
		StringBuffer sql = new StringBuffer("delete from st_user where id=?");
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setLong(1, bean.getId());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Integer nextPK() throws DataBaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT MAX(ID) FROM ST_USER");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
				// pk = rs.getInt("id");
			}
			rs.close();

		} catch (Exception e) {
			throw new DataBaseException("Exception: Exception in getting PK");

		}
		return pk + 1;

	}

	public static Integer add(UserBean userBean) {
		int pk = 0;
		StringBuffer sql = new StringBuffer("insert into wassepur values(?)");
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, userBean.getFirstName());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk;

	}

	public UserBean authenticate(UserBean bean) {
		System.out.println("----------in model--------");
		System.out.println(bean.getLogin());
		System.out.println(bean.getPassword());
		System.out.println("----------in model--------");
		// String Login=bean.getLogin();
		Connection connection = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN= ? AND PASSWORD= ?");

		try {
			connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, bean.getLogin());
			preparedStatement.setString(2, bean.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean = new UserBean();
				bean.setId(resultSet.getLong(1));
				bean.setFirstName(resultSet.getString(2));
				bean.setLastName(resultSet.getString(3));
				bean.setMobileNo(resultSet.getString(4));
			}

			System.out.println("----------in model after--------");

			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println("----------in model------after--");

		} catch (Exception e) {
			e.printStackTrace();

		}
		JDBCDataSource.closeConnection(connection);

		return bean;

	}

	public static List<UserBean> search(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<UserBean> list = new ArrayList<>();
		StringBuffer buffer = new StringBuffer("Select * from st_user where 1=1");

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;
			buffer.append(" Limit " + pageNo + " , " + pageSize);

		}

		System.out.println(buffer + "          lllllllll");
		Connection connection = null;
		UserBean bean = null;
		try {
			connection = JDBCDataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bean = new UserBean();
				bean.setId(resultSet.getLong(1));
				bean.setFirstName(resultSet.getString(2));
				bean.setLastName(resultSet.getString(3));
				bean.setMobileNo(resultSet.getString(4));
				list.add(bean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("user model catch");
		}

		return list;
	}

	public static UserBean findByPk(long id) {
		// TODO Auto-generated method stub
		// long pk=0;
		UserBean bean = new UserBean();
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			String sql = "select * from st_user where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfullLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bean;

	}

	public static void forgetPassword(UserBean bean) throws Exception {
		// UserBean bean2 = new UserBean();
		String login = bean.getLogin();
		System.out.println(login);

		try {
			System.out.println("before findbylogin called");
			bean = findByLogin(login);
			System.out.println("after findbylogin called");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());
			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			String message = EmailBuilder.getForgetPasswordMessage(map);
			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setTo(login);
			emailMessage.setSubject("Password recover");
			emailMessage.setMessage(message);
			emailMessage.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(emailMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserBean findByLogin(String login) {
		// TODO Auto-generated method stub
		// long pk=0;
		System.out.println(login);
		UserBean bean = null;
		Connection connection = null;
		try {
			connection = JDBCDataSource.getConnection();
			String sql = "select * from st_user where login=?";
			System.out.println("login details in findbylogin " + login);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfullLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				System.out.println("fastname------------" + bean.getFirstName());
				System.out.println("login ===============" + bean.getLogin());
				System.out.println("lastname-----------------" + bean.getLastName());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bean;

	}

	public static void changePassword(UserBean bean) {
		// TODO Auto-generated method stub
		String password = bean.getPassword();
		UserBean bean2 = new UserBean();
		bean2 = findByPk(bean.getId());
		if (bean2.getPassword().equals(password)) {
			String sql = "update st_user set password=? where id=?";
			Connection connection = null;
			try {
				connection = JDBCDataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, bean.getConfirmPassword());
				preparedStatement.setLong(2, bean.getId());
				int i = preparedStatement.executeUpdate();
				System.out.println(i + "  record updated ");
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("firstName", bean2.getFirstName());
				map.put("lastName", bean2.getLastName());
				map.put("login", bean2.getLogin());
				map.put("password", bean2.getPassword());
				String message = EmailBuilder.getChangePasswordMessage(map);
				EmailMessage emailMessage = new EmailMessage();
				emailMessage.setTo(bean2.getLogin());
				emailMessage.setSubject("Password changed");
				emailMessage.setMessage(message);
				emailMessage.setMessageType(EmailMessage.HTML_MSG);
				EmailUtility.sendMail(emailMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
