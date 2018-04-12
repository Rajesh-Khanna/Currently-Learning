import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.Arrays;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField Newname;
	private JTextField Newusername;
	private JTextField emaiID;
	private String date_labels[] = Arrays.toString(IntStream.rangeClosed(1, 31).toArray()).split("[\\[\\]]")[1].split(", ");
	private String month_labels[] = Arrays.toString(IntStream.rangeClosed(1, 12).toArray()).split("[\\[\\]]")[1].split(", ");
	private String year_labels[] = Arrays.toString(IntStream.rangeClosed(1918, 2018).toArray()).split("[\\[\\]]")[1].split(", ");
	JComboBox year;
	JComboBox month;
	JComboBox date;
	Connection connection = null;
	ButtonGroup BG = new ButtonGroup();
	private JPasswordField Newpassword;
	JFrame mainFrame = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount(null);
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
	public CreateAccount(JFrame mf) {
		mainFrame = mf;
		connection = Database.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("Name");
		lblUserName.setBounds(61, 52, 93, 30);
		contentPane.add(lblUserName);
		
		JLabel lblCreateAccount = new JLabel("Create Account");
		lblCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCreateAccount.setBounds(156, 13, 166, 37);
		contentPane.add(lblCreateAccount);
		
		Newname = new JTextField();
		Newname.setBounds(156, 56, 223, 22);
		contentPane.add(Newname);
		Newname.setColumns(10);
		
		Newusername = new JTextField();
		Newusername.setBounds(156, 93, 223, 22);
		contentPane.add(Newusername);
		Newusername.setColumns(10);
		
		JLabel lblUserName_1 = new JLabel("User Name");
		lblUserName_1.setBounds(61, 95, 83, 19);
		contentPane.add(lblUserName_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(61, 129, 73, 16);
		contentPane.add(lblPassword);
		
		Newpassword = new JPasswordField();
		Newpassword.setBounds(156, 126, 223, 22);
		contentPane.add(Newpassword);
		
		JRadioButton rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setToolTipText("");
		rdbtnStudent.setBounds(156, 157, 73, 25);
		contentPane.add(rdbtnStudent);
		
		JRadioButton rdbtnAuthor = new JRadioButton("Author");
		rdbtnAuthor.setBounds(233, 157, 67, 25);
		contentPane.add(rdbtnAuthor);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(304, 157, 127, 25);
		contentPane.add(rdbtnAdmin);
		
		rdbtnStudent.setSelected(true);
		BG.add(rdbtnStudent);
		BG.add(rdbtnAuthor);
		BG.add(rdbtnAdmin);
		
		JLabel lblEmailId = new JLabel("email Id");
		lblEmailId.setBounds(61, 194, 56, 16);
		contentPane.add(lblEmailId);
		
		emaiID = new JTextField();
		emaiID.setBounds(156, 191, 223, 22);
		contentPane.add(emaiID);
		emaiID.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(61, 239, 83, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel label = new JLabel("/");
		label.setBounds(207, 239, 5, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(284, 239, 5, 16);
		contentPane.add(label_1);
		
		JLabel lblDate = new JLabel("DD");
		lblDate.setBounds(156, 260, 16, 16);
		contentPane.add(lblDate);
		
		JLabel lblMm = new JLabel("MM");
		lblMm.setBounds(224, 260, 23, 16);
		contentPane.add(lblMm);
		
		JLabel lblYyyy = new JLabel("YYYY");
		lblYyyy.setBounds(302, 260, 30, 16);
		contentPane.add(lblYyyy);
		
		JButton btnSignup = new JButton("Sign up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try {
					PreparedStatement pst=connection.prepareStatement("CREATE TABLE IF NOT EXISTS UserDetails(id int NOT NULL AUTO_INCREMENT,userName TEXT UNIQUE,Password TEXT,Name TEXT,userType TEXT,DOB TEXT,emailID TEXT,subscribe TEXT,notifications TEXT,PRIMARY KEY(id)");
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null,e2.toString());					
				}*/				
				try {
					PreparedStatement pst = connection.prepareStatement("SELECT * FROM userdetails where userType = 'Admin';");
					ResultSet rs=pst.executeQuery();
					String adm = "";
					while(rs.next()) {
						 adm += rs.getString("username")+":>|<:";
					}
					String query = "insert into userdetails (userName,Password,Name,userType,DOB,emailID,subscribe,notifications) values (?,?,?,?,?,?,?,?);";
					pst=connection.prepareStatement(query);
					pst.setString(3, Newname.getText());
					if(Newusername.getText().length() == 0)
						 throw new IllegalArgumentException ("User name must contain atleast one character");  
					pst.setString(1, Newusername.getText());
					if(Newusername.getText().length() == 0)
						 throw new IllegalArgumentException ("Password must contain atleast one character");  
					pst.setString(2, Newpassword.getText());
					if(rdbtnStudent.isSelected()) {
						pst.setString(4, "Student");
					}else if(rdbtnAuthor.isSelected()) {
						pst.setString(4, "Author");
						pst.setString(7, adm);
					}else if(rdbtnAdmin.isSelected()) {
						pst.setString(4, "Admin");
					}
					pst.setString(5, date.getSelectedItem().toString()+"/"+month.getSelectedItem().toString()+"/"+year.getSelectedItem().toString());
					pst.setString(6, emaiID.getText());
					pst.setString(8, "Notification::>|<:");
					pst.execute();
					pst.close();	
					JOptionPane.showMessageDialog(null,"Account created");
					dispose();
					mainFrame.setVisible(true);
				}catch(Exception e1) {
					if(e1.toString().split(": ")[1].split("]")[0].equals("[SQLITE_CONSTRAINT")) {
						JOptionPane.showMessageDialog(null,"User Name already exists");
					}
					else
						JOptionPane.showMessageDialog(null,e1.toString().split(":")[1]);
				}
				
			}
		});
		btnSignup.setBounds(177, 327, 141, 42);
		contentPane.add(btnSignup);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mainFrame.setVisible(true);
			}
		});
		btnBack.setBounds(12, 13, 97, 25);
		contentPane.add(btnBack);
		
		date = new JComboBox(date_labels);
		date.setEditable(true);
		date.setBounds(156, 236, 39, 22);
		contentPane.add(date);
		//date.
		
		
		month = new JComboBox(month_labels);
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mm = Integer.parseInt(month.getSelectedItem().toString()); 
				if(mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
					if(date.getItemCount() == 28) {
						date.addItem("29");
						date.addItem("30");
						date.addItem("31");
					}
					else if(date.getItemCount() == 29) {
						date.addItem("30");
						date.addItem("31");
					}
					else if(date.getItemCount() == 30) {
						date.addItem("31");	
					}
				}
				else if(mm == 4 || mm == 6 || mm == 9 || mm == 11) {
					if(date.getItemCount() == 28) {
						date.addItem("29");
						date.addItem("30");
					}
					else if(date.getItemCount() == 29) {
						date.addItem("30");
					}
					else if(date.getItemCount() == 31) {
						date.removeItemAt(30);					
					}
				}
				else if(mm == 2) {
					if(Integer.parseInt(year.getSelectedItem().toString())%4 == 0) {
						if(date.getItemCount() == 31) {
							date.removeItemAt(28);
							date.removeItemAt(28);
							date.removeItemAt(28);
						}
						else if(date.getItemCount() == 29) {
							date.removeItemAt(28);					
						}
						else if(date.getItemCount() == 30) {
							date.removeItemAt(28);
							date.removeItemAt(28);
						}
					}
					else {
						if(date.getItemCount() == 31) {
							date.removeItemAt(29);
							date.removeItemAt(29);
						}
						else if(date.getItemCount() == 30) {
							date.removeItemAt(28);					
						}
						else if(date.getItemCount() == 28) {
							date.addItem("29");
						}
					}
				}
			}
		});
		month.setBounds(224, 236, 48, 22);
		contentPane.add(month);
		
		
		year = new JComboBox(year_labels);
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(year.getSelectedItem().toString())%4 == 0) {
					if(Integer.parseInt(month.getSelectedItem().toString()) == 2){
						if(date.getItemCount() == 29) {
							date.removeItemAt(28);
						}
					}
				}
				else {
					if(Integer.parseInt(month.getSelectedItem().toString()) == 2){
						if(date.getItemCount() == 28) {
							date.addItem("29");
						}
					}
				}
			}
		});
		year.setBounds(304, 236, 56, 22);
		contentPane.add(year);
				
	}
}
