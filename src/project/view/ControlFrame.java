package project.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import project.model.Sprinkler.Group;
import project.model.SprinklerGroup;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;

public class ControlFrame extends JFrame implements ActionListener{
	
	static SprinklerGroup group = new SprinklerGroup();

	private JPanel contentPane;
	private ImageIcon icon = new ImageIcon();
	private JLabel lblPicture;
	private JButton btnLocationGraph;
	private JPanel panel_2;
	private CardLayout cl = new CardLayout(0, 0);	
	Timer timer;
	JLabel lblTime;
	Calendar calendar=Calendar.getInstance();  
	
	private JButton btnNewButtonEnableAll;
	private JButton btnNewButtonDisableAll;
	private JButton btnNewButtonWeeklyPlan;
	private JButton btnNewButtonWaterConsumption;
	
	private JButton btnEnableEast;
	private JButton btnDisableEast;
	private JButton btnViewEast;
	private JButton btnEwater;
	
	private JButton btnEnableWest;
	private JButton btnDisableWest;	
	private JButton btnViewWest;	
	private JButton btnWwater;
	
	private JButton btnEnableNorth;	
	private JButton btnDisableNorth;	
	private JButton btnViewNorth;	
	private JButton btnNwater;
	
	private JButton btnEnableSouth;	
	private JButton btnDisableSouth;	
	private JButton btnViewSouth;	
	private JButton btnSwater;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panel_4;
	private JTable tableEast;
	private JTable tableWest;
	private JTable tableNorth;
	private JTable tableSouth;
	
	JLabel lblTemperature;
	JLabel lblTempwest;
	JLabel lblTempnorth;
	JLabel lblTempsouth;
	
