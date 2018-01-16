package project.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import project.model.Sprinkler.Group;

public class SprinklerGroup {
	Sprinkler sprinkler;
	
	Sprinkler[] sprinklerAll = new Sprinkler[24];
	Sprinkler[] sprinklerEast = new Sprinkler[6];	
	Sprinkler[] sprinklerWest = new Sprinkler[6];
	Sprinkler[] sprinklerSouth = new Sprinkler[6];
	Sprinkler[] sprinklerNorth = new Sprinkler[6];
	
	WeeklyPlan weeklyPlanEast = new WeeklyPlan();
	WeeklyPlan weeklyPlanWest = new WeeklyPlan();
	WeeklyPlan weeklyPlanSouth = new WeeklyPlan();
	WeeklyPlan weeklyPlanNorth = new WeeklyPlan();
	WeeklyPlan[] weeklyPlanGroup = new WeeklyPlan[4];
	
	//data to write into file
	boolean[][] dataWeeklyPlan = new boolean[4][28];
	String[][] dataForData = new String[24][5];
	
	private int waterConsumptionAll;
	private int waterConsumptionEast;
	private int waterConsumptionWest;
	private int waterConsumptionSouth;
	private int waterConsumptionNorth;
	
	private boolean functionalEast[] = new boolean[6];
	private boolean functionalWest[] = new boolean[6];
	private boolean functionalSouth[] = new boolean[6];
	private boolean functionalNorth[] = new boolean[6];

	private String[] workStatusEast = new String[6];
	private String[] workStatusWest = new String[6];
	private String[] workStatusSouth = new String[6];
	private String[] workStatusNorth = new String[6];

	private int lo = 20;
	private int hi = 50;
	public int curTemperatureEast = 50;
	public int curTemperatureWest = 50;
	public int curTemperatureSouth = 50;
	public int curTemperatureNorth = 50;
	int OnCounter;	
	
	
	public SprinklerGroup() {
		weeklyPlanGroup[0] = weeklyPlanEast;
		weeklyPlanGroup[1] = weeklyPlanWest;
		weeklyPlanGroup[2] = weeklyPlanSouth;
		weeklyPlanGroup[3] = weeklyPlanNorth;
		
		// Initialize all the sprinklers
		for(int i = 0; i < 24; i++){
			sprinklerAll[i] = new Sprinkler(null, null);
		}
		for(int i = 0; i < 6; i++){
			sprinklerEast[i] = new Sprinkler(null, null);
		}
		for(int i = 0; i < 6; i++){
			sprinklerWest[i] = new Sprinkler(null, null);
		}
		for(int i = 0; i < 6; i++){
			sprinklerNorth[i] = new Sprinkler(null, null);
		}
		for(int i = 0; i < 6; i++){
			sprinklerSouth[i] = new Sprinkler(null, null);
		}
		
		loadData();
		generateGroup();
		initWeeklyPlan();
		
		

	}



