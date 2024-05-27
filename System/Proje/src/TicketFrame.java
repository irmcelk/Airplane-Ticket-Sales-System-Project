import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Toolkit;

public class TicketFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Ticket ticket;
    static int kisiSayisi;
    String fullName;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicketFrame frame = new TicketFrame(ticket, kisiSayisi);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TicketFrame(Ticket ticket, int kisiSayisi) throws SQLException, IOException {
    	setTitle("Y\u0131ld\u0131z Hava Yollar\u0131");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(TicketFrame.class.getResource("/images/globe.png")));
    	setResizable(false);
        TicketFrame.ticket = ticket;
        TicketFrame.kisiSayisi = kisiSayisi; // KisiSayisi'ni sýnýf deðiþkenine atamayý unuttum
		setBounds(420, 200, 620, 372);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNewLabel.setBounds(56, 79, 164, 25);
		contentPane.add(lblNewLabel);

		JLabel infoField = new JLabel();
		infoField.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		infoField.setBounds(56, 121, 553, 42);
		contentPane.add(infoField);

		JLabel lblNewLabel_1 = new JLabel("baþarýyla oluþturulmuþtur.");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(56, 160, 226, 42);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Ýyi uçuþlar dileriz :)");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(402, 252, 180, 42);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Bilet ayrýntýlarýna ayrýca Biletlerim kýsmýndan eriþebilirsiniz.");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(56, 200, 465, 42);
		contentPane.add(lblNewLabel_3);

		
		Menu reachMenu = new Menu();
		if (reachMenu != null) {
		   fullName = reachMenu.getFullName();
		}
		System.out.println("Full Name: " + fullName); //kontrol
		lblNewLabel.setText("Sayýn " + fullName + ",");
		infoField.setText(ticket.getDate() + " tarihli " + ticket.getFromWhere() + "-" + ticket.getToWhere()
		        + " için " + ticket.getStart_at() + " - " + ticket.getEnd_at() + " arasý " + kisiSayisi + " adet biletiniz");
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TicketFrame.class.getResource("/images/aplane64.png")));
		lblNewLabel_4.setBounds(440, 35, 119, 88);
		contentPane.add(lblNewLabel_4);
    }
}
