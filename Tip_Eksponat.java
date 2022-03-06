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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Tip_Eksponat {
	private static JTable table;
	private static JTextField textsifTipEksp;
	private static JTextField textnazTipEksp;
	Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	DefaultTableModel model= new DefaultTableModel();
	private JFrame frame;
	public void table_load() {
		try
		{
			pst=conn.prepareStatement("select * from tip_eksponat");
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
					Tip_Eksponat window = new Tip_Eksponat();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
			}
	
	public Tip_Eksponat() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[]= {"sifTipEksp", "nazTipEksp"};
		model.setColumnIdentifiers(col);
		
	}
	public void showWindow() {
		frame= new JFrame("TIP EKSPONAT");
		frame.setBounds(100,100,703,397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci tip eksponata");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="INSERT INTO tip_eksponat(sifTipEksp, nazTipEksp)VALUES(?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,textsifTipEksp.getText());
					pst.setString(2,textnazTipEksp.getText());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textsifTipEksp.getText(),
						textnazTipEksp.getText(),
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "",JOptionPane.OK_OPTION);
						
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(353, 310, 150, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		
		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifTipEksp.getText();
				String naz=textnazTipEksp.getText();
				try {
					pst=conn.prepareStatement("update tip_eksponat set nazTipEksp=? where sifTipEksp=?");
					pst.setString(1, naz);
					pst.setString(2, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifTipEksp.setText("");
					textnazTipEksp.setText("");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(218, 310, 125, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izbriši tip eksponata");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifTipEksp.getText();
				try{
					pst=conn.prepareStatement("delete from tip_eksponat where sifTipEksp=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifTipEksp.setText("");
					textnazTipEksp.setText("");
				}
					catch(SQLException e1){
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(513, 310, 150, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 310, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 113, 536, 125);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifraTipEksponat", "nazTipEksponat"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.getColumnModel().getColumn(1).setPreferredWidth(108);
		
		JLabel lblNewLabel = new JLabel("sifraTipEksponat");
		lblNewLabel.setBounds(10, 31, 136, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textsifTipEksp = new JTextField();
		textsifTipEksp.setBounds(147, 28, 86, 20);
		frame.getContentPane().add(textsifTipEksp);
		textsifTipEksp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("nazTipEksponata");
		lblNewLabel_1.setBounds(336, 31, 125, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textnazTipEksp = new JTextField();
		textnazTipEksp.setBounds(471, 28, 86, 20);
		frame.getContentPane().add(textnazTipEksp);
		textnazTipEksp.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Prika\u017Ei");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from tip_eksponat");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(119, 310, 89, 23);
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
