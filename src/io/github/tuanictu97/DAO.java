package io.github.tuanictu97;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private String url;
	private String user;
	private String password;
	private Connection connection;
	public DAO(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	protected void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(
                                        url, user, password);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
        	connection.close();
        }
    }
    public void createListStudentDefault() throws SQLException {
    	insertStdudent(new Student("John", "New York"));
    	insertStdudent(new Student("Beatriz", "New York"));
    	insertStdudent(new Student("Charles", "New York"));
    	insertStdudent(new Student("Diya", "New York"));
    	insertStdudent(new Student("Fatima", "New York"));
    	insertStdudent(new Student("Gabriel", "New York"));
    	insertStdudent(new Student("Eric", "New York"));
    }
    public List<Student> listAllStudents(){
    	List<Student> listStudent = new ArrayList<>();
    	try {            
            String sql = "SELECT * FROM students";
             
            connect();
             
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
             
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                 
                Student student = new Student(id, name, address);
                listStudent.add(student);
            }
             
            resultSet.close();
            statement.close();
             
            disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
         
        return listStudent;
    }
    
    public Student getStudent(int id){
    	Student student = null;
        try {
        	String sql = "SELECT * FROM students WHERE id = ?";
            
            connect();
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
             
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                 
                student = new Student(id, name, address);
            }
             
            resultSet.close();
            statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
         
        return student;
    }
    
    public boolean insertStdudent(Student student){
    	boolean rowInserted = false;
        try {
        	String sql = "INSERT INTO `students` (`id`, `name`, `address`) VALUES (NULL, ?, ?)";
            connect();
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getAddress());
             
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return rowInserted;
    }
    public boolean editStudent(Student student){
    	boolean rowInserted = false;
        try {
        	String sql = "UPDATE `students` SET `name` = ?, `address` = ? WHERE `students`.`id` = ?";
            connect();
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getAddress());
            statement.setInt(3, student.getId());
             
            rowInserted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return rowInserted;
    }
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM `students` WHERE `students`.`id` = ?";
        connect();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
}
