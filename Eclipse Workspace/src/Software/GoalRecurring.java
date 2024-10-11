package Software;

public class GoalRecurring extends Goal{
	public String freq; 
	
	//for the GUI version of this, we should have a drop down menu that lets user choose between 
	//monthly, weekly 
	GoalRecurring(double amt, String frequency ){
		this.amount = amt;
		this.freq = frequency; //default behavior: 1st of month, week, etc. 
	}
	
	public void is1stWeek() {
		//if it is the first week, resets 
	}
}
