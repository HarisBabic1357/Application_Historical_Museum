package projekt;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Eksponat {

	private static JTable table;
	private static JTextField textsifEksp;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	DefaultTableModel model = new DefaultTableModel();
	private JFrame frame;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	
	public void table_load() {
		try {
			pst = conn.prepareStatement("select * from eksponat");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		public void fillComboBox() {
			try {
				String query= "select * from period, dvorana, eksponat";
				
				
			 pst=conn.prepareStatement(query);
			
				 rs=pst.executeQuery();
				
				while(rs.next()) {
					comboBox.addItem(rs.getString("sifPeriod"));
					comboBox_1.addItem(rs.getString("sifDvor"));
					comboBox_2.addItem(rs.getString("sifTipEksp"));
					
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eksponat window = new Eksponat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Eksponat() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[] = { "sifEksp", "nazEksp", "materijalEksp", "velEksp", "sifPeriod", "sifDvor", "sifTipEksp" };
		model.setColumnIdentifiers(col);
		fillComboBox();

	}

	
	private static JTextField textnazEksp;
	private static JTextField textmaterijalEksp;
	private static JTextField textvelEksp;

	public void showWindow() {
		frame = new JFrame("EKSPONAT");
		frame.setBounds(100, 100, 773, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_3 = new JButton("Ubaci eksponat");
		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO eksponat(sifEksp,nazEksp, materijalEksp, velEksp,sifPeriod, sifDvor, sifTipEksp) VALUES(?,?,?,?,?,?,?)";
				//String sql = "INSERT INTO eksponat(sifEksp,nazEksp, materijalEksp, velEksp) VALUES(?,?,?,?)";
				try {
					pst = conn.prepareStatement(sql);
					pst.setString(1, textsifEksp.getText());
					pst.setString(2, textnazEksp.getText());
					pst.setString(3, textmaterijalEksp.getText());
					pst.setString(4, textvelEksp.getText());
					pst.setString(5, comboBox.getSelectedItem().toString());
					pst.setString(6, comboBox_1.getSelectedItem().toString());
					pst.setString(7, comboBox_2.getSelectedItem().toString());
					pst.execute();
					rs.close();
					pst.close();

				} catch (Exception ev) {
					JOptionPane.showMessageDialog(null, "System Update Completed");
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { textsifEksp.getText(), textnazEksp.getText(), textmaterijalEksp.getText(),
						textvelEksp.getText(), comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString()

				});
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "", JOptionPane.OK_OPTION);

					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(422, 350, 150, 23);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid = textsifEksp.getText();
				String naz = textnazEksp.getText();
				String mat = textmaterijalEksp.getText();
				String vel = textvelEksp.getText();

				// String prez=textsifDog.getText();
				// String rm=textsifRadMj.getText();

				try {
					pst = conn.prepareStatement("update eksponat set nazEksp=?, materijalEksp=?, velEksp=?  where sifEksp=?");
					pst.setString(1, naz);
					pst.setString(2, mat);
					pst.setString(3, vel);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update");
					//table_load();
					textsifEksp.setText("");
					textnazEksp.setText("");
					textmaterijalEksp.setText("");
					textvelEksp.setText("");
					//textsifPeriod.setText("");
					//textsifDvor.setText("");
					//textsifTipEksp.setText("");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(288, 350, 124, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Izbriši eksponat");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid = textsifEksp.getText();

				try {
					pst = conn.prepareStatement("delete from eksponat where sifEksp=?");
					pst.setString(1, bid);

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifEksp.setText("");
					textnazEksp.setText("");
					textmaterijalEksp.setText("");
					textvelEksp.setText("");
					//textsifPeriod.setText("");
					//textsifDvor.setText("");
					//textsifTipEksp.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(582, 350, 150, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 350, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JScrollPane myTable2 = new JScrollPane();
		myTable2.setBounds(28, 133, 704, 171);
		frame.getContentPane().add(myTable2);

		table = new JTable();
		myTable2.setViewportView(table);
		table.setToolTipText("\r\n");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifEksponat", "nazivEksponat", "materijalEksponat", "velicinaEksponat", "sifraPeriod", "sifraDvorana", "sifraTipEksponat"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(88);
		table.getColumnModel().getColumn(2).setPreferredWidth(103);
		table.getColumnModel().getColumn(3).setPreferredWidth(101);
		table.getColumnModel().getColumn(4).setPreferredWidth(64);
		table.getColumnModel().getColumn(6).setPreferredWidth(114);

		textsifEksp = new JTextField();
		textsifEksp.setBounds(121, 40, 86, 20);
		frame.getContentPane().add(textsifEksp);
		textsifEksp.setColumns(10);

		JLabel lblNewLabel = new JLabel("sifEksponat");
		lblNewLabel.setBounds(10, 43, 89, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("nazivEksponat");
		lblNewLabel_1.setBounds(241, 43, 124, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("materijalEksponat");
		lblNewLabel_2.setBounds(504, 43, 124, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("velicinaEksponat");
		lblNewLabel_3.setBounds(10, 86, 118, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("sifraPeriod");
		lblNewLabel_4.setBounds(205, 86, 89, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("sifraDvorana");
		lblNewLabel_5.setBounds(367, 86, 86, 14);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("sifraTipEksponat\r\n");
		lblNewLabel_6.setBounds(543, 86, 108, 14);
		frame.getContentPane().add(lblNewLabel_6);

		textnazEksp = new JTextField();
		textnazEksp.setBounds(384, 40, 86, 20);
		frame.getContentPane().add(textnazEksp);
		textnazEksp.setColumns(10);

		textmaterijalEksp = new JTextField();
		textmaterijalEksp.setBounds(646, 40, 86, 20);
		frame.getContentPane().add(textmaterijalEksp);
		textmaterijalEksp.setColumns(10);

		textvelEksp = new JTextField();
		textvelEksp.setBounds(138, 83, 57, 20);
		frame.getContentPane().add(textvelEksp);
		textvelEksp.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Prikaži");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from eksponat");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(138, 350, 136, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		 comboBox = new JComboBox();
		
		comboBox.setBounds(273, 82, 84, 22);
		frame.getContentPane().add(comboBox);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(454, 82, 77, 22);
		frame.getContentPane().add(comboBox_1);
		
		 comboBox_2 = new JComboBox();
		comboBox_2.setBounds(661, 82, 86, 22);
		frame.getContentPane().add(comboBox_2);
		//fillComboBox();

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Window1 w1 = new Window1();
				//w1.showWindow();
			}
		});

		frame.setVisible(true);
	}
}