	private int year = Calendar.YEAR;
	private int month = Calendar.MONTH;
	private int day = Calendar.DAY_OF_MONTH;
	private int hour = Calendar.HOUR;
	private int minute = Calendar.MINUTE;
	
	
	String[] columnNames = {"#Sprinkler", "ON/OFF", "OK/FAIL"};	
	public String[][] generateDataEast(){		
		String[][] data = {
				{"#Sprinkler", "ON/OFF", "OK/FAIL"},
				{"E1", group.getWorkStatus(Group.EAST)[0],  String.valueOf(group.getFunctional(Group.EAST)[0])},
				{"E2", group.getWorkStatus(Group.EAST)[1],  String.valueOf(group.getFunctional(Group.EAST)[1])},
				{"E3", group.getWorkStatus(Group.EAST)[2],  String.valueOf(group.getFunctional(Group.EAST)[2])},
				{"E4", group.getWorkStatus(Group.EAST)[3],  String.valueOf(group.getFunctional(Group.EAST)[3])},
				{"E5", group.getWorkStatus(Group.EAST)[4],  String.valueOf(group.getFunctional(Group.EAST)[4])},
				{"E6", group.getWorkStatus(Group.EAST)[5],  String.valueOf(group.getFunctional(Group.EAST)[5])}
		};
		return data;
	}
	public String[][] generateDataWest(){		
		String[][] data = {
				{"#Sprinkler", "ON/OFF", "OK/FAIL"},
				{"W1", group.getWorkStatus(Group.WEST)[0],  String.valueOf(group.getFunctional(Group.WEST)[0])},
				{"W2", group.getWorkStatus(Group.WEST)[1],  String.valueOf(group.getFunctional(Group.WEST)[1])},
				{"W3", group.getWorkStatus(Group.WEST)[2],  String.valueOf(group.getFunctional(Group.WEST)[2])},
				{"W4", group.getWorkStatus(Group.WEST)[3],  String.valueOf(group.getFunctional(Group.WEST)[3])},
				{"W5", group.getWorkStatus(Group.WEST)[4],  String.valueOf(group.getFunctional(Group.WEST)[4])},
				{"W6", group.getWorkStatus(Group.WEST)[5],  String.valueOf(group.getFunctional(Group.WEST)[5])}
		};
		return data;
	}
	public String[][] generateDataNorth(){		
		String[][] data = {
				{"#Sprinkler", "ON/OFF", "OK/FAIL"},
				{"N1", group.getWorkStatus(Group.NORTH)[0],  String.valueOf(group.getFunctional(Group.NORTH)[0])},
				{"N2", group.getWorkStatus(Group.NORTH)[1],  String.valueOf(group.getFunctional(Group.NORTH)[1])},
				{"N3", group.getWorkStatus(Group.NORTH)[2],  String.valueOf(group.getFunctional(Group.NORTH)[2])},
				{"N4", group.getWorkStatus(Group.NORTH)[3],  String.valueOf(group.getFunctional(Group.NORTH)[3])},
				{"N5", group.getWorkStatus(Group.NORTH)[4],  String.valueOf(group.getFunctional(Group.NORTH)[4])},
				{"N6", group.getWorkStatus(Group.NORTH)[5],  String.valueOf(group.getFunctional(Group.NORTH)[5])}
		};
		return data;
	}
	public String[][] generateDataSouth(){		
		String[][] data = {
				{"#Sprinkler", "ON/OFF", "OK/FAIL"},
				{"S1", group.getWorkStatus(Group.SOUTH)[0],  String.valueOf(group.getFunctional(Group.SOUTH)[0])},
				{"S2", group.getWorkStatus(Group.SOUTH)[1],  String.valueOf(group.getFunctional(Group.SOUTH)[1])},
				{"S3", group.getWorkStatus(Group.SOUTH)[2],  String.valueOf(group.getFunctional(Group.SOUTH)[2])},
				{"S4", group.getWorkStatus(Group.SOUTH)[3],  String.valueOf(group.getFunctional(Group.SOUTH)[3])},
				{"S5", group.getWorkStatus(Group.SOUTH)[4],  String.valueOf(group.getFunctional(Group.SOUTH)[4])},
				{"S6", group.getWorkStatus(Group.SOUTH)[5],  String.valueOf(group.getFunctional(Group.SOUTH)[5])}
		};
		return data;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlFrame frame = new ControlFrame();
					frame.setVisible(true);
					SimulatorFrame sf = new SimulatorFrame(frame);
					sf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public ControlFrame() {
		
		
		timer = new Timer(1,this); // set timer for the question
		timer.start();
		
		setTitle("HummingBee Home Garden Sprinkler System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		lblTime = new JLabel("Date: " +(+ (1+(calendar.get(GregorianCalendar.DAY_OF_MONTH)+group.getOnCounter())/24) + " / "  
				+ (calendar.get(GregorianCalendar.MONTH)+1) + " / " 
				+ calendar.get(GregorianCalendar.YEAR)  
				+ " Time: " + (group.getOnCounter())%24 + ":00"));
		panel.add(lblTime);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		panel_1.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		panel_2 = new JPanel();
		panel_2.setToolTipText("figure");
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(cl);
		
		tableEast = new JTable(generateDataEast(), columnNames);
		panel_2.add(tableEast, "tableEast");
		tableWest = new JTable(generateDataWest(), columnNames);
		panel_2.add(tableWest, "tableWest");
		tableNorth = new JTable(generateDataNorth(), columnNames);
		panel_2.add(tableNorth, "tableNorth");
		tableSouth = new JTable(generateDataSouth(), columnNames);
		panel_2.add(tableSouth, "tableSouth");
		
		lblPicture = new JLabel(new ImageIcon("EmptyLocation.jpg"));	
		panel_2.add(lblPicture, "graph");
		cl.show(panel_2, "graph");
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "cell 1 0,grow");
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 8;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_3.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		
		btnLocationGraph = new JButton("Location Graph");
		btnLocationGraph.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_4.add(btnLocationGraph);
		btnLocationGraph.addActionListener(this);
		
		btnNewButtonEnableAll = new JButton("EnableAll");
		btnNewButtonEnableAll.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_4.add(btnNewButtonEnableAll);
		
		btnNewButtonDisableAll = new JButton("Disable All");
		btnNewButtonDisableAll.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_4.add(btnNewButtonDisableAll);
		
		btnNewButtonWeeklyPlan = new JButton("Weekly Plan");
		btnNewButtonWeeklyPlan.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_4.add(btnNewButtonWeeklyPlan);
		
		
		btnNewButtonWaterConsumption = new JButton("Water Consumption");
		btnNewButtonWaterConsumption.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_4.add(btnNewButtonWaterConsumption);
		btnNewButtonWaterConsumption.addActionListener(this);
		btnNewButtonWeeklyPlan.addActionListener(this);
		btnNewButtonDisableAll.addActionListener(this);
		btnNewButtonEnableAll.addActionListener(this);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 1;
		panel_3.add(lblGroup, gbc_lblGroup);
		
