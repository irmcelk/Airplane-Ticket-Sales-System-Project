import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Tickets extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextArea biletlerim;
	public int custid = Session.getLoggedInUser().getUserId();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tickets frame = new Tickets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tickets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 150, 1080, 680);
		setBackground(SystemColor.controlShadow);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tickets.class.getResource("/images/globe.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 248, 255));
		setContentPane(contentPane);
		
		biletlerim = new JTextArea();
		biletlerim.setEditable(false);
		biletlerim.setFont(new Font("Arial", Font.BOLD, 13));
		biletlerim.setBounds(10, 10, 1050, 570);
		biletlerim.setBorder(new EmptyBorder(5, 5, 5, 5));
		biletlerim.setBackground(new Color(240, 248, 255));
		biletlerim.setVisible(true);
        contentPane.setLayout(null);
        contentPane.add(biletlerim);
        
        StringBuilder tickets = new StringBuilder();
	    try (Connection conn = project_connection.getConnection()) {
	        String sql = "SELECT * FROM biletlerim WHERE customer_id = ?;";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, custid);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int biletId = rs.getInt("bilet_id");
	                    int ucusId = rs.getInt("ucus_id");
	                    double biletFiyat = rs.getDouble("biletfiyat");
	                    boolean isBooked = rs.getBoolean("is_booked");
	                    int odemeId = rs.getInt("odeme_id");
	                    String odemeTarihi = rs.getString("odeme_tarihi");
	                    String classType = rs.getString("class");
	                    String durum = isBooked ? "\u00D6dendi" : "\u00D6denmedi";
	                    String status = rs.getString("status");
	                    
	                    tickets.append("Bilet ID: ")
	                           .append(biletId)
	                           .append(" / Ucus ID: ")
	                           .append(ucusId)
	                           .append(" / Bilet Fiyat: ")
	                           .append(biletFiyat)
	                           .append(" / \u00D6denme Durumu: ")
	                           .append(durum)
	                           .append(" / \u00D6deme ID: ")
	                           .append(odemeId)
	                           .append(" / \u00D6deme Tarihi: ")
	                           .append(odemeTarihi)
	                           .append(" / Class: ")
	                           .append(classType)
	                           .append(" / Status: ")
	                           .append(status)
	                           .append("\n\n");
	                }
	            }
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    biletlerim.setText(tickets.toString());
        
        JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(119, 136, 153));
		setJMenuBar(menuBar);
		
		JMenuItem profilField = new JMenuItem("Profil");
			profilField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Tickets.this.dispose();
					User userWindow = new User();
			        userWindow.setVisible(true);
				}
			});
		profilField.setBackground(UIManager.getColor("Slider.shadow"));
		profilField.setIcon(new ImageIcon(User.class.getResource("/images/customer_48.png")));
		menuBar.add(profilField);
		
		JMenuItem homeField = new JMenuItem("Home");
		homeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tickets.this.dispose();
				Menu menu = new Menu();
		        menu.setVisible(true);
			}
		});
		homeField.setBackground(UIManager.getColor("Slider.shadow"));
		homeField.setIcon(new ImageIcon(User.class.getResource("/images/home_48.png")));
		menuBar.add(homeField);
		
		JMenuItem basvurularimField = new JMenuItem("Biletlerim");
		basvurularimField.setBackground(UIManager.getColor("Slider.shadow"));
		basvurularimField.setIcon(new ImageIcon(User.class.getResource("/images/tckt_48.png")));
		menuBar.add(basvurularimField);
	}
	

}
