package project.controller;

import java.util.Calendar;
import java.util.Date;

import project.model.Sprinkler;
import project.model.Sprinkler.Group;
import project.model.SprinklerGroup;
import project.model.WeeklyPlan;

//this class is used for setting status of sprinklerGroup based on action of user
public class PlanManager {
	SprinklerGroup sprinklerGroup;
	Sprinkler[] sprinklerEast;
	Sprinkler[] sprinklerWest;
	Sprinkler[] sprinklerSouth;
	Sprinkler[] sprinklerNorth;
	
	WeeklyPlan weeklyPlanEast;
	WeeklyPlan weeklyPlanWest;
	WeeklyPlan weeklyPlanSouth;
	WeeklyPlan weeklyPlanNorth;
	
	
	
	private int time = Calendar.HOUR_OF_DAY;
	private int day = Calendar.DAY_OF_WEEK;
	
	private boolean[][] functionalForWeekEast;
	private boolean[][] functionalForWeekWest;
	private boolean[][] functionalForWeekSouth;
	private boolean[][] functionalForWeekNorth;
	
	public PlanManager(SprinklerGroup sprinklerGroup) {
		this.sprinklerGroup = sprinklerGroup;
		initGroupAndWeeklyPlan();
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
	
	//when user have action, system will use this method to set weekly plan to on
	public void setWeeklyPlanOn(Group group, int time, int day) {
		switch(group) {
		case EAST:
			weeklyPlanEast.setOn(time, day);
			break;
		case WEST:
			weeklyPlanWest.setOn(time, day);
			break;
		case SOUTH:
			weeklyPlanSouth.setOn(time, day);
			break;
		case NORTH:
			weeklyPlanNorth.setOn(time, day);
		}
	}
	
	//when user have action, system will use this method to set weekly plan to off
	public void setWeeklyPlanOff(Group group, int time, int day) {
		switch(group) {
		case EAST:
			weeklyPlanEast.setOff(time, day);
			break;
		case WEST:
			weeklyPlanWest.setOff(time, day);
			break;
		case SOUTH:
			weeklyPlanSouth.setOff(time, day);
			break;
		case NORTH:
			weeklyPlanNorth.setOff(time, day);
		}
	}
	
	//after system start, use this method to initialize the status
	public void setTimeAndWorkByWeeklyPlan() {
		time = Calendar.HOUR_OF_DAY;
		day = Calendar.DAY_OF_WEEK;
		
		setByWeeklyPlan();
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
}


