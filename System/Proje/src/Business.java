import java.sql.Time;
import java.util.Date;

public class Business extends Ticket{

	
	private static int capacity;
	
	public Business(double price, String fromWhere, String toWhere, Date date, Time start_at, Time end_at) {
		super(price * 1.5, fromWhere, toWhere, date, start_at, end_at);
	}

	
	@Override
	public void createTicket() {//when ticket is created capacity will decrease by 1
		
			capacity-=capacity;
			//burada user �n biletlerim k�sm�n�n g�ncellenmesi laz�m ama oras�yla nas�l ba�lant� kurucam???????
			
	}
	

	
	
}
