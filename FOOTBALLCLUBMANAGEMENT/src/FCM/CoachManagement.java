package FCM;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CoachManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfCoachID;
	private JTextField tfName;
	private JTextField tfNationality;
	private JTextField tfTeamID;
	private JTextField tfSearch;
	private Vector vT;
	private DefaultTableModel model2;

	public void addSql(CoachInfor c) {
		Connection conn = new Connect().getConnection();
		String sql = "INSERT INTO coachmn VALUES (?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, tfCoachID.getText());
			pstm.setString(2, tfName.getText());
			pstm.setString(3, tfNationality.getText());
			pstm.setString(4, tfTeamID.getText());
			pstm.execute();
			JOptionPane.showMessageDialog(null, "Complete!");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}

	public void clearForm() {

		tfCoachID.setText(null);
		tfName.setText("");
		tfNationality.setText("");
		tfTeamID.setText("");

	}

	public void add() {
		String CoachID;
		String CoachName;
		String Nationality;
		String TeamID;

		CoachID = tfCoachID.getText();
		CoachName = tfName.getText();
		Nationality = tfNationality.getText();
		TeamID = tfTeamID.getText();

		CoachInfor e = new CoachInfor(CoachID, CoachName, Nationality, TeamID);
		addSql(e);
		model2.setRowCount(0);
	}

	public Vector<CoachInfor> getList() {
		Connection conn = new Connect().getConnection();
		Vector<CoachInfor> vD = new Vector<>();
		String sql = "select * from coachmn ";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				CoachInfor vtemp = new CoachInfor();
				vtemp.setCoachID(rs.getString("CoachID"));
				vtemp.setCoachName(rs.getString("CoachName"));
				vtemp.setNationality(rs.getString("Nationality"));
				vtemp.setTeamID(rs.getString("TeamID"));
				vD.add(vtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vD;
	}

	public void showAll() {
		Vector<CoachInfor> vt = getList();
		model2 = (DefaultTableModel) table.getModel();
		Object[] row = new Object[4];
		for (int i = 0; i < vt.size(); i++) {
			row[0] = vt.get(i).getCoachID();
			row[1] = vt.get(i).getCoachName();
			row[2] = vt.get(i).getNationality();
			row[3] = vt.get(i).getTeamID();
			model2.addRow(row);
            
		}
	}
	public Vector<CoachInfor> getSeach() {
		Connection conn = new Connect().getConnection();
		Vector<CoachInfor> vD = new Vector<>();
		String id = tfSearch.getText();
		String sql = "select * from coachmn where coachID = ?";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				CoachInfor vtemp = new CoachInfor();
				vtemp.setCoachID(rs.getString("CoachID"));
				vtemp.setCoachName(rs.getString("CoachName"));
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
		Vector<CoachInfor> vt = getSeach();
		model2 = (DefaultTableModel) table.getModel();
		Object[] row = new Object[4];
		for (int i = 0; i < vt.size(); i++) {
			row[0] = vt.get(i).getCoachID();
			row[1] = vt.get(i).getCoachName();
			row[2] = vt.get(i).getNationality();
			row[3] = vt.get(i).getTeamID();
			model2.setRowCount(0);
			model2.addRow(row);

		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoachManagement frame = new CoachManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String idSelect() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String CoachID = model.getValueAt(table.getSelectedRow(), 0) + "";
		return CoachID;
	}

	public CoachManagement() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(180, 75, 924, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCoachManagement = new JLabel("COACH MANAGEMENT");
		lblCoachManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoachManagement.setForeground(Color.DARK_GRAY);
		lblCoachManagement.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		lblCoachManagement.setBounds(207, 28, 488, 44);
		contentPane.add(lblCoachManagement);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 77, 831, 192);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "CoachID", "Name", "Nationality", "TeamID" }) {
			boolean[] columnEditables = new boolean[] { false, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(191);
		table.getColumnModel().getColumn(2).setPreferredWidth(218);
		table.getColumnModel().getColumn(3).setPreferredWidth(137);
		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(152, 251, 152));
		menuBar.setBackground(new Color(127, 255, 212));
		menuBar.setBounds(0, 0, 910, 23);
		contentPane.add(menuBar);

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

		JLabel lblCoachID = new JLabel("CoachID");
		lblCoachID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCoachID.setBounds(37, 313, 101, 23);
		contentPane.add(lblCoachID);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(37, 360, 101, 23);
		contentPane.add(lblName);

		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNationality.setBounds(382, 313, 101, 23);
		contentPane.add(lblNationality);

		tfCoachID = new JTextField();
		tfCoachID.setColumns(10);
		tfCoachID.setBounds(141, 316, 174, 23);
		contentPane.add(tfCoachID);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(141, 362, 174, 23);
		contentPane.add(tfName);

		tfNationality = new JTextField();
		tfNationality.setColumns(10);
		tfNationality.setBounds(485, 313, 174, 23);
		contentPane.add(tfNationality);

		tfTeamID = new JTextField();
		tfTeamID.setColumns(10);
		tfTeamID.setBounds(485, 362, 174, 23);
		contentPane.add(tfTeamID);

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String coachID = table.getValueAt(row, 0).toString();
				String coachName = table.getValueAt(row, 1).toString();
				String nation = table.getValueAt(row, 2).toString();
				String teamID = table.getValueAt(row, 3).toString();

				tfCoachID.setText(coachID);
				tfName.setText(coachName);
				tfNationality.setText(nation);
				tfTeamID.setText(teamID);
			}
		});

		JLabel lblTeamID = new JLabel("TeamID");
		lblTeamID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamID.setBounds(382, 360, 101, 23);
		contentPane.add(lblTeamID);

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
				showAll();
				clearForm();
			}
		});
		try {
			btn_add.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("add_icon1.png" ))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		btn_add.setBounds(141, 407, 122, 44);
		contentPane.add(btn_add);

		JButton btn_update = new JButton("UPDATE");
		btn_update.setBounds(361, 407, 122, 44);
		contentPane.add(btn_update);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = new Connect().getConnection();
				String sql = "UPDATE CoachMN SET CoachName = ?, Nationality = ?, TeamID = ? WHERE CoachID = ?";
				try {
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(4, tfCoachID.getText());
					stm.setString(1, tfName.getText());
					stm.setString(2, tfNationality.getText());
					stm.setString(3, tfTeamID.getText());
					stm.executeUpdate();

					JOptionPane.showMessageDialog(null, "Updated!");
					model2.setRowCount(0);
					showAll();

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Error!");
				}

			}
		});
        
		try {
			btn_update.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("update.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		JButton btn_delete = new JButton("DELETE");
		btn_delete.setBounds(573, 407, 122, 44);
		try {
			btn_delete.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("deleteicon.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		contentPane.add(btn_delete);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn = new Connect().getConnection();
				String sql = "DELETE FROM CoachMN WHERE CoachID=?";
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
		});JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.setRowCount(0);
				showAll();
				clearForm();
			}
		});
		try {
			btnRefresh.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("Hopstarter-Soft-Scraps-Button-Refresh.16.png" ))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	

		
		btnRefresh.setBounds(669, 336, 101, 29);
		getContentPane().add(btnRefresh);
		contentPane.add(btnRefresh);
		JButton btnClose = new JButton("Close");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnClose.setBounds(820, 21, 90, 29);
		try {
			btnClose.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("Hopstarter-Button-Button-Close.16 (1).png"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				;
			}
		});
		getContentPane().add(btnClose);
		JLabel lblSearchByCoachid = new JLabel("Search By CoachID");
		lblSearchByCoachid.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchByCoachid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchByCoachid.setBounds(758, 313, 152, 24);
		contentPane.add(lblSearchByCoachid);

		tfSearch = new JTextField();
		tfSearch.setForeground(SystemColor.activeCaptionBorder);
		tfSearch.setColumns(10);
		tfSearch.setBounds(804, 345, 96, 20);
		contentPane.add(tfSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSearch();
				clearForm();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSearch.setBounds(814, 376, 70, 23);
		contentPane.add(btnSearch);
		showAll();
	}

	
}
