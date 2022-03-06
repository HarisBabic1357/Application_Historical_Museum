package projekt;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JTextField;

public class Window1{
	/*public static void main(String[] args) {
		showWindow();
	}*/
	private JFrame frame;
	 Connection conn=null;
	 PreparedStatement pst=  null;
	ResultSet rs= null;
	public static void main(String[]  args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window1 window = new Window1();
					window.frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public Window1() {
		showWindow();
		//conn = muzejdata.ConnectDB();
		//Object col[]= {"sifPeriod", "nazPeriod"};
		//model.setColumnIdentifiers(col);
			}
	public void showWindow() {
		frame= new JFrame("HISTORIJSKI MUZEJ");
		frame.getContentPane().setBackground(Color.BLUE);
		frame.setBounds(100,100,426,280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEksp = new JButton("EKSPONAT\r\n");
		btnEksp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEksp.setBounds(6, 93, 109, 23);
		frame.getContentPane().add(btnEksp);
		btnEksp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Eksponat w2= new Eksponat();
				//w2.showWindow();
			}
		});
		
		JButton btnDog = new JButton("DOGAÐAJ");
		btnDog.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDog.setBounds(6, 125, 109, 23);
		frame.getContentPane().add(btnDog);
		btnDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Dogaðaj w3= new Dogaðaj();
				//w3.showWindow();
			}
		});
		
		JButton btnPer = new JButton("PERIOD");
		btnPer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPer.setBounds(279, 93, 121, 23);
		frame.getContentPane().add(btnPer);
		btnPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Period w4= new Period();
				//w4.showWindow();
			}
		});
		
		JButton btnDvorana = new JButton("DVORANA");
		btnDvorana.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDvorana.setBounds(279, 125, 121, 23);
		frame.getContentPane().add(btnDvorana);
		btnDvorana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Dvorana w5= new Dvorana();
				//w5.showWindow();
			}
		});
		
		JButton btnZap = new JButton("ZAPOSLENIK");
		btnZap.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnZap.setBounds(6, 159, 161, 23);
		frame.getContentPane().add(btnZap);
		btnZap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Zaposlenik w6= new Zaposlenik();
				//w6.showWindow();
			}
		});
		
		JButton btnRadMj = new JButton("RADNO MJESTO");
		btnRadMj.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRadMj.setBounds(207, 159, 193, 23);
		frame.getContentPane().add(btnRadMj);
		btnRadMj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Radno_Mjesto w7= new Radno_Mjesto();
				//w7.showWindow();
			}
		});
		JButton btnTip_Dog = new JButton("TIP DOGAÐAJA");
		btnTip_Dog.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTip_Dog.setBounds(125, 125, 144, 23);
		frame.getContentPane().add(btnTip_Dog);
		btnTip_Dog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Tip_Dogaðaj w8= new Tip_Dogaðaj();
				//w8.showWindow();
			}
		});
		
		JButton btnTip_Eksp = new JButton("TIP EKSPONATA");
		btnTip_Eksp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTip_Eksp.setBounds(125, 93, 144, 23);
		frame.getContentPane().add(btnTip_Eksp);
		btnTip_Eksp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Tip_Eksponat w9= new Tip_Eksponat();
				//w9.showWindow();
			}
		});
		
		
		
		JLabel lblNewLabel = new JLabel("HISTORIJSKI MUZEJ - BAZA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 11, 293, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("IZLOŽENI EKSPONATI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				izložen w9= new izložen();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setToolTipText("");
		btnNewButton.setBounds(207, 193, 193, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UÈESNICI DOGAÐAJA");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				uèestvuje w9= new uèestvuje();
			}
		});
		btnNewButton_1.setBounds(6, 193, 161, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		frame.setVisible(true);
	}
}