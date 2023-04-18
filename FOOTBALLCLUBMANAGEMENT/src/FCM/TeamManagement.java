package FCM;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class TeamManagement extends JFrame {
	private JTable table;
	private JTextField tfTeamID;
	private JTextField tfTeamName;
	private JTextField tfCoach;
	private JTextField tfQuantity;
	public static JTextField txtTeamid;

	private Vector vT;
	private DefaultTableModel model2;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TeamManagement frame = new TeamManagement();
					frame.setVisible(true);
					frame.setTitle("Team Management");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vector<TeamInfor> getList() {
		Connection conn = new Connect().getConnection();
		Vector<TeamInfor> vD = new Vector<>();
		String sql = "select * from teammn ";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				TeamInfor vtemp = new TeamInfor();
				vtemp.setTeamID(rs.getString("TeamID"));
				vtemp.setTeamName(rs.getString("TeamName"));
				vtemp.setCoach(rs.getString("Coach"));
				vtemp.setQuantity(rs.getString("Quantity"));
				vD.add(vtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vD;
	}

	public void add() {
		String teamID;
		String teamName;
		String coach;
		String quantity;

		teamID = tfTeamID.getText();
		teamName = tfTeamName.getText();
		coach = tfCoach.getText();
		quantity = tfQuantity.getText();

		TeamInfor e = new TeamInfor(teamID, teamName, coach, quantity);
		addSql(e);
		model2.setRowCount(0);
		showAll();
	}

	public void addSql(TeamInfor t) {
		Connection conn = new Connect().getConnection();
		String sql = "INSERT INTO TeamMN VALUES (?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, tfTeamID.getText());
			pstm.setString(2, tfTeamName.getText());
			pstm.setString(3, tfCoach.getText());
			pstm.setInt(4, Integer.parseInt(tfQuantity.getText()));
			pstm.execute();
			JOptionPane.showMessageDialog(null, "Complete!");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}

	public void showAll() {
		Vector<TeamInfor> vt = getList();
		model2 = (DefaultTableModel) table.getModel();
		Object[] row = new Object[7];
		for (int i = 0; i < vt.size(); i++) {
			row[0] = vt.get(i).getTeamID();
			row[1] = vt.get(i).getTeamName();
			row[2] = vt.get(i).getCoach();
			row[3] = vt.get(i).getQuantity();
			model2.addRow(row);
		}
	}

	public void clearForm() {
		tfTeamID.setText(null);
		tfTeamName.setText("");
		tfCoach.setText("");
		tfQuantity.setText("");
		txtTeamid.setText("");
	}

	public String idSelect() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String teamID = model.getValueAt(table.getSelectedRow(), 0) + "";
		return teamID;
	}

	public Vector<TeamInfor> getSeach() {
		Connection conn = new Connect().getConnection();
		Vector<TeamInfor> vD = new Vector<>();
		String id = txtTeamid.getText();
		String sql = "select * from teammn where TeamID = ?";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				TeamInfor vtemp = new TeamInfor();
				vtemp.setTeamID(rs.getString("TeamID"));
				vtemp.setTeamName(rs.getString("TeamName"));
				vtemp.setCoach(rs.getString("Coach"));
				vtemp.setQuantity(rs.getString("Quantity"));
				vD.add(vtemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vD;
	}

	public void showSearch() {
		Vector<TeamInfor> vt = getSeach();
		model2 = (DefaultTableModel) table.getModel();
		Object[] row = new Object[4];
		for (int i = 0; i < vt.size(); i++) {
			row[0] = vt.get(i).getTeamID();
			row[1] = vt.get(i).getTeamName();
			row[2] = vt.get(i).getCoach();
			row[3] = vt.get(i).getQuantity();
			model2.addRow(row);
			
		}
	}

	public TeamManagement() {

		getContentPane().setBackground(new Color(0, 255, 127));
		getContentPane().setIgnoreRepaint(true);
		getContentPane().setForeground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(180, 75, 924, 524);
		getContentPane().setLayout(null);

		JLabel lblTM = new JLabel("TEAM MANAGEMENT");
		lblTM.setForeground(Color.DARK_GRAY);
		lblTM.setHorizontalAlignment(SwingConstants.CENTER);
		lblTM.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		lblTM.setBounds(208, 21, 488, 44);
		getContentPane().add(lblTM);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(25, 66, 852, 207);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString();
				String name = table.getValueAt(row, 1).toString();
				String coach = table.getValueAt(row, 2).toString();
				String quantity = table.getValueAt(row, 3).toString();

				tfTeamID.setText(id);
				tfTeamName.setText(name);
				tfCoach.setText(coach);
				tfQuantity.setText(quantity);
			}
		});
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "TeamID", "TeamName", "Coach", "Quantity" }) {
					boolean[] columnEditables = new boolean[] { false, true, true, true };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(288);
		table.getColumnModel().getColumn(2).setPreferredWidth(178);
		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		scrollPane.setViewportView(table);

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
				clearForm();
			}
		});
		try {
			btn_add.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("add_icon1.png" ))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		btn_add.setBounds(202, 397, 122, 44);
		getContentPane().add(btn_add);

		JLabel lblTeamID = new JLabel("TeamID");
		lblTeamID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamID.setBounds(25, 299, 101, 23);
		getContentPane().add(lblTeamID);

		JLabel lblTeamName = new JLabel("TeamName");
		lblTeamName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamName.setBounds(25, 344, 101, 23);
		getContentPane().add(lblTeamName);

		JLabel lblCoach = new JLabel("Coach");
		lblCoach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCoach.setBounds(333, 306, 101, 23);
		getContentPane().add(lblCoach);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantity.setBounds(333, 344, 138, 23);
		getContentPane().add(lblQuantity);

		tfTeamID = new JTextField();
		tfTeamID.setBounds(120, 301, 174, 23);
		getContentPane().add(tfTeamID);
		tfTeamID.setColumns(10);

		tfTeamName = new JTextField();
		tfTeamName.setColumns(10);
		tfTeamName.setBounds(120, 346, 174, 23);
		getContentPane().add(tfTeamName);

		tfCoach = new JTextField();
		tfCoach.setColumns(10);
		tfCoach.setBounds(473, 301, 174, 23);
		getContentPane().add(tfCoach);

		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(473, 346, 174, 23);
		getContentPane().add(tfQuantity);

		JButton btn_update = new JButton("UPDATE");
		try {
			btn_update.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("update.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = new Connect().getConnection();
				String sql = "UPDATE TeamMN SET TeamName = ?, Coach = ?, Quantity = ? WHERE TeamID = ?";
				try {
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(4, tfTeamID.getText());
					stm.setString(2, tfCoach.getText());
					stm.setString(1, tfTeamName.getText());
					stm.setString(3, tfQuantity.getText());
					stm.executeUpdate();

					JOptionPane.showMessageDialog(null, "Updated!");
					model2.setRowCount(0);
					showAll();

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Error!");
				}

			}
		});
		btn_update.setBounds(423, 397, 122, 44);
		getContentPane().add(btn_update);

		JButton btn_delete = new JButton("DELETE");
		btn_delete.setBounds(637, 397, 122, 44);
		getContentPane().add(btn_delete);
		try {
			btn_delete.setIcon( new ImageIcon(ImageIO.read( TeamManagement.class.getResourceAsStream("deleteicon.png" ))));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn = new Connect().getConnection();
				String sql = "DELETE FROM TeamMN WHERE TeamID=?";
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

		txtTeamid = new JTextField();
		txtTeamid.setForeground(SystemColor.activeCaptionBorder);
		txtTeamid.setBounds(781, 324, 96, 20);
		getContentPane().add(txtTeamid);
		txtTeamid.setColumns(10);

		JLabel lblSearch = new JLabel("Search By TeamID");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearch.setBounds(748, 298, 152, 24);
		getContentPane().add(lblSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				model2.setRowCount(0);
				showSearch();
				clearForm();
			}
		});

		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSearch.setBounds(791, 346, 70, 23);
		getContentPane().add(btnSearch);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 255, 127));
		menuBar.setForeground(new Color(152, 251, 152));
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

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnClose.setBounds(824, 21, 86, 29);
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

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 8));
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
		btnRefresh.setBounds(657, 323, 101, 29);
		getContentPane().add(btnRefresh);

		
		showAll();
	}

	private static File getResourceAsStream(String string) {
		
		return null;
	}
}
