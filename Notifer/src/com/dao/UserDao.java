package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.bean.*;



public class UserDao {
	
	
	

	private String dburl="jdbc:mysql://localhost:3306/userdb";
	private String dbuname="root";
	private String dbpassword="Shyam1999@";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	
	private static final String ins="INSERT INTO users"+ " (name,email,country) VALUES "
			+ "(?, ?, ?)";
	
	private static final String selbyid="select id,name,email,country from users where id=?";
	private static final String selall="select * from users";
	private static final String del="delete from users where id=?";
	private static final String update="update users set name=?, email=?, country=? where id=?";
	public UserDao() {
	
	
	}
	
	
	
	protected Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(dburl,dbuname,dbpassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	//login
	
	
	
	//insert
	
	
	public void insertuser(Userbeam user,String k) throws SQLException {
		System.out.println(ins);
		System.out.print(k);
		try (Connection con=getConnection();
				PreparedStatement per=con.prepareStatement("INSERT INTO "+k+ " (name,email,country) VALUES "
						+ "(?, ?, ?)");){
					per.setString(1, user.getName());
					per.setString(2, user.getEmail());
					per.setString(3, user.getCountry());
					System.out.println(per);
					
				
					per.executeUpdate();
				}catch (SQLException e) {
					System.out.print(e);
				}	
	}
	
	//select by id
	
	
	public Userbeam selectUser(int id,String username)
	{
		Userbeam user=null;
		try (Connection con=getConnection();
				PreparedStatement per=con.prepareStatement("select id,name,email,country from "+username+" where id=?");){
			per.setInt(1, id);
			System.out.println(per);
			ResultSet rs=per.executeQuery();
			while(rs.next())
			{
				String name=rs.getString("name");
				String email=rs.getString("email");
				String country=rs.getString("country");
				user= new Userbeam(id,name,email,country);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return user;
	}
	
	
	
	public List<Userbeam> selectAll(String U){
		List<Userbeam> users= new ArrayList<>();
		try (Connection con=getConnection();
				PreparedStatement per=con.prepareStatement("select * from "+U);){
				System.out.println(per);
				ResultSet rs=per.executeQuery();
				while(rs.next())
				{
					int id=rs.getInt("id");
					String name=rs.getString("name");
					String email=rs.getString("email");
					String country=rs.getString("country");
					users.add(new Userbeam(id,name,email,country));
				
				
			
		}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}
	
	
	//update
	
	
	public boolean updateUser(Userbeam user,String username) throws SQLException
	{
		boolean rowupd;
		try (Connection con=getConnection();
				PreparedStatement per=con.prepareStatement("update "+username+" set name=?, email=?, country=? where id=?");){
				System.out.println("update user"+per);
				per.setString(1, user.getName());
				per.setString(2, user.getEmail());
				per.setString(3, user.getCountry());
				per.setInt(4, user.getId());
				rowupd=per.executeUpdate()>0;
				
		} 
		return rowupd;
		
	}
	
	//delete user
	
	public boolean delUser(int id,String username) throws SQLException {
		boolean rowdel;
		try (Connection con=getConnection();
				PreparedStatement per=con.prepareStatement("delete from "+username+" where id=?");){
				per.setInt(1, id);
				rowdel=per.executeUpdate()>0;
		
		}
		return rowdel;
		
		
	}



	
	
	
	

}
