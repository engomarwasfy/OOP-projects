package eg.edu.alexu.csd.oop.calculator.cs46;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui implements ActionListener {

	private MyCalcMethods m = new MyCalcMethods();
	private JFrame frame;
	private JTextField textField;
	private JButton btnNext, prevButton, btnSave, btnLoad, btnEnter, button0, button1, button2, button3, button4,
			button5, button6, button7, button8, button9, plus, minus, multiply, divide, buttonlft, buttonrt, buttondot,
			buttonclear;
	private String server;
	private JLabel label;

	public Gui() {
		frame = new JFrame();
		frame.setBounds(100, 100, 646, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(68, 11, 298, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		prevButton = new JButton("prev");
		prevButton.setBounds(502, 116, 89, 23);
		frame.getContentPane().add(prevButton);
		prevButton.addActionListener(this);

		btnNext = new JButton("next");
		btnNext.setBounds(502, 159, 89, 23);
		frame.getContentPane().add(btnNext);
		btnNext.addActionListener(this);

		btnSave = new JButton("save");
		btnSave.setBounds(502, 45, 89, 23);
		frame.getContentPane().add(btnSave);
		btnSave.addActionListener(this);

		btnLoad = new JButton("load");
		btnLoad.setBounds(502, 79, 89, 23);
		frame.getContentPane().add(btnLoad);
		btnLoad.addActionListener(this);

		btnEnter = new JButton("=");
		btnEnter.setBounds(321, 213, 45, 23);
		frame.getContentPane().add(btnEnter);
		btnEnter.addActionListener(this);
		label = new JLabel("");
		label.setBounds(33, 230, 284, 14);
		frame.getContentPane().add(label);
		plus = new JButton("+");
		plus.setBounds(321, 178, 45, 23);
		frame.getContentPane().add(plus);
		plus.addActionListener(this);

		minus = new JButton("-");
		minus.setBounds(321, 143, 45, 23);
		frame.getContentPane().add(minus);
		minus.addActionListener(this);
		multiply = new JButton("*");
		multiply.setBounds(321, 102, 45, 23);
		frame.getContentPane().add(multiply);
		multiply.addActionListener(this);

		divide = new JButton("/");
		divide.setBounds(321, 65, 45, 23);
		frame.getContentPane().add(divide);
		divide.addActionListener(this);
		button7 = new JButton("7");
		button7.setBounds(100, 65, 45, 23);
		frame.getContentPane().add(button7);
		button7.addActionListener(this);

		button8 = new JButton("8");
		button8.setBounds(171, 65, 45, 23);
		frame.getContentPane().add(button8);
		button8.addActionListener(this);
		button9 = new JButton("9");
		button9.setBounds(237, 65, 45, 23);
		frame.getContentPane().add(button9);
		button9.addActionListener(this);
		button4 = new JButton("4");
		button4.setBounds(100, 102, 45, 23);
		frame.getContentPane().add(button4);
		button4.addActionListener(this);
		button5 = new JButton("5");
		button5.setBounds(171, 102, 45, 23);
		frame.getContentPane().add(button5);
		button5.addActionListener(this);
		button6 = new JButton("6");
		button6.setBounds(237, 102, 45, 23);
		frame.getContentPane().add(button6);
		button6.addActionListener(this);
		button1 = new JButton("1");
		button1.setBounds(100, 143, 45, 23);
		frame.getContentPane().add(button1);
		button1.addActionListener(this);
		button2 = new JButton("2");
		button2.setBounds(171, 143, 45, 23);
		frame.getContentPane().add(button2);
		button2.addActionListener(this);
		button3 = new JButton("3");
		button3.setBounds(237, 143, 45, 23);
		frame.getContentPane().add(button3);
		button3.addActionListener(this);
		button0 = new JButton("0");
		button0.setBounds(100, 178, 89, 23);
		frame.getContentPane().add(button0);
		button0.addActionListener(this);
		buttondot = new JButton(".");
		buttondot.setBounds(199, 178, 45, 23);
		frame.getContentPane().add(buttondot);
		buttondot.addActionListener(this);
		buttonrt = new JButton("(");
		buttonrt.setBounds(247, 178, 45, 23);
		frame.getContentPane().add(buttonrt);
		buttonrt.addActionListener(this);

		buttonlft = new JButton(")");
		buttonlft.setBounds(237, 213, 45, 23);
		frame.getContentPane().add(buttonlft);
		buttonlft.addActionListener(this);
		buttonclear = new JButton("clear");
		buttonclear.setBounds(502, 201, 89, 23);
		frame.getContentPane().add(buttonclear);
		buttonclear.addActionListener(this);
		frame.setVisible(true);
	}

	// ++++++++++++++++++++++++++++++++
	// Actions
	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource().equals(button0)) {
			textField.setText(textField.getText() + "0");
		} else if (e.getSource().equals(button1)) {
			textField.setText(textField.getText() + "1");
		} else if (e.getSource().equals(button2)) {
			textField.setText(textField.getText() + "2");
		} else if (e.getSource().equals(button3)) {
			textField.setText(textField.getText() + "3");
		} else if (e.getSource().equals(button4)) {
			textField.setText(textField.getText() + "4");
		} else if (e.getSource().equals(button5)) {
			textField.setText(textField.getText() + "5");
		} else if (e.getSource().equals(button6)) {
			textField.setText(textField.getText() + "6");
		} else if (e.getSource().equals(button7)) {
			textField.setText(textField.getText() + "7");
		} else if (e.getSource().equals(button8)) {
			textField.setText(textField.getText() + "8");
		} else if (e.getSource().equals(button9)) {
			textField.setText(textField.getText() + "9");
		} else if (e.getSource().equals(buttonrt)) {
			textField.setText(textField.getText() + "(");
		} else if (e.getSource().equals(buttonlft)) {
			textField.setText(textField.getText() + ")");
		} else if (e.getSource().equals(buttonclear)) {
			textField.setText("");
		} else if (e.getSource().equals(buttondot)) {
			textField.setText(textField.getText() + ".");
		} else if (e.getSource().equals(plus)) {
			textField.setText(textField.getText() + "+");
		} else if (e.getSource().equals(minus)) {
			textField.setText(textField.getText() + "-");
		} else if (e.getSource().equals(multiply)) {
			textField.setText(textField.getText() + "*");
		} else if (e.getSource().equals(divide)) {
			textField.setText(textField.getText() + "/");
		} else if (e.getSource().equals(btnEnter)) {

			try {
				m.input(textField.getText());
				label.setText(m.getResult());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "wrong expression");
			}
		}

		else if (e.getSource().equals(btnSave)) {
			m.save();
		} else if (e.getSource().equals(btnLoad)) {
			m.load();
			textField.setText(m.current());
		} else if (e.getSource().equals(btnNext)) {
			if ((server = m.next()) != null) {
				textField.setText(server);
			} else {
				JOptionPane.showMessageDialog(null, "no next");
			}
		}

		else if (e.getSource().equals(prevButton)) {
			if ((server = m.prev()) != null) {
				textField.setText(server);
			} else {
				JOptionPane.showMessageDialog(null, "no prev");
			}
		}
	}

	public static void main(final String[] args) {

		Gui gui = new Gui();

	}

}
