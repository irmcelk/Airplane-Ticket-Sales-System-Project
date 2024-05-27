import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.UIManager;
import java.awt.Choice;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JFrame loginFrm = this;
	private SignUp SignUpFrm;
	private int userId;
	private String fullNm;
	private String fName,lName;

	public static void main(String[] args) throws IOException, SQLException {
		
		Connection conn = project_connection.getConnection();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setAutoRequestFocus(false);
		setResizable(false);
		setName("loginFrm");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/globe.png")));
        setTitle("Ucak Bileti Satin Alma Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 602);

		contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(240, 248, 255));
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 302, 575);
		panel.setBackground(new Color(102, 102, 153));
		contentPane_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/bpass128.png")));
		lblNewLabel.setBounds(25, 147, 237, 210);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Y\u0131ld\u0131z Hava Yollar\u0131na Ho\u015F Geldiniz");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(245, 255, 250));
		lblNewLabel_1.setBounds(10, 63, 282, 55);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u00DCye de\u011Fil misiniz? \u00DCye olmak i\u00E7in t\u0131klay\u0131n.");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setForeground(new Color(245, 255, 250));
		lblNewLabel_2.setBounds(42, 392, 230, 38);
		panel.add(lblNewLabel_2);
		
		JToggleButton signUpBtn = new JToggleButton("\u00DCye Ol");
		signUpBtn.setSelected(true);
		signUpBtn.setBackground(new Color(47, 79, 79));
		signUpBtn.setForeground(new Color(105, 105, 105));
		signUpBtn.setFont(new Font("Calibri", Font.BOLD, 13));
		signUpBtn.setBounds(101, 440, 83, 32);
		panel.add(signUpBtn);
		
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToSignUp();
			}});
		
		JButton loginBtn = new JButton("Giri\u015F Yap");
		loginBtn.setBackground(new Color(240, 255, 255));
		loginBtn.setFont(new Font("Calibri", Font.BOLD, 15));
		loginBtn.setBounds(526, 440, 119, 41);
		contentPane_1.add(loginBtn);
		
		loginBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						login();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(395, 227, 371, 36);
		contentPane_1.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblPswForward = new JLabel("\u015Eifrenizi mi unuttunuz?");
		lblPswForward.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPswForward.setForeground(new Color(75, 7, 72));
		lblPswForward.setBounds(633, 379, 133, 27);
		contentPane_1.add(lblPswForward);
		
		lblPswForward.addMouseListener(new MouseAdapter(){
			@Override
		    public void mouseClicked(MouseEvent e) {
		        goToPswyenile();
		    }
		});
		
		Label label = new Label("Email Adresiniz");
		label.setBounds(395, 191, 101, 21);
		contentPane_1.add(label);
		
		Label label_1 = new Label("\u015Eifreniz");
		label_1.setBounds(395, 296, 59, 21);
		contentPane_1.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(395, 323, 371, 36);
		contentPane_1.add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Giri\u015F Ekran\u0131");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel_4.setBounds(507, 103, 150, 41);
		contentPane_1.add(lblNewLabel_4);
		
		JLabel adminGirisi = new JLabel("");
		adminGirisi.setBackground(Color.WHITE);
		adminGirisi.setForeground(Color.DARK_GRAY);
		adminGirisi.setBounds(827, 0, 59, 77);
		contentPane_1.add(adminGirisi);
		adminGirisi.setIcon(new ImageIcon(Login.class.getResource("/images/admin_login.png")));
		adminGirisi.addMouseListener(new MouseAdapter(){
			@Override
		    public void mouseClicked(MouseEvent e) {
				goToAdminLogin();
		    }
		});
	}
	
	private void goToPswyenile() {
		dispose();
        PasswordGuncelle pswFrame = new PasswordGuncelle(); 
        pswFrame.setVisible(true);
	}
	
	private void goToAdminLogin() {
		dispose();
		AdminLogin adminFrm = new AdminLogin();
		adminFrm.setVisible(true);
	}
	
	private boolean isUserValid() throws IOException {
		String email = textFieldEmail.getText();
		String password = new String(passwordField.getPassword());
		
	    try (Connection conn = project_connection.getConnection();) {
	    	String query = "SELECT * FROM musteriler WHERE email = ? AND passwords = ?";
	    	try (PreparedStatement st = conn.prepareStatement(query)) { 
	        	st.setString(1, email);
	            st.setString(2, password);
	            try (ResultSet rs = st.executeQuery()) {
	                if (rs.next()) {
	                	// Eþleþme varsa, kullanýcý geçerli olarak kabul edilir
	                    userId = rs.getInt("customerID"); //kullanýcýya verilen user id'yi alýyorum	
	                    setUserId(userId);
	                    Session.setLoggedInUser(Login.this);
	                    System.out.println("User ID: " + userId); // Kontrol amaçlý konsola yazdýrýyorum
	                    return true;
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false; //eþleþme yoksa yanlýþ döndürür
	}
	
	private void goToSignUp() {
		dispose();
		SignUp signUpFrm = new SignUp();
		signUpFrm.setVisible(true);
	}
	
	private void bilgiKontrol() { //eger kullanici bir alani bos birakirsa uyari mesaji yayinlanir
		String usr_email,usr_password;
		usr_email = textFieldEmail.getText();
		usr_password = new String(passwordField.getPassword()); //getPassword char array alýyor, onu stringe cevirdim
		
		if (usr_email.isEmpty() || usr_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurun.");
            return;
        }
	}
	
	private void login() throws HeadlessException, IOException, SQLException { 
		String usr_email,usr_password;
		usr_email = textFieldEmail.getText();
		usr_password = new String(passwordField.getPassword()); //getPassword char array alýyor, onu stringe cevirdim
		
		if (usr_email.isEmpty() || usr_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurun.");
            return;
        }else {
        	if(isUserValid() == true) {
        		JOptionPane.showMessageDialog(null, "Giriþ Yapýlýyor");
        		openMenuScreen();
        	}else{
        		JOptionPane.showMessageDialog(null, "Girilen Kullanýcý Bilgileri Hatalý");
        	}
        }
	}
	
	public JFrame getFrame() {
        return this;
    }
	
	private void showErrorMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(Login.this, message, "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
	
	private void openMenuScreen() throws SQLException, IOException {
		Login.this.dispose();
	    Menu menuScreen = new Menu();
	    if (menuScreen != null) {
	        menuScreen.setVisible(true);
	    } else {
	        System.err.println("Menu frame yok.");
	    }
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
