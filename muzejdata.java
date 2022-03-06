package projekt;
import java.sql.*;
import javax.swing.*;
public class muzejdata {
	public static Connection ConnectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/hist_muzej?autoReconnect=true&useSSL=false&serverTimezone=UTC","root","student.,");
			JOptionPane.showMessageDialog(null,"Veza uspostavljena");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Greška u povezivanju");
			return null;
		}
	}

}