		lblTemperature = new JLabel("Temperature");
		lblTemperature.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemperature = new GridBagConstraints();
		gbc_lblTemperature.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemperature.gridx = 1;
		gbc_lblTemperature.gridy = 1;
		panel_3.add(lblTemperature, gbc_lblTemperature);
		
		JLabel lblEast = new JLabel("East");
		lblEast.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEast = new GridBagConstraints();
		gbc_lblEast.insets = new Insets(0, 0, 5, 5);
		gbc_lblEast.gridx = 0;
		gbc_lblEast.gridy = 2;
		panel_3.add(lblEast, gbc_lblEast);
		
		JLabel lblTempeast = new JLabel((ControlFrame.group.curTemperatureEast) + "");
		lblTempeast.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTempeast = new GridBagConstraints();
		gbc_lblTempeast.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempeast.gridx = 1;
		gbc_lblTempeast.gridy = 2;
		panel_3.add(lblTempeast, gbc_lblTempeast);
		
		btnEnableEast = new JButton("Enable");
		btnEnableEast.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnEnableEast = new GridBagConstraints();
		gbc_btnEnableEast.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnableEast.gridx = 2;
		gbc_btnEnableEast.gridy = 2;
		panel_3.add(btnEnableEast, gbc_btnEnableEast);
		btnEnableEast.addActionListener(this);
		
		btnDisableEast = new JButton("Disable");
		btnDisableEast.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnDisableEast = new GridBagConstraints();
		gbc_btnDisableEast.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisableEast.gridx = 3;
		gbc_btnDisableEast.gridy = 2;
		panel_3.add(btnDisableEast, gbc_btnDisableEast);
		btnDisableEast.addActionListener(this);
		
		btnViewEast = new JButton("View");
		btnViewEast.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnViewEast = new GridBagConstraints();
		gbc_btnViewEast.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewEast.gridx = 4;
		gbc_btnViewEast.gridy = 2;
		panel_3.add(btnViewEast, gbc_btnViewEast);
		btnViewEast.addActionListener(this);
		
		btnEwater = new JButton("Water Flow");
		btnEwater.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnEwater = new GridBagConstraints();
		gbc_btnEwater.insets = new Insets(0, 0, 5, 5);
		gbc_btnEwater.gridx = 5;
		gbc_btnEwater.gridy = 2;
		panel_3.add(btnEwater, gbc_btnEwater);
		btnEwater.addActionListener(this);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 2;
		panel_3.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblWest = new JLabel("West");
		lblWest.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWest = new GridBagConstraints();
		gbc_lblWest.insets = new Insets(0, 0, 5, 5);
		gbc_lblWest.gridx = 0;
		gbc_lblWest.gridy = 3;
		panel_3.add(lblWest, gbc_lblWest);
		
		lblTempwest = new JLabel((ControlFrame.group.curTemperatureWest) + "");
		lblTempwest.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTempwest = new GridBagConstraints();
		gbc_lblTempwest.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempwest.gridx = 1;
		gbc_lblTempwest.gridy = 3;
		panel_3.add(lblTempwest, gbc_lblTempwest);
		
		btnEnableWest = new JButton("Enable");
		btnEnableWest.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnEnableWest = new GridBagConstraints();
		gbc_btnEnableWest.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnableWest.gridx = 2;
		gbc_btnEnableWest.gridy = 3;
		panel_3.add(btnEnableWest, gbc_btnEnableWest);
		btnEnableWest.addActionListener(this);
		
		btnDisableWest = new JButton("Disable");
		btnDisableWest.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnDisableWest = new GridBagConstraints();
		gbc_btnDisableWest.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisableWest.gridx = 3;
		gbc_btnDisableWest.gridy = 3;
		panel_3.add(btnDisableWest, gbc_btnDisableWest);
		btnDisableWest.addActionListener(this);
		
		btnViewWest = new JButton("View");
		btnViewWest.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnViewWest = new GridBagConstraints();
		gbc_btnViewWest.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewWest.gridx = 4;
		gbc_btnViewWest.gridy = 3;
		panel_3.add(btnViewWest, gbc_btnViewWest);
		btnViewWest.addActionListener(this);
		