	//generate sprinklerAll
	private void loadData() {
		String weeklyPlanFile = "src/WeeklyPlan.txt";
		String dataFile = "src/Data.txt";
		try {
			BufferedReader readPlan = new BufferedReader(new FileReader(weeklyPlanFile));
			BufferedReader readData = new BufferedReader(new FileReader(dataFile));
			String line;
			
			//read data from weekly plan
			int counter = 0;
			while((line = readPlan.readLine()) != null) {
				String[] weeklyPlan = line.split(":");
				
				for(int i = 0; i < weeklyPlan.length; i++) {
					if(weeklyPlan[i].equals("true")) {
						weeklyPlanGroup[counter].setOn(i/7, i%7);
					} else {
						weeklyPlanGroup[counter].setOff(i/7, i%7);
					}
					
				}
				
				counter++;				
			}
			
			
			//read data from data
			int i = 0;
			while((line = readData.readLine()) != null) {
				String[] data = line.split(":");
				sprinklerAll[i] = new Sprinkler(data[0], null);

				sprinklerAll[i].setGroup(data[1]);
				if(data[2].equals("true")) {
					sprinklerAll[i].setSprinklerOn();
				} else {
					sprinklerAll[i].setSprinklerOff();
				}
				
				if(data[3].equals("true")) {
					sprinklerAll[i].functionalOk();
				} else {
					sprinklerAll[i].functionalNotOk();
				}
				sprinklerAll[i].setWaterConsumption(Integer.parseInt(data[4]));
				i++;
			}
			readPlan.close();
			readData.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	private void writeData(String[][] dataForData, String s, int row, int col) throws IOException{
		FileWriter fw = new FileWriter("Data.txt");
		for(int i = 0; i < 24; i++){
			for(int j = 0; j < 5; j++){
				if(i == row && j == col){
					fw.write(s);        				
				} else {
					fw.write(dataForData[i][j]);      
				}
				if(j != 4){
					fw.write(":");			
				}
			}
			if(i != 24){
				fw.write("\r\n");	
			}
		}	
		fw.flush();	
		fw.close();
	}
	
	//generate four groups
	private void generateGroup() {
		
		for(int i = 0; i < sprinklerAll.length; i++) {
			switch(sprinklerAll[i].group) {
			case EAST:
				sprinklerEast[i%6] = sprinklerAll[i];
				break;
			case WEST:
				sprinklerWest[i%6] = sprinklerAll[i];
				break;
			case SOUTH:
				sprinklerSouth[i%6] = sprinklerAll[i];
				break;
			case NORTH:
				sprinklerNorth[i%6] = sprinklerAll[i];
				break;
			
			}
			//System.out.println(sprinklerEast[1].getID());
		}
		
		
	}
	
	private void initWeeklyPlan() {
		weeklyPlanEast = new WeeklyPlan();
		weeklyPlanWest = new WeeklyPlan();
		weeklyPlanSouth = new WeeklyPlan();
		weeklyPlanNorth = new WeeklyPlan();
	}
	
	public void setGroupWaterFlow(int value, Group g) {
		switch(g) {
		case ALL:
			for(int i = 0; i < 6; i++){
				sprinklerAll[i].setWaterFlow(value);
			}
			break;
		case EAST:
			for(int i = 0; i < 6; i++){
				sprinklerEast[i].setWaterFlow(value);
			}
			break;
		case SOUTH:
			for(int i = 0; i < 6; i++){
				sprinklerSouth[i].setWaterFlow(value);
			}
			break;
		case NORTH:
			for(int i = 0; i < 6; i++){
				sprinklerNorth[i].setWaterFlow(value);
			}
		}
		
	}
	
	//turn on/off sprinklerGroup
	public void setSprinklerGroupOn(Group group) {
		switch(group) {
		case ALL:
			for(Sprinkler spr: sprinklerAll) {
				spr.setSprinklerOn();
			}
			break;
		case EAST:
			for(Sprinkler spr: sprinklerEast) {
				spr.setSprinklerOn();
			}
			break;
		case SOUTH:
			for(Sprinkler spr: sprinklerSouth) {
				spr.setSprinklerOn();
			}
			break;
		case NORTH:
			for(Sprinkler spr: sprinklerNorth) {
				spr.setSprinklerOn();
			}
		}
	}
	public void setSprinklerGroupOff(Group group) {
		switch(group) {
		case ALL:
			for(Sprinkler spr: sprinklerAll) {
				spr.setSprinklerOff();
			}
			break;
		case EAST:
			for(Sprinkler spr: sprinklerEast) {
				spr.setSprinklerOff();
			}
			break;
		case SOUTH:
			for(Sprinkler spr: sprinklerSouth) {
				spr.setSprinklerOff();
			}
			break;
		case NORTH:
			for(Sprinkler spr: sprinklerNorth) {
				spr.setSprinklerOff();
			}
		}
	}

	public Sprinkler[] getSprinklerGroup(Group group) {
		Sprinkler[] sprinklerGroup = null;
		switch(group) {
		case ALL:
			sprinklerGroup = sprinklerAll;
			break;
		case EAST:
			sprinklerGroup = sprinklerEast;
			break;
		case WEST:
			sprinklerGroup = sprinklerWest;
			break;
		case SOUTH:
			sprinklerGroup = sprinklerSouth;
			break;
		case NORTH:
			sprinklerGroup = sprinklerNorth;
		}
		return sprinklerGroup;
	}

	public WeeklyPlan getWeeklyPlan(Group group) {
		WeeklyPlan wp = null;
		switch(group) {
		case EAST:
			wp = weeklyPlanEast;
			break;
		case WEST:
			wp = weeklyPlanWest;
			break;
		case SOUTH:
			wp = weeklyPlanSouth;
			break;
		case NORTH:
			wp = weeklyPlanNorth;
		}
		return wp;
	}
	
	
	
	
	private void calculateWaterConsumption() {
		for(int i = 0; i < 6; i++) {
			waterConsumptionEast += sprinklerEast[i].getWaterConsumption();
			waterConsumptionWest += sprinklerWest[i].getWaterConsumption();
			waterConsumptionSouth += sprinklerSouth[i].getWaterConsumption();
			waterConsumptionNorth += sprinklerNorth[i].getWaterConsumption();
			//System.out.println(sprinklerEast[1].getID());
		}
		waterConsumptionAll = waterConsumptionEast + waterConsumptionWest + waterConsumptionSouth + waterConsumptionNorth;
		
		
	}
	public int getWaterConsumption(Group group) {
		calculateWaterConsumption();
		int res = 0;
		switch(group) {
		case ALL:
			res = waterConsumptionAll;
			break;
		case EAST:
			res = waterConsumptionEast;
			break;
		case WEST:
			res = waterConsumptionWest;
			break;
		case SOUTH:
			res = waterConsumptionSouth;
			break;
		case NORTH:
			res = waterConsumptionNorth;
			
		}
		return res;
	}
	
	//following two methods to generate functional status of sprinkler group
	private void generateFunctionalArray() {
		for(int i = 0; i < 6; i++) {
			functionalEast[i] = sprinklerEast[i].getFunctional();
			functionalWest[i] = sprinklerWest[i].getFunctional();
			functionalSouth[i] = sprinklerSouth[i].getFunctional();
			functionalNorth[i] = sprinklerNorth[i].getFunctional();
			
		}
	}
	public boolean[] getFunctional(Group group) {
		generateFunctionalArray();
		boolean [] res = null;
		switch(group) {
		case EAST:
			res = functionalEast;
			break;
		case WEST:
			res = functionalWest;
			break;
		case SOUTH:
			res = functionalSouth;
			break;
		case NORTH:
			res = functionalNorth;
			
		}
		return res;
	}
	
	public void setFunctionalOn(String id) {
		for(int i = 0; i < sprinklerAll.length; i++) { 
			if(sprinklerAll[i].getID().equals(id)) {
				sprinklerAll[i].functionalOk();
			}
			sprinklerEast[0].functionalOk();
			//System.out.println(sprinklerEast[0].getFunctional());
		}
	}
	public void setFunctionalOff(String id) {
		for(int i = 0; i < sprinklerAll.length; i++) { 
			if(sprinklerAll[i].getID().equals(id)) {
				sprinklerAll[i].functionalNotOk();
			}
		
		}
	}
	//following two methods to generate work status of sprinkler group
	private void generateWorkStatusArray() {
		for(int i = 0; i < 6; i++) {
			workStatusEast[i] = sprinklerEast[i].getWorkStatus();
			workStatusWest[i] = sprinklerWest[i].getWorkStatus();
			workStatusSouth[i] = sprinklerSouth[i].getWorkStatus();
			workStatusNorth[i] = sprinklerNorth[i].getWorkStatus();
			//System.out.println(workStatusEast[i]);
			
		}
	}
	public String[] getWorkStatus(Group group) {
		generateWorkStatusArray();
		String [] res = null;
		switch(group) {
		case EAST:
			res = workStatusEast;
			break;
		case WEST:
			res = workStatusWest;
			break;
		case SOUTH:
			res = workStatusSouth;
			break;
		case NORTH:
			res = workStatusNorth;
			
		}
		return res;
	}
	
	
	//set temperature sensor set up
	public void setTemperatureLimit(Group group, int lo, int hi) { 
		this.lo = lo;
		this.hi = hi;
		updateGroupSpecialStatus(group);
	}
	
	
	private void updateGroupSpecialStatus(Group group) {
		if(group.toString().equals("EAST")) {
			if(curTemperatureEast > hi) {
				setGroupSepcialStatusHi(group);
				
			} else if(curTemperatureEast < lo) {
				setGroupSepcialStatusLo(group);
			}
		} else if(group.toString().equals("WEST")) {
			if(curTemperatureWest > hi) {
				setGroupSepcialStatusHi(group);
				
			} else if(curTemperatureWest < lo) {
				setGroupSepcialStatusLo(group);
			}
		} else if(group.toString().equals("SOUTH")) {
			if(curTemperatureSouth > hi) {
				setGroupSepcialStatusHi(group);
				
			} else if(curTemperatureSouth < lo) {
				setGroupSepcialStatusLo(group);
			}
		} else if(group.toString().equals("NORTH")) {
			if(curTemperatureNorth > hi) {
				setGroupSepcialStatusHi(group);
				
			} else if(curTemperatureNorth < lo) {
				setGroupSepcialStatusLo(group);
			}
		}
	}


	private void setGroupSepcialStatusHi(Group group) {
		switch(group) {
		case EAST:
			for(int i = 0; i < sprinklerEast.length; i++) {
				sprinklerEast[i].setSpecialStatusHi();
			}
			break;
		case WEST:
			for(int i = 0; i < sprinklerWest.length; i++) {
				sprinklerWest[i].setSpecialStatusHi();
			}
			break;
		case SOUTH:
			for(int i = 0; i < sprinklerSouth.length; i++) {
				sprinklerSouth[i].setSpecialStatusHi();
			}
			break;
		case NORTH:
			for(int i = 0; i < sprinklerNorth.length; i++) {
				sprinklerNorth[i].setSpecialStatusHi();
			}
			break;
			
		}
		
	}
	
	private void setGroupSepcialStatusLo(Group group) {
		switch(group) {
		case EAST:
			for(int i = 0; i < sprinklerEast.length; i++) {
				sprinklerEast[i].setSpecialStatusLo();
			}
			break;
		case WEST:
			for(int i = 0; i < sprinklerWest.length; i++) {
				sprinklerWest[i].setSpecialStatusLo();
			}
			break;
		case SOUTH:
			for(int i = 0; i < sprinklerSouth.length; i++) {
				sprinklerSouth[i].setSpecialStatusLo();
			}
			break;
		case NORTH:
			for(int i = 0; i < sprinklerNorth.length; i++) {
				sprinklerNorth[i].setSpecialStatusLo();
			}
			break;
			
		}
		
	}
	
	public void setCurTemperature(Group group, int curTemperature) {
		switch(group) {
		case EAST:
			curTemperatureEast = curTemperature;
			break;
		case WEST:
			curTemperatureWest = curTemperature;
			break;
		case SOUTH:
			curTemperatureSouth = curTemperature;
			break;
		case NORTH:
			curTemperatureNorth = curTemperature;
			break;
		}
		updateGroupSpecialStatus(group);
	}
	
	//for current time show on panel
	public int getOnCounter() {
		for(int i = 0; i < sprinklerAll.length; i++) {
			OnCounter = Math.max(OnCounter, sprinklerAll[i].returnOnCounter());
		}
		return OnCounter;
	}
	
	//weekly plan data to write into file
	public boolean[][] generateDataWeeklyPlanForFile() {
		for(int i = 0; i < weeklyPlanGroup.length; i++) {
				dataWeeklyPlan[i] = weeklyPlanGroup[i].getDataWeeklyPlan();
		}
		return dataWeeklyPlan;
	}
	
	public String[][] generateDataForFile() {
		for(int i = 0; i< 24; i++) { //24 is number of line in data.txt
			dataForData[i][0] = sprinklerAll[i].getID();
			dataForData[i][1] = sprinklerAll[i].getGroup();
			dataForData[i][2] = sprinklerAll[i].getWorkStatus();
			dataForData[i][3] = new Boolean(sprinklerAll[i].getFunctional()).toString();
			dataForData[i][4] = new Integer(sprinklerAll[i].getWaterFlow()).toString();
		}
		return dataForData;
	}
//	public static void main(String[] args) {
//		SprinklerGroup sg = new SprinklerGroup();
//		generateFunctionalArray();
//	}
	
}
