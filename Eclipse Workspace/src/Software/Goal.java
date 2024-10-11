package Software;
import java.io.Serializable;

public class Goal implements Serializable{
	public Double amount;
	
	public void set_amount(double amount) {
		this.amount = amount;
	}
	public Double get_amount() {
		return this.amount;
	}
	
	
}
