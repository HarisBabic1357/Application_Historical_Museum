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

public class Dvorana {
	private static JTextField textsifDvor;
	private static JTextField textnazDvor;
	private static JTextField textopisDvor;
	private static JTextField textkapacLjudi;
	private static JTextField textkapacEksp;
	private static JTable table;
	private JFrame frame;
	private JComboBox comboBox_1;
	Connection conn=null;
	 PreparedStatement pst=  null;
	 ResultSet rs= null;
	 DefaultTableModel model= new DefaultTableModel();
	public void table_load() {
		try
		{
			pst=conn.prepareStatement("select * from dvorana");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void fillComboBox() {
		try {
			String query= "select * from dogaðaj";
			
			
		 pst=conn.prepareStatement(query);
		
			 rs=pst.executeQuery();
			
			while(rs.next()) {
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
				Dvorana window = new Dvorana();
				window.frame.setVisible(true);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	});
	
}
public Dvorana() {
	showWindow();
	conn = muzejdata.ConnectDB();
	Object col[]= {"sifDvor", "nazDvor","opisDvor","kapacLjudi","kapacEksp","sifDog"};
	model.setColumnIdentifiers(col);
	fillComboBox();
	
}
	public void showWindow() {
		 frame= new JFrame("DVORANA");
		frame.setBounds(100,100,753,423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ubaci dvoranu");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="INSERT INTO dvorana(sifDvor,nazDvor, opisDvor, kapacLjudi,kapacEksp, sifDog) VALUES(?,?,?,?,?,?)";
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1,textsifDvor.getText());
					pst.setString(2,textnazDvor.getText());
					pst.setString(3,textopisDvor.getText());
					pst.setString(4,textkapacLjudi.getText());
					pst.setString(5,textkapacEksp.getText());
					pst.setString(6,comboBox_1.getSelectedItem().toString());
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textsifDvor.getText(),
						textnazDvor.getText(),
						textopisDvor.getText(),
						textkapacLjudi.getText(),
						textkapacEksp.getText(),
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
		btnNewButton_3.setBounds(413, 337, 150, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		
		JButton btnNewButton_1 = new JButton("Ažuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid=textsifDvor.getText();
				String im=textnazDvor.getText();
				//String prez=textsifDog.getText();
				//String rm=textsifRadMj.getText();
				
				try {
					pst=conn.prepareStatement("update dvorana set nazDvor=? where sifDvor=?");
					pst.setString(1, im);
					pst.setString(2, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Update");
					//table_load();
					textsifDvor.setText("");
					textnazDvor.setText("");
					textopisDvor.setText("");
					textkapacLjudi.setText("");
					textkapacEksp.setText("");
					//textsifDog.setText("");
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(276, 337, 129, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izbriši dvoranu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                  String bid=textsifDvor.getText();
			
				
				try{
					pst=conn.prepareStatement("delete from dvorana where sifDvor=?");
					pst.setString(1, bid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					//table_load();
					textsifDvor.setText("");
					textnazDvor.setText("");
					textopisDvor.setText("");
					textkapacLjudi.setText("");
					textkapacEksp.setText("");
					//textsifDog.setText("");
				}
					catch(SQLException e1){
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(573, 337, 150, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(21, 337, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("sifraDvorane");
		lblNewLabel.setBounds(21, 33, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("opisDvorane");
		lblNewLabel_1.setBounds(272, 33, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("kapacitetEksponati");
		lblNewLabel_2.setBounds(499, 33, 111, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("nazivDvorane");
		lblNewLabel_3.setBounds(21, 83, 89, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("kapacitetLjudi");
		lblNewLabel_4.setBounds(272, 83, 89, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("sifDoga\u0111aj");
		lblNewLabel_5.setBounds(499, 83, 95, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textsifDvor = new JTextField();
		textsifDvor.setBounds(156, 30, 86, 20);
		frame.getContentPane().add(textsifDvor);
		textsifDvor.setColumns(10);
		
		textnazDvor = new JTextField();
		textnazDvor.setBounds(156, 80, 86, 20);
		frame.getContentPane().add(textnazDvor);
		textnazDvor.setColumns(10);
		
		textopisDvor = new JTextField();
		textopisDvor.setBounds(403, 30, 86, 20);
		frame.getContentPane().add(textopisDvor);
		textopisDvor.setColumns(10);
		
		textkapacLjudi = new JTextField();
		textkapacLjudi.setBounds(403, 80, 86, 20);
		frame.getContentPane().add(textkapacLjudi);
		textkapacLjudi.setColumns(10);
		
		textkapacEksp = new JTextField();
		textkapacEksp.setBounds(620, 30, 86, 20);
		frame.getContentPane().add(textkapacEksp);
		textkapacEksp.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 140, 654, 133);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"sifraDvorane", "nazivDvorane", "opisDvorane", "kapacitetLjudi", "kapacitetEksponati", "sifDoga\u0111aj"
			}
		));
		
		JButton btnNewButton_4 = new JButton("Prikaži");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					pst=conn.prepareStatement("select * from dvorana");
					rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(SQLException ev) {
					ev.printStackTrace();
				}
				
			}
		});
		btnNewButton_4.setBounds(141, 337, 125, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		 comboBox_1 = new JComboBox();
		comboBox_1.setBounds(620, 79, 86, 22);
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
