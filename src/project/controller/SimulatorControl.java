package project.controller;

import project.model.SprinklerGroup;
import project.model.WeeklyPlan;
import project.model.Sprinkler;
import project.model.Sprinkler.Group;

public class SimulatorControl {
	private int time;
	private int day;
	private boolean[][] functionalForWeekEast;
	private boolean[][] functionalForWeekWest;
	private boolean[][] functionalForWeekSouth;
	private boolean[][] functionalForWeekNorth;
	
	private SprinklerGroup sprinklerGroup;
	
	
	private Sprinkler[] sprinklerEast;
	private Sprinkler[] sprinklerWest;
	private Sprinkler[] sprinklerSouth;
	private Sprinkler[] sprinklerNorth;
	
	
	private WeeklyPlan weeklyPlanEast;
	private WeeklyPlan weeklyPlanWest;
	private WeeklyPlan weeklyPlanSouth;
	private WeeklyPlan weeklyPlanNorth;
	
	public SimulatorControl(int time, String day, SprinklerGroup sprinklerGroup) {
		this.time = time;
		this.day = generateDay(day);
		this.sprinklerGroup = sprinklerGroup;
		initGroupAndWeeklyPlan();
		setByWeeklyPlan();
	}
	

	
	private void initGroupAndWeeklyPlan() {
		
		sprinklerEast = sprinklerGroup.getSprinklerGroup(Group.EAST);
		sprinklerWest = sprinklerGroup.getSprinklerGroup(Group.WEST);
		sprinklerSouth = sprinklerGroup.getSprinklerGroup(Group.SOUTH);
		sprinklerNorth = sprinklerGroup.getSprinklerGroup(Group.NORTH);
		
		weeklyPlanEast = sprinklerGroup.getWeeklyPlan(Group.EAST);
		weeklyPlanWest = sprinklerGroup.getWeeklyPlan(Group.WEST);
		weeklyPlanSouth = sprinklerGroup.getWeeklyPlan(Group.SOUTH);
		weeklyPlanNorth = sprinklerGroup.getWeeklyPlan(Group.NORTH);
		
		functionalForWeekEast = weeklyPlanEast.getStatus();
		functionalForWeekWest = weeklyPlanWest.getStatus();
		functionalForWeekSouth = weeklyPlanSouth.getStatus();
		functionalForWeekNorth = weeklyPlanNorth.getStatus();
		
	}
	
	
	private void setByWeeklyPlan() {
		for(int i = 0; i < 6; i++) {
			if(functionalForWeekEast[time][day] == true) {
				sprinklerGroup.setSprinklerGroupOn(Group.EAST);
			} else {
				sprinklerGroup.setSprinklerGroupOff(Group.EAST);
			}
			
			if(functionalForWeekWest[time][day] == true) {
				sprinklerGroup.setSprinklerGroupOn(Group.WEST);
			} else {
				sprinklerGroup.setSprinklerGroupOff(Group.WEST);
			}
			
			if(functionalForWeekSouth[time][day] == true) {
				sprinklerGroup.setSprinklerGroupOn(Group.SOUTH);
			} else {
				sprinklerGroup.setSprinklerGroupOff(Group.SOUTH);
			}
			
			if(functionalForWeekNorth[time][day] == true) {
				sprinklerGroup.setSprinklerGroupOn(Group.NORTH);
			} else {
				sprinklerGroup.setSprinklerGroupOff(Group.NORTH);
			}
		}
	}
	
	public void setCurTemperature(Group group, int curTemperature) {
		sprinklerGroup.setCurTemperature(group, curTemperature);
	}
	
	//translate string day into integer day
	private int generateDay(String day) {
		int res = 0;
		switch(day) {
		case "Mon":
			res = 1;
			break;
		case "Tue":
			res = 2;
			break;
		case "Wed":
			res = 3;
			break;
		case "Thu":
			res = 4;
			break;
		case "Fri":
			res = 5;
			break;
		case "Sat":
			res = 6;
			break;
		case "Sun":
			res = 7;
		}
		return res;
	}
	
	public void setFunctionalOk(String id) {
		sprinklerGroup.setFunctionalOn(id);
	}
	
	public void setFunctionalNotOk(String id) {
		sprinklerGroup.setFunctionalOff(id);
	}
}
