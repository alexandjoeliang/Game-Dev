package Software;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class  GoalByDate extends Goal {
	private Date goalDate;
	private Double amt_m; //the monthly amount to be allocated in order to reach the goal by the given date
	
	public GoalByDate(Double amount, Integer month, Integer day, Integer year){ 
		Calendar calendar = new GregorianCalendar(year, month-1, day);
		/*
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		*/
		this.goalDate = calendar.getTime();
		this.amount = amount;
		this.amt_m = format_amt_m(calc_amt_m());
	}
	
	private int _days_between() { //return number of days between current date and goaldate
		Date today = GregorianCalendar.getInstance().getTime();
		Integer difference =  (int) ((goalDate.getTime()-today.getTime())/86400000);
        return  Math.abs(difference)+1;
	}
	private Double calc_amt_m() { //calls _days_between() to find the monthly amount needed to reach the goal by the given date
		int days = _days_between() ;
		int months = days / 30;
		if (months > 0) {
			return this.amount / months;
		}
		else {
			//System.out.println("There is less than 30 days to reach this goal");
			return this.amount / 1;
		}
	}
	public Double get_amt_m() {
		return this.amt_m;
	}
	public void set_amt(Double amount) {
		this.amount = amount;
		this.amt_m = calc_amt_m();
	}
	public Date get_date() {
		return this.goalDate;
	}
	
	private Double format_amt_m(Double amt) {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(amt));
	}
	protected String format_date() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM d, yyyy");
		String simple_date = sdf.format(this.goalDate); 
		return simple_date;
		
	}
	protected String get_message() {
		return "you need to assign $" + this.get_amt_m().toString() + " to reach your goal of $" + this.get_amount().toString()  
		+ " by " + this.format_date();
	}
	
	//edit properties code
	public void edit_date(Integer month, Integer day, Integer year) {
        Calendar calendar = new GregorianCalendar(year, month-1, day);
        this.goalDate = calendar.getTime();
        this.amt_m = format_amt_m(calc_amt_m());
    }
    public void edit_amount(Double amt) {
        this.amount = amt;
        this.amt_m = format_amt_m(calc_amt_m());
    }
}
