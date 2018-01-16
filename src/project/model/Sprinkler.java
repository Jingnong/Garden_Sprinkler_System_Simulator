package project.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Sprinkler {
	public enum Group {
		EAST, WEST, SOUTH, NORTH, ALL
	}
	
	private String sprinklerID;
	Group group;
	private int waterFlow = 5;
	private int waterFlowValue = 5;//default value of waterFlow is 5

	private boolean on = false;//work status of sprinkler, default value is false
	private boolean specialStatus = false;//temperature sensor will active this variable
	private boolean functional = true;
	private int OnCounter = 6;
	private Timer timer;
	private int timeCounter;
	private boolean specialStatusHi;
	private boolean specialStatusLo;
	private int waterConsumption;
	
	public Sprinkler(String sprinklerID, Group group) {
		this.sprinklerID = sprinklerID;
		this.group = group;
		updateStatus();
	}
	
	
	public String getID() {
		return sprinklerID;
	}
	
	public String getGroup() {
		return group.toString();
	}
	
	public void setGroup(String group) {
		if(group.equals("EAST")) {
			this.group = Group.EAST;
		} else if(group.equals("WEST")) {
			this.group = Group.WEST;
		} else if(group.equals("SOUTH")) {
			this.group = Group.SOUTH;
		} else if(group.equals("NORTH")) {
			this.group = Group.NORTH;
		}
	}
	
	//update status of sprinkler
	private void updateStatus() {
		if((!specialStatusLo)&&(specialStatusHi || on) && functional) {
			waterFlow = waterFlowValue;
		} else {
			waterFlow = 0;
		}
	}
	
	public void setWaterFlow(int value) {
		waterFlowValue = value;
		updateStatus();
	}
	
	public int getWaterFlow() {
		return waterFlow;
	}
	
	//used for temperature sensor, sprinkler will be turned on when it's needed
	//especially, sprinkler will stay on even user turn off 
	public void setSpecialStatusHi() {
		specialStatusHi = true;
		updateStatus();
	}
	
	public void setSpecialStatusLo() {
		specialStatusLo = true;
		updateStatus();
	}
	
	//turn on/off sprinkler
	public void setSprinklerOn() {
		on = true;
		updateStatus();
		startOnCounter();
	}
	public void setSprinklerOff() {
		on = false;
		updateStatus();
		stopOnCounter();
	}
	
	//get work status of sprinkler
	
	public String getWorkStatus() {
		if(on == true) {
			return "ON";
		} else {
			return "OFF";
		}
	}
	
	public boolean getFunctional() {
		return functional;
	}
	
	//To simulate sprinkler functional
	public void functionalOk() {
		functional = true;
	}
	public void functionalNotOk() {
		functional = false;
	}
	
	private void startOnCounter() {
		timeCounter = 1; //counter will use 1 minute to be zero
		timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				timeCounter--;
				if(timeCounter < 0) {
					timer.stop();
					if(functional) {
						OnCounter += 6;
						//System.out.println(OnCounter);
					}
					timeCounter = 1;
					timer.start();
				}
			}
		});
		
		timer.start();
	
	}
	
	private void stopOnCounter() {
		timer.stop();
	}
	
	
	//calculate waterFlow of this sprinkler
	public int getWaterConsumption() {
		this.waterConsumption = waterFlow * OnCounter;

		return waterConsumption;
		
		
	}
	
	public int returnOnCounter() {
		return OnCounter;
	}
	
	public void setWaterConsumption(int wc) {
		this.waterConsumption = wc;
	}
}