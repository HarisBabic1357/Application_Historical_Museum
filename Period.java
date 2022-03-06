package projekt;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.*;



public class Period {
	
	private static JTextField textsifPer;
	private static JTextField textnazPer;
	private static JTable table;
	 Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	DefaultTableModel model= new DefaultTableModel();
	private JFrame frame;
	/*
	public void updateTable() {
		conn= muzejdata.ConnectDB();
		if(conn!=null) {
			
			String sql="Select sifPeriod, nazPeriod";
		
		try {
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			Object[] columnData= new Object[2];
			while(rs.next()) {
				columnData[0]= rs.getString("sifPeriod");
				columnData[1]= rs.getString("nazPeriod");
				
				model.addRow(columnData);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		
	}
	}
	*/
	
	public void table_load() {
		try
		{
			pst=conn.prepareStatement("select * from period");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]  args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Period window = new Period();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
		public Period() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[]= {"sifPeriod", "nazPeriod"};
		model.setColumnIdentifiers(col);
		
	}
	public  void showWindow() {
		 frame= new JFrame("PERIOD");
		frame.setBounds(100,100,755,437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci period");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="INSERT INTO period(sifPeriod, nazPeriod)VALUES(?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,textsifPer.getText());
					pst.setString(2,textnazPer.getText());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textsifPer.getText(),
						textnazPer.getText(),
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "",JOptionPane.OK_OPTION);
						
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(433, 339, 136, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		
		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifPer.getText();
				String naz=textnazPer.getText();
				try {
					pst=conn.prepareStatement("update period set nazPeriod=? where sifPeriod=?");
					pst.setString(1, naz);
					pst.setString(2, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifPer.setText("");
					textnazPer.setText("");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(273, 339, 150, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izbriši period");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifPer.getText();
				try{
					pst=conn.prepareStatement("delete from period where sifPeriod=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifPer.setText("");
					textnazPer.setText("");
				}
					catch(SQLException e1){
						e1.printStackTrace();
					}
					}
					
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(579, 339, 150, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(24, 339, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("sifPeriod");
		lblNewLabel.setBounds(30, 38, 69, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nazivPeriod");
		lblNewLabel_1.setBounds(417, 38, 136, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textsifPer = new JTextField();
		textsifPer.setBounds(143, 35, 86, 20);
		frame.getContentPane().add(textsifPer);
		textsifPer.setColumns(10);
		
		textnazPer = new JTextField();
		textnazPer.setBounds(579, 35, 86, 20);
		frame.getContentPane().add(textnazPer);
		textnazPer.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 95, 556, 163);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifraPeriod", "nazivPeriod"
			}
		));
		
		JButton btnNewButton_4 = new JButton("Prikaži");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from period");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(174, 339, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
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
