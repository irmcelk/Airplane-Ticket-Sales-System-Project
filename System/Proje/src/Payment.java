import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Payment extends JFrame  {
    private double amount;
    private static final long serialVersionUID = 1L;
	private JFrame PaymentFrm;
	private JPanel contentPane_1;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldAgain;
	private JTextField textFieldAd;
	private JTextField textFieldSoyad;
	private JTextField textFieldEmail;
	private boolean paymentRes;
	private int cardID;
	public int custid = Session.getLoggedInUser().getUserId();
    
    public Payment(FlightInfo flightInfo,int kisiSayisi,int entryType) {
    	setTitle("\u00D6deme ");
        // FlightInfo nesnesinden fiyat bilgisini alarak Payment s�n�f�na atama yapal�m
        this.amount = flightInfo.getFiyat(kisiSayisi);
        getContentPane().setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Payment.class.getResource("/images/globe.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 602);

		contentPane_1 = new JPanel();
		contentPane_1.setBackground(new Color(240, 248, 255));
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
        
        JButton btnNewButton = new JButton("Kart ile \u00D6deme");
        JButton btnNewButton_1 = new JButton("Mil ile \u00D6deme");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			btnNewButton_1.setEnabled(false);
        			payment(true,flightInfo.getFiyat(kisiSayisi),flightInfo,kisiSayisi,entryType);
					
					
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton.setBounds(203, 406, 150, 44);
        getContentPane().add(btnNewButton);
        
        btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			btnNewButton.setEnabled(false);
        			payment(false,flightInfo.getFiyat(kisiSayisi),flightInfo,kisiSayisi,entryType);
					
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_1.setBounds(502, 406, 144, 44);
        getContentPane().add(btnNewButton_1);
        
        
        double price= flightInfo.getFiyat(kisiSayisi);
        if(entryType != 0) {
            price = price*1.5;
            flightInfo.setFiyat(price);
        }
        JTextArea textArea = new JTextArea("U\u00E7u\u015F Bilgileri\n"+"Nereden:"+flightInfo.getFrom()+"\nNereye:"+flightInfo.getTo()+"\nFiyat Bilgisi:"+flightInfo.getFiyat(kisiSayisi)+"\nTarih Bilgisi:"+flightInfo.getFlightDate());
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBounds(137, 66, 624, 282);
        contentPane_1.add(textArea);
        
        
        JLabel geriDon = new JLabel("");
        geriDon.setBounds(10, 0, 24, 39);
        geriDon.setIcon(new ImageIcon(Login.class.getResource("/images/geriDon.png")));
        contentPane_1.add(geriDon);
        geriDon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		goToMenu();
        	}
        });
      
    }
  
    
  public  boolean payment(boolean method,double tutar,FlightInfo selectedFlight,int kisiSayisi,int entryType) throws IOException, SQLException {    
	  if(method) {
	        System.out.println(tutar);
	        String sql = "SELECT kredikartiid FROM kredi_karti WHERE user_id = ?";
	        try (Connection conn = project_connection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            // Parametre atama
	            stmt.setInt(1, custid);

	            // Sorguyu �al��t�rma
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    // Sonu�lar� i�leme
	                    cardID = rs.getInt("kredikartiid"); // cardID'yi g�ncelleyin
	                    System.out.println("Kredi Kart� ID: " + cardID);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        Card card = new Card(cardID);
	        System.out.println(card);
	        System.out.println(card.isValid());
		   if(card.isValid()) {
			   if(card.hasMoney(tutar)==true) {
				   Object[] options = {"Evet", "Hay\u0131r"};
			        int cevap = JOptionPane.showOptionDialog(null, "Kay\u0131tl\u0131 kart\u0131n\u0131z ile \u00F6deme yapmay\u0131 onayl\u0131yor musunuz?", "Onay", JOptionPane.YES_NO_OPTION,
			                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				 if (cevap == JOptionPane.YES_OPTION) {
					 card.decreaseMoney(tutar);
					 JOptionPane.showMessageDialog(null, "\u00D6deme onayland\u0131");
				   tutar=(tutar*10)/100;
				   Miles mils=new Miles();
				   mils.addMil(tutar);
				   JOptionPane.showMessageDialog(null, "Bu \u00F6demenizde "+tutar+" kadar mil kazand\u0131n\u0131z!");
		           
		           
		           
		            	ArrayList<Ticket> myTickets = new ArrayList<>();
		            	  for (int i = 0; i < kisiSayisi; i++) {
				                if (entryType == 0) {
				                    myTickets.add(new Economy(selectedFlight.getFiyat(kisiSayisi), selectedFlight.getFrom(), selectedFlight.getTo(), 
				                            selectedFlight.getFlightDate(), selectedFlight.getStart_at(), selectedFlight.getEnd_at()));
				                } else {
				                    myTickets.add(new Business(selectedFlight.getFiyat(kisiSayisi), selectedFlight.getFrom(), selectedFlight.getTo(), 
				                            selectedFlight.getFlightDate(), selectedFlight.getStart_at(), selectedFlight.getEnd_at()));
				                }
				            }
                   try (Connection conn = project_connection.getConnection()) {
                       String insertQuery = "INSERT INTO biletlerim (bilet_id, customer_id, ucus_id, biletfiyat, is_booked, odeme_id, odeme_tarihi, class) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                       try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                           for (Ticket ticket : myTickets) {
                               pstmt.setInt(1, createBiletID());
                               pstmt.setInt(2, Session.getLoggedInUser().getUserId());
                               pstmt.setInt(3, selectedFlight.getUcusID());
                               pstmt.setDouble(4, ticket.getPrice());
                               pstmt.setBoolean(5, true);
                               pstmt.setInt(6, createOdemeID());
                               Date bugununTarihi = Date.valueOf(LocalDate.now());
                               pstmt.setDate(7, bugununTarihi);
                               if(entryType==0) {
                               	pstmt.setString(8, "economy");
                               }
                               else {
                               	pstmt.setString(8, "business");
                               }
                               pstmt.executeUpdate();
                           }
                           System.out.println("Tickets added successfully.");
                       } catch (SQLException ex) {
                           ex.printStackTrace();
                       }
                   } catch (SQLException | IOException ex) {
                       ex.printStackTrace();
                   }
                   
                   TicketFrame TFrame = null;
                   try {
						TFrame = new TicketFrame(myTickets.get(0), kisiSayisi);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                   TFrame.setVisible(true);
		            return true; 
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "\u00D6deme onaylanmad\u0131", "Uyar\u0131", JOptionPane.WARNING_MESSAGE);
				
					 return false;
				 }
		   }
			   else {
				   JOptionPane.showMessageDialog(null, "Kart Bakiyesi Yetersiz");
				   return false;
			   }
	 
	         }
		   else {
			   JOptionPane.showMessageDialog(null, "Ge\\u00E7ersiz Kart");
			   return false;
		   }
		}
	   else {
		  Miles mils=new Miles();
		  if(mils.getTotalMiles()>=tutar) {
			  System.out.println(mils.getTotalMiles());
			  Object[] options = {"Evet", "Hay\u0131r"};
		        int cevap = JOptionPane.showOptionDialog(null, "Mil ile \\u00F6deme yapmay\u0131 onayl\u0131yor musunuz?", "Onay", JOptionPane.YES_NO_OPTION,
		                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			 if (cevap == JOptionPane.YES_OPTION) {
		            mils.decreaseMil(tutar);
		            JOptionPane.showMessageDialog(null, "\\u00D6deme onayland\\u0131");
		            	ArrayList<Ticket> myTickets = new ArrayList<>();
		            	  for (int i = 0; i < kisiSayisi; i++) {
				                if (entryType == 0) {
				                    myTickets.add(new Economy(selectedFlight.getFiyat(kisiSayisi), selectedFlight.getFrom(), selectedFlight.getTo(), 
				                            selectedFlight.getFlightDate(), selectedFlight.getStart_at(), selectedFlight.getEnd_at()));
				                } else {
				                    myTickets.add(new Business(selectedFlight.getFiyat(kisiSayisi), selectedFlight.getFrom(), selectedFlight.getTo(), 
				                            selectedFlight.getFlightDate(), selectedFlight.getStart_at(), selectedFlight.getEnd_at()));
				                }
				            }
                    try (Connection conn = project_connection.getConnection()) {
                        String insertQuery = "INSERT INTO biletlerim (bilet_id, customer_id, ucus_id, biletfiyat, is_booked, odeme_id, odeme_tarihi, class) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                            for (Ticket ticket : myTickets) {
                                pstmt.setInt(1, createBiletID());
                                pstmt.setInt(2, Session.getLoggedInUser().getUserId());
                                pstmt.setInt(3, selectedFlight.getUcusID());
                                pstmt.setDouble(4, ticket.getPrice());
                                pstmt.setBoolean(5, true);
                                pstmt.setInt(6, createOdemeID());
                                Date bugununTarihi = Date.valueOf(LocalDate.now());
                                pstmt.setDate(7, bugununTarihi);
                                if(entryType==0) {
                                	pstmt.setString(8, "economy");
                                }
                                else {
                                	pstmt.setString(8, "business");
                                }
                                pstmt.executeUpdate();
                            }
                            System.out.println("Tickets added successfully.");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } catch (SQLException | IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    TicketFrame TFrame = null;
                    try {
						TFrame = new TicketFrame(myTickets.get(0), kisiSayisi);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                    TFrame.setVisible(true);
		            return true;  
			 }
		  
		        else {
		            JOptionPane.showMessageDialog(null, "\u00D6deme onaylanmad\u0131", "Uyar\u0131", JOptionPane.WARNING_MESSAGE);
					return false;
		        }
	  
		  }
  
		  
		  else {
			  JOptionPane.showMessageDialog(null, "Yetersiz Mil","Uyar\u0131", JOptionPane.WARNING_MESSAGE); 
			  return false;
		  }
	   
	   }
   

  }


public boolean isPaymentRes() {
	return paymentRes;
}

public void setPaymentRes(boolean paymentRes) {
	this.paymentRes = paymentRes;
}

public void decreasePassengerNo(FlightInfo flightInfo,int numPassenger) {
	  int newPassengerNo=flightInfo.getYolcuSayisi()-numPassenger;
	  flightInfo.setYolcuSayisi(newPassengerNo);
}
 
public int createBiletID() throws IOException {
	int newID = 0; //default deger
    try (Connection conn = project_connection.getConnection();
         PreparedStatement statement = conn.prepareStatement("SELECT MAX(bilet_id) FROM Biletlerim"); 
		//prepared statement iceresinde yazdigim query sayesinde veri taban�nda kay�tl� olan customer id'lerden
		// en buyuk olan�n� vt'den �ekeriz ve bu degerin uzerine bir ekleyerek yeni customer id'yi olustururuz
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




public int createOdemeID() throws IOException {
	int newID = 0; //default deger
    try (Connection conn = project_connection.getConnection();
         PreparedStatement statement = conn.prepareStatement("SELECT MAX(odeme_id) FROM Biletlerim"); 
		//prepared statement iceresinde yazdigim query sayesinde veri taban�nda kay�tl� olan customer id'lerden
		// en buyuk olan�n� vt'den �ekeriz ve bu degerin uzerine bir ekleyerek yeni customer id'yi olustururuz
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

private void goToMenu() {
	dispose();
	 Menu menuScreen = new Menu();
	    if (menuScreen != null) {
	        menuScreen.setVisible(true);
	    } else {
	        System.err.println("Menu frame yok.");
	    }
}
}