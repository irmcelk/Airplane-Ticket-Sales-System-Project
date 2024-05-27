import java.sql.Time;
import java.util.Date;

public abstract class Ticket {
	
	
	private double price;//business class�nda price � 1.5 kat�na e�itlerim ki fiyat� iki farkl� �ekilde tutmam�za gerek kalmas�n???
	private String fromWhere;
	private String toWhere;
	private Date date;
    private Time start_at;
    private Time end_at;
	
//her yeni bilet olu�turuldu�unda seatNo bir artacak
	public Ticket(double price, String fromWhere, String toWhere, Date date, Time start_at, Time end_at) {
		super();
		this.price = price;
		this.fromWhere = fromWhere;
		this.toWhere = toWhere;
		this.date = date;
		this.start_at = start_at;
		this.end_at = end_at;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFromWhere() {
		return fromWhere;
	}

	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}

	public String getToWhere() {
		return toWhere;
	}

	public void setToWhere(String toWhere) {
		this.toWhere = toWhere;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStart_at() {
		return start_at;
	}

	public void setStart_at(Time start_at) {
		this.start_at = start_at;
	}

	public Time getEnd_at() {
		return end_at;
	}

	public void setEnd_at(Time end_at) {
		this.end_at = end_at;
	}

	//bu bilet sahibinin ismini d�nd�recek?
	public String getSessionName() {
		return Session.getLoggedInUser().getName();
	}

	public abstract void createTicket();
	
	
	
}
