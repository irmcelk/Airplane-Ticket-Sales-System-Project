import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame AdminMenu;
	private JTextField ucakidfield;
	private JTextField neredenfield;
	private JTextField nereyefield;
	private JTextField kalkisfield;
	private JTextField inisfield;
	private JTextField koltukfield;
	private JTextField datefield;
	private JTextField fiyatfield;
	private int next_id;
	private int selectedUcusID;
	private Time start_at;
	private Time end_at;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 900, 602);
		setBackground(SystemColor.controlShadow);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMenu.class.getResource("/images/globe.png")));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AdminLogin.class.getResource("/images/ucak.jpg")));
		lblNewLabel_1.setBounds(0, 0, 363, 565);
		contentPane.add(lblNewLabel_1);
		
		JButton maxucusid = new JButton("Yeni U\\u00E7u\\u015Fun Numaras\\u0131n\\u0131 Belirle");
		maxucusid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next_id = getnextucusid();
			}
		});
		maxucusid.setFont(new Font("Arial", Font.BOLD, 14));
        maxucusid.setBounds(450, 30, 300, 30);
		contentPane.add(maxucusid);
		
		JLabel ucakidlabel = new JLabel("U\\u00E7ak ID: ");
		ucakidlabel.setBounds(420, 70, 90, 30);
		contentPane.add(ucakidlabel);
		
		ucakidfield = new JTextField();
		ucakidfield.setBounds(520, 70, 260, 30);
        contentPane.add(ucakidfield);
        ucakidfield.setColumns(10);
		
		JLabel neredenlabel = new JLabel("Nereden: ");
		neredenlabel.setBounds(420, 110, 90, 30);
		contentPane.add(neredenlabel);
		
		neredenfield = new JTextField();
		neredenfield.setBounds(520, 110, 260, 30);
        contentPane.add(neredenfield);
        neredenfield.setColumns(10);
		
		JLabel nereyelabel = new JLabel("Nereye: ");
		nereyelabel.setBounds(420, 150, 90, 30);
		contentPane.add(nereyelabel);
		
		nereyefield = new JTextField();
		nereyefield.setBounds(520, 150, 260, 30);
        contentPane.add(nereyefield);
        nereyefield.setColumns(10);
		
		JLabel kalkislabel = new JLabel("Kalk\\u0131\\u015F Saati: ");
		kalkislabel.setBounds(420, 190, 90, 30);
		contentPane.add(kalkislabel);
		
		kalkisfield = new JTextField();
		kalkisfield.setBounds(520, 190, 260, 30);
        contentPane.add(kalkisfield);
        kalkisfield.setColumns(10);
		
		JLabel inislabel = new JLabel("Ýni\\u015F Saati: ");
		inislabel.setBounds(420, 230, 90, 30);
		contentPane.add(inislabel);
		
		inisfield = new JTextField();
		inisfield.setBounds(520, 230, 260, 30);
        contentPane.add(inisfield);
        inisfield.setColumns(10);
		
		JLabel koltuklabel = new JLabel("Koltuk Say\\u0131s\\u0131: ");
		koltuklabel.setBounds(420, 270, 90, 30);
		contentPane.add(koltuklabel);
		
		koltukfield = new JTextField();
		koltukfield.setBounds(520, 270, 260, 30);
        contentPane.add(koltukfield);
        koltukfield.setColumns(10);
		
		JLabel datelabel = new JLabel("U\\u00E7u\\u015F Tarihi: ");
		datelabel.setBounds(420, 310, 90, 30);
		contentPane.add(datelabel);
		
		datefield = new JTextField();
		datefield.setBounds(520, 310, 260, 30);
        contentPane.add(datefield);
        datefield.setColumns(10);
		
		JLabel fiyatlabel = new JLabel("Bilet Fiyat\\u0131:");
		fiyatlabel.setBounds(420, 350, 90, 30);
		contentPane.add(fiyatlabel);
		
		fiyatfield = new JTextField();
		fiyatfield.setBounds(520, 350, 260, 30);
        contentPane.add(fiyatfield);
        fiyatfield.setColumns(10);
        
        JButton showReportButton = new JButton("Raporu \u0130ncele");
        showReportButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showReports();
        	}
        });
        showReportButton.setFont(new Font("Arial", Font.BOLD, 14));
        showReportButton.setBounds(539, 515, 200, 35);
        contentPane.add(showReportButton);
        
        JButton createFlightButton = new JButton("Yeni U\u00E7u\u015F Ekle");
        createFlightButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
        		String ucak_id_String = ucakidfield.getText();
        		int ucak_id = Integer.parseInt(ucak_id_String);
        		String from_loc = neredenfield.getText();
        		String to_loc = nereyefield.getText();
        		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        		String start_at_String = kalkisfield.getText();
        		String end_at_String = inisfield.getText();
        		String yolcu_sayisi_String = koltukfield.getText();
        		int yolcu_sayisi = Integer.parseInt(yolcu_sayisi_String);
        		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        		String ucus_tarihi_String = datefield.getText();
        		DecimalFormat decimalFormat = new DecimalFormat("#.##");
        		String fiyat_String = fiyatfield.getText();
        		double fiyat = Double.parseDouble(decimalFormat.format(Double.parseDouble(fiyat_String)));
        		Time start_at = null;
        		Time end_at = null;
        		Date ucus_tarihi = null;
        		try { start_at = new Time(timeFormat.parse(start_at_String).getTime());
        				end_at = new Time(timeFormat.parse(end_at_String).getTime());
        				ucus_tarihi = new Date(dateFormat.parse(ucus_tarihi_String).getTime());
        		} catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Tarih veya zaman formatý hatalý.");
                    return;
                }
	        	createFlight(next_id, ucak_id, from_loc, to_loc, start_at, end_at, yolcu_sayisi, ucus_tarihi, fiyat);
	            }
        });
        createFlightButton.setFont(new Font("Arial", Font.BOLD, 14));
        createFlightButton.setBounds(420, 425, 200, 35);
        contentPane.add(createFlightButton);
        
        JButton btnNewButton = new JButton("U\u00E7u\u015Flar\u0131 Listele");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String sql = "SELECT * FROM ucuslar;";
                try (Connection conn = project_connection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
	                    DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Uçuþ ID", "Uçak ID", "Nereden", "Nereye", "Kalkýþ Saati", "Ýniþ Saati", "Yolcu Sayýsý", "Uçuþ Tarihi", "Fiyat", "Durum"}, 0);
	                    while (rs.next()) {
	                        int ucusID = rs.getInt("ucusID");
	                        int ucak_id = rs.getInt("ucak_id");
	                        String from_loc = rs.getString("from_loc");
	                        String to_loc = rs.getString("to_loc");
	                        Time start_at = rs.getTime("start_at");
	                        Time end_at = rs.getTime("end_at");
	                        int yolcu_sayisi = rs.getInt("yolcu_sayisi");
	                        Date ucus_tarihi = rs.getDate("ucus_tarihi");
	                        double fiyat = rs.getDouble("fiyat");
	                        String status = rs.getString("status");
	                        tableModel.addRow(new Object[]{ucusID, ucak_id, from_loc, to_loc, start_at, end_at, yolcu_sayisi, ucus_tarihi, fiyat, status});
	                    }
	                    JTable flightsTable = new JTable(tableModel);
	                    JScrollPane scrollPane = new JScrollPane(flightsTable);
	                    JFrame frame = new JFrame("Uçuþ Listesi");
	                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                    frame.getContentPane().add(scrollPane);
	                    frame.pack();
	                    frame.setLocationRelativeTo(null);
	                    frame.setVisible(true);
	                    flightsTable.addMouseListener(new MouseAdapter() {
	                        public void mouseClicked(MouseEvent evt) {
	                            int row = flightsTable.rowAtPoint(evt.getPoint());
	                            if (row >= 0) {
	                            	selectedUcusID = (int) flightsTable.getValueAt(row, 0);
	                                int ucak_id = (int) flightsTable.getValueAt(row, 1);
	                                String from_loc = (String) flightsTable.getValueAt(row, 2);
	                                String to_loc = (String) flightsTable.getValueAt(row, 3);
	                                start_at = (Time) flightsTable.getValueAt(row, 4);
	                                end_at = (Time) flightsTable.getValueAt(row, 5);
	                                int yolcu_sayisi = (int) flightsTable.getValueAt(row, 6);
	                                Date ucus_tarihi = (Date) flightsTable.getValueAt(row, 7);
	                                double fiyat = (double) flightsTable.getValueAt(row, 8);
	                                ucakidfield.setText(String.valueOf(ucak_id));
	                                neredenfield.setText(from_loc);
	                                nereyefield.setText(to_loc);
	                                kalkisfield.setText(start_at.toString());
	                                inisfield.setText(end_at.toString());
	                                koltukfield.setText(String.valueOf(yolcu_sayisi));
	                                datefield.setText(ucus_tarihi.toString());
	                                fiyatfield.setText(String.format("%.2f", fiyat));
	                            }
	                        }
	                    });
	                    flightsTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
	
	                } catch (SQLException | IOException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Veritabaný hatasý: Uçuþlar yüklenemedi.");
	                }
        	}
        });
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
        btnNewButton.setBounds(635, 470, 200, 35);
        contentPane.add(btnNewButton);
        
        JButton cancelFlightButton = new JButton("U\u00E7u\u015Fu \u0130ptal Et");
        cancelFlightButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cancelFlight(selectedUcusID);
        	}
        });
        cancelFlightButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelFlightButton.setBounds(420, 470, 200, 35);
        contentPane.add(cancelFlightButton);
        
        JButton delayFlightButton = new JButton("U\u00E7u\u015F Ertele");
        delayFlightButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String startAtText = kalkisfield.getText(); 
                String endAtText = inisfield.getText(); 
                Time newStart_at = Time.valueOf(startAtText);
                Time newEnd_at = Time.valueOf(endAtText);
                delayFlight(selectedUcusID, newStart_at, newEnd_at);
        	}
        });
        delayFlightButton.setFont(new Font("Arial", Font.BOLD, 14));
        delayFlightButton.setBounds(635, 425, 200, 35);
        contentPane.add(delayFlightButton);
		
		AdminMenu = this;
	}

	public JFrame getFrame() {
	    return this;
	}
	
	public int getnextucusid() {
	    int nextucusid = 0;
	    try (Connection conn = project_connection.getConnection()) {
	        String sql = "SELECT MAX(ucusid) as maxId FROM ucuslar";
	        try (PreparedStatement statement = conn.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                nextucusid = resultSet.getInt("maxId") + 1;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	    return nextucusid;
	}
	
	public void createFlight(int ucus_id, int ucak_id, String from_loc, String to_loc, Time start_at, Time end_at, int yolcu_sayisi, Date ucus_tarihi, double fiyat) {
		try (Connection conn = project_connection.getConnection()) {
			String sql = "INSERT INTO ucuslar (ucusid, ucak_id, from_loc, to_loc, start_at, end_at, yolcu_sayisi, ucus_tarihi, fiyat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, next_id);
				stmt.setInt(2, ucak_id);
				stmt.setString(3,  from_loc);
				stmt.setString(4,  to_loc);
				stmt.setTime(5, start_at);
				stmt.setTime(6, end_at);
				stmt.setInt(7, yolcu_sayisi);
				stmt.setDate(8, ucus_tarihi);
				stmt.setDouble(9, fiyat);
				int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Yeni uçuþ eklenmiþtir.");
                } else {
                    JOptionPane.showMessageDialog(null, "Bir hata oluþtu. Deðiþiklik yapýlamadý.");
                }
			}
		} catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Veritabaný baðlantý hatasý.");
        }
	}
	
	public void cancelFlight(int ucusid) {
	    Connection conn = null;
	    PreparedStatement updateStmt = null;
	    PreparedStatement deleteStmt = null;
	    try {
	        conn = project_connection.getConnection();
	        String updateSql = "UPDATE biletlerim SET status = 'Ýptal Edildi' WHERE ucus_id = ?";
	        updateStmt = conn.prepareStatement(updateSql);
	        updateStmt.setInt(1, ucusid);
	        int rowsUpdated = updateStmt.executeUpdate();
	        
	        String deleteSql = "UPDATE ucuslar SET status = 'Ýptal Edildi' WHERE ucusid = ?";
	        deleteStmt = conn.prepareStatement(deleteSql);
	        deleteStmt.setInt(1, ucusid);
	        deleteStmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    } finally {
	        try {
	            if (updateStmt != null) {
	                updateStmt.close();
	            }
	            if (deleteStmt != null) {
	                deleteStmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void delayFlight(int ucusid, Time newStart_at, Time newEnd_at) {
	    Connection conn = null;
	    PreparedStatement updateUcuslarStmt = null;
	    PreparedStatement updateBiletlerimStmt = null;
	    try {
	        conn = project_connection.getConnection();
	        String updateUcuslarSql = "UPDATE ucuslar SET start_at = ?, end_at = ?, status = 'Ertelendi' WHERE ucusid = ?";
	        updateUcuslarStmt = conn.prepareStatement(updateUcuslarSql);
	        updateUcuslarStmt.setTime(1, newStart_at);
	        updateUcuslarStmt.setTime(2, newEnd_at);
	        updateUcuslarStmt.setInt(3, ucusid);
	        updateUcuslarStmt.executeUpdate();
	        
	        String updateBiletlerimSql = "UPDATE biletlerim SET status = 'Ertelendi' WHERE ucus_id = ?";
	        updateBiletlerimStmt = conn.prepareStatement(updateBiletlerimSql);
	        updateBiletlerimStmt.setInt(1, ucusid);
	        updateBiletlerimStmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    } finally {
	        try {
	            if (updateUcuslarStmt != null) {
	                updateUcuslarStmt.close();
	            }
	            if (updateBiletlerimStmt != null) {
	                updateBiletlerimStmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void showReports() {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = project_connection.getConnection();
	        String sql = "SELECT ucus_id, sum(biletfiyat) as toplam_fiyat FROM biletlerim GROUP BY ucus_id";
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        
	        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Uçuþ ID", "Toplam Gelir"}, 0);
	        
	        while (rs.next()) {
	            int id = rs.getInt("ucus_id");
	            double toplamFiyat = rs.getDouble("toplam_fiyat");
	            tableModel.addRow(new Object[]{id, toplamFiyat});
	        }
	        
	        JTable table = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(table);
	        
	        JFrame frame = new JFrame("Uçuþ Raporlarý");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.add(scrollPane);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}