		btnWwater = new JButton("Water Flow");
		btnWwater.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnWwater = new GridBagConstraints();
		gbc_btnWwater.insets = new Insets(0, 0, 5, 5);
		gbc_btnWwater.gridx = 5;
		gbc_btnWwater.gridy = 3;
		panel_3.add(btnWwater, gbc_btnWwater);
		btnWwater.addActionListener(this);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 3;
		panel_3.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNorth = new JLabel("North");
		lblNorth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNorth = new GridBagConstraints();
		gbc_lblNorth.insets = new Insets(0, 0, 5, 5);
		gbc_lblNorth.gridx = 0;
		gbc_lblNorth.gridy = 4;
		panel_3.add(lblNorth, gbc_lblNorth);
		
		lblTempnorth = new JLabel((ControlFrame.group.curTemperatureNorth) + "");
		lblTempnorth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTempnorth = new GridBagConstraints();
		gbc_lblTempnorth.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempnorth.gridx = 1;
		gbc_lblTempnorth.gridy = 4;
		panel_3.add(lblTempnorth, gbc_lblTempnorth);
		
		btnEnableNorth = new JButton("Enable");
		btnEnableNorth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnEnableNorth = new GridBagConstraints();
		gbc_btnEnableNorth.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnableNorth.gridx = 2;
		gbc_btnEnableNorth.gridy = 4;
		panel_3.add(btnEnableNorth, gbc_btnEnableNorth);
		btnEnableNorth.addActionListener(this);
		
		btnDisableNorth = new JButton("Disable");
		btnDisableNorth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnDisableNorth = new GridBagConstraints();
		gbc_btnDisableNorth.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisableNorth.gridx = 3;
		gbc_btnDisableNorth.gridy = 4;
		panel_3.add(btnDisableNorth, gbc_btnDisableNorth);
		btnDisableNorth.addActionListener(this);
		
		btnViewNorth = new JButton("View");
		btnViewNorth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnViewNorth = new GridBagConstraints();
		gbc_btnViewNorth.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewNorth.gridx = 4;
		gbc_btnViewNorth.gridy = 4;
		panel_3.add(btnViewNorth, gbc_btnViewNorth);
		btnViewNorth.addActionListener(this);
		
		btnNwater = new JButton("Water Flow");
		btnNwater.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNwater = new GridBagConstraints();
		gbc_btnNwater.insets = new Insets(0, 0, 5, 5);
		gbc_btnNwater.gridx = 5;
		gbc_btnNwater.gridy = 4;
		panel_3.add(btnNwater, gbc_btnNwater);
		btnNwater.addActionListener(this);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 6;
		gbc_textField_2.gridy = 4;
		panel_3.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSouth = new JLabel("South");
		lblSouth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSouth = new GridBagConstraints();
		gbc_lblSouth.insets = new Insets(0, 0, 5, 5);
		gbc_lblSouth.gridx = 0;
		gbc_lblSouth.gridy = 5;
		panel_3.add(lblSouth, gbc_lblSouth);
		
		lblTempsouth = new JLabel((ControlFrame.group.curTemperatureSouth) + "");
		lblTempsouth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTempsouth = new GridBagConstraints();
		gbc_lblTempsouth.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempsouth.gridx = 1;
		gbc_lblTempsouth.gridy = 5;
		panel_3.add(lblTempsouth, gbc_lblTempsouth);
		
		btnEnableSouth = new JButton("Enable");
		btnEnableSouth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnEnableSouth = new GridBagConstraints();
		gbc_btnEnableSouth.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnableSouth.gridx = 2;
		gbc_btnEnableSouth.gridy = 5;
		panel_3.add(btnEnableSouth, gbc_btnEnableSouth);
		btnEnableSouth.addActionListener(this);
		
		btnDisableSouth = new JButton("Disable");
		btnDisableSouth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnDisableSouth = new GridBagConstraints();
		gbc_btnDisableSouth.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisableSouth.gridx = 3;
		gbc_btnDisableSouth.gridy = 5;
		panel_3.add(btnDisableSouth, gbc_btnDisableSouth);
		btnDisableSouth.addActionListener(this);
		
		btnViewSouth = new JButton("View");
		btnViewSouth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnViewSouth = new GridBagConstraints();
		gbc_btnViewSouth.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewSouth.gridx = 4;
		gbc_btnViewSouth.gridy = 5;
		panel_3.add(btnViewSouth, gbc_btnViewSouth);
		btnViewSouth.addActionListener(this);
		
