import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
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
import java.awt.Toolkit;

public class AdminLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame AdminFrm;
	private JPanel contentPane;
	private JTextField textFieldNickname;
	private JPasswordField passwordFieldAdmin;
	
	public static void main(String[] args) throws IOException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLogin() {
		setAutoRequestFocus(false);
		setTitle("Admin Giris Ekran\u0131");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminLogin.class.getResource("/images/globe.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Giri\u015F Ekran\u0131");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(599, 104, 191, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AdminLogin.class.getResource("/images/ui.png")));
		lblNewLabel_1.setBounds(0, 10, 498, 545);
		contentPane.add(lblNewLabel_1);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(554, 238, 288, 33);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		passwordFieldAdmin = new JPasswordField();
		passwordFieldAdmin.setBounds(554, 333, 288, 33);
		contentPane.add(passwordFieldAdmin);
		
		JLabel lblNewLabel_2 = new JLabel("Nickname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(554, 214, 61, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Sifre");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(554, 310, 46, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnAdminLogin = new JButton("Giris Yap");
		btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nickname, password;
				nickname = textFieldNickname.getText();
				password = new String(passwordFieldAdmin.getPassword());
				
				if(nickname.isEmpty()||password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Doldurulmasý gereken alanlarý boþ býrakmayýnýz");
				}else {
					try(Connection conn = project_connection.getConnection();){
						String query = "SELECT * FROM admin WHERE nickname = ? and passwords = ?";
						try(PreparedStatement st  = conn.prepareStatement(query)){
							st.setString(1, nickname);
							st.setString(2, password);
							try(ResultSet rs = st.executeQuery()){
								if(rs.next()) {
									JOptionPane.showMessageDialog(null, "Giriþ Yapýlýyor");
					        		openMenuScreen();
								}
							}
							
						}
					}catch(SQLException ex) {
						ex.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnAdminLogin.setBounds(653, 411, 87, 33);
		contentPane.add(btnAdminLogin);
		
		JLabel geriDon = new JLabel("");
		geriDon.setIcon(new ImageIcon(AdminLogin.class.getResource("/images/geriDon.png")));
		geriDon.setBounds(36, 25, 81, 64);
		contentPane.add(geriDon);
		geriDon.addMouseListener(new MouseAdapter(){
			@Override
		    public void mouseClicked(MouseEvent e) {
				goToPswyenile();
		    }
		});
		
		AdminFrm = this;
	}
	
	private void goToPswyenile() {
		dispose();
        Login loginFrm = new Login(); 
        loginFrm.setVisible(true);
	}
	
	public JFrame getFrame() {
	    return this;
	}
	
	private void openMenuScreen() {
	    AdminMenu menuScreen = new AdminMenu();
	    JFrame menuFrame = menuScreen.getFrame();
	    if (menuFrame != null) {
	        menuFrame.setVisible(true);
	        contentPane.setVisible(false);
	        AdminFrm.setVisible(false);
	    } else {
	        System.err.println("Admin Menu frame bulunamadý.");
	    }
	}
	
}
