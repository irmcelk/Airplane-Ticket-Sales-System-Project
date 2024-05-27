import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

public class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel homePane;
	private JLabel hosgeldinizText;
	public String fullName;
	private Integer tiklamaSayisi = 1;
	public JTextArea textArea_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Menu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 150, 1080, 680);
		setBackground(SystemColor.controlShadow);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/images/globe.png")));
		
		homePane = new JPanel();
		homePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		homePane.setBackground(new Color(240, 248, 255));
		setContentPane(homePane);
		homePane.setLayout(null);
		homePane.setVisible(true);
		
		JLabel hosgeldinizText = new JLabel("Ho\u015Fgeldiniz, ");
		hosgeldinizText.setBounds(22, 16, 339, 39);
		homePane.add(hosgeldinizText);	
		hosgeldinizText.setText(hosgeldinizText.getText()+" "+fullName());
		

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(510, 106, 150, 46);
		homePane.add(dateChooser);
		
		Label label = new Label("U\u00E7u\u015F Tarihi");
		label.setBounds(542, 79, 112, 16);
		label.setForeground(new Color(0, 0, 64));
		label.setBackground(new Color(240, 248, 255));
		label.setFont(new Font("Arial", Font.ITALIC, 11));
		homePane.add(label);
		
		
		JComboBox<String> NeredencomboBox = new JComboBox<String>();
		NeredencomboBox.setBounds(55, 106, 150, 46);
		NeredencomboBox.setBackground(new Color(255, 255, 255));
		NeredencomboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		homePane.add(NeredencomboBox);
		
		Label label_1 = new Label("Nereden");
		label_1.setBounds(95, 79, 59, 21);
		label_1.setFont(new Font("Arial", Font.ITALIC, 11));
		label_1.setForeground(new Color(0, 0, 64));
		homePane.add(label_1);
		
		JComboBox<String> NereyecomboBox = new JComboBox<String>();
		NereyecomboBox.setBounds(272, 106, 150, 46);
		NereyecomboBox.setBackground(new Color(255, 255, 255));
		NereyecomboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		homePane.add(NereyecomboBox);
		
		Label label_2 = new Label("Nereye");
		label_2.setBounds(313, 79, 59, 21);
		label_2.setFont(new Font("Arial", Font.ITALIC, 11));
		label_2.setForeground(new Color(0, 0, 64));
		homePane.add(label_2);
		
		Map<String, ArrayList<String>> citiesMap = getCityNamesFromDatabase();
		ArrayList<String> fromCities = citiesMap.get("from");
		 ArrayList<String> toCities = citiesMap.get("to");

		 for (String city : fromCities) {
		     NeredencomboBox.addItem(city);
		 }

		 for (String city : toCities) {
		     NereyecomboBox.addItem(city);
		 }
		 
		 Button buttonsearchflight = new Button("U\u00E7u\u015F Ara");
		 buttonsearchflight.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 String from = NeredencomboBox.getSelectedItem().toString();
				 String to = NereyecomboBox.getSelectedItem().toString();
				 Date date = dateChooser.getDate();
				 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				 Connection conn;
				 try {
					 conn = project_connection.getConnection();
					 searchFlight  FlightSearch = new searchFlight(conn,from,to,sqlDate);
					 Map<Integer, FlightInfo> flights = FlightSearch.flightSearching(tiklamaSayisi);
					 for (Map.Entry<Integer, FlightInfo> entry : flights.entrySet()) {
						 System.out.println("Anahtar: " + entry.getKey());
						 FlightInfo flightInfo = entry.getValue();
						 System.out.println("Değer: " + flightInfo);
					 }
					 // bu bulunan uçuşları yazdırıyor yazdırmaya gerek yok görmek için koydum
					 FlightSearch.listFlights(flights);
					 if(flights.size()>0) {
						 FlightWindow resultFrame = new FlightWindow(flights);
						 JFrame FlightFrame = resultFrame.getFrame();
						 FlightFrame.setVisible(true);
						 Menu.this.dispose();
					 } else {
						 JOptionPane.showMessageDialog(homePane, "Uçuş Bulunamadı.", "Uyarı", JOptionPane.WARNING_MESSAGE);
					 }
				 } catch (SQLException | IOException e1) {
					 e1.printStackTrace();
				 }
			 }
		 });
		 buttonsearchflight.setBounds(347, 229, 136, 30);
		 buttonsearchflight.setForeground(new Color(0, 0, 64));
		 buttonsearchflight.setBackground(new Color(255, 255, 255));
		 buttonsearchflight.setFont(new Font("Arial", Font.PLAIN, 16));
		 homePane.add(buttonsearchflight);
		 
		 Label label_3 = new Label("Kişi Sayısı");
		 label_3.setBounds(746, 79, 74, 21);
		 label_3.setFont(new Font("Arial", Font.ITALIC, 11));
		 label_3.setForeground(new Color(0, 0, 64));
		 homePane.add(label_3);
			
		 Label kisiSayisi = new Label("New label");
		 kisiSayisi.setAlignment(Label.CENTER);
		 kisiSayisi.setBounds(746, 119, 59, 21);
		 homePane.add(kisiSayisi);
			
		 kisiSayisi.setText(Integer.toString(1));
			
		 JLabel plusIcon = new JLabel("");
		 plusIcon.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 tiklamaSayisi++;
				 kisiSayisi.setText(Integer.toString(tiklamaSayisi));
			 }
		 });
		 plusIcon.setIcon(new ImageIcon(Menu.class.getResource("/images/plus_image.jpg")));
		 plusIcon.setBounds(808, 106, 56, 46);
		 homePane.add(plusIcon);
			
		JLabel minusIcon = new JLabel("");
		minusIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tiklamaSayisi > 1){
					tiklamaSayisi --;
				}
				kisiSayisi.setText(Integer.toString(tiklamaSayisi));
			}
		});
		minusIcon.setIcon(new ImageIcon(Menu.class.getResource("/images/minus_image.png")));
		minusIcon.setBounds(692, 120, 59, 21);
		homePane.add(minusIcon);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(119, 136, 153));
		setJMenuBar(menuBar);
		
		JMenuItem profilField = new JMenuItem("Profil");
		profilField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
				User user = new User();
		        user.setVisible(true);
			}
		});
		profilField.setBackground(UIManager.getColor("Slider.shadow"));
		profilField.setIcon(new ImageIcon(Menu.class.getResource("/images/customer_48.png")));
		menuBar.add(profilField);
		
		JMenuItem homeField = new JMenuItem("Home");
		homeField.setBackground(UIManager.getColor("Slider.shadow"));
		homeField.setIcon(new ImageIcon(Menu.class.getResource("/images/home_48.png")));
		menuBar.add(homeField);
		
		JMenuItem basvurularimField = new JMenuItem("Biletlerim");
		basvurularimField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Menu.this.dispose();
		    	Tickets tickets = new Tickets();
		    	tickets.setVisible(true);
		    }
		});
		basvurularimField.setBackground(UIManager.getColor("Slider.shadow"));
		basvurularimField.setIcon(new ImageIcon(Menu.class.getResource("/images/tckt_48.png")));
		menuBar.add(basvurularimField);
	}
	
	public String fullName() {
		 int custid = Session.getLoggedInUser().getUserId();
           try (Connection conn = project_connection.getConnection();) {
               String query = "SELECT * FROM Musteriler WHERE customerID = ?";
               try (PreparedStatement st = conn.prepareStatement(query)) {
                   st.setInt(1, custid);
                   ResultSet rs = st.executeQuery();
                   if (rs.next()) {
                       String fname = rs.getString("fname");
                       String lname = rs.getString("lname");
                       fullName = fname + " " + lname;
                       System.out.print(""+fullName);
                       setFullName(fullName);
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               if (fullName == null) {
                   System.err.println("Tam ad alınamadı veya veritabanında bulunamadı.");
               }
           } catch (SQLException | IOException e1) {
               e1.printStackTrace();
           }
           return fullName;
    }

	private Map<String, ArrayList<String>> getCityNamesFromDatabase() {
	    Map<String, ArrayList<String>> citiesMap = new HashMap<>();
	    try (Connection conn = project_connection.getConnection();) {
	        String query = "SELECT DISTINCT from_loc FROM Ucuslar WHERE status <> 'iptal'";
	        try (PreparedStatement st = conn.prepareStatement(query)) {
	            try (ResultSet rs = st.executeQuery()) {
	                ArrayList<String> fromCities = new ArrayList<>();
	                while (rs.next()) {
	                    fromCities.add(rs.getString("from_loc"));
	                }
	                citiesMap.put("from", fromCities);
	            }
	        }
	        query = "SELECT DISTINCT to_loc FROM Ucuslar WHERE status <> 'iptal'";
	        try (PreparedStatement st = conn.prepareStatement(query)) {
	            try (ResultSet rs = st.executeQuery()) {
	                ArrayList<String> toCities = new ArrayList<>();
	                while (rs.next()) {
	                    toCities.add(rs.getString("to_loc"));
	                }
	                citiesMap.put("to", toCities);
	            }
	        }
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    return citiesMap;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
