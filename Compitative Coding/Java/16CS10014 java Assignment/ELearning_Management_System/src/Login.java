import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	//Connection courseconn = null;
	private JTextField userName;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = Database.dbConnector();
		//courseconn = Database.courseConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(81, 37, 74, 25);
		frame.getContentPane().add(lblUserName);
		
		userName = new JTextField();
		userName.setBounds(167, 29, 197, 40);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(81, 102, 74, 25);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 94, 197, 40);
		frame.getContentPane().add(passwordField);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select * from userdetails where username=? and password=?;";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, userName.getText());
					pst.setString(2, passwordField.getText());
					//JOptionPane.showMessageDialog(null,"******1");

					ResultSet rs=pst.executeQuery();
					int count = 0;
					//JOptionPane.showMessageDialog(null,"******2");

					while(rs.next()) {
						count++;
					}
					if(count == 1) {
						rs.close();
						pst.close();
						query = "SELECT * FROM userdetails WHERE username = ?;";
						pst = connection.prepareStatement(query);
						pst.setString(1, userName.getText());
						rs=pst.executeQuery();
						rs.next();
						userName.setText("");
						passwordField.setText("");
						//frame.dispose();
						frame.setVisible(false);
						Home newUser = new Home(rs.getString("userType").toString(),frame,rs.getString("username").toString());
						newUser.setVisible(true);
					}
					else if(count>1) {
						JOptionPane.showMessageDialog(null,"There are duplicate username","Login Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"Username or password incorrect");
					}
					rs.close();
					pst.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnLogin.setBounds(215, 147, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				CreateAccount newUser = new CreateAccount(frame);
				newUser.setVisible(true);
			}
		});
		btnCreateAccount.setBounds(197, 185, 128, 25);
		frame.getContentPane().add(btnCreateAccount);
	}

}
