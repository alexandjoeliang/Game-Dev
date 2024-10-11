package Software;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Transaction implements Serializable{
	private int id;
	private double amount;
	private LocalDate date;
	private Payee payee;
	//private String payee;
	private String description = "no description";
	
	//constructors
	public Transaction() {}
	public Transaction(double amt){
		this.amount = amt;
		//this.date = new LocalDate();
	}
	/*
	Transaction(double amt, String s){
		this.amount = amt;
		this.payee = s;
	}
	Transaction(double amt, String s, String d){
		this.amount = amt;
		this.payee = s;
		this.description = d;
	}*/
	
	//id is unimplemented, and may not be needed
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return this.amount;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getDate() {
		return this.date;
	}
	
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public Payee getPayee() {
		return this.payee;
	}
	/*
	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	public String getPayee() {
		return this.payee;
	}*/
	public void setDescription(String desc) { //optional
		if (!desc.equals("")) { //if empty, replace with "no description"
			this.description = desc;
		}
	}
	public String getDescription() {
		return this.description;
	}
}
