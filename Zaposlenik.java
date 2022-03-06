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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Zaposlenik {
	private static JTable table;
	private static JTextField textsifZap;
	private static JTextField textprezZap;
	private static JTextField textimeZap;
	private JFrame frame;
	Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	 DefaultTableModel model= new DefaultTableModel();
	 public void table_load() {
			try
			{
				pst=conn.prepareStatement("select * from zaposlenik");
				rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	 private JComboBox comboBox;
		
		public void fillComboBox() {
			try {
				String query= "select * from radno_mjesto";
				
				
			 pst=conn.prepareStatement(query);
			
				 rs=pst.executeQuery();
				
				while(rs.next()) {
					comboBox.addItem(rs.getString("sifRadMjesto"));
					
					
					
					
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
					Zaposlenik window = new Zaposlenik();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
			}
	public Zaposlenik() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[]= {"sifZap", "nazZap","prezZap","sifRadMjesto"};
		model.setColumnIdentifiers(col);
		fillComboBox();
		
	}
	public void showWindow() {
		frame= new JFrame("ZAPOSLENIK");
		frame.setBounds(100,100,677,397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci zaposlenika");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="INSERT INTO zaposlenik(sifZap, imeZap, prezZap, sifRadMjesto)VALUES(?,?,?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,textsifZap.getText());
					pst.setString(2,textimeZap.getText());
					pst.setString(3,textprezZap.getText());
					pst.setString(4,comboBox.getSelectedItem().toString());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textsifZap.getText(),
						textimeZap.getText(),
						textprezZap.getText(),
						comboBox.getSelectedItem().toString()
						
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "",JOptionPane.OK_OPTION);
						
					}
				}
			
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(336, 314, 151, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		
		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifZap.getText();
				String im=textimeZap.getText();
				String prez=textprezZap.getText();
				//String rm=textsifRadMj.getText();
				
				try {
					pst=conn.prepareStatement("update zaposlenik set imeZap=?, prezZap=? where sifZap=?");
					pst.setString(1, im);
					pst.setString(2, prez);
					pst.setString(3, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifZap.setText("");
					textimeZap.setText("");
					textprezZap.setText("");
				   
					//textsifRadMj.setText("");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(207, 314, 119, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izbriši zaposlenika");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifZap.getText();
			
				
				try{
					pst=conn.prepareStatement("delete from zaposlenik where sifZap=?");
					pst.setString(1, bid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifZap.setText("");
					textimeZap.setText("");
					textprezZap.setText("");
					//textsifRadMj.setText("");
				}
					catch(SQLException e1){
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(506, 314, 145, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 314, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 112, 477, 163);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifZaposlenika", "imeZaposlenika", "prezimeZaposlenika", "sifRadnoMjesto"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		table.getColumnModel().getColumn(1).setPreferredWidth(121);
		table.getColumnModel().getColumn(2).setPreferredWidth(141);
		table.getColumnModel().getColumn(3).setPreferredWidth(113);
		
		JLabel lblNewLabel = new JLabel("sifZaposlenika");
		lblNewLabel.setBounds(30, 34, 112, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textsifZap = new JTextField();
		textsifZap.setBounds(152, 31, 86, 20);
		frame.getContentPane().add(textsifZap);
		textsifZap.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("prezimeZaposlenika");
		lblNewLabel_1.setBounds(318, 34, 119, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textprezZap = new JTextField();
		textprezZap.setBounds(476, 31, 86, 20);
		frame.getContentPane().add(textprezZap);
		textprezZap.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("imeZaposlenika");
		lblNewLabel_2.setBounds(30, 71, 112, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textimeZap = new JTextField();
		textimeZap.setBounds(152, 68, 86, 20);
		frame.getContentPane().add(textimeZap);
		textimeZap.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("sifraRadnoMjesto");
		lblNewLabel_3.setBounds(318, 71, 119, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_4 = new JButton("Prikaži");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from zaposlenik");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(109, 314, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(476, 62, 86, 22);
		frame.getContentPane().add(comboBox);
		
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
