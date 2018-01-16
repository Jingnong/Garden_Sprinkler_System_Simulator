package project.model;

public class WeeklyPlan {

	boolean[][] onForWeek = new boolean[4][7];
	boolean[] dataWeeklyPlan = new boolean[28];
	
	public WeeklyPlan() {
		initStatus();
	}
	
	//all sprinkles are initialized to off
	private void initStatus() {
		for(int i = 0; i < onForWeek.length; i++) {
			for(int j = 0; j < onForWeek[i].length; j++) {
				onForWeek[i][j] = false;
			}
		}
	}
	
	//for controller to set weekly plan
	public void setOn(int time, int day) {
		onForWeek[time][day] = true;
	}
	
	public void setOff(int time, int day) {
		onForWeek[time][day] = false;
	}
	
	
	//return weekly plan
	public boolean[][] getStatus() {
		return onForWeek;
	}
	
	public boolean[] getDataWeeklyPlan() {
		int cnt = 0;
		while(cnt < dataWeeklyPlan.length) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 7; j++) {
					dataWeeklyPlan[cnt] = onForWeek[i][j];
				}
			}
			cnt++;
		}
		
		return dataWeeklyPlan;
	}
}
