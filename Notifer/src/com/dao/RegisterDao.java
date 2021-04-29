package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xadmin.usermanagement.bean.Registerbeam;



public class RegisterDao {
	
	
	private String dburl="jdbc:mysql://localhost:3306/userdb";
	private String dbuname="root";
	private String dbpassword="Shyam1999@";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	
	
	
	public void loadDriver(String dbdriver)
	{
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public Connection getConnection()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection(dburl,dbuname,dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	public String insert(Registerbeam member)
	{
		loadDriver(dbdriver);
		Connection con=getConnection();
		String result="Date entered sucessully";
		String sql="insert into member values(?,?,?,?)";
		String q="CREATE TABLE "+member.getUname()+" (id INTEGER AUTO_INCREMENT, " +" name VARCHAR(255), " +  " email VARCHAR(255), " + " country VARCHAR(255), " +  " PRIMARY KEY ( id ))";
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getUname());
		ps.setString(2, member.getPassword());
		ps.setString(3, member.getEmail());
		ps.setString(4, member.getPhone());
		ps.executeUpdate();
		Statement stmt=con.createStatement();
		stmt.executeUpdate(q);
		
		
		con.close(); 
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="data not entered";
				
			}
		
		return result;
		
	}


	
	
	
	
	
	

}
