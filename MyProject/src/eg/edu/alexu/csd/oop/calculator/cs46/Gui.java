package eg.edu.alexu.csd.oop.calculator.cs46;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui {

    private MyCalcMethods m = new MyCalcMethods();
    private JFrame frame;
    private JTextField textFiled;
    private JButton btnNext;
    private JButton btnSave;
    private JButton btnLoad;
    private JButton btnEnter;
    private String server;
    private JLabel label;

    public Gui() {
	frame = new JFrame();
	frame.setBounds(100, 100, 646, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);

	textFiled = new JTextField();
	textFiled.setBounds(68, 11, 298, 20);
	frame.getContentPane().add(textFiled);
	textFiled.setColumns(10);

	JButton prevButton = new JButton("prev");
	prevButton.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent e) {
		if ((server = m.prev()) != null) {
		    textFiled.setText(server);
		} else {
		    JOptionPane.showMessageDialog(null, "no prev");
		}
	    }
	});
	prevButton.setBounds(502, 116, 89, 23);
	frame.getContentPane().add(prevButton);

	btnNext = new JButton("next");
	btnNext.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent e) {
		if ((server = m.next()) != null) {
		    textFiled.setText(server);
		} else {
		    JOptionPane.showMessageDialog(null, "no next");
		}
	    }
	});
	btnNext.setBounds(502, 159, 89, 23);
	frame.getContentPane().add(btnNext);

	btnSave = new JButton("save");
	btnSave.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		m.save();
	    }
	});
	btnSave.setBounds(502, 45, 89, 23);
	frame.getContentPane().add(btnSave);

	btnLoad = new JButton("load");
	btnLoad.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent e) {
		m.load();
		textFiled.setText(m.current());
	    }
	});
	btnLoad.setBounds(502, 79, 89, 23);
	frame.getContentPane().add(btnLoad);

	btnEnter = new JButton("=");
	btnEnter.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent e) {

		try {
		    m.input(textFiled.getText());
		    label.setText(m.getResult());
		} catch (Exception e1) {
		    JOptionPane.showMessageDialog(null, "wrong expression");
		}
	    }
	});
	btnEnter.setBounds(321, 213, 45, 23);
	frame.getContentPane().add(btnEnter);

	label = new JLabel("");
	label.setBounds(33, 230, 284, 14);
	frame.getContentPane().add(label);

	JButton plus = new JButton("+");
	plus.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "+");
	    }
	});
	plus.setBounds(321, 178, 45, 23);
	frame.getContentPane().add(plus);

	JButton minus = new JButton("-");
	minus.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "-");
	    }
	});
	minus.setBounds(321, 143, 45, 23);
	frame.getContentPane().add(minus);

	JButton multiply = new JButton("*");
	multiply.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "*");
	    }
	});
	multiply.setBounds(321, 102, 45, 23);
	frame.getContentPane().add(multiply);

	JButton divide = new JButton("/");
	divide.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "/");
	    }
	});
	divide.setBounds(321, 65, 45, 23);
	frame.getContentPane().add(divide);

	JButton button7 = new JButton("7");
	button7.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "7");
	    }
	});
	button7.setBounds(100, 65, 45, 23);
	frame.getContentPane().add(button7);

	JButton button8 = new JButton("8");
	button8.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "8");
	    }
	});
	button8.setBounds(171, 65, 45, 23);
	frame.getContentPane().add(button8);

	JButton button9 = new JButton("9");
	button9.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "9");
	    }
	});
	button9.setBounds(237, 65, 45, 23);
	frame.getContentPane().add(button9);

	JButton button4 = new JButton("4");
	button4.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "4");
	    }
	});
	button4.setBounds(100, 102, 45, 23);
	frame.getContentPane().add(button4);

	JButton button5 = new JButton("5");
	button5.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "5");
	    }
	});
	button5.setBounds(171, 102, 45, 23);
	frame.getContentPane().add(button5);

	JButton button6 = new JButton("6");
	button6.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "6");
	    }
	});
	button6.setBounds(237, 102, 45, 23);
	frame.getContentPane().add(button6);

	JButton button1 = new JButton("1");
	button1.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "1");
	    }
	});
	button1.setBounds(100, 143, 45, 23);
	frame.getContentPane().add(button1);

	JButton button2 = new JButton("2");
	button2.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "2");
	    }
	});
	button2.setBounds(171, 143, 45, 23);
	frame.getContentPane().add(button2);

	JButton button3 = new JButton("3");
	button3.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "3");
	    }
	});
	button3.setBounds(237, 143, 45, 23);
	frame.getContentPane().add(button3);

	JButton button0 = new JButton("0");
	button0.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "0");
	    }
	});
	button0.setBounds(100, 178, 89, 23);
	frame.getContentPane().add(button0);

	JButton buttondot = new JButton(".");
	buttondot.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + ".");
	    }
	});
	buttondot.setBounds(199, 178, 45, 23);
	frame.getContentPane().add(buttondot);

	JButton buttonrt = new JButton("(");
	buttonrt.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + "(");
	    }
	});
	buttonrt.setBounds(247, 178, 45, 23);
	frame.getContentPane().add(buttonrt);

	JButton buttonlft = new JButton(")");
	buttonlft.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText(textFiled.getText() + ")");
	    }
	});
	buttonlft.setBounds(237, 213, 45, 23);
	frame.getContentPane().add(buttonlft);

	JButton buttonclear = new JButton("clear");
	buttonclear.addActionListener(new ActionListener() {
	    public void actionPerformed(final ActionEvent arg0) {
		textFiled.setText("");
	    }
	});
	buttonclear.setBounds(502, 201, 89, 23);
	frame.getContentPane().add(buttonclear);
	frame.setVisible(true);
    }

    public static void main(final String[] args) {

	Gui gui = new Gui();

    }
}
