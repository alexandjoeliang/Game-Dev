package Drivers;

import Software.Budget;

public class Driver4 {
	public static void main(String[] args) {
	
	//1. create a new Budget
		//Budget holds categories, which hold subcategories. 
		Budget B = new Budget(); 
	// 2. set the monthly cash inflow. (use case #5)
		B.set_monthly_in(3000.00); 
	// 3. set (or add) broad categories (use case #1)
	// The Category class holds and organizes subcategories. 
		B.add_category("Kitchen");
		
		B.add_category("Utilities");
		
		B.add_category("Transportation");
	//4. add subcategories.  (use case #2)
	//when the user adds subcategories, they are assigning every penny to a certain "bin.",
	//each bin is a subcateogry. 
	//when the user creates a new subcateorgy and assigns money to it, they are taking money 
	//from their unassigned "jar" and placing into a new subcategory jar. 
	//thus, by adding subcategories , the user achieves 
	//use case #4, allocating an amount to a subcategory, for the month
		
		//1st field is parent category, 2nd field is the subcategory name, 
		//3rd field is the amount you want to assign for the month. 
		B.add_subcategory("Kitchen", "groceries", 1000.00);
		B.add_subcategory("Kitchen","new pots", 100.00);
		B.add_subcategory("Utilities", "electricity", 100.00);
		B.add_subcategory("Utilities", "gas", 40.00);
		B.add_subcategory("Utilities", "WiFi", 40.00);
		B.add_subcategory("Transportation", "Gas", 30.00);
	//we can aslo create subcategories and assign an amount leter
		//B.add_subcategory("Transportation", "Car Insurance");
		B.printCategoryInfo();	
	//adding transactions, (use case #6)
		System.out.println("\nadding transactions");
		B.add_transaction("groceries", 100.00);
		B.add_subcategory("Utilities", "gas", 40.00);
		System.out.println("\nBudget after adding a transaction");
		//printing shows whehter subcategiries are over or under-budgeted (use case #7)
		B.printCategoryInfo();	
		
	//rename subcategories  (use case #8)
		System.out.println("\ntesting rename subcategory");
		B.rename_subcategory("groceries", "Groceries");
		B.printCategoryInfo();
	//rename categories  (use case #9)
		System.out.println("\ntesting rename subcategory");
		B.rename_category("Transportation", "Movin Around");
		B.printCategoryInfo();
	//undoing the rename
		B.rename_category("Movin Around", "Transportation");
	//deleting subcategories: use case 
		System.out.println("\ntesting delete");
		//deleting a subcatory (use case #10)
		
		//if we delete a subcateogry, we need to reassign the subcategory's past 
		//activity to a new category. That is the second argument to delete( ) 
		B.add_subcategory("Kitchen", "Booze", 300.00); 
		B.printCategoryInfo();
		//B.delete_subcategory("Booze", "Groceries"); //delete works!
		
		//deleting Booze will move all of Booze's transactions,
		// and net available balance to groceries. 
		B.delete_subcategory("Booze", "Groceries");
		B.printCategoryInfo();
		
		//say we now rememebr to assign money to car insurance
		B.assign("Car Insurance", 100.00);
		B.printCategoryInfo();
		
		System.out.println("\ntesting adjust spending");
		//if we change our minds about the amount, we can adjust (use case #11)
		B.assign("Car Insurance", 95.00);
		B.printCategoryInfo();
		
	}
	
}
