import java.io.IOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Card {
	public int custId = Session.getLoggedInUser().getUserId();
    private String ownerName;
    private String ownerSurname;
    private String cardNo;
    private int cvv;
    private Date expirationDate;
    private int cardID;

	public Card(int cardID) {
        this.cardID = loadCardIDFromDB();
        loadCardDetailsFromDB();
    }

    public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}
	
	private int loadCardIDFromDB() {
		try (Connection conn = project_connection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("SELECT kredikartiid FROM kredi_karti WHERE user_id = ?")) {
			pstmt.setInt(1, custId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	this.cardID = rs.getInt("kredikartiid");
            }
		} catch (SQLException | IOException e) {
            e.printStackTrace();
        }
		return cardID;
	}

    private void loadCardDetailsFromDB() {
        try (Connection conn = project_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM kredi_karti WHERE user_id = ?")) {
            pstmt.setInt(1, custId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.ownerName = rs.getString("sahip_ismi");
                this.ownerSurname = rs.getString("sahip_soyismi");
                this.cardNo = rs.getString("kart_no");
                this.cvv = rs.getInt("cvv"); 
                this.expirationDate = rs.getDate("expire_date"); 
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "Card{" +
               "cardID=" + cardID +
               ", ownerName='" + ownerName + '\'' +
               ", ownerSurname='" + ownerSurname + '\'' +
               ", cardNo='" + cardNo + '\'' +
               ", cvv=" + cvv +
               ", expirationDate=" + expirationDate +
               '}';
    }


    private String createCardID() throws SQLException, IOException {
        int maxkredikartiid = 0;
        try (Connection conn = project_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(kredikartiid) as max_id FROM kredi_karti;")) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	maxkredikartiid = rs.getInt("max_id");
            }
        }
        maxkredikartiid++;
        try (Connection conn = project_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO kredi_karti (user_id, sahip_ismi, sahip_soyismi, kart_no, cvv, expire_date, kredikartiid) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, custId);
            pstmt.setString(2, ownerName);
            pstmt.setString(3, ownerSurname);
            pstmt.setString(4, cardNo);
            pstmt.setInt(5, cvv);
            pstmt.setDate(6, expirationDate);
            pstmt.setInt(7, maxkredikartiid);
            pstmt.executeUpdate();
        }
        return String.valueOf(maxkredikartiid);
    }
    
    public boolean isValid() throws SQLException, IOException {
        String sql = "SELECT * FROM kredi_karti WHERE kredikartiid = ? AND sahip_ismi = ? AND sahip_soyismi = ? AND kart_no = ? AND cvv = ? AND expire_date = ?";
        boolean valid = false;
        try (Connection conn = project_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, this.cardID);
            pstmt.setString(2, this.ownerName);
            pstmt.setString(3, this.ownerSurname);
            pstmt.setString(4, this.cardNo);
            pstmt.setInt(5, this.cvv); 
            pstmt.setDate(6, this.expirationDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	Connection conn2 = project_connection.getConnection();
                    valid = checkBankDetails(conn2); 
                }
            }
        }
        return valid;
    }

    private boolean checkBankDetails(Connection conn) throws SQLException {
        String sql2 = "SELECT * FROM banka_bilgileri WHERE kredikartiid = ? AND sahip_ismi = ? AND sahip_soyismi = ? AND kart_no = ? AND cvv = ? AND expire_date = ?";
        try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            pstmt2.setInt(1, this.cardID);
            pstmt2.setString(2, this.ownerName);
            pstmt2.setString(3, this.ownerSurname);
            pstmt2.setString(4, this.cardNo);
            pstmt2.setInt(5, this.cvv); 
            pstmt2.setDate(6, this.expirationDate);
            try (ResultSet rs2 = pstmt2.executeQuery()) {
                return rs2.next();
            }
        }
    }


    public boolean hasMoney(double amount) throws SQLException, IOException {
        try (Connection conn = project_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT para_miktari FROM banka_bilgileri WHERE user_id = ?")) {
        	pstmt.setInt(1, custId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int balance = rs.getInt("para_miktari");
                return balance >= amount;
            }
            return false; 
        }
    }
    public double getMoney() throws SQLException, IOException {
        try (Connection conn = project_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT para_miktari FROM banka_bilgileri WHERE user_id = ?")) {
        	pstmt.setInt(1, custId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int balance = rs.getInt("para_miktari");
                return balance ;
            }
            return 0; 
        }
    }
    public double decreaseMoney(double amount) {
            try (Connection conn = project_connection.getConnection()) {
                String sql3 = "UPDATE banka_bilgileri SET para_miktari=para_miktari - ? WHERE user_id = ?;";
                try (PreparedStatement stmt = conn.prepareStatement(sql3)) {
                    stmt.setDouble(1, amount);
                    stmt.setInt(2, custId);
                    int affectedRows = stmt.executeUpdate();
                    if (affectedRows > 0) {
                    	amount=getMoney();
                        JOptionPane.showMessageDialog(null, "Kart bilgileri ba\u015Far\u0131yla g\u00FCncellenmi\u015Ftir.Yeni para miktar\u0131:  "+ amount);
                    } else {
                        JOptionPane.showMessageDialog(null, "Bir hata olu\u015Ftu. De\u011Fi\u015Fiklik yap\u0131lamad\u0131.");
                    }
                }
            } catch (SQLException | IOException ex) {
                JOptionPane.showMessageDialog(null, "Veritaban\u0131 hatas\u0131: " + ex.getMessage());
            }
           
			return amount;
        }
}
