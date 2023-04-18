package FCM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Canvas;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Display extends JFrame {

	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
		
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Display() {
		setBounds(180, 75, 924, 524);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setSize(923, 512);;
		setTitle("FOOTBALL CLUB MANAGEMENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JButton btnteam = new JButton("");
		btnteam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnteam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLocationRelativeTo(null);
				new TeamManagement().setVisible(true);
			
			}
		});
		btnteam.setIcon(new ImageIcon("E:\\ANH\\TEAMMOI.jpg"));
		btnteam.setBounds(75, 83, 230, 157);
		contentPane.add(btnteam);
		
		JButton btnSoccer = new JButton("");
		btnSoccer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSoccer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SoccerManagement().setVisible(true);
			}
		});
		btnSoccer.setIcon(new ImageIcon("E:\\ANH\\moi2.jpg"));
		btnSoccer.setBounds(346, 83, 230, 157);
		contentPane.add(btnSoccer);
		
		JButton btnCoach = new JButton("");
		btnCoach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCoach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CoachManagement().setVisible(true);;
			}
		});
		btnCoach.setIcon(new ImageIcon("E:\\ANH\\moi3.jpg"));
		btnCoach.setBounds(613, 83, 230, 157);
		contentPane.add(btnCoach);
		
		JLabel lblNewLabel_1 = new JLabel("Program created by Hung");
		lblNewLabel_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1.setBounds(41, 441, 179, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel Titlename = new JLabel("VIETNAM FOOTBALL CLUB MANAGEMENT ");
		Titlename.setHorizontalAlignment(SwingConstants.CENTER);
		Titlename.setForeground(Color.RED);
		Titlename.setFont(new Font("Verdana", Font.BOLD, 28));
		Titlename.setHorizontalTextPosition(SwingConstants.CENTER);
		Titlename.setBounds(130, 27, 676, 46);
		contentPane.add(Titlename);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 899, 22);
		contentPane.add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Refresh");
		mnMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		mnMenu.add(mntmNewMenuItem_1);
		
		
		JMenu mnNewMenu = new JMenu("Get Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Welcome");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Check for Updates");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem(" Help Contents");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		JMenuItem mntmService = new JMenuItem("Service");
		mnReport.add(mntmService);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("App");
		mnReport.add(mntmNewMenuItem_1_1);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		try {
			btnNewButton.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("Hopstarter-Button-Button-Close.16 (1).png" ))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		btnNewButton.setBounds(798, 441, 101, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\ANH\\mmmm.jpg"));
		lblNewLabel.setBounds(10, 0, 899, 478);
		contentPane.add(lblNewLabel);
	}
}
