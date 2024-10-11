package Drivers;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import Software.Budget;

public class Driver2 {
	public static void main(String[] args) {
	/*
	//1. create a new Budget
		//Budget holds categories, which hold subcategories. 
		Budget B = new Budget(); 
	// 2. set the monthly cash inflow. 
		B.set_monthly_in(3000.00); 
	// 3. set (or add) broad categories
		// The Category class holds and organizes subcategories. 
		B.add_category("Kitchen");
		
		B.add_category("Utilities");
		
		B.add_category("Transportation");
	//4. add subcategories. 
		//1st field is parent category, 2nd field is the subcategory name, 
		//3rd field is the amount you want to assign for the month. 
		B.add_subcategory("Kitchen", "groceries", 1000.00);
		B.add_subcategory("Kitchen","new pots", 100.00);
		B.add_subcategory("Utilities", "electricity", 100.00);
		B.add_subcategory("Transportation", "Gas", 30.00);
		B.print();	
	//adding transactions
		B.add_transaction("groceries", 100.00);
		//System.out.println("\nBudget after adding a transaction");
		//B.print();	
		//System.out.println("\ntesting rename");
		B.rename_subcategory("groceries", "Groceries");
		//B.print();
		Subcategory sc1 = B._find_subcat("Groceries"); //find subcat works!!

		//System.out.println("\ntesting delete");
		System.out.println("\ntesting delete");
		//B.print();
		//Integer cat = B._find_subcat_idx("Groceries"); //find subcatidx works!!
		//delete subcategory
		B.add_subcategory("Kitchen", "Booze", 300.00); 
		B.print();
		//B.delete_subcategory("Booze", "Groceries"); //delete works!
		B.print();
		B.assign("Groceries", 100);
		*/
		Budget b2 = new Budget();
		b2.set_monthly_in(100.00);
		b2.add_category("Kitchen");
		b2.add_category("Utilities");
		b2.add_category("Transportation");
		//b2.add_subcategory("Kitchen", "Groceries", 150); //this errors because the user doesn't have enough money
		b2.assign("Groceries", 150);
	}
	
}
