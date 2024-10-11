package Software;
import java.io.Serializable;
import java.util.ArrayList;

//Payee class is the names of companies/people/etc. that you've paid to
public class Payee implements Serializable{
	private String name;
	private String description = "no description";
	public ArrayList<Transaction> payeetransactionList = new ArrayList<Transaction>();
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String desc) {
		if (!desc.equals("")) {
			this.description = desc;
		}
	}
	public String getDescription() {
		return this.description;
	}
}
