import java.sql.*;

public class Main {
	static Connection connect;
	public static void main(String[] args) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/sqlove", "sqlove", "pw321");
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("Select * from Restaurant");
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
