import java.sql.Time;
import java.util.Date;

public class Economy extends Ticket {
	
	private static int capacity;

	public Economy( double price, String fromWhere, String toWhere, Date date, Time start_at, Time end_at) {
		super( price, fromWhere, toWhere, date, start_at, end_at);
	}

	@Override
	public void createTicket() {//when ticket is created capacity will decrease by 1
		
			capacity-=capacity;
		
	}
	
	
}
