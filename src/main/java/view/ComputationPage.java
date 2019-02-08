package view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import computation.Computation;

public class ComputationPage extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	private JButton addButton;
	private JButton subtractButton;
	private JButton divideButton;
	private JButton multiplyButton;
	private JLabel result;
	
	/** Creates new form ComputationPage */
	public ComputationPage() {
		initComponents();
	}

	/** This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Computation");

		// buttons
		addButton = new JButton();
		addButton.setText("Add 2 and 5");
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		subtractButton = new JButton();
		subtractButton.setText("Subtract 5 from 2");
		subtractButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				subtractButtonActionPerformed(evt);
			}
		});
		
		divideButton = new JButton();
		divideButton.setText("Divide 2 by 5");
		divideButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				divideButtonActionPerformed(evt);
			}
		});
		
		multiplyButton = new JButton();
		multiplyButton.setText("Multiply 2 by 5");
		multiplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				multiplyButtonActionPerformed(evt);
			}
		});
		
		// label
		result = new JLabel();
		result.setText("");
		
		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(addButton)
				.addComponent(subtractButton)
				.addComponent(divideButton)
				.addComponent(multiplyButton)
				.addComponent(result)
				);
	
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(addButton)
				.addComponent(subtractButton)
				.addComponent(divideButton)
				.addComponent(multiplyButton)
				.addComponent(result)
				);
		
		pack();
	}

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Computation c = new Computation();
		int i = c.add(2, 5);
		result.setText("" + i);
		refreshData();
	}

	private void subtractButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Computation c = new Computation();
		int i = c.subtract(2, 5);
		result.setText("" + i);
		refreshData();
	}
	
	private void divideButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Computation c = new Computation();
		double d = c.divide(2, 5);
		result.setText("" + d);
		refreshData();
	}
	
	private void multiplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Computation c = new Computation();
		int i = c.multiply(2, 5);
		result.setText("" + i);
		refreshData();
	}
	
	private void refreshData() {
		pack();
	}

}
