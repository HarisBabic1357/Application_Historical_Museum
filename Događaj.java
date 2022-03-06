package projekt;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

public class Dogaðaj {

	private static JTextField textsifDog;
	private static JTextField textdatumDog;
	private static JTextField texttemaDog;
	private static JTextField textopisDog;
	private static JTable table;
	private JFrame frame;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	DefaultTableModel model = new DefaultTableModel();
	private JComboBox comboBox;

	public void table_load() {
		try {
			pst = conn.prepareStatement("select * from dogaðaj");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void fillComboBox() {
		try {
			String query= "select * from tip_dogaðaj";
			
			
		 pst=conn.prepareStatement(query);
		
			 rs=pst.executeQuery();
			
			while(rs.next()) {
				comboBox.addItem(rs.getString("sifTipDog"));
				
				
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
					Dogaðaj window = new Dogaðaj();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Dogaðaj() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[] = { "sifDog", "datumDog", "temaDog", "opisDog", "sifTipDog" };
		model.setColumnIdentifiers(col);
		fillComboBox();

	}

	public void showWindow() {
		frame = new JFrame("DOGAÐAJ");
		frame.setBounds(100, 100, 752, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_3 = new JButton("Ubaci dogaðaj");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO dogaðaj(sifDog, datumDog, temaDog, opisDog, sifTipDog) VALUES (?,?,?,?,?)";
				try {
					pst = conn.prepareStatement(sql);
					pst.setString(1, textsifDog.getText());
					pst.setString(2, textdatumDog.getText());
					pst.setString(3, texttemaDog.getText());
					pst.setString(4, textopisDog.getText());
					pst.setString(5, comboBox.getSelectedItem().toString());
					pst.execute();
					rs.close();
					pst.close();

				} catch (Exception ev) {
					JOptionPane.showMessageDialog(null, "System Update Completed");
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { textsifDog.getText(), textdatumDog.getText(), texttemaDog.getText(),
						textopisDog.getText(), comboBox.getSelectedItem().toString()

				});
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "", JOptionPane.OK_OPTION);

					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(416, 359, 150, 23);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_1 = new JButton("Ažuriraj ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bid = textsifDog.getText();
				String dt = textdatumDog.getText();
				String tema = texttemaDog.getText();
				String opis = textopisDog.getText();
				// String rm=textsifRadMj.getText();

				try {
					pst = conn.prepareStatement("update dogaðaj set datumDog=?, opisDog=? where sifDog=?");
					pst.setString(1, dt);
					pst.setString(2, opis);
					//pst.setString(2, tema);
					//pst.setString(3, opis);
					pst.setString(3, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update");
					//table_load();
					textsifDog.setText("");
					textdatumDog.setText("");
					texttemaDog.setText("");
					textopisDog.setText("");
					//textsifTipDog.setText("");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(268, 359, 138, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Izbriši dogaðaj");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid = textsifDog.getText();

				try {
					pst = conn.prepareStatement("delete from dogaðaj where sifDog=?");
					pst.setString(1, bid);

					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifDog.setText("");
					textdatumDog.setText("");
					texttemaDog.setText("");
					textopisDog.setText("");
					//textsifTipDog.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(576, 359, 150, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 359, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("sifDogaðaj");
		lblNewLabel.setBounds(20, 33, 97, 14);
		frame.getContentPane().add(lblNewLabel);

		textsifDog = new JTextField();
		textsifDog.setBounds(134, 30, 86, 20);
		frame.getContentPane().add(textsifDog);
		textsifDog.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("datumDogaðaj");
		lblNewLabel_1.setBounds(349, 33, 102, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textdatumDog = new JTextField();
		textdatumDog.setBounds(531, 30, 86, 20);
		frame.getContentPane().add(textdatumDog);
		textdatumDog.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("temaDogaðaj");
		lblNewLabel_2.setBounds(20, 107, 97, 14);
		frame.getContentPane().add(lblNewLabel_2);

		texttemaDog = new JTextField();
		texttemaDog.setBounds(134, 104, 86, 20);
		frame.getContentPane().add(texttemaDog);
		texttemaDog.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("opisDogaðaj");
		lblNewLabel_3.setBounds(263, 107, 79, 14);
		frame.getContentPane().add(lblNewLabel_3);

		textopisDog = new JTextField();
		textopisDog.setToolTipText("");
		textopisDog.setBounds(365, 104, 86, 20);
		frame.getContentPane().add(textopisDog);
		textopisDog.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("sifTipDogaðaj");
		lblNewLabel_4.setBounds(502, 107, 97, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 644, 126);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "sifDogaðaj", "datumDogaðaj",
				"temaDogaðaj", "opisDogaðaj", "sifTipDogaðaj" }));
		
		JButton btnNewButton_4 = new JButton("Prikaži");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from dogaðaj");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(169, 359, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
	 comboBox = new JComboBox();
		comboBox.setBounds(625, 99, 89, 22);
		frame.getContentPane().add(comboBox);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Window1 w1 = new Window1();
				// w1.showWindow();
			}
		});

		frame.setVisible(true);
	}

}
