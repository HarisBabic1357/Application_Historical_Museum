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

public class Tip_Doga�aj {
	private static JTextField textsifTipDog;
	private static JTextField textnazTipDog;
	private static JTable table;
	Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	DefaultTableModel model= new DefaultTableModel();
	private JFrame frame;
	public void table_load() {
		try
		{
			pst=conn.prepareStatement("select * from tip_doga�aj");
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
					Tip_Doga�aj window = new Tip_Doga�aj();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
			}
	
	public Tip_Doga�aj() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[]= {"sifTipDog", "nazTipDog"};
		model.setColumnIdentifiers(col);
		
	}
	public  void showWindow() {
		 frame= new JFrame("TIP DOGA�AJA");
		frame.setBounds(100,100,730,426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci tip doga�aja");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="INSERT INTO tip_doga�aj(sifTipDog, nazTipDog)VALUES(?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,textsifTipDog.getText());
					pst.setString(2,textnazTipDog.getText());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textsifTipDog.getText(),
						textnazTipDog.getText()
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
		
	
		
		JButton btnNewButton_1 = new JButton("A�uriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifTipDog.getText();
				String naz=textnazTipDog.getText();
				try {
					pst=conn.prepareStatement("update tip_doga�aj set nazTipDog=? where sifTipDog=?");
					pst.setString(1, naz);
					pst.setString(2, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifTipDog.setText("");
					textnazTipDog.setText("");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(249, 334, 139, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izbri�i tip doga�aja");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifTipDog.getText();
				try{
					pst=conn.prepareStatement("delete from tip_doga�aj where sifTipDog=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifTipDog.setText("");
					textnazTipDog.setText("");
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
		
		JLabel lblNewLabel = new JLabel("sifTipDoga�aj");
		lblNewLabel.setBounds(25, 33, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textsifTipDog = new JTextField();
		textsifTipDog.setBounds(130, 30, 86, 20);
		frame.getContentPane().add(textsifTipDog);
		textsifTipDog.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("nazivTipDoga�aj");
		lblNewLabel_1.setBounds(315, 33, 98, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textnazTipDog = new JTextField();
		textnazTipDog.setBounds(462, 30, 86, 20);
		frame.getContentPane().add(textnazTipDog);
		textnazTipDog.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 81, 564, 178);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifTipDoga�aj", "nazivTipDoga�aj"
			}
		));
		
		JButton btnNewButton_4 = new JButton("Prika�i");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from tip_doga�aj");
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
