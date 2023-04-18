package FCM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField tfuser;
	private JPasswordField tfpass;
	private JPasswordField tfconfirm;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(415, 186, 453, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
        
	    setTitle("Sign Up");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnlogin = new JButton("Log in");
		btnlogin.setMnemonic(KeyEvent.VK_ENTER);
		contentPane.getRootPane().setDefaultButton(btnlogin);
		
		JLabel lblSignup = new JLabel("SIGN UP");
		lblSignup.setBounds(10, 10, 419, 59);
		lblSignup.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 20));
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setForeground(Color.YELLOW);
		contentPane.add(lblSignup);
		
		JLabel user = new JLabel(" Username");
		user.setOpaque(true);
		user.setForeground(new Color(240, 248, 255));
		user.setBackground(Color.BLACK);
		user.setFont(new Font("Tahoma", Font.BOLD, 15));
		user.setBounds(20, 79, 148, 30);
		contentPane.add(user);
		
		tfuser = new JTextField();
		tfuser.setBounds(168, 79, 183, 30);
		contentPane.add(tfuser);

		
		JLabel lblPass = new JLabel(" Password");
		lblPass.setOpaque(true);
		lblPass.setBackground(new Color(0, 0, 0));
		lblPass.setToolTipText("");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(23, 119, 146, 30);
		contentPane.add(lblPass);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(168, 119, 183, 30);
		contentPane.add(tfpass);
		
		JLabel confirm = new JLabel(" Confirm Password");
		confirm.setToolTipText("");
		confirm.setOpaque(true);
		confirm.setForeground(Color.WHITE);
		confirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		confirm.setBackground(Color.BLACK);
		confirm.setBounds(23, 159, 145, 30);
		contentPane.add(confirm);
		
		tfconfirm = new JPasswordField();
		tfconfirm.setBounds(168, 159, 183, 30);
		contentPane.add(tfconfirm);
		
	    JButton btnsignup = new JButton("Sign up now!");
		btnsignup.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnsignup.setMnemonic(KeyEvent.VK_ENTER);
		contentPane.getRootPane().setDefaultButton(btnsignup);


		btnsignup.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {			
				
			 StringBuilder sb = new StringBuilder();
			 if (tfuser.getText().equals("")) {
				sb.append("Username is required!\n");
		 
			} 
			String pass = new String(tfpass.getPassword());
			if(pass.equals("")) {
				sb.append("Password is required!\n");
				tfpass.requestFocus();
			}
			String confirm = new String(tfconfirm.getPassword());
			if (!pass.equals("")&& !pass.equals(confirm)) {
				sb.append("Password and Confirm Password must be the same!\n");
				tfpass.requestFocus();

			}
			if(sb.length()>0) {
				JOptionPane.showMessageDialog(tfpass,sb.toString() );
				return;
				
			}else 	JOptionPane.showMessageDialog(tfpass, "Information is valid! Login now!");
			String sql = "INSERT INTO SignupInfo VALUES (?, ?)";
			try {
				Connection conn = new Connect().getConnection();
				PreparedStatement stm = conn.prepareStatement(sql);
				stm.setString(1, tfuser.getText());
				stm.setString(2, tfpass.getText());
				stm.execute();
	
				new Login().setVisible(true);
				
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			dispose();
					
				;
			}
			
		});
		btnsignup.setBounds(194, 199, 132, 30);
		contentPane.add(btnsignup);
		
		JLabel loginimage = new JLabel("");
		loginimage.setIcon(new ImageIcon("E:\\ANH\\loginbongda.png"));
		loginimage.setBounds(-53, 0, 492, 263);
		contentPane.add(loginimage);
	 
	}
}
