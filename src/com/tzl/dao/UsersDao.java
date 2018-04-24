package com.tzl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tzl.entity.Users;
import com.tzl.util.DBUtil;


/**
 * @author ttadmin
 *
 */
public class UsersDao {

	/**
	 * @param users2
	 * @param 
	 */
	public boolean add(Users users2) {
		String name = "213";
		String sql = "insert account values(?,?,?,?,?,?)";
		boolean bo = false;
		try {
			bo = DBUtil.executeUpdate(sql, users2.getAccount(),users2.getPwd(),users2.getType(),users2.getOnline(),users2.getSex(),users2.getNickName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bo;
	}

	/**
	 * @param account
	 * @param object
	 * @return
	 * @param 
	 */
	public Users queryuser(String account, String pwd) {
		String sql = "select * from account where account=? and pwd=?";
		Users u = null;
		try {
			ResultSet rs = DBUtil.executeExcute(account, pwd, sql);
			while (rs.next()) {			
				u = new Users();
				u.setAccount(rs.getString("account"));
				u.setPwd(rs.getString("pwd"));
				u.setType(rs.getInt("type"));
				u.setOnline(rs.getInt("online"));
				u.setSex(rs.getString("sex"));
				u.setNickName(rs.getString("nickName"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBUtil.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}

	/**
	 * @param i
	 * @param account
	 * @param 
	 */
	public boolean updateuser(int i, String account) {
		String sql = "update account set online=? where account = ?";
		boolean bo = false;
		try {
			bo = DBUtil.executeUpdate(sql, i,account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bo;
	}

	/**
	 * @return
	 * @param 
	 */
	public List<Users> queryonline() {
		List<Users> list = new ArrayList<Users>();
		ResultSet rs = null;
		String sql = "select * from account where online=1 and type =0";
		Users u = null;
		try {
			rs = DBUtil.query(sql);
			while (rs.next()) {			
				u = new Users();
				u.setAccount(rs.getString("account"));
				u.setPwd(rs.getString("pwd"));
				u.setType(rs.getInt("type"));
				u.setOnline(rs.getInt("online"));
				u.setSex(rs.getString("sex"));
				u.setNickName(rs.getString("nickName"));
				list.add(u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBUtil.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}
