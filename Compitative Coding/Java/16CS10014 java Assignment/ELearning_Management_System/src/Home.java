import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField SearchField;
	private static String UserType;
	private JTextField searchField;
	private JTextField coursename;
	private JTextField coursefield;
	private JTextField coursepath;
	Connection connection = null;
	Connection courseconn = null;
	String USER = null;
	private JTable table;
	JFrame mainFrame = null;
	Notification notification;
	String nots[];
	String password ;
	String name;
	String emlid;
	private JTextField newemailID;
	private JTextField newName;
	private JPasswordField newPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home("Admin",null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home(String userType,JFrame mf,String User) {
		mainFrame = mf;
		USER = User;
		notification = new Notification();
		courseconn = Database.courseConnector();
		connection = Database.dbConnector();
		//aconnection = Database.adbConnector();
		UserType = userType;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 43, 500, 300);
		contentPane.add(tabbedPane);
		
		/**
		 * Search engine 
		 */
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(12, 15, 56, 16);
		panel.add(lblSearch);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			//@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					PreparedStatement pst;
					String query;
					if(userType.equals("Admin")) {
						query = "SELECT * FROM filedb where courseName = ? or courseField = ?;";
						pst = courseconn.prepareStatement(query);
						pst.setString(1, searchField.getText());
						pst.setString(2, searchField.getText());
					}else if(userType.equals("Author")){
						query = "SELECT * FROM filedb where courseName = ? or courseField = ? and state = 1 or username = '"+USER+"';";	
						pst = courseconn.prepareStatement(query);
						pst.setString(1, searchField.getText());
						pst.setString(2, searchField.getText());
					}else{
						query = "SELECT * FROM filedb where courseName = ? or courseField = ? and state=1;";	
						pst = courseconn.prepareStatement(query);
						pst.setString(1, searchField.getText());
						pst.setString(2, searchField.getText());
					}
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					while(rs.next()) {
						
					}
					rs.close();
					pst.close();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,"+++55++");	
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		searchField.setBounds(80, 10, 223, 25);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 482, 210);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String id = (table.getModel().getValueAt(row, 0)).toString();
					//PreparedStatement pst = courseconn.prepareStatement("SELECT * FROM filedb where id = '"+id+"';");
					//ResultSet rs=pst.executeQuery();
					setVisible(false);
					CoursePage CP = new CoursePage(id,mainFrame,USER);
					CP.setVisible(true);
					//rs.close();
					//pst.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);	
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblCourseNameOr = new JLabel("course name or field name");
		lblCourseNameOr.setBounds(315, 15, 179, 16);
		panel.add(lblCourseNameOr);
		
		/**
		 * Notifications
		 */
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Notification", null, panel_1, null);
		panel_1.setLayout(null);
		
		JList list = new JList();
		list.setBounds(0, 0, 500, 263);
		panel_1.add(list);
		DefaultListModel DLM = new DefaultListModel();
		try {						
			PreparedStatement pst = connection.prepareStatement("SELECT * FROM userdetails where username = ?");
			pst.setString(1, USER);
			ResultSet rs=pst.executeQuery();
			while (rs.next()){
			nots = rs.getString("notifications").split(":>|<:");
			password = rs.getString("password");
			emlid = rs.getString("emailID");
			name = rs.getString("name");
			}
			for(String s : nots) {
				DLM.addElement(s);
			}
			list.setModel(DLM);
			rs.close();
			pst.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"0");
			JOptionPane.showMessageDialog(null,e);
		}
		/**
		 * upload
		 */
		
		
		// ***************************************Commenting need to be removed **************************************************
		if(UserType.equals("Author")){	
		
			JPanel panel_2 = new JPanel();
			tabbedPane.addTab("Upload courses", null, panel_2, null);
			panel_2.setLayout(null);
			
			JLabel lblCourseName = new JLabel("Course Name");
			lblCourseName.setBounds(12, 13, 92, 24);
			panel_2.add(lblCourseName);
			
			coursename = new JTextField();
			coursename.setBounds(167, 14, 232, 23);
			panel_2.add(coursename);
			coursename.setColumns(10);
			
			JLabel lblCourseField = new JLabel("Course Field");
			lblCourseField.setBounds(12, 52, 92, 16);
			panel_2.add(lblCourseField);
			
			coursefield = new JTextField();
			coursefield.setBounds(167, 49, 232, 22);
			panel_2.add(coursefield);
			coursefield.setColumns(10);
			
			JLabel lblUploadCourseFolder = new JLabel("Course Folder Path");
			lblUploadCourseFolder.setBounds(12, 81, 135, 24);
			panel_2.add(lblUploadCourseFolder);
			
			coursepath = new JTextField();
			coursepath.setBounds(167, 78, 232, 22);
			panel_2.add(coursepath);
			coursepath.setColumns(10);
			
			JButton btnSubmit = new JButton("Upload");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						//PreparedStatement pst = courseconn.prepareStatement("CREATE TABLE IF NOT EXISTS filedb(id int NOT NULL AUTO_INCREMENT,courseName TEXT,courseField TEXT,coursePath TEXT,Author TEXT,username TEXT,comments MEDIUMTEXT,state BIT,PRIMARY KEY(id)");
						//pst.executeUpdate();
						String query = "INSERT INTO filedb(courseName,courseField,coursePath,username,state) VALUES (?,?,?,?,?);";
						PreparedStatement pst = courseconn.prepareStatement(query);
						pst.setString(1, coursename.getText());
						pst.setString(2, coursefield.getText());
						pst.setString(3, coursepath.getText());
						//JOptionPane.showMessageDialog(null,USER);
						pst.setString(4, USER);
						pst.setBoolean(5, false);
						pst.executeUpdate();				
						notification.sublist(USER,USER+"has uploaded a new course on "+coursename.getText());
						JOptionPane.showMessageDialog(null,"Your course has been successfuly uploaded. It will be hosted after verification");
					}catch(Exception e) {
						System.out.println(e);
					}
				}
			});
			btnSubmit.setBounds(106, 143, 97, 25);
			panel_2.add(btnSubmit);
			
			JButton btnClear = new JButton("Clear");
			btnClear.setBounds(234, 143, 97, 25);
			panel_2.add(btnClear);
		}
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Update Account", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblPassword = new JLabel("New Password:");
		lblPassword.setBounds(26, 20, 99, 26);
		panel_3.add(lblPassword);
		
		JLabel lblEmailid = new JLabel("New emailID:");
		lblEmailid.setBounds(26, 69, 99, 16);
		panel_3.add(lblEmailid);
		
		newemailID = new JTextField();
		newemailID.setBounds(137, 63, 174, 28);
		newemailID.setText(emlid);						
		panel_3.add(newemailID);
		newemailID.setColumns(10);
		
		JLabel lblName = new JLabel("New Name:");
		lblName.setBounds(26, 114, 99, 16);
		panel_3.add(lblName);
		
		newName = new JTextField();
		newName.setBounds(137, 108, 174, 28);
		panel_3.add(newName);
		newName.setText(name);
		newName.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(SystemColor.activeCaptionBorder);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement pst = connection.prepareStatement("Update userdetails set password='"+newPassword.getText()+"', name = '"+newName.getText()+"', emailid = '"+newemailID.getText()+"' where username = '"+USER+"';");
					pst.executeUpdate();
					pst.close();
					JOptionPane.showMessageDialog(null,"Your account details are updated ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		btnUpdate.setBounds(115, 198, 90, 28);
		panel_3.add(btnUpdate);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(137, 13, 174, 41);
		newPassword.setText(password);						
		panel_3.add(newPassword);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.RED);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				mainFrame.setVisible(true);
			}
		});
		btnLogout.setBounds(415, 6, 97, 25);
		contentPane.add(btnLogout);
		
		/**
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(12, 67, 62, 16);
		contentPane.add(lblSearch);
		
		SearchField = new JTextField();
		SearchField.setBounds(86, 64, 270, 22);
		contentPane.add(SearchField);
		SearchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(368, 63, 90, 25);
		contentPane.add(btnSearch);
		
		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.setBounds(63, 108, 113, 40);
		contentPane.add(btnNotifications);
		
		JLabel lblELearningMangment = new JLabel("E Learning Mangment System");
		lblELearningMangment.setForeground(new Color(128, 128, 128));
		lblELearningMangment.setFont(new Font("Verdana", Font.BOLD, 16));
		lblELearningMangment.setBounds(93, 13, 270, 25);
		contentPane.add(lblELearningMangment);
		
		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.setBounds(129, 177, 112, 40);
		contentPane.add(btnUpdateInfo);
		
		JButton btnActivityLog = new JButton("Activity log");
		btnActivityLog.setBounds(204, 108, 113, 40);
		contentPane.add(btnActivityLog);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(372, 261, 97, 25);
		contentPane.add(btnLogout);
		if(UserType.equals("Author")){
			JButton btnUpload = new JButton("Upload course");
			btnUpload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnUpload.setBounds(294, 177, 164, 40);
			contentPane.add(btnUpload);
		}
		*/
	}
}
