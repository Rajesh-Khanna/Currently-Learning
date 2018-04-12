import java.sql.*;

import javax.swing.JOptionPane;
public class Notification {
	Connection courseconn = null;
	String notice;
	
	Notification(){
		courseconn = Database.dbConnector();
	}
	
	public void sublist(String user,String msg){
		String subscribers[] = null;
		try {
			PreparedStatement pst = courseconn.prepareStatement("SELECT * FROM userdetails where username = '"+user+"';");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			subscribers = rs.getString("subscribe").toString().split(":>|<:");
			}
			for(String u : subscribers) {
				System.out.println(u);
				NOTIFY(u,msg);
			}
			rs.close();
			pst.close();			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"2@!");
			JOptionPane.showMessageDialog(null,e);
		}
		

	}
	
	public void NOTIFY(String user1,String msg){
		try {
			PreparedStatement pst = courseconn.prepareStatement("SELECT * FROM userdetails where username = '"+user1+"';");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			notice = rs.getString("notifications");
			}
			rs.close();
			pst.close();			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"3@!");
			JOptionPane.showMessageDialog(null,e);
		}

		try {
			notice = msg+":>|<:"+notice;
			System.out.println(notice);
			PreparedStatement pst = courseconn.prepareStatement("Update userdetails set notifications='"+notice+"' where username = '"+user1+"';");
			pst.execute();
			pst.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"33");
			JOptionPane.showMessageDialog(null,e);
		}
	}	
}
