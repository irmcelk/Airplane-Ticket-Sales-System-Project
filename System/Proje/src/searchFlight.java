import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.sql.Time;

public class searchFlight {
	 private Connection connection;
	 private String from;
	 private String to;
	 private Date flight_date;
	  
	public searchFlight(Connection connection, String from, String to, Date flight_date) {
		this.connection = connection;
		this.from = from;
		this.to = to;
		this.flight_date = flight_date;
	}
	
	
	public int fullness(int yolcuSayisi,int ucus_id,int kisiSayisi) {
		 int biletSayisi = 0;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT count(*) FROM biletlerim WHERE ucus_id=?");
			statement.setInt(1, ucus_id);
			 ResultSet resultSet = statement.executeQuery();
			 if(resultSet.next()) {
				 biletSayisi = resultSet.getInt(1);
			 }
			 resultSet.close();
		     statement.close();
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
		if(yolcuSayisi-biletSayisi >= kisiSayisi) {
			return 1;
		}
		return 0;
	}
	 
	
	
	 public Map<Integer, FlightInfo> flightSearching(int kisiSayisi) {
	        Map<Integer, FlightInfo> flightMap = new HashMap<>();

	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Ucuslar WHERE from_loc = ? AND to_loc = ? AND ucus_tarihi = ?");
	            statement.setString(1, from);
	            statement.setString(2, to);
	            statement.setDate(3,this.flight_date);

	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int ucusID = resultSet.getInt("ucusID");
	                int ucakID = resultSet.getInt("ucak_id");
	                int yolcuSayisi = resultSet.getInt("yolcu_sayisi");
	                double fiyat = resultSet.getDouble("fiyat");
	                Time start_at = resultSet.getTime("start_at");
	                Time end_at = resultSet.getTime("end_at");
	                
	                // -------kapasite kontrolu nas�l yap�lacak---------- onu eklemeyi unutma
	                FlightInfo flightInfo = new FlightInfo(ucusID, ucakID,from,to,flight_date, yolcuSayisi, fiyat,start_at,end_at);
	                if(fullness(yolcuSayisi,ucusID,kisiSayisi) == 1) {
	                	flightMap.put(kisiSayisi, flightInfo);
	                }
	                
	            }

	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return flightMap;
	    }
	 
	 public void listFlights( Map<Integer, FlightInfo> flights) {
		 System.out.println("U�u�lar:");
		    if (flights.isEmpty()) {
		        System.out.println("Aranan �zellikte u�u� bulunamad�.");
		    } else {
		        System.out.println("Aranan �zellikte u�u�lar:");
		        for (Map.Entry<Integer, FlightInfo> entry : flights.entrySet()) {
		            int kisiSayisi = entry.getKey();
		            FlightInfo flightInfo = entry.getValue();
		            System.out.println("Ucus ID: " + flightInfo.getUcusID());
		            System.out.println("Ucak ID: " + flightInfo.getUcakID());
		            System.out.println("Yolcu Sayisi: " + flightInfo.getYolcuSayisi());
		            System.out.println("Fiyat: " + flightInfo.getFiyat(kisiSayisi));
		            System.out.println("-------------------------------------");
		        }
		    }
	 }
	
	
	 

}
