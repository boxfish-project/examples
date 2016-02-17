package com.lenicliu.java.sql;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.h2.tools.RunScript;

public class HandleResultSet {
	public static void main(String[] args) {
		// load driver class [required]
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		// get connection [required]
		try {
			conn = DriverManager.getConnection("jdbc:h2:mem:simple", "sa", "sa");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// init schema
		try {
			RunScript.execute(conn, new FileReader("src/main/resources/schema.sql"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		// prepare statment
		try {
			stmt = conn.prepareStatement("select * from profile");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// execute statement
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Map<String, Object>> records = new ArrayList<>();

		// handle result to map
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int column_count = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> record = new LinkedHashMap<>();
				for (int i = 1; i <= column_count; i++) {
					record.put(metaData.getColumnLabel(i).toLowerCase(), rs.getObject(i));
				}
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// release resources
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// print record list
		for (Map<String, Object> record : records) {
			System.out.println(record);
		}
	}
}