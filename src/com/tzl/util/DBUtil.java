package com.tzl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

//	// ï¿½ï¿½È¡ï¿½ï¿½Ý¿ï¿½ï¿½ï¿½ï¿½ï¿?
//	public static Connection getConn() {
//		String url = "jdbc:sqlserver://localhost:1433;databaseName=meitao";
//		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//		Connection conn = null;
//		// 1. ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æ£ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿?
//		try {
//			Class.forName(driver);
//			// 2.ï¿½ï¿½È¡ï¿½ï¿½Ý¿ï¿½ï¿½ï¿½ï¿½ï¿?
//			conn = DriverManager.getConnection(url, "sa", "123");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return conn;
//
//	}
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=meitao";
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs= null;
	private static String uid = "sa";
	private static String pid = "123";
	public static PreparedStatement createPreparedStatement(String sql,Object... objects) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,uid,pid);
			pst = con.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				pst.setObject(i+1, objects[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pst;
	}
	public static ResultSet executeExcute(String name,String pwd,String sql) throws Exception{
		pst = createPreparedStatement(sql,name,pwd);
		rs = pst.executeQuery();
		return rs;
		
	}
	public static boolean executeUpdate(String sql,Object... objects) throws SQLException {
		boolean bo = false;
		try {
			pst = createPreparedStatement(sql,objects);
			int i = pst.executeUpdate();
			if (i>0) {
				bo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				close(null,pst,con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bo;
	}
	public static ResultSet query(String sql,Object... objects) throws ClassNotFoundException {
		try {
			pst = createPreparedStatement(sql,objects);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static void close(ResultSet rs,PreparedStatement pst,Connection con) throws Exception{  
        if(rs!=null){
            rs.close();
            rs = null;
        }
        if(pst!=null){
            pst.close();
        }
        if(con!=null){
            con.close();
        }
	}
	public static void close() throws Exception{  
		if(rs!=null){
            rs.close();
            
        }
        if(pst!=null){
            pst.close();
        }
        if(con!=null){
            con.close();
        }
	}
}
