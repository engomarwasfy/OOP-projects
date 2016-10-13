package eg.edu.alexu.csd.oop.calculator.cs46;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author wasfy
 *
 */

public class Gui implements ActionListener {

/**
*
*/
private MyCalcMethods m = new MyCalcMethods();
/**
*
*/
	private JFrame frame;
	/**
	*
	*/
	private JTextField textField;
	/**
	*
	*/
	private JButton btnNext, prevButton, btnSave, btnLoad;
	/**
	*
	*/
	private JButton btnEnter, button0, button1, button2, button3, button4;
	/**
	*
	*/
	private JButton button5, button6, button7, button8;
	/**
	*
	*/
	private JButton button9, plus, minus, multiply, divide;
	/**
	*
	*/
	private JButton  buttonlft, buttonrt, buttondot, buttonclear;
	/**
	*
	*/
	private String server;
	/**
	*
	*/
	private JLabel label;

	/**
	 *
	 */
	public Gui() {
		frame = new JFrame();
		frame.setBounds(s("100"), s("10"), s("646"), s("300"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField();
		textField.setBounds(s("68"), s("11"), s("298"), s("20"));
		frame.getContentPane().add(textField);
		textField.setColumns(s("10"));

		prevButton = new JButton("prev");
		prevButton.setBounds(s("502"), s("116"), s("89"), s("23"));
		frame.getContentPane().add(prevButton);
		prevButton.addActionListener(this);

		btnNext = new JButton("next");
		btnNext.setBounds(s("502"), s("159"), s("89"), s("23"));
		frame.getContentPane().add(btnNext);
		btnNext.addActionListener(this);

		btnSave = new JButton("save");
		btnSave.setBounds(s("502"), s("45"), s("89"), s("23"));
		frame.getContentPane().add(btnSave);
		btnSave.addActionListener(this);

		btnLoad = new JButton("load");
		btnLoad.setBounds(s("502"), s("79"), s("89"), s("23"));
		frame.getContentPane().add(btnLoad);
		btnLoad.addActionListener(this);

		btnEnter = new JButton("=");
		btnEnter.setBounds(s("321"), s("213"), s("45"), s("23"));
		frame.getContentPane().add(btnEnter);
		btnEnter.addActionListener(this);
		label = new JLabel("");
		label.setBounds(s("33"), s("230"), s("284"), s("14"));
		frame.getContentPane().add(label);
		plus = new JButton("+");
		plus.setBounds(s("321"), s("178"), s("45"), s("23"));
		frame.getContentPane().add(plus);
		plus.addActionListener(this);

		minus = new JButton("-");
		minus.setBounds(s("321"), s("143"), s("45"), s("23"));
		frame.getContentPane().add(minus);
		minus.addActionListener(this);
		multiply = new JButton("*");
		multiply.setBounds(s("321"), s("102"), s("45"), s("23"));
		frame.getContentPane().add(multiply);
		multiply.addActionListener(this);

		divide = new JButton("/");
		divide.setBounds(s("321"), s("65"), s("45"), s("23"));
		frame.getContentPane().add(divide);
		divide.addActionListener(this);
		button7 = new JButton("7");
		button7.setBounds(s("100"), s("65"), s("45"), s("23"));
		frame.getContentPane().add(button7);
		button7.addActionListener(this);

		button8 = new JButton("8");
		button8.setBounds(s("171"), s("65"), s("45"), s("23"));
		frame.getContentPane().add(button8);
		button8.addActionListener(this);
		button9 = new JButton("9");
		button9.setBounds(s("237"), s("65"), s("45"), s("23"));
		frame.getContentPane().add(button9);
		button9.addActionListener(this);
		button4 = new JButton("4");
		button4.setBounds(s("100"), s("102"), s("45"), s("23"));
		frame.getContentPane().add(button4);
		button4.addActionListener(this);
		button5 = new JButton("5");
		button5.setBounds(s("171"), s("102"), s("45"), s("23"));
		frame.getContentPane().add(button5);
		button5.addActionListener(this);
		button6 = new JButton("6");
		button6.setBounds(s("237"), s("102"), s("45"), s("23"));
		frame.getContentPane().add(button6);
		button6.addActionListener(this);
		button1 = new JButton("1");
		button1.setBounds(s("100"), s("143"), s("45"), s("23"));
		frame.getContentPane().add(button1);
		button1.addActionListener(this);
		button2 = new JButton("2");
		button2.setBounds(s("171"), s("143"), s("45"), s("23"));
		frame.getContentPane().add(button2);
		button2.addActionListener(this);
		button3 = new JButton("3");
		button3.setBounds(s("237"), s("143"), s("45"), s("23"));
		frame.getContentPane().add(button3);
		button3.addActionListener(this);
		button0 = new JButton("0");
		button0.setBounds(s("100"), s("178"), s("89"), s("23"));
		frame.getContentPane().add(button0);
		button0.addActionListener(this);
		buttondot = new JButton(".");
		buttondot.setBounds(s("199"), s("178"), s("45"), s("23"));
		frame.getContentPane().add(buttondot);
		buttondot.addActionListener(this);
		buttonrt = new JButton("(");
		buttonrt.setBounds(s("247"), s("178"), s("45"), s("23"));
		frame.getContentPane().add(buttonrt);
		buttonrt.addActionListener(this);

		buttonlft = new JButton(")");
		buttonlft.setBounds(s("237"), s("213"), s("45"), s("23"));
		frame.getContentPane().add(buttonlft);
		buttonlft.addActionListener(this);
		buttonclear = new JButton("clear");
		buttonclear.setBounds(s("502"), s("201"), s("89"), s("23"));
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
				String s = "wrong expression";
				JOptionPane.showMessageDialog(null, "s");
			}

		} else if (e.getSource().equals(btnSave)) {
			m.save();
		} else if (e.getSource().equals(btnLoad)) {
			m.load();
			textField.setText(m.current());
		} else if (e.getSource().equals(btnNext)) {
			server = m.next();
			if (server != null) {
				textField.setText(server);
			} else {
				JOptionPane.showMessageDialog(null, "no next");
			}


		} else if (e.getSource().equals(prevButton)) {
			server = m.prev();
			if (server != null) {
				textField.setText(server);
			} else {
				JOptionPane.showMessageDialog(null, "no prev");
			}
		}
	}

	/**
	 * @return s int
	 * @param x
	 *            string
	 */
	public final int s(final String x) {
		return Integer.parseInt(x);
	}

	/**
	 * @param args
	 *            string
	 */
	public static void main(final String[] args) {

		Gui gui = new Gui();

	}

}
