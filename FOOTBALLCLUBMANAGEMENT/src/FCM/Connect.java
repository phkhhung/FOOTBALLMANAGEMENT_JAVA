package FCM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {
 public Connection getConnection() {
	 Connection conn = null;
	 try{
		   String userName = "root";
		   String password = "123456";
		   String url = "jdbc:mysql://localhost:3306/JAVA";
		   Class.forName ("com.mysql.cj.jdbc.Driver");
		   conn = DriverManager.getConnection(url, userName, password);
	} catch(Exception e){
		   System.out.println(e.getMessage());
	}
	return conn;
	}

 
 
public static void main(String[] args) {
	Connection conn = null;
	try{
		   String userName = "root";
		   String password = "123456";
		   String url = "jdbc:mysql://localhost:3306/JAVA";
		   Class.forName ("com.mysql.cj.jdbc.Driver");
		   conn = DriverManager.getConnection(url, userName, password);
		   System.out.println("Connection thanh cong");
		   
		   String sql = "SELECT * FROM SignupInfo";
		   PreparedStatement stm= conn.prepareStatement(sql);
		   stm = conn.prepareStatement(sql);
		   ResultSet rs = stm.executeQuery();	   
	} catch(Exception e){
		  // System.out.println(e.getMessage());
	}
}

}

	
	

