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

public class Radno_Mjesto {
	private JFrame frame;
	private static JTextField textsifRadMj;
	private static JTextField textnazRadMj;
	private static JTable table;
	Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	 DefaultTableModel model= new DefaultTableModel();
	 public void table_load() {
			try
			{
				pst=conn.prepareStatement("select * from radno_mjesto");
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
					Radno_Mjesto window = new Radno_Mjesto();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public Radno_Mjesto() {
		showWindow();
		conn = muzejdata.ConnectDB();
		Object col[]= {"sifRadMjesto", "nazRadMjesto"};
		model.setColumnIdentifiers(col);
			}
	public  void showWindow() {
		 frame= new JFrame("RADNO MJESTO");
		frame.setBounds(100,100,752,438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci radno mjesto");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="INSERT INTO radno_mjesto(sifRadMjesto, nazRadMjesto)VALUES(?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,textsifRadMj.getText());
					pst.setString(2,textnazRadMj.getText());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textsifRadMj.getText(),
						textnazRadMj.getText(),
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "",JOptionPane.OK_OPTION);
						
					}
				}
			
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(428, 352, 150, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		
		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifRadMj.getText();
				String naz=textnazRadMj.getText();
				try {
					pst=conn.prepareStatement("update radno_mjesto set nazRadMjesto=? where sifRadMjesto=?");
					pst.setString(1, naz);
					pst.setString(2, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifRadMj.setText("");
					textnazRadMj.setText("");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(268, 352, 150, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izbriši radno mjesto");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifRadMj.getText();
				try{
					pst=conn.prepareStatement("delete from radno_mjesto where sifRadMjesto=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifRadMj.setText("");
					textnazRadMj.setText("");
				}
					catch(SQLException e1){
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(586, 352, 150, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(10, 352, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("sifRadnoMjesto");
		lblNewLabel.setBounds(20, 35, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textsifRadMj = new JTextField();
		textsifRadMj.setBounds(169, 32, 86, 20);
		frame.getContentPane().add(textsifRadMj);
		textsifRadMj.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("nazivRadnoMjesto");
		lblNewLabel_1.setBounds(398, 35, 116, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textnazRadMj = new JTextField();
		textnazRadMj.setBounds(568, 32, 86, 20);
		frame.getContentPane().add(textnazRadMj);
		textnazRadMj.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 101, 598, 148);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifraRadnoMjesto", "nazivRadnoMjesto"
			}
		));
		
		JButton btnNewButton_4 = new JButton("Prika\u017Ei");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from radno_mjesto");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(169, 352, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setPreferredWidth(116);
		
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
