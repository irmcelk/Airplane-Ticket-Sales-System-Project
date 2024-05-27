
import java.sql.Date;
import java.sql.Time;
public class FlightInfo {
   
	private int ucusID;
    private int ucakID;
    private String from;
    private String to;
    private Date flightDate;
    private int yolcuSayisi;
    private double fiyat;
    private Time start_at;
    private Time end_at;
	public FlightInfo(int ucusID, int ucakID, String from, String to, Date flightDate, int yolcuSayisi, double fiyat,
			Time start_at, Time end_at) {
		super();
		this.ucusID = ucusID;
		this.ucakID = ucakID;
		this.from = from;
		this.to = to;
		this.flightDate = flightDate;
		this.yolcuSayisi = yolcuSayisi;
		this.fiyat = fiyat;
		this.start_at = start_at;
		this.end_at = end_at;
	}
	public int getUcusID() {
		return ucusID;
	}
	
	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	public void setYolcuSayisi(int yolcuSayisi) {
		this.yolcuSayisi = yolcuSayisi;
	}
	public int getUcakID() {
		return ucakID;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public Date getFlightDate() {
		return flightDate;
	}
	public int getYolcuSayisi() {
		return yolcuSayisi;
	}
	public double getFiyat(int kisiSayisi) {
		return kisiSayisi*fiyat;
	}
	public Time getStart_at() {
		return start_at;
	}
	public Time getEnd_at() {
		return end_at;
	}
	@Override
	public String toString() {
		return "FlightInfo [ucusID=" + ucusID + ", ucakID=" + ucakID + ", from=" + from + ", to=" + to + ", flightDate="
				+ flightDate + ", yolcuSayisi=" + yolcuSayisi + ", fiyat=" + fiyat + ", start_at=" + start_at
				+ ", end_at=" + end_at + "]";
	}
	
	public double getFlightFiyat() {
		return fiyat;
	}

	

	
	

	
    
}
