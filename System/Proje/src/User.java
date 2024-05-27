import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
public class User extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public int custid = Session.getLoggedInUser().getUserId();
	public JTextArea biletlerim;
	Menu menuWindow = new Menu();
	Miles miles = new Miles();
	Tickets tickets = new Tickets();
	private JTextField isimfield;
	private JTextField soyisimfield;
	private JTextField emailfield;
	private JPasswordField þifrefield;
	private JTextField phonefield;
	private JTextField adresfield;
	private JTextField milfield;
	private JTextField tcfield;
	private JMenuItem basvurularimField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}}});
	}
	public User() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1080, 680);
		setBounds(430, 150, 1074, 657);
		setBackground(SystemColor.controlShadow);
		setIconImage(Toolkit.getDefaultToolkit().getImage(User.class.getResource("/images/globe.png")));
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
		biletlerim.setVisible(false);
        contentPane.setLayout(null);
        contentPane.add(biletlerim);
        
		JLabel isimlabel = new JLabel("Ýsim: ");
        isimlabel.setBounds(40, 60, 120, 20);
        contentPane.add(isimlabel);
        
        isimfield = new JTextField();
        isimfield.setBounds(170, 60, 270, 20);
        contentPane.add(isimfield);
        isimfield.setColumns(10);
        
        JButton isimbuton = new JButton("New button");
        isimbuton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name = isimfield.getText();
                try (Connection conn = project_connection.getConnection()) {
                    String sql3 = "UPDATE musteriler SET fname = ? WHERE customerid = ?;";
                    try (PreparedStatement stmt = conn.prepareStatement(sql3)) {
                        stmt.setString(1, name); 
                        stmt.setInt(2, custid); 
                        int affectedRows = stmt.executeUpdate(); 
                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Baþarýyla deðiþtirilmiþtir.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
        	}
        });
        isimbuton.setBounds(450, 60, 50, 20);
        int buttonWidth = isimbuton.getWidth()+10;
        int buttonHeight = isimbuton.getHeight();
        ImageIcon originalIcon = new ImageIcon(User.class.getResource("/images/change.png"));
        ImageIcon resizedIcon = resizeIcon(originalIcon, buttonWidth, buttonHeight);
        isimbuton.setIcon(resizedIcon);
        contentPane.add(isimbuton);
        
        JLabel soyisimlabel = new JLabel("Soyisim: ");
        soyisimlabel.setBounds(40, 90, 120, 20);
        contentPane.add(soyisimlabel);
        
        soyisimfield = new JTextField();
        soyisimfield.setBounds(170, 90, 270, 20);
        contentPane.add(soyisimfield);
        soyisimfield.setColumns(10);
        
        JButton soyisimbuton = new JButton("New button");
        soyisimbuton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String surname = soyisimfield.getText();
                try (Connection conn = project_connection.getConnection()) {
                    String sql4 = "UPDATE musteriler SET lname = ? WHERE customerid = ?;";
                    try (PreparedStatement stmt = conn.prepareStatement(sql4)) {
                        stmt.setString(1, surname); 
                        stmt.setInt(2, custid); 
                        int affectedRows = stmt.executeUpdate(); 
                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Baþarýyla deðiþtirilmiþtir.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
        	}
        });
        soyisimbuton.setBounds(450, 90, 50, 20);
        soyisimbuton.setIcon(resizedIcon);
        contentPane.add(soyisimbuton);
        
        JLabel emaillabel = new JLabel("Email: ");
        emaillabel.setBounds(40, 120, 120, 20);
        contentPane.add(emaillabel);
        
        emailfield = new JTextField();
        emailfield.setBounds(170, 120, 270, 20);
        contentPane.add(emailfield);
        emailfield.setColumns(10);
        
        JButton emailbuton = new JButton("New button");
        emailbuton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String email = emailfield.getText();
                try (Connection conn = project_connection.getConnection()) {
                    String sql5 = "UPDATE musteriler SET email = ? WHERE customerid = ?;";
                    try (PreparedStatement stmt = conn.prepareStatement(sql5)) {
                        stmt.setString(1, email); 
                        stmt.setInt(2, custid); 
                        int affectedRows = stmt.executeUpdate(); 
                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Baþarýyla deðiþtirilmiþtir.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
        	}
        });
        emailbuton.setBounds(450, 120, 50, 20);
        emailbuton.setIcon(resizedIcon);
        contentPane.add(emailbuton);
        
        JLabel þifrelabel = new JLabel("Þifre: ");
        þifrelabel.setBounds(40, 150, 120, 20);
        contentPane.add(þifrelabel);
        
        þifrefield = new JPasswordField();
        þifrefield.setBounds(170, 150, 270, 20);
        contentPane.add(þifrefield);
        
        JButton þifrebuton = new JButton("New button");
        þifrebuton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		String þifre = new String(þifrefield.getPassword());
