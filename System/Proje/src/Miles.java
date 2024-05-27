import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Miles {
    private int custId = Session.getLoggedInUser().getUserId();
    private int totalMiles;
    private int counter;

    public Miles() {
        this.totalMiles = 0;
        this.counter = 0;
    }

    public int getTotalMiles() {
    	int mil = 0; 
        try (Connection conn = project_connection.getConnection()) {
            String sql = "SELECT * FROM profil where user_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, custId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        mil = rs.getInt("mil_durumu"); 
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } catch (SQLException | IOException e3) {
			e3.printStackTrace();
        }
        return mil; 
    }
 
    public double decreaseMil(double amount) {
            try (Connection conn = project_connection.getConnection()) {
                String sql3 = "UPDATE profil SET mil_durumu=mil_durumu - ? WHERE user_id = ?;";
                try (PreparedStatement stmt = conn.prepareStatement(sql3)) {
                    stmt.setDouble(1, amount);
                    stmt.setInt(2, custId);
                    int affectedRows = stmt.executeUpdate();
                    if (affectedRows > 0) {
                    	amount=getTotalMiles();
                        JOptionPane.showMessageDialog(null, "Mil bilgileri ba\u015Far\u0131yla g\u00FCncellenmi\u015Ftir.Yeni mil miktar\u0131:  "+ amount);
                    } else {
                        JOptionPane.showMessageDialog(null, "Bir hata olu\u015Ftu. De\u011Fi\u015Fiklik yap\u0131lamad\u0131.");
                    }
                }
            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(null, "Veritaban\u0131 hatas\u0131: " + ex.getMessage());
            }
           
			return amount;
        }
    
    public double addMil(double tutar) {
        try (Connection conn = project_connection.getConnection()) {
            String sql3 = "UPDATE profil SET mil_durumu=mil_durumu + ? WHERE user_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(sql3)) {
                stmt.setDouble(1, tutar);
                stmt.setInt(2, custId);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                	tutar=getTotalMiles();
                    JOptionPane.showMessageDialog(null, "Mil bilgileri ba\u015Far\u0131yla g\u00FCncellenmi\u015Ftir.Yeni mil miktar\u0131:  "+ tutar);
                } else {
                    JOptionPane.showMessageDialog(null, "Bir hata olu\u015Ftu. De\u011Fi\u015Fiklik yap\u0131lamad\u0131.");
                }
            }
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Veritaban\u0131 hatas\u0131: " + ex.getMessage());
        }
       
		return tutar;
    }

    public int getCounter() {
        return this.counter;
    }
}
