import java.sql.*;
import javax.swing.*;
public class Database {

			Connection fdb = null;	// file DataBase connection
			Connection conn = null;  // User info DataBase
			Connection adb = null; 	// activity log DataBase connection
			public static void main(String[] args) {
				courseConnector();
			}
			
			public static Connection dbConnector()
			{
				/*try {
					Class.forName("org.sqlite.JDBC");
					Connection conn = DriverManager.getConnection("JDBC:sqlite:C:\\Users\\Rajesh Khanna\\eclipse-workspace\\UserDetails.sqlite");
					//JOptionPane.showMessageDialog(null,"Connection Successful");
					return conn;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					return null;
				}*/
				try {
					//Class.forName("org.sqlite.JDBC");
					String driver = "com.mysql.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/courseDB";
					String username = "root";
					String password = "qwer";
					Connection conn = DriverManager.getConnection(url,username,password);
					//JOptionPane.showMessageDialog(null,"Connection Successful");
					return conn;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					return null;
				}
			}
			public static Connection fdbConnector()
			{
				try {
					Class.forName("org.sqlite.JDBC");
					Connection fdb = DriverManager.getConnection("JDBC:sqlite:C:\\Users\\Rajesh Khanna\\eclipse-workspace\\coursedb.sqlite");
					//JOptionPane.showMessageDialog(null,"Connection Successful");
					return fdb;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					return null;
				}
			}/**
			public static Connection adbConnector()
			{
				try {
					Class.forName("org.sqlite.JDBC");
					Connection adb = DriverManager.getConnection("JDBC:sqlite:C:\\Users\\Rajesh Khanna\\eclipse-workspace\\Activitydb.sqlite");
					//JOptionPane.showMessageDialog(null,"Connection Successful");
					return adb;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					return null;
				}
			}*/
			public static Connection courseConnector()
			{
				try {
					//Class.forName("org.sqlite.JDBC");
					String driver = "com.mysql.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/courseDB";
					String username = "root";
					String password = "qwer";
					Connection cconn = DriverManager.getConnection(url,username,password);
					//JOptionPane.showMessageDialog(null,"Connection Successful");
					return cconn;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
					return null;
				}
			}
}