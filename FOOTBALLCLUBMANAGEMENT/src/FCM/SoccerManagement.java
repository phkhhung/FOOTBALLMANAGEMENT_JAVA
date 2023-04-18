package FCM;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.SystemColor;

public class SoccerManagement extends JFrame {
	private JTable table;
	private JTextField tfid;
	private JTextField tfname;
	private JTextField tfnati;
	private JTextField tfTeamid;
	private JTextField tfSearch;
	private Vector vT;
	private DefaultTableModel model2;
	private JComboBox comboBox;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoccerManagement frame = new SoccerManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SoccerManagement() {
		getContentPane().setBackground(new Color(0, 255, 0));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(180, 75, 924, 524);
		getContentPane().setLayout(null);
		
		JLabel lblSMN = new JLabel("SOCCER MANAGEMENT");
		lblSMN.setHorizontalAlignment(SwingConstants.CENTER);
		lblSMN.setForeground(Color.DARK_GRAY);
		lblSMN.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		lblSMN.setBounds(204, 25, 488, 44);
		getContentPane().add(lblSMN);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(152, 251, 152));
		menuBar.setBackground(new Color(0, 255, 0));
		menuBar.setBounds(0, 0, 910, 23);
		getContentPane().add(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem ItemInfo = new JMenuItem("Information");
		ItemInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Information information = new Information();
				information.setVisible(true);
				information.setDefaultCloseOperation(information.HIDE_ON_CLOSE);
			}
		});
		menu.add(ItemInfo);
		
		JMenuItem ItemExit = new JMenuItem("Exit");
		menu.add(ItemExit);
		
		JMenu MenuHelp = new JMenu("Help");
		menuBar.add(MenuHelp);
		
		JMenuItem ItemGetHelp = new JMenuItem("Get Help?");
		MenuHelp.add(ItemGetHelp);
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		JMenuItem mntmService = new JMenuItem("Service");
		mnReport.add(mntmService);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("App");
		mnReport.add(mntmNewMenuItem_1_1);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 65, 852, 227);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Position", "Nationality", "TeamID"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(67);
		table.getColumnModel().getColumn(1).setPreferredWidth(161);
		table.getColumnModel().getColumn(2).setPreferredWidth(178);
		table.getColumnModel().getColumn(3).setPreferredWidth(154);
		scrollPane.setViewportView(table);
		
		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			add();
			}
		});
		try {
			btn_add.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("add_icon1.png" ))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String SoccerID = table.getValueAt(row, 0).toString();
				String SoccerName = table.getValueAt(row, 1).toString();
				String Position = table.getValueAt(row, 2).toString();
				String nation = table.getValueAt(row, 3).toString();
				String teamID = table.getValueAt(row, 4).toString();

				tfid.setText(SoccerID);
				tfname.setText(SoccerName);
				comboBox.setSelectedItem(Position);
				tfnati.setText(nation);
				tfTeamid.setText(teamID);
			}
		});
		btn_add.setBounds(109, 416, 122, 44);
		getContentPane().add(btn_add);
		
		JButton btn_update = new JButton("UPDATE");
		try {
			btn_update.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("update.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = new Connect().getConnection();
				String sql = "UPDATE SoccerMN SET SoccerName = ?,Position = ? ,Nationality = ?, TeamID = ? WHERE SoccerID = ?";
				try {
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(5, tfid.getText());
					stm.setString(1, tfname.getText());
					stm.setString(2, (String) comboBox.getItemAt(comboBox.getSelectedIndex()));
					stm.setString(3, tfnati.getText());
					stm.setString(4, tfTeamid.getText());
					stm.executeUpdate();

					JOptionPane.showMessageDialog(null, "Updated!");
					model2.setRowCount(0);
					showAll();

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Error!");
				}

			}
		});
   
		btn_update.setBounds(346, 416, 122, 44);
		getContentPane().add(btn_update);
		
		JButton btn_delete = new JButton("DELETE");
		btn_delete.setBounds(586, 416, 122, 44);
		try {
			btn_delete.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("deleteicon.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		getContentPane().add(btn_delete);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn = new Connect().getConnection();
				String sql = "DELETE FROM SoccerMN WHERE SoccerID=?";
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, idSelect());
					ps.executeUpdate();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
				model2.setRowCount(0);
				showAll();

			}
		});
		
		JLabel lblSoccerid = new JLabel("SoccerID");
		lblSoccerid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoccerid.setBounds(45, 313, 101, 23);
		getContentPane().add(lblSoccerid);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(45, 346, 101, 23);
		getContentPane().add(lblName);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNationality.setBounds(332, 313, 101, 23);
		getContentPane().add(lblNationality);
		
		JLabel lblTeamid = new JLabel("TeamID");
		lblTeamid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamid.setBounds(332, 346, 101, 23);
		getContentPane().add(lblTeamid);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPosition.setBounds(45, 380, 101, 23);
		getContentPane().add(lblPosition);
		
		tfid = new JTextField();
		tfid.setColumns(10);
		tfid.setBounds(148, 315, 174, 23);
		getContentPane().add(tfid);
		
		tfname = new JTextField();
		tfname.setColumns(10);
		tfname.setBounds(148, 348, 174, 23);
		getContentPane().add(tfname);
		
		tfnati = new JTextField();
		tfnati.setColumns(10);
		tfnati.setBounds(417, 315, 174, 23);
		getContentPane().add(tfnati);
		
		tfTeamid = new JTextField();
		tfTeamid.setColumns(10);
		tfTeamid.setBounds(417, 348, 174, 23);
		getContentPane().add(tfTeamid);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Goalkeeper", "Midfielder", "Winger", "Attacker", "Defender"}));
		comboBox.setBounds(204, 380, 118, 25);
		getContentPane().add(comboBox);
		
		
		
		JButton btnrefresh = new JButton("REFRESH");
		btnrefresh.setFont(new Font("Tahoma", Font.BOLD, 8));
		try {
			btnrefresh.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("Hopstarter-Soft-Scraps-Button-Refresh.16.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.setRowCount(0);
				showAll();
				clearForm();
				
				
			}
		});
		btnrefresh.setBounds(636, 328, 101, 29);
		getContentPane().add(btnrefresh);
		
		JLabel lblSearchBySoccerid = new JLabel("Search By SoccerID");
		lblSearchBySoccerid.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBySoccerid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchBySoccerid.setBounds(747, 319, 152, 24);
		getContentPane().add(lblSearchBySoccerid);
		
		tfSearch = new JTextField();
		tfSearch.setForeground(SystemColor.activeCaptionBorder);
		tfSearch.setColumns(10);
		tfSearch.setBounds(773, 349, 96, 20);
		getContentPane().add(tfSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSearch.setBounds(783, 382, 70, 23);
		getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSearch();
				clearForm();
			}
		});
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(840, 21, 70, 29);
		btnClose.setHorizontalTextPosition(SwingConstants.LEFT);
		getContentPane().add(btnClose);
		btnClose.setBounds(820, 21, 90, 29);
		try {
			btnClose.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("Hopstarter-Button-Button-Close.16 (1).png"))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				;
			}
		});
	
		showAll();
	}
	public void clearForm() {
    	tfid.setText(null);
		tfname.setText("");
		tfnati.setText("");
		tfTeamid.setText("");

}
	public void addSql(SoccerInfor c) {
		Connection conn = new Connect().getConnection();
		String sql = "INSERT INTO SoccerMN VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, c.getSoccerID());
			pstm.setString(2, c.getSoccerName());
			pstm.setString(3, c.getPosition());
			pstm.setString(4, c.getNationality());
			pstm.setString(5,c.getTeamID());
			pstm.execute();
			JOptionPane.showMessageDialog(null, "Complete!");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}
	public void add() {
		String SoccerID;
		String SoccerName;
		String Position = "";
		String Nationality;
		String TeamID;
		

		SoccerID = tfid.getText();
		SoccerName = tfname.getText();
		switch(comboBox.getSelectedIndex()) {
		case 0 : Position = "Goalkeeper";
		break;
		case 1: Position = "Midfielder";
		break;
		case 2: Position ="Winger";
		break;
		case 3: Position ="Attacker";
		break;
		case 4: Position= "Defender";
		break;
		}
	
		Nationality = tfnati.getText();
		TeamID = tfTeamid.getText();

		SoccerInfor e = new SoccerInfor(SoccerID, SoccerName,Position,Nationality, TeamID);
		addSql(e);
		model2.setRowCount(0);
		showAll();
	}
	public Vector<SoccerInfor> getList() {
		Connection conn = new Connect().getConnection();
		Vector<SoccerInfor> vD = new Vector<>();
		String sql = "select * from SoccerMN ";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				SoccerInfor vtemp = new SoccerInfor();
				vtemp.setSoccerID(rs.getString("SoccerID"));
				vtemp.setSoccerName(rs.getString("SoccerName"));
				vtemp.setPosition(rs.getString("Position"));
				vtemp.setNationality(rs.getString("Nationality"));
				vtemp.setTeamID(rs.getString("TeamID"));
				vD.add(vtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vD;
	}
	
	public String idSelect() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String CoachID = model.getValueAt(table.getSelectedRow(), 0) + "";
		return CoachID;
	}
	public void showAll() {
		Vector<SoccerInfor> vt = getList();
		model2 = (DefaultTableModel) table.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < vt.size(); i++) {
			row[0] = vt.get(i).getSoccerID();
			row[1] = vt.get(i).getSoccerName();
			row[2] = vt.get(i).getPosition();
			row[3] = vt.get(i).getNationality();
			row[4] = vt.get(i).getTeamID();
			model2.addRow(row);
            
		}
	}
	public Vector<SoccerInfor> getSeach() {
		Connection conn = new Connect().getConnection();
		Vector<SoccerInfor> vD = new Vector<>();
		String id = tfSearch.getText();
		String sql = "select * from SoccerMN where SoccerID = ?";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				SoccerInfor vtemp = new SoccerInfor();
				vtemp.setSoccerID(rs.getString("SoccerID"));
				vtemp.setSoccerName(rs.getString("SoccerName"));
				vtemp.setPosition(rs.getString("Position"));
				vtemp.setNationality(rs.getString("Nationality"));
				vtemp.setTeamID(rs.getString("TeamID"));
				vD.add(vtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vD;
	}

	public void showSearch() {
		Vector<SoccerInfor> vt = getSeach();
		model2 = (DefaultTableModel) table.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < vt.size(); i++) {
			row[0] = vt.get(i).getSoccerID();
			row[1] = vt.get(i).getSoccerName();
			row[2] = vt.get(i).getPosition();
			row[3] = vt.get(i).getNationality();
			row[4] = vt.get(i).getTeamID();
			model2.setRowCount(0);
			model2.addRow(row);

			
		}
	}
}
