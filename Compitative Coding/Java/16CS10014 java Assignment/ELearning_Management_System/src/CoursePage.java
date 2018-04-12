import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class CoursePage extends JFrame {

	private JPanel contentPane;
	private JTextField commentField;
	private String courseIndex;
	String uname;
	String UT = "Admin";
	String courseName;
	String Author;
	String comments;
	String subscribers;
	Notification notification;
	String channels;
	JFrame mf = null;
	int state;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursePage frame = new CoursePage(null,null,"Rajesh");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"4");
				}
			}
		});
	}
	Connection courseconn = null;
	Connection connection = null;
	/**
	 * Create the frame.
	 */
	
	public CoursePage(String index,JFrame mFrame,String name) {
		mf = mFrame;
		courseIndex = index;
		uname = name;
		courseconn = Database.courseConnector();
		connection = Database.dbConnector();
		//JOptionPane.showMessageDialog(null,courseIndex);	

		notification = new Notification();
		//JOptionPane.showMessageDialog(null,"das");	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {	/**
			PreparedStatement pst;
			if(UT.equals("Admin")) {
				pst = courseconn.prepareStatement("SELECT * FROM filedb where id = ?");
				pst.setString(1, courseIndex);
			}else {
			}*/
			PreparedStatement pst;
			pst = courseconn.prepareStatement("SELECT * FROM filedb where id = ?;");
			pst.setString(1, courseIndex.toString());

			ResultSet rs=pst.executeQuery();
			System.out.println( courseIndex.toString());
			while(rs.next()) {
			System.out.println(rs.getString("coursename"));
			courseName = rs.getString("coursename");
			Author = rs.getString("username");
			comments = rs.getString("comments").toString();
			state = rs.getInt("state");
			}
			rs.close();
			pst.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"*2*");

			JOptionPane.showMessageDialog(null,e);
	
		}

		try {
			PreparedStatement pst = connection.prepareStatement("SELECT * FROM userdetails where username = ?");
			pst.setString(1, uname);
			ResultSet rs=pst.executeQuery();
			while (rs.next()){
			UT = rs.getString("userType");
			subscribers = rs.getString("subscribe").toString();
			//JOptionPane.showMessageDialog(null,subscribers);
			}
			pst = connection.prepareStatement("SELECT * FROM userdetails where username = ?");
			pst.setString(1, Author);
			rs=pst.executeQuery();
			while (rs.next()){
			//UT = rs.getString("userType");
			channels = rs.getString("subscribe").toString();
			}		
			rs.close();
			pst.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"*0*");
			JOptionPane.showMessageDialog(null,e);
		}
				

		JLabel lblCourseTitle = new JLabel("Course Title");
		lblCourseTitle.setText(courseName);
		lblCourseTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblCourseTitle.setBounds(12, 13, 414, 40);
		contentPane.add(lblCourseTitle);
		
		JButton btnSubscribe = new JButton("Subscribe");
		btnSubscribe.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnSubscribe.setForeground(new Color(255, 255, 255));
		btnSubscribe.setBackground(Color.RED);
		if(channels.contains(uname)){
			btnSubscribe.setText("Subscribed");
		}
		btnSubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					PreparedStatement pst;
					//JOptionPane.showMessageDialog(null,subscribers);
					if(!channels.contains(uname)){
						channels = channels+uname+":>|<:";
						pst = courseconn.prepareStatement("Update userdetails set subscribe='"+channels+"' where username = '"+Author+"';");
						pst.execute();
						pst.close();
						btnSubscribe.setText("Subscribed");
						//JOptionPane.showMessageDialog(null,channels);
						//pst = courseconn.prepareStatement("Update userdetails set subscribe='"+subscribers+":>|<:"+Author+"' where username = '"+uname+"';");
						//pst.execute();
						//pst.close();
					}
					else {
						channels = channels.replace(uname+":>|<:","");
						pst = courseconn.prepareStatement("Update userdetails set subscribe='"+channels+"' where username = '"+Author+"';");
						pst.execute();
						pst.close();
						//JOptionPane.showMessageDialog(null,channels);
						btnSubscribe.setText("Subscribe");						
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"66");
					JOptionPane.showMessageDialog(null,e);
				}

			}
		});
		btnSubscribe.setBounds(462, 65, 105, 41);
		contentPane.add(btnSubscribe);
		
		JLabel lblAuthor = new JLabel(Author);
		lblAuthor.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));
		lblAuthor.setBounds(336, 81, 105, 25);
		contentPane.add(lblAuthor);
		
		commentField = new JTextField();
		commentField.setBounds(12, 292, 401, 40);
		contentPane.add(commentField);
		commentField.setColumns(10);
		
		JLabel lblComments = new JLabel("Comments :");
		lblComments.setBounds(12, 263, 105, 16);
		contentPane.add(lblComments);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 347, 515, 147);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		DefaultListModel DLM = new DefaultListModel();
		String commentsArray[] = comments.split(":>|<:");
		for (String comnt : commentsArray){
			DLM.addElement(comnt);
		}
		list.setModel(DLM);
		
		
		JButton btnPost = new JButton("post");
		btnPost.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnPost.setBackground(UIManager.getColor("nimbusInfoBlue"));
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					PreparedStatement pst;
					pst = courseconn.prepareStatement("Update filedb set comments='"+comments+":>|<:"+uname+": "+commentField.getText()+"' where id = '"+ courseIndex +"'");
					pst.execute();
					pst.close();
					commentField.setText("");
					notification.NOTIFY(Author, uname+" has commented on your "+courseName+" course page");
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"*3*");

					JOptionPane.showMessageDialog(null,e);
				}			
			}
		});
		btnPost.setBounds(425, 292, 97, 40);
		contentPane.add(btnPost);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 204, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Home newUser = new Home(UT.toString(),mf,uname);
				newUser.setVisible(true);
			}
		});
		btnBack.setBounds(462, 13, 97, 40);
		contentPane.add(btnBack);
		if(UT.equals("Author")) {
			JLabel lblVerify = new JLabel("Not Verified");
			if(state==1){
				lblVerify.setText("Verified");
			}
			lblVerify.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 15));
			lblVerify.setBounds(462, 193, 97, 34);
			contentPane.add(lblVerify);
		}
		
		if(UT.equals("Admin")) {
			JButton btnVerified = new JButton("Verify");
			btnVerified.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {	
						PreparedStatement pst;
						if(state == 0) {
							pst = courseconn.prepareStatement("Update filedb set state=1 where id = '"+ courseIndex +"'");
							pst.execute();
							pst.close();
							btnVerified.setText("Verified");
							notification.NOTIFY(Author, " Your course on "+courseName+" has been verified and is openly published");
						}
						else {
							pst = courseconn.prepareStatement("Update filedb set state=0 where id = '"+ courseIndex +"'");
							pst.execute();
							pst.close();
							btnVerified.setText("Verify");
							notification.NOTIFY(Author, " Your course on "+courseName+" has been de verified and it cannon be seen by public");
						}	
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"*3*");
						JOptionPane.showMessageDialog(null,e);
					}			
				}
			});
			btnVerified.setBounds(462, 118, 105, 40);
			contentPane.add(btnVerified);
		}
	}
}