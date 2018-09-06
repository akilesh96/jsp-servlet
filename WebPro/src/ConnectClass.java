import java.sql.*;
public class ConnectClass {
	private static Connection con;
	public static Connection getConnection() throws Exception{
		String url="jdbc:mysql://localhost:3306/test";
		String drive="com.mysql.jdbc.Driver";
		String user="root";
		Class.forName(drive);
		con=DriverManager.getConnection(url,user,"");
		return con;
	}
	public static void close(){
		try{
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
