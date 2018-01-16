package project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import project.model.SprinklerGroup;
import project.controller.PlanManager;
import project.model.Sprinkler.Group;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WeeklyPlanFrame extends JFrame implements ActionListener{

	JPanel contentPane;
	JRadioButton rdbtneast;            
    JRadioButton rdbtnwest;
    JRadioButton rdbtnnorth;
    JRadioButton rdbtnsouth;
    JButton btnback;
    JButton btnsubmit;
	JCheckBox[][] checkboxArray;
	String[] time = {"Days", "00:00-06:00", "06:00-12:00", "12:00-18:00","18:00-24:00"};
	String[] day = {"Monday", "Tuesday", "Wednesday","Thursday", "Friday", "Saturday", "Sunday"};
	private JPanel panel;
	private JLabel lblWeeklyPlan;
	Box dayBox;
	Box buttonBox;
	
	SprinklerGroup sg;
	Group g = Group.EAST;
	PlanManager planManager;

	/**
	 * Create the frame.
	 */
	public WeeklyPlanFrame(SprinklerGroup sg) {
		
		this.sg = sg;
		planManager = new PlanManager(sg);
		
		setTitle("HummingBee Home Garden Sprinkler System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);

        checkboxArray = new JCheckBox[4][7];
        buttonBox = new Box(BoxLayout.Y_AXIS);

        rdbtneast = new JRadioButton("East");
        rdbtneast.addActionListener(this);
        buttonBox.add(rdbtneast); 
        rdbtneast.setSelected(true);
          
        rdbtnwest = new JRadioButton("West");
        rdbtnwest.addActionListener(this);
        buttonBox.add(rdbtnwest);

        rdbtnnorth = new JRadioButton("North");
        rdbtnnorth.addActionListener(this);
        buttonBox.add(rdbtnnorth);

        rdbtnsouth = new JRadioButton("South");
        rdbtnsouth.addActionListener(this);
        buttonBox.add(rdbtnsouth);
        
        btnsubmit = new JButton("Submit");
        btnsubmit.addActionListener(this);
        buttonBox.add(btnsubmit);
        
        btnback = new JButton("Back");
        btnback.addActionListener(this);
        buttonBox.add(btnback);
        

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 5; i++) {
           nameBox.add(new Label(time[i]));
        }
        
        dayBox = new Box(BoxLayout.X_AXIS);
        for (int i = 0; i < 7; i++) {
        	dayBox.add(new Label(day[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        this.getContentPane().add(background);
          
        GridLayout grid = new GridLayout(5,7);
        grid.setVgap(1);
        grid.setHgap(2);
        contentPane = new JPanel(grid);
        contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
        background.add(BorderLayout.CENTER, contentPane);
        
        panel = new JPanel();
        background.add(panel, BorderLayout.NORTH);
        
        lblWeeklyPlan = new JLabel("Weekly Plan");
        panel.add(lblWeeklyPlan);
        
        showWeeklyPlan();

        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rdbtneast){
			g = Group.EAST;
			showWeeklyPlan();
			rdbtnwest.setSelected(false);
			rdbtnnorth.setSelected(false);
			rdbtnsouth.setSelected(false);
		}
		if(e.getSource() == rdbtnwest){
			g = Group.WEST;
			showWeeklyPlan();
			rdbtneast.setSelected(false);
			rdbtnnorth.setSelected(false);
			rdbtnsouth.setSelected(false);
		}
		if(e.getSource() == rdbtnsouth){
			g = Group.NORTH;
			showWeeklyPlan();
			rdbtneast.setSelected(false);
			rdbtnwest.setSelected(false);
			rdbtnnorth.setSelected(false);
		}
		if(e.getSource() == rdbtnnorth){
			g = Group.SOUTH;
			showWeeklyPlan();
			rdbtneast.setSelected(false);
			rdbtnwest.setSelected(false);
			rdbtnsouth.setSelected(false);
		}
		if(e.getSource() == btnsubmit){
			changeWeeklyPlan(g);
		}	
		if(e.getSource() == btnback){
			this.setVisible(false);
			ControlFrame cf = new ControlFrame();
			cf.setVisible(true);
		}		
	}
	
	public void showWeeklyPlan(){

			JLabel l1 = new JLabel("Monday");
			contentPane.add(l1);
			JLabel l2 = new JLabel("Tuesday");
			contentPane.add(l2);   
			JLabel l3 = new JLabel("Wednesday");
			contentPane.add(l3);   
			JLabel l4 = new JLabel("Thursday");
			contentPane.add(l4);   
			JLabel l5 = new JLabel("Friday");
			contentPane.add(l5);   
			JLabel l6 = new JLabel("Saturday");
			contentPane.add(l6);   
			JLabel l7 = new JLabel("Sunday");
			contentPane.add(l7);   

		for (int i = 0; i < 4; i++) {
        	for(int j = 0; j < 7; j++){
				JCheckBox c = new JCheckBox();
				c.setSelected(sg.getWeeklyPlan(g).getStatus()[i][j]);
				checkboxArray[i][j] = c;
				contentPane.add(c);       
        	}
        } // end loop
	}
	
	public void changeWeeklyPlan(Group g){
		for (int i = 0; i < 4; i++) {
        	for(int j = 0; j < 7; j++){
				boolean checked = checkboxArray[i][j].isSelected();
				if(checked){
					planManager.setWeeklyPlanOn(g, i, j);
				} else {
					planManager.setWeeklyPlanOff(g, i, j);
				}
        	}
        }
		
	}
}
