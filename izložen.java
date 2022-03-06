package projekt;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
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
import javax.swing.JComboBox;

public class izložen {
	private static JTable table;
	Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	DefaultTableModel model= new DefaultTableModel();
	private JFrame frame;
	public void table_load() {
		try
		{
			pst=conn.prepareStatement("select * from izložen");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	public void fillComboBox() {
		try {
			String query= "select * from eksponat,dogaðaj";
			
			
		 pst=conn.prepareStatement(query);
		
			 rs=pst.executeQuery();
			
			while(rs.next()) {
				comboBox.addItem(rs.getString("sifEksp"));
				comboBox_1.addItem(rs.getString("sifDog"));
				
				
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]  args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					izložen window = new izložen ();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public izložen() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[]= {"sifEksp", "sifDog"};
		model.setColumnIdentifiers(col);
		fillComboBox();
	}
	public  void showWindow() {
		 frame= new JFrame("SPISAK IZLOŽENIH");
		frame.setBounds(100,100,730,426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci izloženi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="INSERT INTO izložen(sifEksp, sifDog)VALUES(?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,comboBox.getSelectedItem().toString());
					pst.setString(2,comboBox_1.getSelectedItem().toString());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						comboBox.getSelectedItem().toString(),
						comboBox_1.getSelectedItem().toString()
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "",JOptionPane.OK_OPTION);
						
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(398, 334, 150, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		/*
		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifEksp.getText();
				String bid2=textsifDog.getText();
				try {
					pst=conn.prepareStatement("update izložen set sifDog=? where sifEksp=?");
					pst.setString(1, bid2);
					pst.setString(2, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifEksp.setText("");
					textsifDog.setText("");
					
									}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(249, 334, 139, 23);
		frame.getContentPane().add(btnNewButton_1);
		*/
		JButton btnNewButton_2 = new JButton("Izbriši izloženi ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=comboBox.getSelectedItem().toString();
				try{
					pst=conn.prepareStatement("delete from izložen where sifEksp=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					//textsifEksp.setText("");
					//textsifDog.setText("");
				}
					catch(SQLException e1){
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(554, 334, 150, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(25, 334, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("sifEksponat");
		lblNewLabel.setBounds(25, 33, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("sifDogaðaj");
		lblNewLabel_1.setBounds(315, 33, 98, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 81, 564, 178);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifraEksponat", "sifraDogaðaj"
			}
		));
		
		JButton btnNewButton_4 = new JButton("Prikaži");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from izložen");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(150, 334, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(134, 29, 89, 22);
		frame.getContentPane().add(comboBox);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(504, 29, 98, 22);
		frame.getContentPane().add(comboBox_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			Window1 w1= new Window1();
			//w1.showWindow();
			}
		});
		
		
				
		frame.setVisible(true);
	}

}
