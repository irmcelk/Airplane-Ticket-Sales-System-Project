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
			//burada user ýn biletlerim kýsmýnýn güncellenmesi lazým ama orasýyla nasýl baðlantý kurucam???????
			
	}
	

	
	
}
