import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PasswordGuncelle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailInfo;
	private JPasswordField newPassword;
	private JPasswordField newPswAgain;
	String usr_email, new_psw, new_psw_again;

	public String getUsr_email() {
		return usr_email;
	}

	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}

	public String getNew_psw() {
		return new_psw;
	}

	public void setNew_psw(String new_psw) {
		this.new_psw = new_psw;
	}

	public String getNew_psw_again() {
		return new_psw_again;
	}

	public void setNew_psw_again(String new_psw_again) {
		this.new_psw_again = new_psw_again;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordGuncelle frame = new PasswordGuncelle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PasswordGuncelle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u015Eifre Yenileme Ekran\u0131");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(545, 93, 224, 45);
		contentPane.add(lblNewLabel);
		
		emailInfo = new JTextField();
		emailInfo.setBounds(526, 213, 262, 28);
		contentPane.add(emailInfo);
		emailInfo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email adresiniz");
		lblNewLabel_1.setBounds(526, 184, 88, 19);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		
		JLabel lblNewLabel_1_1 = new JLabel("Yeni \u015Eifreniz");
		lblNewLabel_1_1.setBounds(526, 259, 88, 19);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		newPassword = new JPasswordField();
		newPassword.setBounds(526, 288, 262, 28);
		contentPane.add(newPassword);
		
		JLabel lblNewLabel_1_2 = new JLabel("Yeni \u015Eifreniz Tekrar");
		lblNewLabel_1_2.setBounds(526, 337, 109, 19);
		contentPane.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		newPswAgain = new JPasswordField();
		newPswAgain.setBounds(526, 366, 262, 28);
		contentPane.add(newPswAgain);
		
		JButton yenileBtn = new JButton("Yenile");
		yenileBtn.setBounds(604, 430, 97, 35);
		contentPane.add(yenileBtn);
		yenileBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		yenileBtn.setBackground(new Color(240, 248, 255));
		
		JLabel lblBackToLogin = new JLabel("Login ekran\u0131na geri d\u00F6nmek i\u00E7in t\u0131klay\u0131n\u0131z");
		lblBackToLogin.setBounds(99, 496, 268, 59);
		contentPane.add(lblBackToLogin);
		lblBackToLogin.setForeground(new Color(64, 0, 64));
		lblBackToLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PasswordGuncelle.class.getResource("/images/pswDegistir.png")));
		lblNewLabel_2.setBounds(0, 61, 472, 454);
		contentPane.add(lblNewLabel_2);
		
		lblBackToLogin.addMouseListener(new MouseAdapter(){
			@Override
		    public void mouseClicked(MouseEvent e) {
		        backToLogin();
		    }
		});
		
		yenileBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					guncelle();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	
	private void backToLogin() {
		dispose(); // Mevcut pencereyi kapat
        Login loginFrame = new Login(); 
        loginFrame.setVisible(true); // Yeni login ekranÄ±nÄ± gÃ¶rÃ¼nÃ¼r hale getir
	}
	
	private void guncelle() throws IOException {
		usr_email = emailInfo.getText();
		new_psw = new String(newPassword.getPassword());
		new_psw_again = new String(newPswAgain.getPassword());
		
		if (usr_email.isEmpty() || new_psw.isEmpty() || new_psw_again.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lütgfen tüm alanlarý doldurun.");
            return;
        }else if(!new_psw.equals(new_psw_again)) {
        	JOptionPane.showMessageDialog(null, "Girdiðiniz þifreler eþleþmiyor.");
            return;
        }
		else {
        	 try (Connection conn = project_connection.getConnection();) {
     	    	String query = "SELECT * FROM musteriler WHERE email = ?";
     	    	try (PreparedStatement st = conn.prepareStatement(query)) { 
    	        	st.setString(1, usr_email);
    	            try (ResultSet rs = st.executeQuery()) {
    	                if (rs.next()) {
    	                	// EÅŸleÅŸme varsa, kullanÄ±cÄ± geÃ§erli olarak kabul edilir
    	                	String updateQuery = "UPDATE musteriler SET passwords = ? WHERE email = ?";
    	                    try (PreparedStatement updateSt = conn.prepareStatement(updateQuery)) {
    	                        updateSt.setString(1, new_psw);
    	                        updateSt.setString(2, usr_email);
    	                        int rowsAffected = updateSt.executeUpdate();
    	                        if (rowsAffected > 0) {
    	                            JOptionPane.showMessageDialog(null, "Þifre baþarýyla güncellendi.");
    	                        } else {
    	                            JOptionPane.showMessageDialog(null, "Þifre güncelleme iþlemi baþarýsýz.");
    	                        }
    	                    }
    	                } else {
    	                    JOptionPane.showMessageDialog(null, "Belirtilen e-posta adresine sahip bir kullanýcý bulunamadý.");
    	                }
    	                    
	                }
	            }
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }
    	}
    }
	
}