;                try (Connection conn = project_connection.getConnection()) {
                    String sql6 = "UPDATE musteriler SET passwords = ? WHERE customerid = ?;";
                    try (PreparedStatement stmt = conn.prepareStatement(sql6)) {
                        stmt.setString(1, þifre); 
                        stmt.setInt(2, custid); 
                        int affectedRows = stmt.executeUpdate(); 
                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Baþarýyla deðiþtirilmiþtir.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
        	}
        });
        þifrebuton.setBounds(450, 150, 50, 20);
        þifrebuton.setIcon(resizedIcon);
        contentPane.add(þifrebuton);
        
        JLabel phonelabel = new JLabel("Telefon numarasý: ");
        phonelabel.setBounds(40, 180, 120, 20);
        contentPane.add(phonelabel);
        
        phonefield = new JTextField();
        phonefield.setBounds(170, 180, 270, 20);
        contentPane.add(phonefield);
        phonefield.setColumns(10);
        
        JButton phonebuton = new JButton("New button");
        phonebuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phone = phonefield.getText();
                try (Connection conn = project_connection.getConnection()) {
                    String checkSql = "SELECT * FROM profil WHERE user_id = ?;";
                    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                        checkStmt.setInt(1, custid);
                        ResultSet rs = checkStmt.executeQuery();
                        if (rs.next()) {
                            String sql7 = "UPDATE profil SET phone_number = ? WHERE user_id = ?;";
                            try (PreparedStatement stmt = conn.prepareStatement(sql7)) {
                                stmt.setString(1, phone);
                                stmt.setInt(2, custid);
                                int affectedRows = stmt.executeUpdate();
                                if (affectedRows > 0) {
                                    JOptionPane.showMessageDialog(null, "Telefon numarasý baþarýyla güncellenmiþtir.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                                }
                            }
                        } else {
                            String insertSql = "INSERT INTO profil (user_id, phone_number) VALUES (?, ?);";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                                insertStmt.setInt(1, custid);
                                insertStmt.setString(2, phone);
                                int affectedRows = insertStmt.executeUpdate();
                                if (affectedRows > 0) {
                                    JOptionPane.showMessageDialog(null, "Telefon numarasý baþarýyla eklenmiþtir.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Telefon numarasý eklenemedi.");
                                }
                            }
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
            }
        });
        phonebuton.setBounds(450, 180, 50, 20);
        phonebuton.setIcon(resizedIcon);
        contentPane.add(phonebuton);
        
        JLabel tclabel = new JLabel("Kimlik Numarasý: ");
        tclabel.setBounds(40, 210, 120, 20);
        contentPane.add(tclabel);
        
        tcfield = new JTextField();
        tcfield.setBounds(170, 210, 270, 20);
        contentPane.add(tcfield);
        phonefield.setColumns(10);
        
        JButton tcbuton = new JButton("New button");
        tcbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tc = tcfield.getText();
                try (Connection conn = project_connection.getConnection()) {
                    String checkSql = "SELECT * FROM profil WHERE user_id = ?;";
                    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                        checkStmt.setInt(1, custid);
                        ResultSet rs = checkStmt.executeQuery();
                        if (rs.next()) {
                            String sql8 = "UPDATE profil SET tc_no = ? WHERE user_id = ?;";
                            try (PreparedStatement stmt = conn.prepareStatement(sql8)) {
                                stmt.setString(1, tc);
                                stmt.setInt(2, custid);
                                int affectedRows = stmt.executeUpdate();
                                if (affectedRows > 0) {
                                    JOptionPane.showMessageDialog(null, "TC kimlik numarasý baþarýyla güncellenmiþtir.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                                }
                            }
                        } else {
                            String insertSql = "INSERT INTO profil (user_id, tc_no) VALUES (?, ?);";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                                insertStmt.setInt(1, custid);
                                insertStmt.setString(2, tc);
                                int affectedRows = insertStmt.executeUpdate();
                                if (affectedRows > 0) {
                                    JOptionPane.showMessageDialog(null, "TC kimlik numarasý baþarýyla eklenmiþtir.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. TC kimlik numarasý eklenemedi.");
                                }
                            }
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
            }
        });
        tcbuton.setBounds(450, 210, 50, 20);
        tcbuton.setIcon(resizedIcon);
        contentPane.add(tcbuton);
        
        JLabel adreslabel = new JLabel("Adres: ");
        adreslabel.setBounds(40, 240, 120, 20);
        contentPane.add(adreslabel);
        
        adresfield = new JTextField();
        adresfield.setBounds(170, 240, 270, 20);
        contentPane.add(adresfield);
        adresfield.setColumns(10);
        
        JButton adresbuton = new JButton("New button");
        adresbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adres = adresfield.getText();
                try (Connection conn = project_connection.getConnection()) {
                    String checkSql = "SELECT * FROM profil WHERE user_id = ?;";
                    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                        checkStmt.setInt(1, custid);
                        ResultSet rs = checkStmt.executeQuery();
                        if (rs.next()) {
                            String sql9 = "UPDATE profil SET adres = ? WHERE user_id = ?;";
                            try (PreparedStatement stmt = conn.prepareStatement(sql9)) {
                                stmt.setString(1, adres);
                                stmt.setInt(2, custid);
                                int affectedRows = stmt.executeUpdate();
                                if (affectedRows > 0) {
                                    JOptionPane.showMessageDialog(null, "Adres baþarýyla güncellenmiþtir.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                                }
                            }
                        } else {
                            String insertSql = "INSERT INTO profil (user_id, adres) VALUES (?, ?);";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                                insertStmt.setInt(1, custid);
                                insertStmt.setString(2, adres);
                                int affectedRows = insertStmt.executeUpdate();
                                if (affectedRows > 0) {
                                    JOptionPane.showMessageDialog(null, "Adres baþarýyla eklenmiþtir.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Adres eklenemedi.");
                                }
                            }
                        }
                    }
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                }
            }
        });
        adresbuton.setBounds(450, 240, 50, 20);
        adresbuton.setIcon(resizedIcon);
        contentPane.add(adresbuton);

        
        JLabel millabel = new JLabel("Mil Durumu: ");
        millabel.setBounds(500, 30, 80, 20);
        contentPane.add(millabel);
        
        milfield = new JTextField();
        milfield.setBounds(581, 30, 100, 20);
        contentPane.add(milfield);
        milfield.setColumns(10);
        
        JLabel kartlabel_1 = new JLabel("Kart 1");
        kartlabel_1.setBounds(40, 290, 50, 20);
        contentPane.add(kartlabel_1);
        
        JLabel kartisim_1 = new JLabel("Sahip Ýsmi: ");
        kartisim_1.setBounds(90, 290, 130, 20);
        contentPane.add(kartisim_1);
        
        JTextField kartisimfield_1 = new JTextField();
        kartisimfield_1.setBounds(225, 290, 215, 20);
        contentPane.add(kartisimfield_1);
        kartisimfield_1.setColumns(10);
        
        JLabel kartsoyisim_1 = new JLabel("Sahip Soyismi: ");
        kartsoyisim_1.setBounds(90, 320, 130, 20);
        contentPane.add(kartsoyisim_1);
        
        JTextField kartsoyisimfield_1 = new JTextField();
        kartsoyisimfield_1.setBounds(225, 320, 215, 20);
        contentPane.add(kartsoyisimfield_1);
        kartsoyisimfield_1.setColumns(10);
        
        JLabel kartno = new JLabel("Kart Numarasý: ");
        kartno.setBounds(90, 350, 130, 20);
        contentPane.add(kartno);
        
        JTextField kartnofield_1 = new JTextField();
        kartnofield_1.setBounds(225, 350, 215, 20);
        contentPane.add(kartnofield_1);
        kartnofield_1.setColumns(10);
        
        JLabel expire_date = new JLabel("Son Kullaným Tarihi: ");
        expire_date.setBounds(90, 380, 130, 20);
        contentPane.add(expire_date);
        
        JTextField kartdatefield_1 = new JTextField();
        kartdatefield_1.setBounds(225, 380, 215, 20);
        contentPane.add(kartdatefield_1);
        kartdatefield_1.setColumns(10);
        
        JLabel cvv = new JLabel("Güvenlik Þifresi: ");
        cvv.setBounds(90, 410, 130, 20);
        contentPane.add(cvv);
        
        JPasswordField kartcvvfield_1 = new JPasswordField();
        kartcvvfield_1.setBounds(225, 410, 215, 20);
        contentPane.add(kartcvvfield_1);
        
        JButton kartbuton_1 = new JButton("New button");
        kartbuton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ownerName = kartisimfield_1.getText();
                String ownerSurname = kartsoyisimfield_1.getText();
                String cardNumber = kartnofield_1.getText();
                String expirationDateString = kartdatefield_1.getText();
                String cvvString = new String(kartcvvfield_1.getPassword());
                int cvv = Integer.parseInt(cvvString);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date parsedDate = dateFormat.parse(expirationDateString);
                    java.sql.Date sqlExpirationDate = new java.sql.Date(parsedDate.getTime());

                    try (Connection conn = project_connection.getConnection()) {
                        String checkSql = "SELECT * FROM Kredi_Karti WHERE user_id = ?;";
                        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                            checkStmt.setInt(1, custid);
                            ResultSet rs = checkStmt.executeQuery();
                            if (rs.next()) {
                                // Kullanýcýnýn kredi kartý bilgisi varsa güncellesin:
                                String updateSql = "UPDATE Kredi_Karti SET sahip_ismi = ?, sahip_soyismi = ?, kart_no = ?, expire_date = ?, cvv = ? WHERE user_id = ?;";
                                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                                    updateStmt.setString(1, ownerName);
                                    updateStmt.setString(2, ownerSurname);
                                    updateStmt.setString(3, cardNumber);
                                    updateStmt.setDate(4, sqlExpirationDate);
                                    updateStmt.setInt(5, cvv);
                                    updateStmt.setInt(6, custid);

                                    int affectedRows = updateStmt.executeUpdate();
                                    if (affectedRows > 0) {
                                        JOptionPane.showMessageDialog(null, "Kart bilgileri baþarýyla güncellenmiþtir.");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                                    }
                                }
                            } else {
                                //kredi kartý bilgisi yoksa yeni eklesin
                            	int newKrediId = createKartId();
                                String insertSql = "INSERT INTO Kredi_Karti (krediKartiId,user_id, sahip_ismi, sahip_soyismi, kart_no, expire_date, cvv) VALUES (?,?, ?, ?, ?, ?, ?);";
                                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                                	insertStmt.setInt(1, newKrediId);
                                    insertStmt.setInt(2, custid);
                                    insertStmt.setString(3, ownerName);
                                    insertStmt.setString(4, ownerSurname);
                                    insertStmt.setString(5, cardNumber);
                                    insertStmt.setDate(6, sqlExpirationDate);
                                    insertStmt.setInt(7, cvv);

                                    int affectedRows = insertStmt.executeUpdate();
                                    if (affectedRows > 0) {
                                        JOptionPane.showMessageDialog(null, "Yeni kart bilgileri baþarýyla eklenmiþtir.");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Kart bilgileri eklenemedi.");
                                    }
                                }
                            }
                        }
                    } catch (SQLException | IOException ex) {
                        JOptionPane.showMessageDialog(null, "Veritabaný hatasý: " + ex.getMessage());
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Tarih formatý hatalý: " + ex.getMessage());
                }
            }
        });
        kartbuton_1.setBounds(450, 290, 50, 20);
        kartbuton_1.setIcon(resizedIcon);
        contentPane.add(kartbuton_1);

        
        JButton gösterenbuton = new JButton("Kullanýcý Bilgilerini Göster");
        gösterenbuton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String bilgiler[] = showProfil();
	        	isimfield.setText(bilgiler[1]);
	            soyisimfield.setText(bilgiler[2]);
	            emailfield.setText(bilgiler[3]);
	            þifrefield.setText(bilgiler[4]);
	            phonefield.setText(bilgiler[5]);
	            adresfield.setText(bilgiler[7]);
	            milfield.setText(bilgiler[0]);
	            kartisimfield_1.setText(bilgiler[8]);
	            kartsoyisimfield_1.setText(bilgiler[9]);
	            kartnofield_1.setText(bilgiler[10]);
	            kartdatefield_1.setText(bilgiler[11]);
	            kartcvvfield_1.setText(bilgiler[12]);
	            tcfield.setText(bilgiler[6]);
	        }
	    });
        gösterenbuton.setFont(new Font("Arial", Font.BOLD, 14));
        gösterenbuton.setBounds(100, 30, 273, 20);
        contentPane.add(gösterenbuton);
        
        JButton biletAlButton = new JButton("BÝLET AL");
        biletAlButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		User.this.dispose();
		        menuWindow.setVisible(true);
        	}
        });
        biletAlButton.setFont(new Font("Arial", Font.BOLD, 16));
        biletAlButton.setBounds(782, 468, 197, 37);
        contentPane.add(biletAlButton);
        
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(119, 136, 153));
		setJMenuBar(menuBar);
		
		JMenuItem profilField = new JMenuItem("Profil");
		profilField.setBackground(UIManager.getColor("Slider.shadow"));
		profilField.setIcon(new ImageIcon(User.class.getResource("/images/customer_48.png")));
		menuBar.add(profilField);
		
		JMenuItem homeField = new JMenuItem("Home");
		homeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User.this.dispose();
		        menuWindow.setVisible(true);
			}
		});
		homeField.setBackground(UIManager.getColor("Slider.shadow"));
		homeField.setIcon(new ImageIcon(User.class.getResource("/images/home_48.png")));
		menuBar.add(homeField);
		
		JMenuItem basvurularimField = new JMenuItem("Biletlerim");
		basvurularimField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTickets(biletlerim);
			}
		});
		basvurularimField.setBackground(UIManager.getColor("Slider.shadow"));
		basvurularimField.setIcon(new ImageIcon(User.class.getResource("/images/tckt_48.png")));
		menuBar.add(basvurularimField);
	}
	
	private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
	    Image img = icon.getImage();  
	    Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);  
	    return new ImageIcon(resizedImage);
	}
	
	private String[] showProfil() {
		String[] biletler = new String[13];
        try (Connection conn = project_connection.getConnection()) {
        	int milValue = miles.getTotalMiles();
            biletler[0] = String.valueOf(milValue);
            String sql = "SELECT * FROM musteriler where customerid = ?;";
            String sql2 = "SELECT * FROM profil where user_id = ?;";
            String sql3 = "SELECT * FROM Kredi_Karti where user_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
                stmt.setInt(1, custid);
                stmt2.setInt(1, custid);
                stmt3.setInt(1, custid);
                ResultSet rs = stmt.executeQuery();
                ResultSet rs2 = stmt2.executeQuery();
                ResultSet rs3 = stmt3.executeQuery();
                if (rs.next()) {
                	biletler[1] = rs.getString("fname");
                    biletler[2] = rs.getString("lname");
                    biletler[3] = rs.getString("email");
                    biletler[4] = rs.getString("passwords");
                }
                if (rs2.next()) {
                	biletler[5] = rs2.getString("phone_number");
                    biletler[6] = rs2.getString("tc_no");
                    biletler[7] = rs2.getString("adres");
                }
                if (rs3.next()) {
                	biletler[8] = rs3.getString("sahip_ismi");
                    biletler[9] = rs3.getString("sahip_soyismi");
                    biletler[10] = rs3.getString("kart_no");
                    biletler[11] = rs3.getString("expire_date");
                    biletler[12] = String.valueOf(rs3.getInt("cvv"));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return biletler;
	}
	
	private int createKartId() throws IOException {
		int newID = 0; //default deger
	    try (Connection conn = project_connection.getConnection();
	         PreparedStatement statement = conn.prepareStatement("SELECT MAX(krediKartiId) FROM Kredi_Karti"); 
	         ResultSet resultSet = statement.executeQuery()) {
	         if (resultSet.next()) {
	            int maxID = resultSet.getInt(1);
	            newID = maxID + 1;
	            }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return newID;
	}
	
	public void showTickets(JTextArea textArea) {
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
	                    String durum = isBooked ? "Ödendi" : "Ödenmedi";
	                    String status = rs.getString("status");
	                    
	                    tickets.append("Bilet ID: ")
	                           .append(biletId)
	                           .append(" / Ucus ID: ")
	                           .append(ucusId)
	                           .append(" / Bilet Fiyat: ")
	                           .append(biletFiyat)
	                           .append(" / Ödenme Durumu: ")
	                           .append(durum)
	                           .append(" / Ödeme ID: ")
	                           .append(odemeId)
	                           .append(" / Ödeme Tarihi: ")
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
	    
	    textArea.setVisible(true);
	    textArea.setText(tickets.toString());
	}
}