package data;

import java.sql.*;
import dev.ciroh.Credentials;
public class DbConnector {
	private static DbConnector instance;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = Credentials.HOST ;
	private String port = Credentials.PORT;
	private String user= Credentials.USER;
	private String password = Credentials.PASSWORD;
	private String db = Credentials.DB;
	private int connected = 0;
	private Connection conn = null;
	
	private DbConnector() {
		try {
			Class.forName(driver);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DbConnector getInstance() {
		
		if (instance == null) {
			instance = new DbConnector();
		}
		return instance;
	}
	
	public Connection getConn() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db,user,password );
													}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		connected++;
		return conn;
		
	}
	
	public void releaseConn() {
		connected-- ;
		try {
			if (connected<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
   }
