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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfuser;
	private JPasswordField tfpass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
				
				
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(415, 186, 453, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
	    setTitle("Log in");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOG IN ");
		lblLogin.setBounds(10, 10, 419, 59);
		lblLogin.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 20));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.YELLOW);
		contentPane.add(lblLogin);
		
		JLabel user = new JLabel(" Username");
		user.setOpaque(true);
		user.setForeground(new Color(240, 248, 255));
		user.setBackground(Color.BLACK);
		user.setFont(new Font("Tahoma", Font.BOLD, 15));
		user.setBounds(81, 79, 87, 30);
		contentPane.add(user);
		
		tfuser = new JTextField();
		tfuser.setBounds(168, 79, 183, 30);
		contentPane.add(tfuser);
		tfuser.setColumns(10);
		
		JLabel lblPass = new JLabel(" Password");
		lblPass.setOpaque(true);
		lblPass.setBackground(new Color(0, 0, 0));
		lblPass.setToolTipText("");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(82, 119, 87, 30);
		contentPane.add(lblPass);
		
		JButton btnlogin = new JButton("Log in");
		btnlogin.setMnemonic(KeyEvent.VK_ENTER);
		contentPane.getRootPane().setDefaultButton(btnlogin);
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder ab = new StringBuilder();
				 if (tfuser.getText().equals("")) {
					ab.append("Username is required!\n");
			 
				} 
				String pass = new String(tfpass.getPassword());
				if(pass.equals("")) {
					ab.append("Password is required!\n");
					tfpass.requestFocus();
				}
				
				if(ab.length()>0) {
					JOptionPane.showMessageDialog(tfpass,ab.toString() );
					return;
					
				}else 	{
				Connection conn = new Connect().getConnection();
				Vector vD = new Vector();
				String sql = "SELECT * FROM SignupInfo WHERE username = ? AND password = ?";
				PreparedStatement stm;
				try {
					stm = conn.prepareStatement(sql);
					stm.setString(1, tfuser.getText());
					stm.setString(2, tfpass.getText());
					ResultSet rs = stm.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(tfpass, "Succeed! Press Enter!");
						new Display().setVisible(true);
						dispose();

					} else 	JOptionPane.showMessageDialog(null, " Incorrect account or password!");
					tfpass.setText(null);

				} catch (SQLException e1) {
				
					JOptionPane.showMessageDialog(null, " Incorrect account or password!");
						e1.printStackTrace();
				
				}
				
				}
			
					;
				}
				
			});
			
			
		;
		
	
		btnlogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnlogin.setBounds(155, 170, 100, 30);
		contentPane.add(btnlogin);
		
		JButton btnsignup = new JButton("Sign up");
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		     
				new Signup().setVisible(true);
				
		     dispose();
			}
		});
		btnsignup.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnsignup.setBounds(267, 170, 100, 30);
		contentPane.add(btnsignup);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(168, 119, 183, 30);
		contentPane.add(tfpass);
		
		JLabel loginimage = new JLabel("");
		loginimage.setIcon(new ImageIcon("E:\\ANH\\loginbongda.png"));
		loginimage.setBounds(-53, 0, 492, 263);
		contentPane.add(loginimage);
	}
}
