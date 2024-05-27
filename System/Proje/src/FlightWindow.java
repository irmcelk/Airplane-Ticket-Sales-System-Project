import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.ScrollPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Label;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Toolkit;

public class FlightWindow {

	private JFrame frmUcusListelemeEkran;
	private static Map<Integer, FlightInfo> flights;

	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                FlightWindow window = new FlightWindow(flights);
	                window.frmUcusListelemeEkran.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	public FlightWindow(Map<Integer, FlightInfo> flights) {
		FlightWindow.flights = flights;
		initialize();
	}

	private void initialize() {
		frmUcusListelemeEkran = new JFrame();
		frmUcusListelemeEkran.setTitle("Ucus Listeleme Ekranı");
		frmUcusListelemeEkran.setIconImage(Toolkit.getDefaultToolkit().getImage(FlightWindow.class.getResource("/images/globe.png")));
		frmUcusListelemeEkran.getContentPane().setBackground(new Color(240, 248, 255));
		frmUcusListelemeEkran.setBounds(430, 150, 1080, 680);
		frmUcusListelemeEkran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUcusListelemeEkran.getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 82, 474, 264);
		frmUcusListelemeEkran.getContentPane().add(scrollPane);
		
		
		 Label fiyatGoster = new Label("");
		 fiyatGoster.setBounds(388, 365, 119, 21);
		 frmUcusListelemeEkran.getContentPane().add(fiyatGoster);
			
		JList<String> flightList = new JList<>(getFlightListData());
		flightList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = flightList.getSelectedIndex();
		        if (selectedIndex != -1) {
		            int flightIndex = selectedIndex / 2; 
		            int entryType = selectedIndex % 2;  
		            int kisiSayisi = flights.keySet().toArray(new Integer[0])[flightIndex];
		                     
		            FlightInfo selectedFlight = flights.get(kisiSayisi); 
		            
		            double price= selectedFlight.getFiyat(kisiSayisi);
		            if(entryType != 0) {
		                price = price*1.5;
		               //selectedFlight.setFiyat(price);
		            }
		            fiyatGoster.setText("Toplam Tutar: "+Double.toString(price));
		        }
				
			}
		});
		flightList.setVisibleRowCount(4);
        flightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(flightList);
        flightList.setFont(new Font("Arial", Font.PLAIN, 12));
        	
		Button SatinAl = new Button("Sat\u0131n Al");
		SatinAl.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedIndex = flightList.getSelectedIndex();
		        if (selectedIndex != -1) {
		            int flightIndex = selectedIndex / 2; 
		            int entryType = selectedIndex % 2;  
		            int kisiSayisi = flights.keySet().toArray(new Integer[0])[flightIndex];
		            FlightInfo selectedFlight = flights.get(kisiSayisi); 
		            frmUcusListelemeEkran.dispose();
		            Payment paymentScreen = new Payment(selectedFlight,kisiSayisi,entryType);
		            paymentScreen.setVisible(true);
		           
                	} else {
                		JOptionPane.showMessageDialog(frmUcusListelemeEkran, "Lutfen bir ucus secin.", "Uyari", JOptionPane.WARNING_MESSAGE);
                	}
                
            
			}
			
		});
		
		SatinAl.setFont(new Font("Arial", Font.PLAIN, 12));
		SatinAl.setBackground(new Color(240, 248, 255));
		SatinAl.setBounds(529, 365, 80, 21);
		frmUcusListelemeEkran.getContentPane().add(SatinAl);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(119, 136, 153));
		frmUcusListelemeEkran.setJMenuBar(menuBar);
		
		JMenuItem profilField = new JMenuItem("Profil");
		profilField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUcusListelemeEkran.dispose();
				User user = new User();
		        user.setVisible(true);
			}
		});
		profilField.setBackground(UIManager.getColor("Slider.shadow"));
		profilField.setIcon(new ImageIcon(Menu.class.getResource("/images/customer_48.png")));
		menuBar.add(profilField);
		
		JMenuItem homeField = new JMenuItem("Home");
		homeField.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) 
			 {
				 	Menu resultFrame = null;
					resultFrame = new Menu(); 
				    JFrame MenuFrame = resultFrame;
		            MenuFrame.setVisible(true);
		            frmUcusListelemeEkran.getContentPane().setVisible(false);
		            frmUcusListelemeEkran.setVisible(false);
			 }
		 });
		homeField.setBackground(UIManager.getColor("Slider.shadow"));
		homeField.setIcon(new ImageIcon(Menu.class.getResource("/images/home_48.png")));
		menuBar.add(homeField);
		
		JMenuItem biletlerimField = new JMenuItem("Biletlerim");
		biletlerimField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUcusListelemeEkran.dispose();
				Tickets ticketss = new Tickets();
				ticketss.setVisible(true);
			}
		});
		biletlerimField.setBackground(UIManager.getColor("Slider.shadow"));
		biletlerimField.setIcon(new ImageIcon(Menu.class.getResource("/images/tckt_48.png")));
		menuBar.add(biletlerimField);
	}
	
	private String[] getFlightListData() {
	    String[] data = new String[flights.size() * 2];
	    int index = 0;
	    for (Map.Entry<Integer, FlightInfo> entry : flights.entrySet()) {
	        int flightID = entry.getKey();
	        FlightInfo flightInfo = entry.getValue();
	        data[index++] = flightInfo.getFrom() + " -> " + flightInfo.getTo() + " - " + flightInfo.getFlightDate() + " -Kalkış Saati: " + flightInfo.getStart_at() + " - Fiyat: " + flightInfo.getFlightFiyat() + " -> " + "Economy";
	        data[index++] = flightInfo.getFrom() + " -> " + flightInfo.getTo() + " - " + flightInfo.getFlightDate() + " -Kalkış Saati: " + flightInfo.getStart_at() + " - Fiyat: " + flightInfo.getFlightFiyat() * 1.5 + " -> " + "Business";
	    }
	    return data;
	}

			
    public JFrame getFrame() {
        return frmUcusListelemeEkran;
    }
}
