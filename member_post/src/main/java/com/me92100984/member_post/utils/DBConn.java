package com.me92100984.member_post.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {
	public static Connection getConnection() throws ClassNotFoundException, SQLException { //여기선 던져도됨
//		Class.forName("org.mariadb.jdbc.Driver");
//		return DriverManager.getConnection("jdbc:mariadb://54.180.26.240:3306/post", "sample", "1234");
	
		Properties props = new Properties();
		InputStream is = DBConn.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String driver = props.getProperty("driver");
		String host = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		Class.forName(driver);
		return DriverManager.getConnection(host, username, password);
		}
		
		public static void main(String[] args) throws Exception {
			System.out.println(getConnection());
		}
}
