package project.view;

import project.controller.*;
import project.model.*;
import project.model.Sprinkler.Group;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class WaterConsumptionFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	JRadioButton rdbtnEast;	
	JRadioButton rdbtnWest;	
	JRadioButton rdbtnNorth;
	JRadioButton rdbtnSouth;
	JPanel panel;
	JButton btnback;
	JFreeChart chart;
	ImageIcon icon;
	JLabel imgLabel = new JLabel();
	SprinklerGroup sg;
	Group g = Group.EAST;
	JPanel panel_2;
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public WaterConsumptionFrame(SprinklerGroup sg) throws IOException {
		
		this.sg = sg;
		
		setTitle("HummingBee Home Garden Sprinkler System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.add(imgLabel);
		showChart(); // show the water consumption chart
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		rdbtnEast = new JRadioButton("East");
		
		panel_1.add(rdbtnEast);
		rdbtnEast.addActionListener(this);
		
		rdbtnWest = new JRadioButton("West");
		panel_1.add(rdbtnWest);
		rdbtnWest.addActionListener(this);
		
		rdbtnNorth = new JRadioButton("North");
		panel_1.add(rdbtnNorth);
		rdbtnNorth.addActionListener(this);
		
		rdbtnSouth = new JRadioButton("South");
		panel_1.add(rdbtnSouth);
		rdbtnSouth.addActionListener(this);
		
		btnback = new JButton("Back");
        btnback.addActionListener(this);
        panel_1.add(btnback);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblMonthlyWaterConsumption = new JLabel("Monthly Water Consumption");
		panel_2.add(lblMonthlyWaterConsumption);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == rdbtnEast){
			g = Group.EAST;
			try {
				showChart();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rdbtnWest.setSelected(false);
			rdbtnNorth.setSelected(false);
			rdbtnSouth.setSelected(false);
		}
		if(e.getSource() == rdbtnWest){
			g = Group.WEST;
			try {
				showChart();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rdbtnEast.setSelected(false);
			rdbtnNorth.setSelected(false);
			rdbtnSouth.setSelected(false);
		}
		if(e.getSource() == rdbtnNorth){
			g = Group.NORTH;
			try {
				showChart();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rdbtnEast.setSelected(false);
			rdbtnWest.setSelected(false);
			rdbtnSouth.setSelected(false);
		}
		if(e.getSource() == rdbtnSouth){
			g = Group.SOUTH;
			try {
				showChart();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rdbtnEast.setSelected(false);
			rdbtnWest.setSelected(false);
			rdbtnNorth.setSelected(false);
		}
		if(e.getSource() == btnback){
			this.setVisible(false);
			ControlFrame cf = new ControlFrame();
			cf.setVisible(true);
		}
	}
	
	/**
	 * create data set
	 * @return the data set
	 */
	public CategoryDataset getDataSet(Group g){ //create data set
		int mon,tue,wed,thu,fri,sat,sun;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(mon=sg.getWaterConsumption(g), "", "Monday");
		dataset.addValue(tue=sg.getWaterConsumption(g)-mon, "", "Tuesday");
		dataset.addValue(wed=sg.getWaterConsumption(g)-tue, "", "Wednesday");
		dataset.addValue(thu=sg.getWaterConsumption(g)-wed, "", "Thursday");
		dataset.addValue(fri=sg.getWaterConsumption(g)-thu, "", "Friday");
		dataset.addValue(sat=sg.getWaterConsumption(g)-fri, "", "Saturday");
		dataset.addValue(sun=sg.getWaterConsumption(g)-sat, "", "Sunday");
		return dataset; 
	}
	
	/**
	 * Create the bar chart according to the date set to show the water consumption
	 * @throws IOException : If an input or output exception occurred
	 */
	public void showChart() throws IOException{ // create the bar chart using JFreeChart
	CategoryDataset dataset = getDataSet(g);
	chart = ChartFactory.createStackedBarChart("Weekly Water Consumption", "Days","Water Consumption (Gallon)", dataset, PlotOrientation.VERTICAL,false, false, false);
	ChartUtilities.writeChartAsJPEG(new FileOutputStream("chartImage.jpg"), chart, 650, 250);     
	icon = new ImageIcon("chartImage.jpg");
	imgLabel.setIcon(icon); // add the chart to the label

	}

}
