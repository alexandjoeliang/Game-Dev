package Drivers;
import java.text.SimpleDateFormat;

import Software.GoalByDate;  

public class Driver3 {
	public static void main(String[] args) {
	GoalByDate g = new GoalByDate(100.0, 12, 18, 2021);
	double montlhy_amt = g.get_amt_m();
	System.out.printf("you need to assign : $%.2f to reach your goal of %.2f "
			+ " by " + g.get_date().toString(),  montlhy_amt, g.get_amount());
	}
}
