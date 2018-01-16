package project.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.model.SprinklerGroup;
import project.controller.SimulatorControl;
import project.model.Sprinkler.Group;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class SimulatorFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtEast;
	private JTextField txtWest;
	private JTextField txtNorth;
	private JTextField txtSouth;
	private JTextField txtOk;
	private JTextField txtFail;
	private JTextField txtTime;
	private JTextField txtDate;
	JButton btnSubmit;
	JButton btnCancel;
	SimulatorControl simControl;
	ControlFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public SimulatorFrame(ControlFrame frame) {
		
		this.frame = frame;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblSimulator = new JLabel("Simulator");
		lblSimulator.setForeground(Color.BLUE);
		lblSimulator.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(lblSimulator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblSetTemperature = new JLabel("Set Temperature");
		lblSetTemperature.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSetTemperature = new GridBagConstraints();
		gbc_lblSetTemperature.insets = new Insets(0, 0, 5, 5);
		gbc_lblSetTemperature.gridx = 0;
		gbc_lblSetTemperature.gridy = 0;
		panel_1.add(lblSetTemperature, gbc_lblSetTemperature);
		
		JLabel lblEast = new JLabel("East");
		lblEast.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEast = new GridBagConstraints();
		gbc_lblEast.anchor = GridBagConstraints.EAST;
		gbc_lblEast.insets = new Insets(0, 0, 5, 5);
		gbc_lblEast.gridx = 0;
		gbc_lblEast.gridy = 1;
		panel_1.add(lblEast, gbc_lblEast);
		
		txtEast = new JTextField();
		txtEast.setText("1");
		GridBagConstraints gbc_txtEast = new GridBagConstraints();
		gbc_txtEast.insets = new Insets(0, 0, 5, 5);
		gbc_txtEast.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEast.gridx = 1;
		gbc_txtEast.gridy = 1;
		panel_1.add(txtEast, gbc_txtEast);
		txtEast.setColumns(10);
		
		JLabel lblWest = new JLabel("West");
		lblWest.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWest = new GridBagConstraints();
		gbc_lblWest.anchor = GridBagConstraints.EAST;
		gbc_lblWest.insets = new Insets(0, 0, 5, 5);
		gbc_lblWest.gridx = 0;
		gbc_lblWest.gridy = 2;
		panel_1.add(lblWest, gbc_lblWest);
		
		txtWest = new JTextField();
		txtWest.setText("2");
		GridBagConstraints gbc_txtWest = new GridBagConstraints();
		gbc_txtWest.insets = new Insets(0, 0, 5, 5);
		gbc_txtWest.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWest.gridx = 1;
		gbc_txtWest.gridy = 2;
		panel_1.add(txtWest, gbc_txtWest);
		txtWest.setColumns(10);
		
		JLabel lblNorth = new JLabel("North");
		lblNorth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNorth = new GridBagConstraints();
		gbc_lblNorth.anchor = GridBagConstraints.EAST;
		gbc_lblNorth.insets = new Insets(0, 0, 5, 5);
		gbc_lblNorth.gridx = 0;
		gbc_lblNorth.gridy = 3;
		panel_1.add(lblNorth, gbc_lblNorth);
		
		txtNorth = new JTextField();
		txtNorth.setText("3");
		GridBagConstraints gbc_txtNorth = new GridBagConstraints();
		gbc_txtNorth.insets = new Insets(0, 0, 5, 5);
		gbc_txtNorth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNorth.gridx = 1;
		gbc_txtNorth.gridy = 3;
		panel_1.add(txtNorth, gbc_txtNorth);
		txtNorth.setColumns(10);
		
		JLabel lblSouth = new JLabel("South");
		lblSouth.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSouth = new GridBagConstraints();
		gbc_lblSouth.anchor = GridBagConstraints.EAST;
		gbc_lblSouth.insets = new Insets(0, 0, 5, 5);
		gbc_lblSouth.gridx = 0;
		gbc_lblSouth.gridy = 4;
		panel_1.add(lblSouth, gbc_lblSouth);
		
		txtSouth = new JTextField();
		txtSouth.setText("4");
		GridBagConstraints gbc_txtSouth = new GridBagConstraints();
		gbc_txtSouth.insets = new Insets(0, 0, 5, 5);
		gbc_txtSouth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSouth.gridx = 1;
		gbc_txtSouth.gridy = 4;
		panel_1.add(txtSouth, gbc_txtSouth);
		txtSouth.setColumns(10);
		
		JLabel lblHigh = new JLabel("High");
		lblHigh.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblHigh = new GridBagConstraints();
		gbc_lblHigh.anchor = GridBagConstraints.EAST;
		gbc_lblHigh.insets = new Insets(0, 0, 5, 5);
		gbc_lblHigh.gridx = 0;
		gbc_lblHigh.gridy = 5;
		panel_1.add(lblHigh, gbc_lblHigh);
		
		textField = new JTextField("70");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblLow = new JLabel("Low");
		lblLow.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLow = new GridBagConstraints();
		gbc_lblLow.anchor = GridBagConstraints.EAST;
		gbc_lblLow.insets = new Insets(0, 0, 5, 5);
		gbc_lblLow.gridx = 0;
		gbc_lblLow.gridy = 6;
		panel_1.add(lblLow, gbc_lblLow);
		
		textField_1 = new JTextField("20");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 6;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSetStatus = new JLabel("Set Status");
		lblSetStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSetStatus = new GridBagConstraints();
		gbc_lblSetStatus.anchor = GridBagConstraints.WEST;
		gbc_lblSetStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblSetStatus.gridx = 0;
		gbc_lblSetStatus.gridy = 7;
		panel_1.add(lblSetStatus, gbc_lblSetStatus);
		
		JLabel lblOk = new JLabel("OK");
		lblOk.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblOk = new GridBagConstraints();
		gbc_lblOk.anchor = GridBagConstraints.EAST;
		gbc_lblOk.insets = new Insets(0, 0, 5, 5);
		gbc_lblOk.gridx = 0;
		gbc_lblOk.gridy = 8;
		panel_1.add(lblOk, gbc_lblOk);
		
		txtOk = new JTextField();
		txtOk.setText("ok");
		GridBagConstraints gbc_txtOk = new GridBagConstraints();
		gbc_txtOk.insets = new Insets(0, 0, 5, 5);
		gbc_txtOk.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOk.gridx = 1;
		gbc_txtOk.gridy = 8;
		panel_1.add(txtOk, gbc_txtOk);
		txtOk.setColumns(10);
		
		JLabel lblFail = new JLabel("Fail");
		lblFail.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFail = new GridBagConstraints();
		gbc_lblFail.anchor = GridBagConstraints.EAST;
		gbc_lblFail.insets = new Insets(0, 0, 5, 5);
		gbc_lblFail.gridx = 0;
		gbc_lblFail.gridy = 9;
		panel_1.add(lblFail, gbc_lblFail);
		
		txtFail = new JTextField();
		txtFail.setText("fail");
		GridBagConstraints gbc_txtFail = new GridBagConstraints();
		gbc_txtFail.insets = new Insets(0, 0, 5, 5);
		gbc_txtFail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFail.gridx = 1;
		gbc_txtFail.gridy = 9;
		panel_1.add(txtFail, gbc_txtFail);
		txtFail.setColumns(10);
		
		JLabel lblSetTime = new JLabel("Set Time");
		lblSetTime.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSetTime = new GridBagConstraints();
		gbc_lblSetTime.anchor = GridBagConstraints.EAST;
		gbc_lblSetTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblSetTime.gridx = 0;
		gbc_lblSetTime.gridy = 10;
		panel_1.add(lblSetTime, gbc_lblSetTime);
		lblSetTime.setVisible(false);
		
		txtTime = new JTextField();
		txtTime.setText("00");
		GridBagConstraints gbc_txtTime = new GridBagConstraints();
		gbc_txtTime.insets = new Insets(0, 0, 5, 5);
		gbc_txtTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTime.gridx = 1;
		gbc_txtTime.gridy = 10;
		panel_1.add(txtTime, gbc_txtTime);
		txtTime.setColumns(10);
		txtTime.setVisible(false);
		
		JLabel lblSetDate = new JLabel("Set Date");
		lblSetDate.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSetDate = new GridBagConstraints();
		gbc_lblSetDate.anchor = GridBagConstraints.EAST;
		gbc_lblSetDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblSetDate.gridx = 0;
		gbc_lblSetDate.gridy = 11;
		panel_1.add(lblSetDate, gbc_lblSetDate);
		lblSetDate.setVisible(false);
		
		txtDate = new JTextField();
		txtDate.setText("0");
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDate.gridx = 1;
		gbc_txtDate.gridy = 11;
		panel_1.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);
		txtDate.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 12;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_2.add(btnSubmit);
		btnSubmit.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_2.add(btnCancel);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSubmit){
		    ControlFrame.group.setCurTemperature(Group.EAST, Integer.parseInt(txtEast.getText()));
		    ControlFrame.group.setCurTemperature(Group.WEST, Integer.parseInt(txtWest.getText()));
		    ControlFrame.group.setCurTemperature(Group.NORTH, Integer.parseInt(txtNorth.getText()));
		    ControlFrame.group.setCurTemperature(Group.SOUTH, Integer.parseInt(txtSouth.getText()));
		    ControlFrame.group.setFunctionalOn(txtOk.getText());
		    ControlFrame.group.setFunctionalOff(txtFail.getText());

		    int time = Integer.parseInt(txtTime.getText());
			String day = txtDate.getText();
			simControl = new SimulatorControl(time, day, ControlFrame.group);
		    //System.out.println(ControlFrame.group.getFunctional(Group.EAST)[0]);
			frame.setVisible(false);
			ControlFrame cf = new ControlFrame();
			cf.setVisible(true);
		}
		if(e.getSource() == btnCancel){
			
		}
		
	}

}
