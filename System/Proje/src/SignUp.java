import java.awt.EventQueue;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class SignUp extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame SignUpFrm;
	private JPanel contentPane_1;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldAgain;
	private JTextField textFieldAd;
	private JTextField textFieldSoyad;
	private JTextField textFieldEmail;

	public static void main(String[] args) throws IOException, SQLException{
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                SignUp frame = new SignUp(); // SignUp ekranýný baþlat
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	public SignUp() {
		setAutoRequestFocus(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/images/globe.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 602);

		contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(240, 248, 255));
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		Label label = new Label("Email Adresiniz");
		label.setForeground(new Color(128, 128, 128));
		label.setBounds(422, 222, 101, 21);
		contentPane_1.add(label);
		
		Label label_1 = new Label("Sifreniz (en az 8 en \u00E7ok 16 haneli olmal\u0131d\u0131r)");
		label_1.setForeground(new Color(128, 128, 128));
		label_1.setBounds(422, 291, 257, 21);
		contentPane_1.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(422, 318, 410, 36);
		contentPane_1.add(passwordField);
		
		JToggleButton signUpBtn = new JToggleButton("\u00DCye Ol");
		signUpBtn.setBounds(578, 490, 101, 43);
		contentPane_1.add(signUpBtn);
		signUpBtn.setSelected(true);
		signUpBtn.setBackground(new Color(220, 20, 60));
		signUpBtn.setForeground(new Color(105, 105, 105));
		signUpBtn.setFont(new Font("Calibri", Font.BOLD, 15));
		
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					signUp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sendInfostoProfile();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Y\u0131ld\u0131z Hava Yollar\u0131 \u00DCyelik Kay\u0131t Bilgileri");
		lblNewLabel_1.setBounds(450, 23, 371, 55);
		contentPane_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 22));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SignUp.class.getResource("/images/ucak.jpg")));
		lblNewLabel.setBounds(0, 0, 371, 575);
		contentPane_1.add(lblNewLabel);
		
		passwordFieldAgain = new JPasswordField();
		passwordFieldAgain.setBounds(422, 387, 410, 36);
		contentPane_1.add(passwordFieldAgain);
		
		textFieldAd = new JTextField();
		textFieldAd.setColumns(10);
		textFieldAd.setBounds(422, 111, 410, 36);
		contentPane_1.add(textFieldAd);
		
		Label label_1_1 = new Label("Sifreniz tekrar");
		label_1_1.setForeground(new Color(128, 128, 128));
		label_1_1.setBounds(422, 360, 100, 21);
		contentPane_1.add(label_1_1);
		
		Label label_2 = new Label("Ad\u0131n\u0131z");
		label_2.setForeground(new Color(128, 128, 128));
		label_2.setBounds(422, 84, 76, 21);
		contentPane_1.add(label_2);
		
		Label label_3 = new Label("Soyad\u0131n\u0131z");
		label_3.setForeground(new Color(128, 128, 128));
		label_3.setBounds(422, 153, 101, 21);
		contentPane_1.add(label_3);
		
		textFieldSoyad = new JTextField();
		textFieldSoyad.setColumns(10);
		textFieldSoyad.setBounds(422, 180, 410, 36);
		contentPane_1.add(textFieldSoyad);
		
		JLabel lblNewLabel_2 = new JLabel("\u00DCye olduysan\u0131z giri\u015F ekran\u0131na ge\u00E7in");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 464, 336, 91);
		contentPane_1.add(lblNewLabel_2);
		
		JLabel lblLogin = new JLabel("Hesab\u0131n\u0131z var m\u0131? Giri\u015F Yap\u0131n.");
		lblLogin.setForeground(new Color(220, 20, 60));
		lblLogin.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		lblLogin.setBounds(422, 433, 159, 36);
		contentPane_1.add(lblLogin);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(422, 249, 410, 36);
		contentPane_1.add(textFieldEmail);
		
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
		        goToLogin();
		    }
		});
		
	}
	
	private void goToLogin() {
		dispose(); // Mevcut pencereyi kapat
        Login loginFrame = new Login(); 
        loginFrame.setVisible(true); // Yeni login ekranýný görünür hale getir
	}
	
	private int createUserId() throws IOException {
		int newID = 0; //default deger
	    try (Connection conn = project_connection.getConnection();
	         PreparedStatement statement = conn.prepareStatement("SELECT MAX(customerID) FROM Musteriler"); 
    		//prepared statement iceresinde yazdigim query sayesinde veri tabanýnda kayýtlý olan customer id'lerden
    		// en buyuk olanýný vt'den çekeriz ve bu degerin uzerine bir ekleyerek yeni customer id'yi olustururuz
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
	
	private void signUp() throws SQLException, IOException {
		int newUserID = createUserId();
	    // Yeni kullanýcý kimliði -1'e eþitse, bir hata oluþmuþ demektir, iþlemi sonlandýr
	    if (newUserID == 0) {
	        JOptionPane.showMessageDialog(null, "HATA! Kullanýcý kimliði oluþturulamadý.");
	        return;
	    }
		String firstName, lastName, email;
        firstName = textFieldAd.getText();
        lastName = textFieldSoyad.getText();
        email = textFieldEmail.getText();
        String pass = new String(passwordField.getPassword());
        String passAgain = new String(passwordFieldAgain.getPassword());
        
        String query1 = "select count(*) from musteriler where fname = ? and lname = ? and email = ?";
        //musterileri kaydetmek icin:
        String query2 = "insert into musteriler (customerid, fname, lname, email, passwords) values (?, ?, ?, ?, ?)";
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || pass.isEmpty() || passAgain.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lütfen tum alanlarý doldurun.");
            return;
        }
        if (!pass.equals(passAgain)) {
            JOptionPane.showMessageDialog(null, "Girilen þifreler eþleþmiyor. Lütfen þifreleri doðru girdiðinize emin olun.");
            return;
        }
        if(pass.length()<8||pass.length()>16) {
        	JOptionPane.showMessageDialog(null,"Þifreniz en az 8 en çok 16 karakterden oluþmalýdýr.");
        	return;
        }
        try (Connection conn = project_connection.getConnection();
    		PreparedStatement p1 = conn.prepareStatement(query1);
    		PreparedStatement p2 = conn.prepareStatement(query2)) {
               
        	p1.setString(1, firstName);
        	p1.setString(2, lastName);
        	p1.setString(3, email);
           
        	try (ResultSet r1 = p1.executeQuery()) {
        		if (r1.next()) {
        			int count = r1.getInt(1);
                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Bu bilgiler bir kullanýcýya ait. Lütfen daha önce kayýtlý olmayan bilgileri giriniz.");
                    } else {
                    	p2.setInt(1,newUserID);
                    	p2.setString(2, firstName);
                        p2.setString(3, lastName);
                        p2.setString(4, email);
                        p2.setString(5, pass);
                       
                        int affectedRows = p2.executeUpdate();
                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Kullanýcý baþarýyla kaydedildi.");
                            goToLogin(); // Kayýt baþarýlýysa giriþ ekranýna yönlendirir
                            dispose(); // Kayýt ekranýný kapatýrýz
                            } else {
                                JOptionPane.showMessageDialog(null, "Kullanýcý kayýt iþlemi baþarýsýz. Lütfen tekrar deneyin.");
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	
	private void sendInfostoProfile() throws SQLException,IOException {
		int userID;
		String email = textFieldEmail.getText();		
		String query1 = "select customerID from musteriler where email = ?";
        String query2 = "insert into Profil (user_id, email, mil_durumu, phone_number, tc_no, adres) values (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = project_connection.getConnection();
        		PreparedStatement p1 = conn.prepareStatement(query1);) {
                   
            	p1.setString(1, email);
               
            	try (ResultSet r1 = p1.executeQuery()) {
            		if (r1.next()) {
            			userID = r1.getInt("customerID");
            			try (PreparedStatement p2 = conn.prepareStatement(query2)) {
            			    p2.setInt(1, userID);
            			    p2.setString(2, email); 
            			    p2.setNull(3, java.sql.Types.INTEGER); //null deger atadim, bunlar sonraki asamalarda dolacak
            			    p2.setNull(4, java.sql.Types.VARCHAR);
            			    p2.setNull(5, java.sql.Types.VARCHAR); 
            			    p2.setNull(6, java.sql.Types.VARCHAR);
            			    
            			    int affectedRows = p2.executeUpdate();
            			    if (affectedRows > 0) {
            			        System.out.println("Profil tablosuna basariyla eklendi");
            			    } else {
            			    	System.out.println("Profil tablosuna ekleme islemi basarisiz oldu.");
            			    }
            			} catch (SQLException e) {
            			    e.printStackTrace();
            			}
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
	}
	
	public JFrame getFrame() {
        return SignUpFrm;
    }
}