		btnSwater = new JButton("Water Flow");
		btnSwater.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSwater = new GridBagConstraints();
		gbc_btnSwater.insets = new Insets(0, 0, 5, 5);
		gbc_btnSwater.gridx = 5;
		gbc_btnSwater.gridy = 5;
		panel_3.add(btnSwater, gbc_btnSwater);
		btnSwater.addActionListener(this);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 6;
		gbc_textField_3.gridy = 5;
		panel_3.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
	}
	
	public void updateTable(){
		tableEast = new JTable(generateDataEast(), columnNames);
		tableWest = new JTable(generateDataWest(), columnNames);
		tableNorth = new JTable(generateDataNorth(), columnNames);
		tableSouth = new JTable(generateDataSouth(), columnNames);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnNewButtonEnableAll){
			group.setSprinklerGroupOn(Group.ALL);
		}
		if(e.getSource() == btnNewButtonDisableAll){
			group.setSprinklerGroupOff(Group.ALL);
		}
		if(e.getSource() == btnNewButtonWeeklyPlan){
			this.setVisible(false);
			WeeklyPlanFrame wpf = new WeeklyPlanFrame(group);
			wpf.setVisible(true);
		}
		if(e.getSource() == btnNewButtonWaterConsumption){
			this.setVisible(false);
			try {
				WaterConsumptionFrame wcf = new WaterConsumptionFrame(group);
				wcf.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
		if(e.getSource() == btnEnableEast){
			group.setSprinklerGroupOn(Group.EAST);
		}
		if(e.getSource() == btnDisableEast){
			group.setSprinklerGroupOff(Group.EAST);
		}
		if(e.getSource() == btnViewEast){
			updateTable();
			panel_2.add(tableEast, "tableEast");
			cl.show(panel_2, "tableEast");
		}
		if(e.getSource() == btnEwater){
			int wfEast = Integer.parseInt(textField.getText());
			group.setGroupWaterFlow(wfEast, Group.EAST);
		}
		if(e.getSource() == btnEnableWest){
			group.setSprinklerGroupOn(Group.WEST);
		}
		if(e.getSource() == btnDisableWest){
			group.setSprinklerGroupOff(Group.WEST);
		}
		if(e.getSource() == btnViewWest){
			updateTable();
			panel_2.add(tableWest, "tableWest");
			cl.show(panel_2, "tableWest");
		}		
		if(e.getSource() == btnWwater){
			int wfWest = Integer.parseInt(textField_1.getText());
			group.setGroupWaterFlow(wfWest, Group.WEST);
		}
		if(e.getSource() == btnEnableNorth){
			group.setSprinklerGroupOn(Group.NORTH);
		}
		if(e.getSource() == btnDisableNorth){
			group.setSprinklerGroupOff(Group.NORTH);
		}
		if(e.getSource() == btnViewNorth){
			updateTable();
			panel_2.add(tableNorth, "tableNorth");
			cl.show(panel_2, "tableNorth");
		}
		if(e.getSource() == btnNwater){
			int wfNorth = Integer.parseInt(textField_2.getText());
			group.setGroupWaterFlow(wfNorth, Group.NORTH);
		}
		if(e.getSource() == btnEnableSouth){
			group.setSprinklerGroupOn(Group.SOUTH);
		}
		if(e.getSource() == btnDisableSouth){
			group.setSprinklerGroupOff(Group.SOUTH);
		}
		if(e.getSource() == btnViewSouth){
			updateTable();
			panel_2.add(tableSouth, "tableSouth");
			cl.show(panel_2, "tableSouth");
		}
		if(e.getSource() == btnSwater){
			int wfSouth = Integer.parseInt(textField_3.getText());
			group.setGroupWaterFlow(wfSouth, Group.SOUTH);
		}
		if(e.getSource() == btnLocationGraph){
			cl.show(panel_2, "graph");
		}
		if(e.getSource() == timer){
			lblTime.setText("Date: " +( + (1+(calendar.get(GregorianCalendar.DAY_OF_MONTH)+group.getOnCounter())/24) + " / "  
					+ (calendar.get(GregorianCalendar.MONTH)+1) + " / " 
					+ calendar.get(GregorianCalendar.YEAR)  
					+ " Time: " + (group.getOnCounter())%24 + ":00"));
		}
			
	}
	
	public void changeTemp(){
		lblTemperature.setText((ControlFrame.group.curTemperatureEast) + "");
		lblTempwest.setText((ControlFrame.group.curTemperatureEast) + "");
		lblTempnorth.setText((ControlFrame.group.curTemperatureEast) + "");
		lblTempsouth.setText((ControlFrame.group.curTemperatureEast) + "");
	}

}
