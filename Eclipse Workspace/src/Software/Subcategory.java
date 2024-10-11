package Software;
import java.io.Serializable;
import java.util.ArrayList;
//NAMING CONVENTIONS:
// variable_m denotes monthly  
// variable_a denotes all time  
// net_ dentoes net 
public class Subcategory implements Serializable{
	private String name; //name of the category
	protected Double out_a = 0.00; //out_a : money out SINCE CATEGORY INCEPTION, useful for goals
	protected Double out_m = 0.00; 
	protected Double in_m = 0.00; //money in THIS MONTH; 
	protected Double in_a = 0.00; //money in all time
	
	public GoalByDate goal; //goal is optional, used to save up money for things; goals doesn't actually assign money though. 

	// better explained mathematically:
	private ArrayList<Transaction> alltransactionList = new ArrayList<Transaction>();
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private Double availableFunds; //availableFunds is money assigned to the category
	//rename availableFunds to net_a?? 
	
	public Subcategory(String name, double amount) {
		this.name = name;
		this.in_m += amount;
		this.in_a += amount;
	}
	
	public Subcategory(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setGoal(GoalByDate goal) {
		this.goal = goal;
	}
	public GoalByDate getGoal() {
		return this.goal;
	}
	
	//monthly in is paycheck for the month assigned amount to the subcategory
	public void set_monthly_in(Double amount) {
		this.in_m = amount;
		this.in_a += amount;
	}
	public Double get_monthly_in() {
		return this.in_m;
	}
	
	public Double get_available() {
		return this.in_a - this.out_a;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactionList.add(transaction);
		//this.in_a -= transaction.getAmount();
		//this.in_m -= transaction.getAmount();
		this.out_a += transaction.getAmount();
		this.out_m += transaction.getAmount();
		
	}
	public ArrayList<Transaction> getTransactionList() {
		return this.transactionList;
	}
	
	// public double getCharges(String from, String to){ } 
	
	//2nd way to keep track of monthly spending 
	public Double get_money_out() {  
		//calculate based on summing up transactions
		for (Transaction transaction : this.transactionList) {
			this.out_a = this.out_a + transaction.getAmount();
		}
		return this.out_a;
	}
	
	
	public Double calculate_goal_deviation() {  
		return this.goal.amount - this.get_money_out();
	}
	
	//rahel made a different version, intends to discuss 
	public Double calculate_goal_deviation_() {  
		return this.goal.amount - this.out_a;
	}
	
	
	public String displayBalanceLeft() {
		String balanceLeft = "";
		this.get_money_out();
		double leftOver = this.goal.amount - this.out_a;
		if (leftOver > 0) {
			balanceLeft = "There is $"+leftOver+" remaining for the category "+this.name;
		}
		else if (leftOver == 0) {
			balanceLeft = "There is no remaining budget for the category "+this.name;
		}
		else {
			leftOver = leftOver*-1;
			balanceLeft = "You have overspent $"+leftOver+" for the category "+this.name;
		}
		return balanceLeft;
	}
	
	public void print() { //name, amount assigned this month, amount available
		Double net_a = this.in_m - this.out_a;
		System.out.println(this.getName().toString() + "\t" + this.in_m.toString() + "\t" + net_a.toString());
	}
	
	public void reset() {
		this.in_m = 0.00;
		this.out_m = 0.00;
		//System.out.println(this.getName()+" reset");
		for (Transaction t : this.transactionList) {
			this.alltransactionList.add(t);
			this.transactionList.remove(t);
		}
	}
	
	public void move(Subcategory src) {
		//src.move(sc) moves sc's activity to this's fields. 
		//dst.move(
		//move transactions
		for (Transaction t: this.transactionList) {
			src.addTransaction(t);
		}
		src.in_m += this.in_m;
		src.out_m += this.out_m;
		src.out_a += this.in_a;
		src.in_a += this.in_a;

		//delete this's fields just in case, even tho we're deleting it from category and budget. 
		/*
		src.goal = null;
		src.in_a = 0.00;
		src.in_m = 0.00;
		src.out_a = 0.00;
		src.out_m = 0.00;
		src.name = " ";
		src.transactionList = null;
		*/
	
	}
	public void print_transactions() {
		for (Transaction T: this.transactionList) {
			
		}
	}
}
