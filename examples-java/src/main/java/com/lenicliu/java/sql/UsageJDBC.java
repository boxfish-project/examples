package com.lenicliu.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageJDBC {
	public static void main(String[] args) {

		// load driver class [required]
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// get connection [required]
		try {
			conn = DriverManager.getConnection("jdbc:h2:mem:simple", "sa", "sa");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// create table
		try {
			stmt = conn.prepareStatement("create table profile(id int,username varchar(200))");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// insert
		try {
			stmt = conn.prepareStatement("insert into profile(id,username)values(?,?)");
			stmt.setInt(1, 1);
			stmt.setString(2, "lenicliu");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// select
		try {
			stmt = conn.prepareStatement("select id,username from profile where username = ?");
			stmt.setString(1, "lenicliu");
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("username"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// update
		try {
			stmt = conn.prepareStatement("update profile set username = ? where id = ?");
			stmt.setString(1, "after name");
			stmt.setInt(2, 1);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// check update result
		try {
			stmt = conn.prepareStatement("select id,username from profile where id = ?");
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("username"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// delete
		try {
			stmt = conn.prepareStatement("delete from profile where id = ?");
			stmt.setInt(1, 1);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// use function
		try {
			stmt = conn.prepareStatement("select count(*) from profile");
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("totals:\t" + rs.getInt(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// drop table
		try {
			stmt = conn.prepareStatement("drop table profile");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// release connection [required]
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}