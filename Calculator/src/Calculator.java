import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;
import java.util.Stack;

public class Calculator {

	private JFrame frame;
	String input = "Myself";
	String result = "me";
	boolean male; //check the gender of the latest (top of stack) word 
	private JTextField txtResult;
	final Stack<String> inputS = new Stack<String>();
	final Stack<String> resultS = new Stack<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Family Calculator");
		frame.setBounds(100, 100, 320, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		// display result
		txtResult = new JTextField();
		txtResult.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtResult.setHorizontalAlignment(SwingConstants.RIGHT);
		txtResult.setText("me");
		txtResult.setBounds(153, 101, 139, 32);
		frame.getContentPane().add(txtResult);
		txtResult.setColumns(10);
		result = txtResult.getText();

		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblResult.setBounds(92, 108, 60, 16);
		frame.getContentPane().add(lblResult);

		// display inputs relationship
		final JTextPane txtpnDisplay = new JTextPane();
		txtpnDisplay.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtpnDisplay.setText(input);
		txtpnDisplay.setBounds(22, 16, 269, 81);
		frame.getContentPane().add(txtpnDisplay);

		//keyboard panel
		JPanel panelKeyboard = new JPanel();
		panelKeyboard.setBounds(17, 142, 281, 314);
		frame.getContentPane().add(panelKeyboard);

		// set button standard size
		Dimension d = new Dimension(70, 70);

		// btnWife buttons is used here, so has to declare it here
		final JButton btnHusband = new JButton("Husband");
		final JButton btnWife = new JButton("Wife");

		//Husband
		btnHusband.setPreferredSize(d);
		panelKeyboard.add(btnHusband);
		btnHusband.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = true;
				btnHusband.setEnabled(false);
				btnWife.setEnabled(true);
				
				if (inputS.size() == 0)
					input = "My husband";
				else
					input = "' husband";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me")) {
					txtResult.setText("husband");
					resultS.push("husband");
				} else if (result.equals("mother")) {
					txtResult.setText("father");
					resultS.push("father");
				} else if (result.equals("daughter")) {
					txtResult.setText("son-in-law");
					resultS.push("son-in-law");
				} else if (result.equals("aunt")) {
					txtResult.setText("uncle");
					resultS.push("uncle");
				} else if (result.equals("sister")) {
					txtResult.setText("brother-in-law");
					resultS.push("brother-in-law");
				} else if (result.equals("sister-in-law")) {
					txtResult.setText("brother");
					resultS.push("brother");
				} else if (result.equals("grandmother")) {
					txtResult.setText("grandfather");
					resultS.push("grandfather");
				} else {
					handler();
				}
			}
		});

		//Wife
		btnWife.setPreferredSize(d);
		panelKeyboard.add(btnWife);
		btnWife.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = false;
				btnHusband.setEnabled(true);
				btnWife.setEnabled(false);
				
				if (inputS.size() == 0)
					input = "My wife";
				else
					input = "' wife";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me")) {
					txtResult.setText("wife");
					resultS.push("wife");
				} else if (result.equals("father")) {
					txtResult.setText("mother");
					resultS.push("mother");
				} else if (result.equals("son")) {
					txtResult.setText("daughter-in-law");
					resultS.push("daughter-in-law");
				} else if (result.equals("uncle")) {
					txtResult.setText("aunt");
					resultS.push("aunt");
				} else if (result.equals("brother")) {
					txtResult.setText("sister-in-law");
					resultS.push("sister-in-law");
				} else if (result.equals("brother-in-law")) {
					txtResult.setText("sister");
					resultS.push("sister");
				} else if (result.equals("grandfather")) {
					txtResult.setText("grandmother");
					resultS.push("grandmother");
				} else {
					handler();
				}
			}
		});

		//clear the fields and stacks and set to default
		JButton btnClear = new JButton("Clear");
		btnClear.setPreferredSize(d);
		panelKeyboard.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputS.clear();// clear the field
				resultS.clear();
				txtpnDisplay.setText("Myself");// display default text
				txtResult.setText("me");
				btnHusband.setEnabled(true);// enable 'husband' and 'wife' buttons
				btnWife.setEnabled(true);
			}
		});

		//Father
		JButton btnFather = new JButton("Father");
		btnFather.setPreferredSize(d);
		panelKeyboard.add(btnFather);
		btnFather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = true;
				btnHusband.setEnabled(false);
				btnWife.setEnabled(true);

				if (inputS.size() == 0)
					input = "My father";
				else
					input = "' father";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me") || result.equals("brother")
						|| result.equals("sister")) {
					txtResult.setText("father");
					resultS.push("father");
				} else if (result.equals("father") || result.equals("mother")
						|| result.equals("uncle") || result.equals("aunt")) {
					txtResult.setText("grandfather");
					resultS.push("grandfather");
				} else if (result.equals("son") || result.equals("daughter")) {
					if (male) {
						txtResult.setText("me");
						resultS.push("me");
					} else {
						txtResult.setText("husband");
						resultS.push("husband");
					}
				} else if (result.equals("grandfather")
						|| result.equals("grandmother")) {
					txtResult.setText("great-grandfather");
					resultS.push("great-grandfather");
				} else if (result.equals("grandson")
						|| result.equals("granddaughter")) {
					txtResult.setText("son");
					resultS.push("son");
				} else if (result.equals("nephew") || result.equals("niece")) {
					txtResult.setText("brother"); // could be brother-in-law
					resultS.push("brother");
				} else if (result.equals("wife") || result.equals("husband")) {
					txtResult.setText("father-in-law");
					resultS.push("father-in-law");
				} else if (result.equals("cousin")) {
					txtResult.setText("uncle");
					resultS.push("uncle");
				} else {
					handler();
				}
			}
		});

		JButton btnMother = new JButton("Mother");
		btnMother.setPreferredSize(d);
		panelKeyboard.add(btnMother);
		btnMother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = false;
				if (!male) {
					btnHusband.setEnabled(true);
					btnWife.setEnabled(false);
				}
				if (inputS.size() == 0)
					input = "My mother";
				else
					input = "' mother";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me") || result.equals("brother")
						|| result.equals("sister")) {
					txtResult.setText("mother");
					resultS.push("mother");
				} else if (result.equals("father") || result.equals("mother")
						|| result.equals("uncle") || result.equals("aunt")) {
					txtResult.setText("grandmother");
					resultS.push("grandmother");
				} else if (result.equals("son") || result.equals("daughter")) {
					if (!male) {
						txtResult.setText("me");
						resultS.push("me");
					} else {
						txtResult.setText("wife");
						resultS.push("wife");
					}
				} else if (result.equals("grandfather")
						|| result.equals("grandmother")) {
					txtResult.setText("great-grandmother");
					resultS.push("great-grandmother");
				} else if (result.equals("grandson")
						|| result.equals("granddaughter")) {
					txtResult.setText("daughter");
					resultS.push("daughter");
				} else if (result.equals("nephew") || result.equals("niece")) {
					txtResult.setText("sister");// could be sister-in-law
					resultS.push("sister");
				} else if (result.equals("wife") || result.equals("husband")) {
					txtResult.setText("mother-in-law");
					resultS.push("mother-in-law");
				} else if (result.equals("cousin")) {
					txtResult.setText("aunt");
					resultS.push("aunt");
				} else {
					handler();
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setPreferredSize(d);
		panelKeyboard.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (inputS.size() == 0 || resultS.size() == 0) {
					txtpnDisplay.setText("Myself");
					txtResult.setText("me");
				} else {
					inputS.pop();
					resultS.pop();
					String temp = "";
					for (int i = 0; i < inputS.size(); i++)
						temp += inputS.get(i);
					if (inputS.size() == 0 || resultS.size() == 0) {
						temp = "Myself";
						txtResult.setText("me");
					}
					txtpnDisplay.setText(temp);
					if (!inputS.isEmpty() || !resultS.isEmpty())
						txtResult.setText((String) resultS.peek());
				}
			}
		});

		JButton btnBrother = new JButton("Brother");
		btnBrother.setPreferredSize(d);
		panelKeyboard.add(btnBrother);
		btnBrother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = true;
				if (male) { // disable if male
					btnHusband.setEnabled(false);
					btnWife.setEnabled(true);
				}

				if (inputS.size() == 0)
					input = "My brother";
				else
					input = "' brother";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me") || result.equals("brother")
						|| result.equals("sister")) {
					txtResult.setText("brother");
					resultS.push("brother");
				} else if (result.equals("father") || result.equals("mother")) {
					txtResult.setText("uncle");
					resultS.push("uncle");
				} else if (result.equals("son") || result.equals("daughter")) {
					txtResult.setText("son");
					resultS.push("son");
				} else if (result.equals("grandfather")
						|| result.equals("grandmother")) {
					txtResult.setText("granduncle");
					resultS.push("granduncle");
				} else if (result.equals("grandson")
						|| result.equals("granddaughter")) {
					txtResult.setText("grandson");
					resultS.push("grandson");
				} else if (result.equals("nephew") || result.equals("niece")) {
					txtResult.setText("nephew");
					resultS.push("nephew");
				} else if (result.equals("wife") || result.equals("husband")) {
					txtResult.setText("brother-in-law");
					resultS.push("brother-in-law");
				} else if (result.equals("uncle")) {
					txtResult.setText("uncle/father");// needs to be handled later
					resultS.push("uncle/father");
				} else if (result.equals("cousin")) {
					txtResult.setText("cousin");
					resultS.push("cousin");
				} else {
					handler();
				}
			}
		});

		JButton btnSister = new JButton("Sister");
		btnSister.setPreferredSize(d);
		panelKeyboard.add(btnSister);
		btnSister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = false;
				if (!male) {
					btnHusband.setEnabled(true);
					btnWife.setEnabled(false);
				}

				if (inputS.size() == 0)
					input = "My sister";
				else
					input = "' sister";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me") || result.equals("brother")
						|| result.equals("sister")) {
					txtResult.setText("sister");
					resultS.push("sister");
				} else if (result.equals("father") || result.equals("mother")) {
					txtResult.setText("aunt");
					resultS.push("aunt");
				} else if (result.equals("son") || result.equals("daughter")) {
					txtResult.setText("daughter");
					resultS.push("daughter");
				} else if (result.equals("grandfather")
						|| result.equals("grandmother")) {
					txtResult.setText("grandaunt");
					resultS.push("grandaunt");
				} else if (result.equals("grandson")
						|| result.equals("granddaughter")) {
					txtResult.setText("granddaughter");
					resultS.push("granddaughter");
				} else if (result.equals("nephew") || result.equals("niece")) {
					txtResult.setText("niece");
					resultS.push("niece");
				} else if (result.equals("wife") || result.equals("husband")) {
					txtResult.setText("sister-in-law");
					resultS.push("sister-in-law");
				} else if (result.equals("aunt")) {
					txtResult.setText("aunt/mother");// needs to be handled later
					resultS.push("uncle/father");
				} else if (result.equals("cousin")) {
					txtResult.setText("cousin");
					resultS.push("cousin");
				} else {
					handler();
				}
			}
		});

		final JButton btnMale = new JButton("♂");
		btnMale.setToolTipText("I am male");
		btnMale.setForeground(new Color(0, 0, 255));
		btnMale.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		btnMale.setPreferredSize(d);
		panelKeyboard.add(btnMale);
		btnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = true;
				btnHusband.setEnabled(false);
				btnWife.setEnabled(true);
			}
		});

		JButton btnSon = new JButton("Son");
		btnSon.setPreferredSize(d);
		panelKeyboard.add(btnSon);
		btnSon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = true;
				if (male) { // disable if male
					btnHusband.setEnabled(false);
					btnWife.setEnabled(true);
				}

				if (inputS.size() == 0)
					input = "My son";
				else
					input = "' son";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me") || result.equals("husband")
						|| result.equals("wife")) {
					txtResult.setText("son");
					resultS.push("son");
				} else if (result.equals("brother") || result.equals("sister")) {
					txtResult.setText("nephew");
					resultS.push("nephew");
				} else if (result.equals("father") || result.equals("mother")) {
					if (male) {
						txtResult.setText("brother");
						resultS.push("brother");
					} else {
						txtResult.setText("me");
						resultS.push("me");
					}
				} else if (result.equals("son") || result.equals("daughter")) {
					txtResult.setText("grandson");
					resultS.push("grandson");
				} else if (result.equals("grandfather")
						|| result.equals("grandmother")) {
					txtResult.setText("father"); // could be uncle
					resultS.push("father");
				} else if (result.equals("grandson")
						|| result.equals("granddaughter")) {
					txtResult.setText("great-grandson");
					resultS.push("great-grandson");
				} else if (result.equals("nephew") || result.equals("niece")) {
					txtResult.setText("grandnephew");
					resultS.push("grandnephew");
				} else if (result.equals("aunt") || result.equals("uncle")) {
					txtResult.setText("cousin");
					resultS.push("cousin");
				} else if (result.equals("cousin")) {
					txtResult.setText("nephew");
					resultS.push("nephew");
				} else {
					handler();
				}
			}
		});

		JButton btnDaughter = new JButton("Daughter");
		btnDaughter.setPreferredSize(d);
		panelKeyboard.add(btnDaughter);
		btnDaughter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = false;
				if (!male) {
					btnHusband.setEnabled(true);
					btnWife.setEnabled(false);
				}

				if (inputS.size() == 0)
					input = "My daughter";
				else
					input = "' daughter";
				inputS.push(input);
				String temp = "";
				for (int i = 0; i < inputS.size(); i++)
					temp += inputS.get(i);
				txtpnDisplay.setText(temp);

				result = txtResult.getText();
				if (result.equals("me") || result.equals("husband")
						|| result.equals("wife")) {
					txtResult.setText("daughter");
					resultS.push("daughter");
				} else if (result.equals("brother") || result.equals("sister")) {
					txtResult.setText("niece");
					resultS.push("niece");
				} else if (result.equals("father") || result.equals("mother")) {
					if (!male) {
						txtResult.setText("sister");
						resultS.push("sister");
					} else {
						txtResult.setText("me");
						resultS.push("me");
					}
				} else if (result.equals("son") || result.equals("daughter")) {
					txtResult.setText("granddaughter");
					resultS.push("granddaughter");
				} else if (result.equals("grandfather")
						|| result.equals("grandmother")) {
					txtResult.setText("mother"); // could be aunt
					resultS.push("mother");
				} else if (result.equals("grandson")
						|| result.equals("granddaughter")) {
					txtResult.setText("great-granddaughter");
					resultS.push("great-granddaughter");
				} else if (result.equals("nephew") || result.equals("niece")) {
					txtResult.setText("grandniece");
					resultS.push("grandniece");
				} else if (result.equals("aunt") || result.equals("uncle")) {
					txtResult.setText("cousin");
					resultS.push("cousin");
				} else if (result.equals("cousin")) {
					txtResult.setText("niece");
					resultS.push("niece");
				} else {
					handler();
				}
			}
		});

		JButton btnFemale = new JButton("♀");
		btnFemale.setToolTipText("I am female");
		btnFemale.setForeground(new Color(255, 0, 0));
		btnFemale.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		btnFemale.setPreferredSize(d);
		panelKeyboard.add(btnFemale);
		btnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male = false;
				btnHusband.setEnabled(true);
				btnWife.setEnabled(false);
			}
		});

	}// end initialize method

	// handle weird or too complex questions
	public void handler() {
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			txtResult.setText("good question");
			break;
		case 1:
			txtResult.setText("beyond my scope");
			break;
		case 2:
			txtResult.setText("hmm...");
			break;
		case 3:
			txtResult.setText("error");
			break;
		}
		resultS.push("temp");
	}
}
